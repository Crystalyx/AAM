package aam.common.tiles;

import aam.common.items.weapon.anvil.WeaponPartItem;
import aam.common.weapon.WeaponManager;
import aam.common.weapon.anvil.WeaponPart;
import aam.utils.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class TEMechanistsTable extends TileEntity implements IInventory
{

	public ItemStack[] inv = new ItemStack[10];

	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}

	@Override
	public int getSizeInventory()
	{
		return 10;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size)
	{
		ItemStack is = inv[slot].copy();
		is.stackSize = size;

		if (inv[slot].stackSize - size <= 0)
		{
			inv[slot] = null;
		}
		else
		{
			inv[slot].stackSize -= size;
		}

		if (slot == 0)
		{
			for (int i = 1; i < this.getSizeInventory(); i++)
			{
				this.setInventorySlotContents(i, null);
			}
		}

		return is;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item)
	{
		inv[slot] = item;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		return super.getDescriptionPacket();
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
	}

	@Override
	public String getInventoryName()
	{
		return "teMechanistsTable";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
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
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setBoolean("Melee", this.melee);
		InventoryUtils.saveInventory(this, tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.melee = tag.getBoolean("Melee");
		InventoryUtils.readInventory(this, tag);
	}

	public boolean melee = true;

	public void switchType()
	{
		this.melee = !this.melee;
	}

	public void forge(String toolName)
	{
		List<WeaponPart> parts = new ArrayList<WeaponPart>();

		for (int i = 0; i < 7; i++)
		{
			if (getStackInSlot(i) != null)
			{
				ItemStack is = getStackInSlot(i);
				if (is.getItem() instanceof WeaponPartItem)
				{
					parts.add(((WeaponPartItem) is.getItem()).pt);
				}
			}
		}
		if (parts.size() > 0)
		{
			int[] partids = new int[parts.size()];
			for (int i = 0; i < parts.size(); i++)
			{
				partids[i] = parts.get(i).id;
			}
			ItemStack is = null;
			if (melee)
			{
				is = WeaponManager.createMeleeWeapon(toolName, partids);
			}
			else
			{
				is = WeaponManager.createRangedWeapon(toolName, partids);
			}
			if (is != null)
			{
				this.setInventorySlotContents(9, is);
			}
		}
	}

}
