package aam.api.Interface;

import aam.api.Elementals;
import net.minecraft.item.ItemStack;

public interface IElementalAwakable
{
	/**
	 * @param is
	 * @param e
	 * @return an blank itemstack to be an awakened version of is
	 */
	public ItemStack getAwakenedVersion(ItemStack is, Elementals e);
}
