package AAM.Common.Tabs;

import AAM.Common.Items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StaffTab extends CreativeTabs
{

	public StaffTab()
	{
		super("aam.staff.tab");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.staffs.get(0);
	}

}
