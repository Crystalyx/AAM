package aam.common.weapon;

import aam.common.items.ModItems;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;

public class FlameUpgrade extends WeaponUpgrade
{

	public FlameUpgrade()
	{
		super("aam:flame", ModItems.bigConcentrate, 3, EnumChatFormatting.RED);
	}

	@Override
	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		int time = 5 * 20 + (int) baseDamage;
		l.setFire(time);
	}

}
