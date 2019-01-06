package aam.common.tabs;

import aam.common.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ArtTab extends CreativeTabs
{

	public ArtTab()
	{
		super("aam.art.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.WatchOfTime;
	}

}
