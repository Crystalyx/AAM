package AAM.Common.Event;

import java.util.Collection;

import AAM.Common.Potions.ModPotions;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class PotionEventHandler
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void entityUpdate(LivingUpdateEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;
			if (p.getActivePotionEffect(ModPotions.flight) != null)
			{
				p.capabilities.allowFlying = true;
				p.fallDistance = 0;
			}
			else
			{
				if (!p.capabilities.isCreativeMode)
				{
					p.capabilities.isFlying = false;
					p.capabilities.allowFlying = false;
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void entityAttacked(LivingHurtEvent e)
	{
		Collection<PotionEffect> coll = e.entityLiving.getActivePotionEffects();

		for (PotionEffect eff : coll)
		{
			if (eff.getPotionID() == ModPotions.invincibility.id)
			{
				e.setCanceled(true);
			}
			if (eff.getPotionID() == ModPotions.lavaResistance.id)
			{
				if (e.source.equals(DamageSource.lava))
				{
					e.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void entityAttacked(AttackEntityEvent e)
	{
		Collection<PotionEffect> coll = e.entityPlayer.getActivePotionEffects();

		for (PotionEffect eff : coll)
		{
			if (eff.getPotionID() == ModPotions.flame.id)
			{
				e.target.setFire(20 + e.entityPlayer.getActivePotionEffect(ModPotions.flame).getAmplifier() * 10);
			}
		}
	}
}
