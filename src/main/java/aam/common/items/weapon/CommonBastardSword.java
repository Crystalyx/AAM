package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import aam.utils.EnumRarity;

public class CommonBastardSword extends MeleeWeapon
{
	public CommonBastardSword()
	{
		super("aam.sword.bastard.common",0,1);
		this.baseDamage = 17;
		this.rarity = EnumRarity.Common;
		this.texture = "aam:sword/one_and_a_half_sword_common";
		this.durability = 300;
		this.repairs = 4;
		this.knockback = 0;
	}
}
