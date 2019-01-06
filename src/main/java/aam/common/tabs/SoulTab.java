package aam.common.tabs;

import aam.common.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SoulTab extends CreativeTabs
{

	public SoulTab()
	{
		super("aam.soul.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.AnvilHammer;
	}
}
