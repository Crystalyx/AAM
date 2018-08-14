package AAM.API.Abstract;

import AAM.API.Interface.IEnergyStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class EnergyStorage extends Item implements IEnergyStorage
{

	@Override
	public boolean canBeCharged(ItemStack is)
	{
		return true;
	}

	@Override
	public double charge(ItemStack i, double energy)
	{
		double excess = 0;
		if (this.canBeCharged(i))
		{
			if (i.hasTagCompound())
			{
				NBTTagCompound tg = i.getTagCompound();
				double currValue = tg.getDouble("TrEnergy");
				excess = Math.max((currValue + energy) - this.getMaxStoredEnergy(i), 0);
				tg.setDouble("TrEnergy", (currValue + energy) - excess);
			}
			else
			{
				NBTTagCompound tg = new NBTTagCompound();
				excess = Math.max(energy - this.getMaxStoredEnergy(i), 0);
				tg.setDouble("TrEnergy", energy - excess);
				i.setTagCompound(tg);
			}
		}
		return excess;
	}

	public double chargeBypass(ItemStack i, double energy)
	{
		double excess = 0;
		if (i.hasTagCompound())
		{
			NBTTagCompound tg = i.getTagCompound();
			double currValue = tg.getDouble("TrEnergy");
			excess = Math.max((currValue + energy) - this.getMaxStoredEnergy(i), 0);
			tg.setDouble("TrEnergy", (currValue + energy) - excess);
		}
		else
		{
			NBTTagCompound tg = new NBTTagCompound();
			excess = Math.max(energy - this.getMaxStoredEnergy(i), 0);
			tg.setDouble("TrEnergy", energy - excess);
			i.setTagCompound(tg);
		}
		return excess;
	}

	@Override
	public boolean canBeDischarged(ItemStack is)
	{
		return true;
	}

	@Override
	public double discharge(ItemStack i, double energy)
	{
		double deficit = 0;
		if (this.canBeDischarged(i))
		{
			if (i.hasTagCompound())
			{
				NBTTagCompound tg = i.getTagCompound();
				double currValue = tg.getDouble("TrEnergy");
				deficit = -Math.min((currValue - energy), 0);
				tg.setDouble("TrEnergy", (currValue - energy) + deficit);
			}
			else
			{
				NBTTagCompound tg = new NBTTagCompound();
				tg.setDouble("TrEnergy", 0);
				i.setTagCompound(tg);
			}
		}
		return deficit;
	}

	public double dischargeBypass(ItemStack i, double energy)
	{
		double deficit = 0;
		if (i.hasTagCompound())
		{
			NBTTagCompound tg = i.getTagCompound();
			double currValue = tg.getDouble("TrEnergy");
			deficit = -Math.min((currValue - energy), 0);
			tg.setDouble("TrEnergy", (currValue - energy) + deficit);
		}
		else
		{
			NBTTagCompound tg = new NBTTagCompound();
			tg.setDouble("TrEnergy", 0);
			i.setTagCompound(tg);
		}
		return deficit;
	}

	@Override
	public abstract double getMaxStoredEnergy(ItemStack i);

	@Override
	public double getStoredEnergy(ItemStack i)
	{
		if (i != null)
		{
			if (i.hasTagCompound())
			{
				NBTTagCompound tg = i.getTagCompound();
				return tg.getDouble("TrEnergy");
			}
		}
		return 0;
	}

}
