package aam.common.weapon;

import aam.common.items.ModItems;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class SoulVampirismUpgrade extends WeaponUpgrade
{

	public SoulVampirismUpgrade()
	{
		super("aam:soulVampirism", ModItems.bigConcentrate, 1, EnumChatFormatting.BLUE);
	}

	@Override
	public void onAttack(EntityPlayer p, EntityLivingBase e, float damage)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		ph.addSoul((int) (damage / 10f));
	}
}
