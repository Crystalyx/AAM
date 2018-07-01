package AAM.Common.Tabs;

import AAM.Common.Items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AlchemyTab extends CreativeTabs
{

	public AlchemyTab()
	{
		super("aam.alchemy.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.MiniumStone;
	}

}
