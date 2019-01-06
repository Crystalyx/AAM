package aam.api.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IExtendedReach
{
	public float getReachValue(EntityPlayer p, ItemStack is);
}
