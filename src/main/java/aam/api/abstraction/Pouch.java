package aam.api.abstraction;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class Pouch extends Item
{
	public Pouch()
	{
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
	}

	public abstract int getSizeInventory();

	public static int getSizeInventory(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof Pouch)
			{
				return ((Pouch) i.getItem()).getSizeInventory();
			}
		}
		return 0;
	}

}
