package AAM.Common.Soul;

import AAM.API.Interface.ISoulUpgrade;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;

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
	public float getMeleeDamage(PlayerDataHandler ph, int soulLevel, float baseDamage, boolean inAttack)
	{
		return 0;
	}

	@Override
	public float getSpecificMeleeDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		return 0;
	}

	@Override
	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{

	}

	@Override
	public float getRangedDamage(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack)
	{
		return 0;
	}

	@Override
	public float getSpecificRangedDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		return 0;
	}
}
