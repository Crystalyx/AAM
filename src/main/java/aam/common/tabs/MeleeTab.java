package aam.common.tabs;

import aam.common.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MeleeTab extends CreativeTabs
{

	public MeleeTab()
	{
		super("aam.melee.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.melee.get(0);
	}

}
