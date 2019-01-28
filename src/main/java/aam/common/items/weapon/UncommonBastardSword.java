package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import aam.utils.EnumRarity;

public class UncommonBastardSword extends MeleeWeapon
{
	public UncommonBastardSword()
	{
		super("aam.sword.bastard.uncommon",1,2);
		this.baseDamage = 27;
		this.rarity = EnumRarity.Uncommon;
		this.texture = "aam:sword/one_and_a_half_sword_uncommon";
		this.durability = 700;
		this.repairs = 2;
		this.knockback = 0;
	}
}
