package aam.common.tiles;

import aam.api.abstraction.StructureCore;
import aam.common.items.ModItems;
import aam.common.items.resources.SwordDye;
import aam.common.items.soul.Artifact;
import aam.common.soul.Soul;
import aam.common.soul.SoulUpgrade;
import aam.utils.InventoryUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.Structure;
import aam.utils.Structures;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

import java.util.ArrayList;
import java.util.List;

public class TESoulAltar extends StructureCore implements IInventory
{
	public ItemStack[] inv = new ItemStack[12];
	public boolean opened = false;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (opened)
		{
			if (this.getStackInSlot(0) != null)
			{
				if (this.getStackInSlot(0).getItem() == ModItems.SoulSword)
				{
					if (this.getStackInSlot(0).hasTagCompound())
					{
						String name = this.getStackInSlot(0).getTagCompound().getString("Owner");

						if (name != "")
						{
							if (worldObj.getPlayerEntityByName(name) != null && formed)
							{
								EntityPlayer p = worldObj.getPlayerEntityByName(name);
								PlayerDataHandler ph = PlayerDataHandler.get(p);
								saveSoulItems(ph);
								this.clearItems();
								placeItems(ph);
							}
						}
					}
				}
			}
		}
	}

	private void saveSoulItems(PlayerDataHandler ph)
	{
		if (this.getStackInSlot(1) == null)
		{
			ph.color = -1;
		}
		else
		{
			if (this.getStackInSlot(1).getItem() instanceof SwordDye)
			{
				ph.color = this.getStackInSlot(1).getItemDamage();
			}
		}

		if (this.getStackInSlot(2) == null)
		{
			ph.stype = Soul.Normal;
			ph.art = false;
		}
		else
		{
			if (this.getStackInSlot(2).getItem() instanceof Artifact)
			{
				ph.stype = Soul.values()[this.getStackInSlot(2).getItemDamage()];
				ph.art = true;
			}
		}

		if (this.getStackInSlot(3) == null)
		{
			ph.bow = false;
		}
		else
		{
			if (this.getStackInSlot(3).getItem() == ModItems.CrystalBow)
			{
				ph.bow = true;
			}

		}

		ph.upgLevel = new int[SoulUpgrade.values().length];
		for (int i = 0; i < 6; i++)
		{
			ItemStack up = this.getStackInSlot(i + 4);
			if (up != null)
			{
				if (up.getItem() == ModItems.SoulUpgradeItem)
				{
					ph.upgLevel[up.getItemDamage()] += 1;
				}
			}
		}

		inv[0] = ph.getSwordStack();

		if (ph.bow)
		{
			ItemStack bowis = this.getStackInSlot(3);
			if (bowis.hasTagCompound())
			{
				if (ph.art)
				{
					bowis.getTagCompound().setInteger("Art", ph.stype.ordinal());
				}
				else
				{
					bowis.getTagCompound().setInteger("Art", -1);
				}
			}
			else
			{
				NBTTagCompound bowtag = new NBTTagCompound();
				if (ph.art)
				{
					bowtag.setInteger("Art", ph.stype.ordinal());
				}
				bowtag.setInteger("State", 0);
				bowis.setTagCompound(bowtag);
			}
		}
	}

	@Override
	public int getSizeInventory()
	{
		return 12;
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
		if (slot != 0)
		{
			if (this.getStackInSlot(0) != null)
			{
				if (this.getStackInSlot(0).getItem() == ModItems.SoulSword)
				{
					if (this.getStackInSlot(0).hasTagCompound())
					{
						String name = this.getStackInSlot(0).getTagCompound().getString("Owner");

						if (name != "")
						{
							if (worldObj.getPlayerEntityByName(name) != null)
							{
								EntityPlayer p = worldObj.getPlayerEntityByName(name);
								PlayerDataHandler ph = PlayerDataHandler.get(p);
								saveSoulItems(ph);
								clearItems();
								placeItems(ph);
							}
						}
					}
				}
			}
		}
		else
		{
			if (item != null)
			{
				if (item.getItem() == ModItems.SoulSword)
				{
					if (item.hasTagCompound())
					{
						String name = item.getTagCompound().getString("Owner");

						if (name != "")
						{
							if (worldObj.getPlayerEntityByName(name) != null)
							{
								EntityPlayer p = worldObj.getPlayerEntityByName(name);
								PlayerDataHandler ph = PlayerDataHandler.get(p);
								clearItems();
								placeItems(ph);
							}
						}
					}
				}
			}
		}
	}

	public void clearItems()
	{
		for (int i = 1; i < this.getSizeInventory(); i++)
		{
			inv[i] = null;
		}
	}

	private void placeItems(PlayerDataHandler ph)
	{
		if (ph.color != -1)
		{
			inv[1] = new ItemStack(ModItems.SoulLens, 1, ph.color);
		}
		if (ph.art)
		{
			inv[2] = new ItemStack(ModItems.Artifact, 1, ph.stype.ordinal());
		}
		if (ph.bow)
		{
			inv[3] = new ItemStack(ModItems.CrystalBow);
		}

		List<Integer> l = new ArrayList<>();
		for (int i = 0; i < ph.upgLevel.length; i++)
		{
			for (int j = 0; j < ph.upgLevel[i]; j++)
			{
				l.add(i);
			}
		}
		for (int i = 0; i < Math.min(l.size(), 6); i++)
		{
			inv[4 + i] = new ItemStack(ModItems.SoulUpgradeItem, 1, l.get(i));
		}

	}

	@Override
	public String getInventoryName()
	{
		return "soulAltar";
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
		opened = true;
		if (this.getStackInSlot(0) != null)
		{
			if (this.getStackInSlot(0).getItem() == ModItems.SoulSword)
			{
				if (this.getStackInSlot(0).hasTagCompound())
				{
					String name = this.getStackInSlot(0).getTagCompound().getString("Owner");

					if (name != "")
					{
						if (worldObj.getPlayerEntityByName(name) != null)
						{
							EntityPlayer p = worldObj.getPlayerEntityByName(name);
							PlayerDataHandler ph = PlayerDataHandler.get(p);
							placeItems(ph);
						}
					}
				}
			}
		}
	}

	@Override
	public void closeInventory()
	{
		opened = false;
		if (this.getStackInSlot(0) != null)
		{
			if (this.getStackInSlot(0).getItem() == ModItems.SoulSword)
			{
				if (this.getStackInSlot(0).hasTagCompound())
				{
					String name = this.getStackInSlot(0).getTagCompound().getString("Owner");

					if (name != "")
					{
						if (worldObj.getPlayerEntityByName(name) != null)
						{
							EntityPlayer p = worldObj.getPlayerEntityByName(name);
							PlayerDataHandler ph = PlayerDataHandler.get(p);
							saveSoulItems(ph);
						}
					}
				}
			}
		}
		clearItems();
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
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
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
		InventoryUtils.readInventory(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		InventoryUtils.saveInventory(this, tag);
	}

	@Override
	public Structure getStructure()
	{
		return Structures.soulAltar;
	}

	@Override
	public Wec3 getOffset()
	{
		return new Wec3(3, 2, 3);
	}

	@Override
	public void onFormed()
	{
		this.setInventorySlotContents(0, this.getStackInSlot(0));
	}

	@Override
	public void onUnFormed()
	{

	}
}
