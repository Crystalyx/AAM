package aam.api.abstraction;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class Sheath extends Item
{
	public Sheath()
	{
		this.setMaxStackSize(1);
	}

	public void performEffect(ItemStack _itemStack, EntityPlayer _p){}
}
