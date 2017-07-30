package AAM.Common.Event;

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

			if (p.getItemInUse() != null && p.isBlocking() && e.source.getSourceOfDamage() != null)
			{
				if (p.getItemInUse().getItem() instanceof ItemSword)
				{
					float dam = 0;
					if (p.getCurrentEquippedItem().getItemDamage() - e.ammount < 0)
						dam = e.ammount - p.getCurrentEquippedItem().getItemDamage();
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
	public void PlayerAttackedEvent(LivingFallEvent e)
	{
		/*
		 * Some Code with Artifacts Inventory
		 */
	}

}
