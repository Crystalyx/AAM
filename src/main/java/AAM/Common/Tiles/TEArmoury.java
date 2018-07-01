package AAM.Common.Tiles;

import AAM.Common.Items.Soul.SoulSword;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;

public class TEArmoury extends MultiInventory
{
	@Override
	public void updateEntity()
	{
		if (this.getStackInSlot(27) != null)
		{
			if (this.getStackInSlot(27).getItem() instanceof SoulSword)
			{
				ItemStack sw = this.getStackInSlot(27);
				if (sw.hasTagCompound())
				{
					String name = sw.getTagCompound().getString("Owner");
					EntityPlayer p = this.worldObj.getPlayerEntityByName(name);
					if (p != null)
					{
						PlayerDataHandler ph = PlayerDataHandler.get(p);

						if (this.getStackInSlot(28) != null)
						{
							if (this.getStackInSlot(28).getItem() instanceof ItemSword && !(this.getStackInSlot(28).getItem() instanceof SoulSword))
							{
								ItemStack is = this.getStackInSlot(28);
								if (this.cons == 100)
								{
									this.setInventorySlotContents(28, null);
									this.cons = 0;
									ph.swords.add((ItemSword) is.getItem());
									ph.soulDamage += ((ItemSword) is.getItem()).func_150931_i() + 4;
								}
								else
								{
									this.cons++;
								}
							}
							else
								this.cons = 0;
						}
						else
							this.cons = 0;

						this.clearInventory();
						for (int i = 0; i < ph.swords.size(); i++)
						{
							ItemStack is = new ItemStack(ph.swords.get(i));
							this.mltinv.add(is);
						}
					}
				}
				else
					this.clearInventory();
			}
			else
				this.clearInventory();
		}
		else
			this.clearInventory();
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
					ItemStack sw = this.getStackInSlot(27);
					if (sw != null)
						if (sw.hasTagCompound())
						{
							PlayerDataHandler ph = PlayerDataHandler.get(this.worldObj.getPlayerEntityByName(sw.getTagCompound().getString("Owner")));
							ph.soulDamage -= ((ItemSword) ph.swords.get(mslot)).func_150931_i() + 4;
							ph.swords.remove(mslot);
						}
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

	@Override
	public void setInventorySlotContents(int slot, ItemStack is)
	{
		super.setInventorySlotContents(slot, is);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is)
	{
		return slot >= 27;
	}

	@Override
	public String getInventoryName()
	{
		return "armoury";
	}

	public int cons = 0;

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		tag.setInteger("Cons", this.cons);
		MiscUtils.saveInventory(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.cons = tag.getInteger("Cons");
		MiscUtils.readInventory(this, tag);
	}

	@Override
	public int getUSlots()
	{
		return 2;
	}

}
