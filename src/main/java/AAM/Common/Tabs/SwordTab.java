package AAM.Common.Tabs;

import AAM.Common.Items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SwordTab extends CreativeTabs
{

	public SwordTab()
	{
		super("aam.sword.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.swords.get(0);
	}

}
