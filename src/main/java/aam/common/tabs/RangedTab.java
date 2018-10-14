package aam.common.tabs;

import aam.common.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class RangedTab extends CreativeTabs
{

	public RangedTab()
	{
		super("aam.ranged.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.ranged.get(0);
	}

}
