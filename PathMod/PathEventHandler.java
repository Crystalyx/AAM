package PathMod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import java.util.Random;

public class PathEventHandler
{
	public static Random r = new Random();

	@SubscribeEvent
	public void livingdamage(AttackEntityEvent e)
	{
		int f = r.nextInt(100);
		if (e.entityPlayer.getCurrentEquippedItem() != null)
		{
			if (e.entityPlayer.getCurrentEquippedItem().getItem() == PathMod.knife)
			{
				ItemStack is = null;

				if (f <= 5)
				{
					is = new ItemStack(Items.emerald);
				}
				else
					if (f > 5 && f <= 15)
					{
						is = new ItemStack(Items.diamond);
					}
					else
						if (f > 15 && f <= 25)
						{
							is = new ItemStack(Items.gold_ingot);
						}
				if (is != null)
				{
					EntityItem el = new EntityItem(e.entityPlayer.worldObj, e.entityPlayer.posX, e.entityPlayer.posY, e.entityPlayer.posZ, is);
					el.setVelocity(r.nextDouble() * 0.4, r.nextDouble() * 0.4, r.nextDouble() * 0.4);
					if (!e.entityPlayer.worldObj.isRemote)
						e.entityPlayer.worldObj.spawnEntityInWorld(el);
				}
			}
		}
	}
}
