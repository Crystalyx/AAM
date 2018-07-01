package AAM.Common.Tabs;

import AAM.Common.Items.ModItems;
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
