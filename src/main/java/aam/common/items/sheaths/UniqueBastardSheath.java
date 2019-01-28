package aam.common.items.sheaths;

import aam.api.abstraction.Sheath;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class UniqueBastardSheath extends Sheath
{
	public UniqueBastardSheath()
	{
		this.setTextureName("aam:sheaths/one_and_a_half_sword_unique");
		this.setUnlocalizedName("aam.sheath.bastard.unique");
	}

	@Override
	public EnumRarity getRarity(ItemStack _itemStack)
	{
		return EnumRarity.epic;
	}
}
