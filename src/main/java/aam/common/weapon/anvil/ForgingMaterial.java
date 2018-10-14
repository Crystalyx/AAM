package aam.common.weapon.anvil;

public class ForgingMaterial
{
	public int maxDamage = 0;
	public int enchantability = 14;
	public String icon;
	public String name;

	public ForgingMaterial(String name, String icon, int materialValue, int enchantability)
	{
		this.name = name;
		this.icon = icon;
		this.maxDamage = materialValue;
		this.enchantability = enchantability;
	}
}
