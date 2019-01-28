package aam.common.items.sheaths;

import aam.api.abstraction.Sheath;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class UncommonBastardSheath extends Sheath
{
	public UncommonBastardSheath()
	{
		this.setTextureName("aam:sheaths/one_and_a_half_sword_uncommon");
		this.setUnlocalizedName("aam.sheath.bastard.uncommon");
	}
	@Override
	public EnumRarity getRarity(ItemStack _itemStack)
	{
		return EnumRarity.uncommon;
	}
}
