package aam.common.weapon;

import aam.common.items.ModItems;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;

public class AirGem extends WeaponUpgrade
{

	public AirGem()
	{
		super("airGem", ModItems.AirGem, 0, EnumChatFormatting.YELLOW);
	}

	@Override
	public float getMeleeDamageBonus(PlayerDataHandler ph, int soulLevel, float baseDamage, boolean inAttack)
	{
		return 2f;
	}

	@Override
	public float getRangedDamageBonus(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack)
	{
		return 5f;
	}

	@Override
	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		int time = 5 * 20 + (int) baseDamage;
		Wec3 v = new Wec3(ph.player).sub(new Wec3(l)).flip();
		v.normalize();
		v = v.div(4);
		v.ptm(l);
	}
}
