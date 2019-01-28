package aam.api;

import aam.api.abstraction.Pouch;
import aam.utils.InventoryUtils;
import aam.utils.Logger;
import aam.utils.forge.ItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class PouchInventory extends ItemStackHandler implements IInventory
{
	public ItemStack pouch;

	public PouchInventory(ItemStack pouch)
	{
		super(Pouch.getSizeInventory(pouch));
		if (pouch.hasTagCompound())
			super.deserializeNBT(pouch.getTagCompound());
		this.pouch = pouch;
		this.markDirty();
	}

	@Override
	public int getSizeInventory()
	{
		return Pouch.getSizeInventory(pouch);
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return super.getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		ItemStack is = super.extractItem(slot, count, false);
		markDirty();
		return is;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is)
	{
		super.setStackInSlot(slot, is);
		markDirty();
	}

	@Override
	public String getInventoryName()
	{
		return "pouch";
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
	public void markDirty()
	{
		Logger.info("before " + this.pouch.getTagCompound());
		this.pouch.setTagCompound(super.serializeNBT());
		Logger.info("after " + this.pouch.getTagCompound());
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
	public boolean isItemValidForSlot(int slot, ItemStack item)
	{
		return true;
	}

	public void saveItems(ItemStack is, PouchInventory inv)
	{
		InventoryUtils.saveInventory(inv, is.getTagCompound());
	}

	public void loadItems(ItemStack is, PouchInventory inv)
	{
		InventoryUtils.readInventory(inv, is.getTagCompound());
	}

}
