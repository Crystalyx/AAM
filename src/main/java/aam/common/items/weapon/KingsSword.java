package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import aam.utils.MiscUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class KingsSword extends MeleeWeapon
{

	public KingsSword()
	{
		super("aam.item.sword.king.broad", 2, 6);
		this.setBaseDmg(126);
		this.setBypassesArmor(false);
		this.setRarity(EnumRarity.epic);
		this.setTexture("aam:sword/kings_sword");
		this.setDurability(24000);
		this.setRepairs(-1);
		this.setRepairItem(null, 0);
		this.setKnockback(10);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		p.setItemInUse(is, Integer.MAX_VALUE);
		return is;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is)
	{
		return EnumAction.block;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer p, int count)
	{
		MiscUtils.retractEntitiesFrom(p.worldObj, new Wec3(p), 4, Math.min(count / 15, 5) / 10f, p);
	}

}
