package AAM.API;

import net.minecraft.item.ItemStack;

public interface IUpgradableItem
{
	public int getUpgradeLevel(ItemStack is);

	public void addUpgradeLevel(ItemStack is);

	public void addUpgradeLevel(ItemStack is, int level);

	public void setUpgradeLevel(ItemStack is, int level);

	public int getMaxLevel(ItemStack is);

	public boolean enableLayers(ItemStack is);
}
