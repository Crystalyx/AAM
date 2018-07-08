package AAM.API;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class GameWeapon extends ItemSword implements IUpgradableItem
{

	public GameWeapon(ToolMaterial mat)
	{
		super(mat);
	}

	@Override
	public int getUpgradeLevel(ItemStack is)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addUpgradeLevel(ItemStack is)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addUpgradeLevel(ItemStack is, int level)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpgradeLevel(ItemStack is, int level)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxLevel(ItemStack is)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean enableLayers(ItemStack is)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
