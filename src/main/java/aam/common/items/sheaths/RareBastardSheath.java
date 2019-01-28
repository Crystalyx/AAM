package aam.common.items.sheaths;

import aam.api.abstraction.Sheath;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class RareBastardSheath extends Sheath
{
	public RareBastardSheath()
	{
		this.setTextureName("aam:sheaths/one_and_a_half_sword_rare");
		this.setUnlocalizedName("aam.sheath.bastard.rare");
	}
	@Override
	public EnumRarity getRarity(ItemStack _itemStack)
	{
		return EnumRarity.rare;
	}
}
