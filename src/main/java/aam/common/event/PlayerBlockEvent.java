package aam.common.event;

import aam.api.GameWeapon;
import aam.common.soul.SoulWeaponType;
import aam.common.weapon.WeaponManager;
import aam.utils.PlayerDataHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PlayerBlockEvent
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void PlayerAttackedEvent(LivingHurtEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;

			PlayerDataHandler ph = PlayerDataHandler.get(p);
			if (p.getItemInUse() != null && p.isBlocking() && e.source.getSourceOfDamage() != null && !ph.sword.equals(SoulWeaponType.Hammer))
			{
				if (p.getItemInUse().getItem() instanceof GameWeapon)
				{
					float dam = 0;
					if (WeaponManager.getDamagePoints(p.getCurrentEquippedItem()) - e.ammount < 0)
					{
						dam = e.ammount - WeaponManager.getDamagePoints(p.getCurrentEquippedItem());
					}
					WeaponManager.setDamagePoints(p.getCurrentEquippedItem(), Math.round(e.ammount));
					e.ammount = dam;
				}
				else
					if (p.getItemInUse().getItem() instanceof ItemSword)
					{
						float dam = 0;
						if (p.getCurrentEquippedItem().getItemDamage() - e.ammount < 0)
						{
							dam = e.ammount - p.getCurrentEquippedItem().getItemDamage();
						}
						p.getCurrentEquippedItem().damageItem(Math.round(e.ammount), p);
						e.ammount = dam;
					}
			}
		}
		/*
		 * Some Code with Artifacts Inventory
		 */
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void PlayerFallEvent(LivingFallEvent e)
	{
		/*
		 * Some Code with Artifacts Inventory
		 */
	}

}
