/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Tiles;

import AAM.Common.Recipes.ModificationAnvilRecipe;
import AAM.Common.Recipes.ModificationAnvilRecipes;
import AAM.Utils.WorldPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Lord_Crystalyx
 */
public class TEModificationAnvil extends TileEntity implements IInventory
{

	public TEModificationAnvil()
	{
	}

	public ItemStack[] inventory = new ItemStack[7];

	@Override
	public int getSizeInventory()
	{
		return 7;
	}

	public int ccount = 0;

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

	public void repair()
	{
		if (this.inventory[0] != null)
		{

		}
	}

	public int count(ItemStack i)
	{
		int count = 0;
		for (int l = 0; l < this.getSizeInventory(); l++)
		{
			if (this.inventory[l] != null)
			{
				if (this.inventory[l].getItem() == i.getItem() && this.inventory[l].getItemDamage() == i.getItemDamage())
				{
					count += this.inventory[l].stackSize;
				}
			}
		}
		return count;
	}

	public boolean isCrafting = false;
	public int craftTime = 0;
	ModificationAnvilRecipe currrec;

	public void startCrafting()
	{
		this.isCrafting = true;
		this.currrec = ModificationAnvilRecipes.findRecipeFor(this);
		if (this.currrec == null)
		{
			this.isCrafting = false;
			repair();
			return;
		}
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.isCrafting)
		{
			// if (!this.worldObj.isRemote)
			{
				if (this.currrec == null)
				{
					this.isCrafting = false;
					return;
				}

				if (this.craftTime > 0)
				{
					if (this.currrec == ModificationAnvilRecipes.findRecipeFor(this))
					{
						--this.craftTime;
					}
					else
					{
						this.isCrafting = false;
						return;
					}
				}
				if (this.craftTime <= 0)
				{

					clearInv();
					this.inventory[0] = this.currrec.output;
					this.isCrafting = false;
				}
			}
		}

	}

	public void clearInv()
	{
		for (int i = 0; i < this.inventory.length; i++)
		{
			this.inventory[i] = null;
		}
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		if (this.inventory[slot] != null)
		{
			if (this.inventory[slot].stackSize - count <= 0)
			{
				ItemStack ret = this.inventory[slot];
				this.inventory[slot] = null;
				return ret;
			}
			else
			{
				ItemStack ret = new ItemStack(this.inventory[slot].getItem(), count, this.inventory[slot].getItemDamage());
				this.inventory[slot].stackSize -= count;
				return ret;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.inventory[slot] != null)
		{
			ItemStack itemstack = this.inventory[slot];
			this.inventory[slot] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item)
	{
		if (item != null)
			this.inventory[slot] = item.copy();
		else
			this.inventory[slot] = null;
	}

	@Override
	public String getInventoryName()
	{
		return "modificationAnvil";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 0;
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
	public boolean isItemValidForSlot(int slot, ItemStack item)
	{
		return true;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inventory.length)
			{
				this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i)
		{
			if (this.inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("Items", nbttaglist);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return new WorldPos(this).getAABB(3);
	}

}
