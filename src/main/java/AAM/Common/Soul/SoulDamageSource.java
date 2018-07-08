package AAM.Common.Soul;

import AAM.Utils.PlayerDataHandler;
import net.minecraft.util.EntityDamageSource;

public class SoulDamageSource extends EntityDamageSource
{

	public SoulDamageSource(PlayerDataHandler ph)
	{
		super(id, ph.player);
		this.ph = ph;
		if (ph.sword.equals(WeaponType.Rapier))
		{
			this.setDamageBypassesArmor();
		}
	}

	public static final String id = "soulDamage";

	public PlayerDataHandler ph;

}
