package AAM.Common.Soul;

import AAM.API.ISoulUpgrade;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public enum Soul implements ISoulUpgrade
{
	Light, Normal, Blood, Lunar, Plant, Watery, First, Seventh;

	public float getLeveledDamage(int soulLevel, float baseDamage)
	{
		float dmg = baseDamage * (2 * (soulLevel - 1)) / 100f;
		return dmg;
	}

	public float getFullMeleeDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage, boolean inAttack)
	{
		return getMeleeDamage(ph, soulLevel, baseDamage, inAttack) + getSpecificMeleeDamage(ph, l, soulLevel, baseDamage);
	}

	public float getFullRangedDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage, boolean inAttack)
	{
		return getRangedDamage(ph, soulLevel, baseDamage, inAttack) + getSpecificRangedDamage(ph, l, soulLevel, baseDamage);
	}

	@Override
	public float getMeleeDamage(PlayerDataHandler ph, int soulLevel, float baseDamage, boolean inAttack)
	{
		float dmg = baseDamage;
		if (ph.art)
		{
			if (this.equals(Soul.Light))
			{
				dmg += baseDamage * 0.2 + getLeveledDamage(soulLevel, baseDamage);
			}
			if (this.equals(Soul.Normal))
			{
				dmg += baseDamage * 0.3 + getLeveledDamage(soulLevel, baseDamage);
			}
			if (this.equals(Soul.Blood))
			{
				if (!inAttack || ph.consumeSoul(1))
				{
					dmg += baseDamage * 0.25 + getLeveledDamage(soulLevel, baseDamage);
				}
			}
			if (this.equals(Soul.Lunar))
			{
				dmg += baseDamage * 0.2 + getLeveledDamage(soulLevel, baseDamage);
			}
			if (this.equals(Soul.Plant))
			{
				dmg += baseDamage * 0.15f + getLeveledDamage(soulLevel, baseDamage);
			}
		}

		return dmg;
	}

	@Override
	public float getSpecificMeleeDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		float dmg = 0;
		if (ph.art)
		{
			if (this.equals(Soul.Light))
			{
				if (l.getCreatureAttribute().equals(EnumCreatureAttribute.UNDEAD))
				{
					dmg += baseDamage * 0.15f + getLeveledDamage(soulLevel, baseDamage);
				}
			}
			if (this.equals(Soul.Normal))
			{
				if (l.getCreatureAttribute().equals(EnumCreatureAttribute.UNDEFINED))
				{
					dmg += baseDamage * 0.35 + getLeveledDamage(soulLevel, baseDamage);
				}
			}
			if (this.equals(Soul.Blood))
			{
				if (ph.consumeSoul(1))
				{
					if (l instanceof EntityPlayer)
					{
						dmg += baseDamage * 0.25 + getLeveledDamage(soulLevel, baseDamage);
					}
				}
			}
			if (this.equals(Soul.Lunar))
			{
				dmg += baseDamage * (soulLevel - 1) * (4 * l.worldObj.getCurrentMoonPhaseFactor()) / 100f;
			}
		}
		return dmg;
	}

	@Override
	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		if (ph.art)
		{
			if (this.equals(Soul.Plant))
			{
				if (MiscUtils.randWPercent(25 + 2 * (soulLevel - 1)))
					l.addPotionEffect(new PotionEffect(Potion.poison.id, 150, 2));
			}
		}
	}

	@Override
	public float getRangedDamage(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack)
	{
		return this.getMeleeDamage(ph, level, baseDamage, inAttack) - 0.1f * baseDamage;
	}

	@Override
	public float getSpecificRangedDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		return this.getSpecificMeleeDamage(ph, l, soulLevel, baseDamage);
	}
}
