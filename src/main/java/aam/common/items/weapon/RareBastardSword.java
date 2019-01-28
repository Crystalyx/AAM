package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import aam.utils.EnumRarity;

public class RareBastardSword extends MeleeWeapon
{
	public RareBastardSword()
	{
		super("aam.sword.bastard.rare",1,2);
		this.baseDamage = 34;
		this.rarity = EnumRarity.Rare;
		this.texture = "aam:sword/one_and_a_half_sword_rare";
		this.durability = 1200;
		this.repairs = 1;
		this.knockback = 2;
	}
}
