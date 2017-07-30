package AAM.Utils;

import net.minecraft.util.DamageSource;

public class SoulDamageSource extends DamageSource
{

	public SoulDamageSource(PlayerDataHandler ph)
	{
		super(id);
		this.ph = ph;
		if (ph.sword.equals(SwordType.rapier))
		{
			this.setDamageBypassesArmor();
		}
	}

	public static final String id = "soulDamage";

	public PlayerDataHandler ph;

}
