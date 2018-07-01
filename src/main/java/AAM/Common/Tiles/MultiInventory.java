package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class MultiInventory extends TileEntity implements IInventory
{
	public List<ItemStack> mltinv = new ArrayList<ItemStack>();
	public ItemStack[] projection;
	public int line = 0;

	public MultiInventory()
	{
		this.projection = new ItemStack[getProjectionSize()];
	}

	public void clearInventory()
	{
		this.mltinv = new ArrayList<ItemStack>();
	}

	@Override
	public int getSizeInventory()
	{
		return 27;
	}

	public int getMultiSizeInventory()
	{
		return 81;
	}

	public int getProjectionSize()
	{
		return 81;
	}

	public void fillProjection()
	{
		for (int i = 0; i < getProjectionSize(); i++)
		{
			this.projection[i] = this.mltinv.get(i + line * 9);
		}
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		if (slot < getMultiSizeInventory())
		{
			int mslot = this.line * 9 + slot;
			if (mslot < this.mltinv.size())
			{
				return this.mltinv.get(mslot);
			}
			return null;
		}
		else
		{
			return this.inv[slot - getMultiSizeInventory()];
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int ct)
	{
		int am = ct > 64 ? 64 : ct;

		if (slot < getMultiSizeInventory())
		{
			/**
			 * Easy slot disabling
			 */

			int mslot = this.line * 9 + slot;
			if (mslot < this.mltinv.size())
			{
				ItemStack is = this.mltinv.get(mslot).copy();
				is.stackSize = am;

				if (this.mltinv.get(mslot).stackSize - am == 0)
				{
					this.mltinv.remove(mslot);
				}
				else
					this.mltinv.get(mslot).stackSize -= am;
				return is;
			}
			return null;
		}
		else
		{
			ItemStack is = this.inv[slot - getMultiSizeInventory()].copy();
			is.stackSize = am;

			if (this.inv[slot - getMultiSizeInventory()].stackSize - am == 0)
			{
				this.inv[slot - getMultiSizeInventory()] = null;
			}
			else
				this.inv[slot - getMultiSizeInventory()].stackSize -= am;
			return is;
		}
	}

	/**
	 * Unsortable slots
	 */
	public ItemStack[] inv = new ItemStack[this.getUSlots()];

	public abstract int getUSlots();

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is)
	{
		if (slot < this.getMultiSizeInventory())
		{
			// MiscUtils.dropStack(this.worldObj, this.xCoord, this.yCoord,
			// this.zCoord, is);
			/**
			 * Easy slot disabling
			 */
			// int mslot = this.line * 9 + slot;
			// if (mslot < this.mltinv.size())
			// {
			// this.mltinv.set(mslot, is);
			// if (is == null)
			// {
			// this.mltinv.remove(mslot);
			// }
			// }
			// else
			// {
			addMultiInventoryContent(is);
			Logger.info(is);
			// }
		}
		else
		{
			this.inv[slot - this.getMultiSizeInventory()] = is;
		}
	}

	public void setMultiInventorySlotContents(int slot, int line, ItemStack is)
	{
		int mslot = line * 9 + slot;
		if (mslot < this.mltinv.size())
		{
			this.mltinv.set(mslot, is);
			if (is == null)
			{
				this.mltinv.remove(mslot);
			}
		}
		else
		{
			this.mltinv.add(is);
		}
	}

	public void addMultiInventoryContent(ItemStack is)
	{
		if (is != null)
		{
			boolean f = false;
			for (int i = 0; i < this.mltinv.size(); i++)
			{
				if (is.getItem().equals(this.mltinv.get(i).getItem()) && ItemStack.areItemStackTagsEqual(is, this.mltinv.get(i)))
				{
					f = true;
					this.mltinv.get(i).stackSize += is.stackSize;
					break;
				}
			}
			if (!f)
				this.mltinv.add(is);
		}
	}

	@Override
	public abstract String getInventoryName();

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return Integer.MAX_VALUE - 100000;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p)
	{
		return true;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is)
	{
		return true;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		MiscUtils.readMultiInventory(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		MiscUtils.saveMultiInventory(this, tag);
	}

}
