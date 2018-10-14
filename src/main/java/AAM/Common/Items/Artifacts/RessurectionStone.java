package aam.common.items.artifacts;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RessurectionStone extends Item
{
	public RessurectionStone()
	{
		this.setMaxDamage(12001);
		this.setMaxStackSize(1);
		this.setTextureName("aam:ressurection_stone");
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int tick, boolean hand)
	{
		this.setMaxDamage(12001);
		if (i.getItemDamage() != 0)
		{
			i.setItemDamage(i.getItemDamage() - 1);
		}
	}
}
