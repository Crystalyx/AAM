package AAM.Common.Soul;

import AAM.Utils.PlayerDataHandler;
import AAM.Utils.SwordType;
import net.minecraft.util.DamageSource;

public class SoulDamageSource extends DamageSource
{

	public SoulDamageSource(PlayerDataHandler ph)
	{
		super(id);
		this.ph = ph;
		if (ph.sword.equals(SwordType.Rapier))
		{
			this.setDamageBypassesArmor();
		}
	}

	public static final String id = "soulDamage";

	public PlayerDataHandler ph;

}
