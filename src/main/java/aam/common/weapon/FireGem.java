package aam.common.weapon;

import aam.common.items.ModItems;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;

public class FireGem extends WeaponUpgrade
{

	public FireGem()
	{
		super("fireGem", ModItems.FireGem, 0, EnumChatFormatting.GOLD);
	}

	@Override
	public float getMeleeDamageBonus(PlayerDataHandler ph, int soulLevel, float baseDamage, boolean inAttack)
	{
		return 2f;
	}

	@Override
	public float getRangedDamageBonus(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack)
	{
		return 2f;
	}

	@Override
	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		int time = 5 * 20 + (int) baseDamage;
		l.setFire(time);
		l.attackEntityFrom(DamageSource.inFire, 1);
	}
}
