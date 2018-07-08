package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

import AAM.API.StructureCore;
import AAM.Common.Items.ModItems;
import AAM.Common.Items.Resources.SwordDye;
import AAM.Common.Items.Soul.Artifact;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Common.Soul.Soul;
import AAM.Common.Soul.SoulUpgrade;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Structure;
import AAM.Utils.Structures;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TESoulAltar extends StructureCore implements IInventory
{
	public ItemStack[] inv = new ItemStack[12];
	public int value = 0;
	public int maxValue = 1000;
	public boolean opened = false;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.opened)
		{
			if (this.formed && this.getStackInSlot(0) != null && this.getStackInSlot(0).getItem() instanceof SoulSword)
			{
				ItemStack is = this.getStackInSlot(0);

				if (is.hasTagCompound())
				{
					NBTTagCompound tag = is.getTagCompound();
					String name = tag.getString("Owner");
					if (!name.equals(""))
					{
						if (this.worldObj.getPlayerEntityByName(name) != null)
						{
							PlayerDataHandler ph = PlayerDataHandler.get(this.worldObj.getPlayerEntityByName(name));
							saveSoulItems(ph);
						}
					}
				}
			}
			else
			{
				for (int i = 1; i < this.getSizeInventory(); i++)
				{
					this.setInventorySlotContents(i, null);
					// MiscUtils.dropSlot(this, i);
				}
			}
			this.markDirty();
		}
	}

	private void saveSoulItems(PlayerDataHandler ph)
	{
		if (this.getStackInSlot(1) == null)
		{
			ph.partType = -1;
		}
		else
		{
			if (this.getStackInSlot(1).getItem() instanceof SwordDye)
			{
				ph.partType = this.getStackInSlot(1).getItemDamage();
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

		this.inv[0] = ph.getSwordStack();

		if (ph.bow)
		{
			ItemStack bowis = this.getStackInSlot(3);
			if (bowis.hasTagCompound())
			{
				if (ph.art)
					bowis.getTagCompound().setInteger("Art", ph.stype.ordinal());
				else
					bowis.getTagCompound().setInteger("Art", -1);
			}
			else
			{
				NBTTagCompound bowtag = new NBTTagCompound();
				if (ph.art)
					bowtag.setInteger("Art", ph.stype.ordinal());
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
		}
		else
			this.inv[slot].stackSize -= size;

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
		this.inv[slot] = item;
		if (this.formed)
		{
			if (slot == 0 && item != null && item.getItem() instanceof SoulSword && item.hasTagCompound())
			{
				this.placeItems(item.getTagCompound().getString("Owner"));
				return;
			}
		}
		if (slot == 0)
			for (int i = 1; i < 12; i++)
			{
				this.setInventorySlotContents(i, null);
			}
		this.markDirty();
	}

	private void placeItems(String playerName)
	{
		if (this.worldObj != null && !this.worldObj.isRemote)
		{
			if (this.worldObj.getPlayerEntityByName(playerName) != null)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(this.worldObj.getPlayerEntityByName(playerName));
				if (ph.partType != -1)
				{
					this.setInventorySlotContents(1, new ItemStack(ModItems.Shield, 1, ph.partType));
				}

				if (this.getStackInSlot(2) == null)
				{
					if (ph.art)
						this.setInventorySlotContents(2, new ItemStack(ModItems.Artifact, 1, ph.stype.ordinal()));
				}
				if (ph.bow)
				{
					ItemStack is = new ItemStack(ModItems.CrystalBow);
					NBTTagCompound tg = new NBTTagCompound();
					if (ph.art)
						tg.setInteger("Art", ph.stype.ordinal());
					else
						tg.setInteger("Art", -1);

					is.setTagCompound(tg);
					this.setInventorySlotContents(3, is);
				}
				List<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < ph.upgLevel.length; i++)
				{
					for (int j = 0; j < ph.upgLevel[i]; j++)
					{
						list.add(i);
					}
				}
				for (int i = 0; i < Math.min(6, list.size()); i++)
				{
					this.setInventorySlotContents(4 + i, new ItemStack(ModItems.SoulUpgradeItem, 1, list.get(i)));
				}
			}
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
		this.opened = true;
		if (this.getStackInSlot(0) != null)
		{
			if (this.getStackInSlot(0).getItem() instanceof SoulSword)
			{
				if (this.getStackInSlot(0).hasTagCompound())
				{
					String name = this.getStackInSlot(0).getTagCompound().getString("Owner");
					if (!name.equals(""))
					{
						this.placeItems(name);
					}
				}
			}
		}
	}

	@Override
	public void closeInventory()
	{
		this.opened = false;
		ItemStack is = this.getStackInSlot(0);
		if (is != null)
		{
			if (is.hasTagCompound())
			{
				NBTTagCompound tag = is.getTagCompound();
				String name = tag.getString("Owner");
				if (!name.equals(""))
				{
					if (this.worldObj.getPlayerEntityByName(name) != null)
					{
						PlayerDataHandler ph = PlayerDataHandler.get(this.worldObj.getPlayerEntityByName(name));
						saveSoulItems(ph);
					}
				}
			}
		}
		for (int i = 1; i < this.getSizeInventory(); i++)
		{
			this.setInventorySlotContents(i, null);
		}
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

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.value = tag.getInteger("Value");
		this.maxValue = tag.getInteger("MaxValue");
		MiscUtils.readInventory(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("Value", this.value);
		tag.setInteger("MaxValue", this.maxValue);
		MiscUtils.saveInventory(this, tag);
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
