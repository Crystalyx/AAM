package AAM.Common.Tabs;

import AAM.Common.Items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TankTab extends CreativeTabs
{

	public TankTab()
	{
		super("aam.tank.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.spears.get(0);
	}

}
