package aam.common.soul;

import aam.api.interfaces.ISoulUpgrade;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public enum SoulUpgrade implements ISoulUpgrade
{
	Blood("aam:soulsword/blood_upgrade"), Cast("aam:soulsword/cast_upgrade"), Moon("aam:soulsword/moon_upgrade"), Recharge("aam:soulsword/recharge_upgrade"), Ender("aam:soulsword/enderinversion_upgrade");// ,
	// Heal

	public String texture;

	private SoulUpgrade(String texture)
	{
		this.texture = texture;
	}

	@Override
	public float getMeleeDamageBonus(PlayerDataHandler ph, int soulLevel, float baseDamage, boolean inAttack)
	{
		return 0;
	}

	@Override
	public float getSpecificMeleeDamageBonus(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		return 0;
	}

	@Override
	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{

	}

	@Override
	public float getRangedDamageBonus(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack)
	{
		return 0;
	}

	@Override
	public float getSpecificRangedDamageBonus(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		return 0;
	}

	@Override
	public void onAttack(EntityPlayer p, EntityLivingBase e, float damage)
	{

	}

	@Override
	public boolean onEnderTeleport(EntityPlayer p, EntityLivingBase ender)
	{
		return this.equals(Ender);
	}
}
