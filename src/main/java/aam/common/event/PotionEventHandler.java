package aam.common.event;

import aam.common.potions.ModPotions;
import aam.utils.PlayerDataHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import java.util.Collection;

public class PotionEventHandler
{
	public static AttributeModifier[] flightSpeed = new AttributeModifier[20];
	public static AttributeModifier[] healthIncrease = new AttributeModifier[20];

	static
	{
		for (int i = 0; i < flightSpeed.length; i++)
		{
			flightSpeed[i] = new AttributeModifier("potion_flight", 0.05 * (i + 1), 0);
			flightSpeed[i].setSaved(true);
		}

		for (int i = 0; i < flightSpeed.length; i++)
		{
			healthIncrease[i] = new AttributeModifier("potion_health_increase", 20 + 8 * i, 0);
			healthIncrease[i].setSaved(true);
		}
	}

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
				int power = Math.min(p.getActivePotionEffect(ModPotions.flight).getAmplifier() - 1, 19);
				if (power > -1)
				{
					try
					{
						p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(flightSpeed[power]);
					}
					catch (Exception e2)
					{
					}

					if (!p.onGround)
					{
						p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(flightSpeed[power]);
					}
				}
			}
			else
			{
				if (!p.capabilities.isCreativeMode)
				{
					p.capabilities.isFlying = false;
					p.capabilities.allowFlying = false;
				}
			}

			// if (p.getActivePotionEffect(ModPotions.healthincr) != null)
			// {
			// int power =
			// Math.min(p.getActivePotionEffect(ModPotions.healthincr).getAmplifier(),
			// 19);
			// int time =
			// p.getActivePotionEffect(ModPotions.healthincr).getDuration();
			//
			// if (power > -1 && time > 1)
			// {
			// p.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(healthIncrease[power]);
			// }
			// else
			// p.getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(healthIncrease[power]);
			//
			// }
			if (p.getActivePotionEffect(ModPotions.soul) != null)
			{
				int snk = p.isSneaking() ? 2 : 0;
				int blk = p.isBlocking() ? 2 : 0;
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				ph.addSoul((p.getActivePotionEffect(ModPotions.soul).getAmplifier() + 1) * 2 + snk + blk);
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
