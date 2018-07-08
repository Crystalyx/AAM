package AAM.Common.Soul;

public enum WeaponType
{
	Broad("soulsword/excalibur_broad", "soulsword/arbitur_broad", "soulsword/swordpasses", 5, false, WarriorType.Carry), Rapier("soulsword/excalibur_rapier", "soulsword/arbitur_rapier", "soulsword/swordpasses", 4, true,
			WarriorType.Carry), Saber("soulsword/excalibur_saber", "soulsword/arbitur_saber", "soulsword/swordpasses", 5, false, WarriorType.Carry), Long("soulsword/excalibur_long", "soulsword/arbitur_long", "soulsword/swordpasses", 5,
					false, WarriorType.Carry), SolarStaff("soulsword/solar_staff", "soulsword/arbitur_solar_staff", "soulsword/staffpasses", 7, false,
							WarriorType.Caster), LightStaff("soulsword/light_staff", "soulsword/arbitur_light_staff", "soulsword/staffpasses", 4, true, WarriorType.Caster);

	public String texture;
	public String arbitur;
	public String passFolder;
	public float baseDamage;
	public boolean bypassesArmor;
	public WarriorType warrior = WarriorType.NotSelected;

	private WeaponType(String texture, String arbitur, String passFolder, float baseDamage, boolean bypassesArmor, WarriorType warrior)
	{
		this.texture = texture;
		this.arbitur = arbitur;
		this.passFolder = passFolder;
		this.baseDamage = baseDamage;
		this.bypassesArmor = bypassesArmor;
		this.warrior = warrior;

	}

}
