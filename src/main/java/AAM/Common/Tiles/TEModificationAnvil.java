/**
 * This Class Created By Lord_Crystalyx.
 */
package aam.common.tiles;

import aam.api.AnvilRecipe;
import aam.common.recipes.Recipes;
import aam.utils.InventoryUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	public ItemStack[] inventory = new ItemStack[5];

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	public int ccount = 0;

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}

	public boolean isCrafting = false;
	public int craftTime = 0;

	public void startCrafting()
	{
		isCrafting = true;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		boolean hasRecipe = false;
		for (AnvilRecipe rec : Recipes.anvilRecipes)
		{
			if (rec.matches(this, worldObj))
			{
				if (!isCrafting)
				{
					isCrafting = true;
				}
				if (craftTime >= 400)
				{
					rec.onCrafted(this);
				}
				else
				{
					craftTime += 1;
				}
				hasRecipe = true;
			}
		}
		if (!hasRecipe)
		{
			isCrafting = false;
			craftTime = 0;
		}
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		if (inventory[slot] != null)
		{
			if (inventory[slot].stackSize - count <= 0)
			{
				ItemStack ret = inventory[slot];
				inventory[slot] = null;
				return ret;
			}
			else
			{
				ItemStack ret = inventory[slot].copy();
				ret.stackSize = count;
				inventory[slot].stackSize -= count;
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
		if (inventory[slot] != null)
		{
			ItemStack itemstack = inventory[slot];
			inventory[slot] = null;
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
		{
			inventory[slot] = item.copy();
		}
		else
		{
			inventory[slot] = null;
		}
		isCrafting = false;
		craftTime = 0;
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
	public boolean isItemValidForSlot(int slot, ItemStack item)
	{
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		InventoryUtils.readInventory(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		InventoryUtils.saveInventory(this, tag);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return new Wec3(this).extend(3);
	}

}
