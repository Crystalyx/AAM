package aam.common.items.alchemy;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class MiniumStone extends Item
{
	public MiniumStone()
	{
		this.setMaxDamage(64);
		this.setTextureName("aam:tools/minium_stone");
		this.setMaxStackSize(1);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean sneak)
	{
		if (i.hasTagCompound())
		{
			int left = i.getTagCompound().getInteger("TrLast");
			l.add(EnumChatFormatting.AQUA + "" + left + " Pseudo Souls");
		}
	}
}
