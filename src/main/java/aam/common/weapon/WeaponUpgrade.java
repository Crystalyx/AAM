package aam.common.weapon;

import aam.api.Interface.ISoulUpgrade;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;

public class WeaponUpgrade implements ISoulUpgrade
{
	public Item item;
	public int meta;
	public EnumChatFormatting color;
	public String name;

	public WeaponUpgrade(String name, Item i, int meta, EnumChatFormatting color)
	{
		this.name = name;
		item = i;
		this.meta = meta;
		this.color = color;
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
		return false;
	}

}
