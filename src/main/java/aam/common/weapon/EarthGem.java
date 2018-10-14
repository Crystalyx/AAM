package aam.common.weapon;

import aam.common.items.ModItems;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;

public class EarthGem extends WeaponUpgrade
{

	public EarthGem()
	{
		super("earthGem", ModItems.EarthGem, 0, EnumChatFormatting.GREEN);
	}

	@Override
	public float getMeleeDamageBonus(PlayerDataHandler ph, int soulLevel, float baseDamage, boolean inAttack)
	{
		return 7f;
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
	}
}
