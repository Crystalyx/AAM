package aam.common.items.weapon.anvil;

import aam.common.weapon.anvil.WeaponPart;
import net.minecraft.item.Item;

public class WeaponPartItem extends Item
{
	public WeaponPart pt;

	public WeaponPartItem(WeaponPart pt)
	{
		this.pt = pt;
		this.setUnlocalizedName(pt.name);
		this.setTextureName(pt.path);
	}
}
