package aam.api.interfaces;

import net.minecraft.item.ItemStack;

public interface IEnergyStorage
{
	public boolean canBeCharged(ItemStack is);

	public double charge(ItemStack i, double energy);

	public boolean canBeDischarged(ItemStack is);

	public double discharge(ItemStack i, double energy);

	public double getMaxStoredEnergy(ItemStack i);

	public double getStoredEnergy(ItemStack i);
}
