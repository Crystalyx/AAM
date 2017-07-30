/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import GF.Registry.Registry;

public class ItemDust extends Item
{
	public int fullType;

	public ItemDust(int type)
	{
		this.fullType=type;
		this.setUnlocalizedName("dust"+Registry.names[type]);
		this.setCreativeTab(Registry.gftab);
		this.setTextureName("goldflushing:dust");
	}
	@Override
	public int getColorFromItemStack(ItemStack i, int p_82790_2_)
	{
		return Registry.colors[this.fullType-1];		
	}
}
