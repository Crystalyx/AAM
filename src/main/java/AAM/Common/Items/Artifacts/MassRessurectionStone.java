package aam.common.items.artifacts;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MassRessurectionStone extends Item
{
	public MassRessurectionStone()
	{
		this.setMaxDamage(24001);
		this.setMaxStackSize(1);
		this.setTextureName("aam:mass_ressurection_stone");
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int tick, boolean hand)
	{
		this.setMaxDamage(24001);
		if (i.getItemDamage() != 0)
		{
			i.setItemDamage(i.getItemDamage() - 1);
		}
	}
}
