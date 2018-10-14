package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;

public class EnderBroadsword extends MeleeWeapon
{

	public EnderBroadsword()
	{
		super("aam.item.sword.ender.broad", 1, 3);
		this.setBaseDmg(60);
		this.setBypassesArmor(false);
		this.setRarity(EnumRarity.epic);
		this.setTexture("aam:sword/ender_broad");
		this.setDurability(2400);
		this.setRepairs(3);
		this.setRepairItem(Items.ender_eye, 0);
	}

	@Override
	public boolean onEnderTeleport(EntityPlayer p, EntityLivingBase ender)
	{
		p.heal(p.getMaxHealth() / 8);
		return true;
	}

}
