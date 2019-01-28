package aam.common.items.weapon;

import aam.api.abstraction.MeleeWeapon;
import aam.utils.EnumRarity;
import aam.utils.MiscUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class KingsSword extends MeleeWeapon
{

	public KingsSword()
	{
		super("aam.sword.king.broad", 2, 6);
		this.baseDamage = 126;
		this.rarity = EnumRarity.Legendary;
		this.texture = "aam:sword/kings_sword";
		this.durability = 6000;
		this.repairs = -1;
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
