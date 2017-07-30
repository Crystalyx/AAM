/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GFTab extends CreativeTabs {


	public GFTab() {
		super("gftab");
	}

	@Override
	public Item getTabIconItem() {
		return Items.apple;
	}

}
