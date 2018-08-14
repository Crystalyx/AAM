package AAM.Common.Items.Alchemy;

import AAM.API.Interface.ICircleExtender;
import AAM.Common.Entity.EntityMinium;
import AAM.Utils.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MiniumShard extends Item implements ICircleExtender
{
	public MiniumShard()
	{
		this.setTextureName("aam:tools/minium");
	}

	@Override
	public void onExtended(ItemStack i, EntityPlayer p, World w, Wec3 pos)
	{
		i.stackSize -= 1;
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
