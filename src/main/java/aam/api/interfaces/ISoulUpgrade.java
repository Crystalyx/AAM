package aam.api.interfaces;

import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public interface ISoulUpgrade
{
	public float getMeleeDamageBonus(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack);

	public float getSpecificMeleeDamageBonus(PlayerDataHandler ph, EntityLivingBase l, int level, float baseDamage);

	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int level, float baseDamage);

	public float getRangedDamageBonus(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack);

	public float getSpecificRangedDamageBonus(PlayerDataHandler ph, EntityLivingBase l, int level, float baseDamage);

	public void onAttack(EntityPlayer p, EntityLivingBase e, float damage);

	public boolean onEnderTeleport(EntityPlayer p, EntityLivingBase ender);

}
