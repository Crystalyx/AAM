/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Tiles;

import AAM.API.Interface.IUpgradableItem;
import AAM.Common.Items.ModItems;
import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
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
	public ItemStack[] inventory = new ItemStack[4];

	@Override
	public int getSizeInventory()
	{
		return 4;
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

	public boolean isCrafting = false;
	public int craftTime = 0;

	public void startCrafting()
	{
		this.isCrafting = true;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.getStackInSlot(0) != null && this.getStackInSlot(1) != null && this.getStackInSlot(2) != null && this.getStackInSlot(3) == null)
		{
			if (this.getStackInSlot(0).getItem() instanceof IUpgradableItem && this.getStackInSlot(1).getItem() == ModItems.ModificationCatalyst && this.getStackInSlot(2).getItem() == ModItems.AnvilHammer)
			{
				IUpgradableItem ui = ((IUpgradableItem) this.getStackInSlot(0).getItem());
				int level = ui.getUpgradeLevel(this.getStackInSlot(0));
				int maxLevel = ui.getMaxLevel(this.getStackInSlot(0));
				int catLevel = this.getStackInSlot(1).getItemDamage();
				int modLevel = MiscUtils.isInLimit(catLevel, 0, 4) ? 1 : (MiscUtils.isInLimit(catLevel, 5, 9) ? 2 : catLevel >= 10 ? 3 : 0);
				if (this.getStackInSlot(0).getItem() == ModItems.SoulSword)
				{
					if (this.getStackInSlot(0).hasTagCompound())
					{
						String name = this.getStackInSlot(0).getTagCompound().getString("Owner");
						if (this.worldObj.getPlayerEntityByName(name) == null)
						{
							this.isCrafting = false;
							this.craftTime = 0;
							return;
						}
					}
					else
					{
						this.isCrafting = false;
						this.craftTime = 0;
						return;
					}
				}
				if (level < maxLevel && ((MiscUtils.isInLimit(level, 0, 4) && modLevel == 1) || (MiscUtils.isInLimit(level, 5, 9) && modLevel == 2) || (level >= 10 && modLevel == 3)))
				{
					if (this.craftTime <= 400)
					{
						this.isCrafting = true;
						this.craftTime += 1;
					}
					else
					{
						this.isCrafting = false;
						this.craftTime = 0;
						ui.addUpgradeLevel(this.worldObj, this.getStackInSlot(0));
						this.decrStackSize(1, 1);
						this.getStackInSlot(2).setItemDamage(this.getStackInSlot(2).getItemDamage() + level + 1);
						this.setInventorySlotContents(3, this.getStackInSlot(0));
						this.setInventorySlotContents(0, null);
					}
				}
				else
				{
					this.isCrafting = false;
					this.craftTime = 0;
					return;
				}
			}
			else
			{
				this.isCrafting = false;
				this.craftTime = 0;
				return;
			}
		}
		else
		{
			this.isCrafting = false;
			this.craftTime = 0;
			return;
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
				ItemStack ret = this.inventory[slot].copy();
				ret.stackSize = count;
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
		MiscUtils.readInventory(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		MiscUtils.saveInventory(this, tag);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return new Wec3(this).extend(3);
	}

}
