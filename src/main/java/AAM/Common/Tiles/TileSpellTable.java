package AAM.Common.Tiles;

import AAM.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileSpellTable extends TileEntity implements IInventory
{
	public ItemStack[] inv = new ItemStack[this.getSizeInventory()];

	public byte rotation = 0;

	@Override
	public int getSizeInventory()
	{
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size)
	{
		ItemStack is = this.inv[slot].copy();
		is.stackSize = size;

		if (this.inv[slot].stackSize - size <= 0)
		{
			this.inv[slot] = null;
		} else
			this.inv[slot].stackSize -= size;

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
		this.inv[slot] = item;
	}

	@Override
	public String getInventoryName()
	{
		return "spellTable";
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
		MiscUtils.readInventory(this, tag);
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		MiscUtils.saveInventory(this, tag);
	}

}
