package aam.common.items.alchemy;

import aam.common.entity.EntityMinium;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MiniumShard extends Item
{
	public MiniumShard()
	{
		this.setTextureName("aam:tools/minium");
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack)
	{
		return true;
	}

	@Override
	public Entity createEntity(World w, Entity loc, ItemStack is)
	{
		EntityMinium em = new EntityMinium(w, new Wec3(loc), is);
		em.setVelocity(loc.motionX, loc.motionY, loc.motionZ);
		em.delayBeforeCanPickup = 40;
		return em;
	}
}
