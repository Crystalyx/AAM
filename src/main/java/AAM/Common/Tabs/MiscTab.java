package AAM.Common.Tabs;

import AAM.Common.Items.ModItems;
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
