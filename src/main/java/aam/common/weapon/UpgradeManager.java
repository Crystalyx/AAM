package aam.common.weapon;

import aam.api.interfaces.IUpgradableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UpgradeManager
{
	public static IUpgradableItem translateToIUI(ItemStack is)
	{
		if (is != null)
		{
			Item item = is.getItem();
			if (item instanceof IUpgradableItem)
			{
				IUpgradableItem iUI = (IUpgradableItem) item;
				return iUI;
			}
		}
		return null;
	}

	public static int getUpgradeLevel(ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			return translateToIUI(is).getUpgradeLevel(is);
		}
		return 0;
	}

	public static void addUpgradeLevel(World w, ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			translateToIUI(is).addUpgradeLevel(w, is);
		}
	}

	public static void addUpgradeLevel(World w, ItemStack is, int level)
	{
		if (translateToIUI(is) != null)
		{
			translateToIUI(is).addUpgradeLevel(w, is, level);
		}
	}

	public static void setUpgradeLevel(World w, ItemStack is, int level)
	{
		if (translateToIUI(is) != null)
		{
			translateToIUI(is).setUpgradeLevel(w, is, level);
		}
	}

	public static int getMaxLevel(ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			return translateToIUI(is).getMaxLevel(is);
		}
		return 0;
	}

	public static boolean enableLayers(ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			return translateToIUI(is).enableLayers(is);
		}
		return false;
	}

	public static int getMinSlotCount(ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			return translateToIUI(is).getMinSlotCount(is);
		}
		return 0;
	}

	public static int getMaxSlotCount(ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			return translateToIUI(is).getMaxSlotCount(is);
		}
		return 0;
	}

	public static int getDurability(ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			return translateToIUI(is).getDurability(is);
		}
		return 0;
	}

	public static int getMaxRepairCount(ItemStack is)
	{
		if (translateToIUI(is) != null)
		{
			return translateToIUI(is).getMaxRepairCount(is);
		}
		return 0;
	}
}
