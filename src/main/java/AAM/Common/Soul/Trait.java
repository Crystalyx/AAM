package AAM.Common.Soul;

public enum Trait
{
	Soul(100), Level(1), MeleeDamage(5), RangedDamage(5), MentalHealth(100), WeaponLevel(0), Cooldown(3);

	public int base = 0;

	private Trait(int base)
	{
		this.base = base;
	}

	public void setBase(int base)
	{
		this.base = base;
	}
}
