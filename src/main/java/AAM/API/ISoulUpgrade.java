package AAM.API;

import AAM.Utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;

public interface ISoulUpgrade
{
	public float getMeleeDamage(PlayerDataHandler ph, int soulLevel, float baseDamage, boolean inAttack);

	public float getSpecificMeleeDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage);

	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage);

	public float getRangedDamage(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack);

	public float getSpecificRangedDamage(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage);

}
