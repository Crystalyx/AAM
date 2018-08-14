package AAM.API;

import AAM.API.Interface.IUpgradableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GameWeapon extends ItemSword implements IUpgradableItem
{

	public GameWeapon(ToolMaterial mat)
	{
		super(mat);
	}

	@Override
	public int getUpgradeLevel(ItemStack is)
	{
		return is.hasTagCompound() ? is.getTagCompound().getInteger("UpgradeLevel") : 0;
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is)
	{
		this.setUpgradeLevel(w, is, this.getUpgradeLevel(is) + 1);
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is, int level)
	{
		this.setUpgradeLevel(w, is, this.getUpgradeLevel(is) + level);
	}

	@Override
	public void setUpgradeLevel(World w, ItemStack is, int level)
	{
		if (is.hasTagCompound())
		{
			is.getTagCompound().setInteger("UpgradeLevel", level);
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("UpgradeLevel", level);
			is.setTagCompound(tag);
		}
	}

	@Override
	public int getMaxLevel(ItemStack is)
	{
		return 15;
	}

	@Override
	public boolean enableLayers(ItemStack is)
	{
		return true;
	}

}
