package aam.common.weapon;

import aam.common.items.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class EnderInversionUpgrade extends WeaponUpgrade
{

	public EnderInversionUpgrade()
	{
		super("aam:enderinversion", ModItems.SoulUpgradeItem, 4, EnumChatFormatting.DARK_AQUA);
	}

	@Override
	public boolean onEnderTeleport(EntityPlayer p, EntityLivingBase ender)
	{
		return true;
	}

}
