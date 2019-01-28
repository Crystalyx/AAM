package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import aam.utils.EnumRarity;

public class UniqueBastardSword extends MeleeWeapon
{
	public UniqueBastardSword()
	{
		super("aam.sword.bastard.unique",3,4);
		this.baseDamage = 69;
		this.rarity = EnumRarity.Unique;
		this.texture = "aam:sword/one_and_a_half_sword_unique";
		this.durability = 700;
		this.repairs = 2;
		this.knockback = 0;
	}
}
