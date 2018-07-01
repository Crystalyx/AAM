package AAM.Common.Items.Resources;

import net.minecraft.item.ItemStack;

public class Coin extends Material
{
	public Coin()
	{
		this.maxStackSize = 100;
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return "aam.coin_" + is.getItemDamage();
	}
}
