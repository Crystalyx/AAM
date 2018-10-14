package aam.common.soul;

public enum SoulWeaponType
{
	Broad("soulsword/excalibur_broad", "soulsword/arbitur_broad", 0, 5, false, WarriorType.Carry), Rapier("soulsword/excalibur_rapier", "soulsword/arbitur_rapier", 0, 4, true, WarriorType.Carry), Saber("soulsword/excalibur_saber",
			"soulsword/arbitur_saber", 0, 5, false, WarriorType.Carry), Long("soulsword/excalibur_long", "soulsword/arbitur_long", 0, 5, false, WarriorType.Carry), SolarStaff("soulsword/solar_staff", "soulsword/arbitur_solar_staff", 1, 7,
					false, WarriorType.Caster), LightStaff("soulsword/light_staff", "soulsword/arbitur_light_staff", 1, 4, true,
							WarriorType.Caster), Spear("soulsword/spear", "soulsword/arbitur_spear", 1, 6, false, WarriorType.Tank), Hammer("soulsword/hammer", "soulsword/arbitur_hammer", 2, 7, false, WarriorType.Tank);

	public String texture;
	public String arbitur;
	public String passFolder;
	public float baseDamage;
	public boolean bypassesArmor;
	public WarriorType warrior = WarriorType.NotSelected;

	private SoulWeaponType(String texture, String arbitur, int pass, float baseDamage, boolean bypassesArmor, WarriorType warrior)
	{
		String[] passes = new String[] { "soulsword/swordpasses", "soulsword/staffpasses", "soulsword/tankpasses" };
		this.texture = texture;
		this.arbitur = arbitur;
		this.passFolder = passes[pass];
		this.baseDamage = baseDamage;
		this.bypassesArmor = bypassesArmor;
		this.warrior = warrior;

	}

}
