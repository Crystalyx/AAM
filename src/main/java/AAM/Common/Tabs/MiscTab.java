package aam.common.tabs;

import aam.common.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MiscTab extends CreativeTabs
{
	public MiscTab()
	{
		super("aam.misc.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.RiteBook;
	}

}
