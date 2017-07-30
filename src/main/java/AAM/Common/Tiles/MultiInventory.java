package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

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
	public int line = 0;

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

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		if (slot < 27)
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
			return this.inv[slot - 27];
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int ct)
	{
		if (slot < 27)
		{
			/**
			 * Easy slot disabling
			 */
			int mslot = this.line * 9 + slot;
			if (mslot < this.mltinv.size())
			{
				ItemStack is = this.mltinv.get(mslot).copy();
				is.stackSize = ct;

				if (this.mltinv.get(mslot).stackSize - ct <= 0)
				{
					this.mltinv.remove(mslot);
				}
				else
					this.mltinv.get(mslot).stackSize -= ct;
				return is;
			}
			return null;
		}
		else
		{
			ItemStack is = this.inv[slot - 27].copy();
			is.stackSize = ct;

			if (this.inv[slot - 27].stackSize - ct <= 0)
			{
				this.inv[slot - 27] = null;
			}
			else
				this.inv[slot - 27].stackSize -= ct;
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
			MiscUtils.dropStack(this.worldObj, this.xCoord, this.yCoord, this.zCoord, is);
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
			// } else
			// {
			// this.mltinv.add(is);
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
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p)
	{
		return false;
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

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		MiscUtils.readMultiInventory(this, tag);

	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		MiscUtils.saveMultiInventory(this, tag);
	}

}
