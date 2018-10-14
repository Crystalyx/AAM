package aam.common.weapon;

import aam.common.items.ModItems;
import aam.common.potions.ModPotions;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

public class IceGem extends WeaponUpgrade
{

	public IceGem()
	{
		super("iceGem", ModItems.IceGem, 0, EnumChatFormatting.AQUA);
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
		l.addPotionEffect(new PotionEffect(ModPotions.cold.id, time, 3));
	}
}
