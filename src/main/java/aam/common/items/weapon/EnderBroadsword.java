package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import aam.utils.EnumRarity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;

public class EnderBroadsword extends MeleeWeapon
{

	public EnderBroadsword()
	{
		super("aam.sword.ender.broad", 1, 3);
		this.baseDamage = 60;
		this.rarity = EnumRarity.Epic;
		this.texture = "aam:sword/ender_broad";
		this.durability = 1800;
		this.repairItem = Items.ender_eye;
	}

	@Override
	public boolean onEnderTeleport(EntityPlayer p, EntityLivingBase ender)
	{
		p.heal(p.getMaxHealth() / 8);
		return true;
	}

}
