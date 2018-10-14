package aam.common.weapon.anvil;

import net.minecraft.item.EnumRarity;

public class WeaponPart
{
	public String name;
	public PartType type;
	public String path;
	public String pass;
	public float damage = 0;
	public int durability = 0;
	public int repairs = 1;
	public int slots = 1;
	public boolean bypassesArmor = false;
	public EnumRarity rarity = EnumRarity.common;
	public int soulConsumed = 0;
	public int id = 0;
	public int materialCost = 0;
	public ForgingMaterial material = null;

	public WeaponPart(int id, String name, PartType type, String path, String pass, boolean bypassesArmor, ForgingMaterial fm, int materialCost)
	{
		this.name = name;
		this.type = type;
		this.pass = pass;
		this.path = path;
		this.bypassesArmor = bypassesArmor;
		this.id = id;
		this.materialCost = materialCost;
		this.material = fm;
	}

	public WeaponPart(int id, String name, PartType type, String path, String pass, boolean bypassesArmor)
	{
		this.name = name;
		this.type = type;
		this.pass = pass;
		this.path = path;
		this.bypassesArmor = bypassesArmor;
		this.id = id;
	}

	public WeaponPart setSoulConsumed(int soulConsumed)
	{
		this.soulConsumed = soulConsumed;
		return this;
	}

	public WeaponPart setRarity(EnumRarity rarity)
	{
		this.rarity = rarity;
		return this;
	}

	public WeaponPart setAttack(float attack)
	{
		this.damage = attack;
		return this;
	}

	public WeaponPart setDurability(int durability)
	{
		this.durability = durability;
		return this;
	}

	public WeaponPart setRepairs(int repairs)
	{
		this.repairs = repairs;
		return this;
	}

	public WeaponPart setSlots(int slots)
	{
		this.slots = slots;
		return this;
	}

}
