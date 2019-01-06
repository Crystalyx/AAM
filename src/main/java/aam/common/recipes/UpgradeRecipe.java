package aam.common.recipes;

import aam.api.AnvilRecipe;
import aam.api.interfaces.IUpgradableItem;
import aam.common.items.ModItems;
import aam.common.tiles.TEModificationAnvil;
import aam.utils.MathUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UpgradeRecipe extends AnvilRecipe
{

	@Override
	public boolean matches(TEModificationAnvil ic, World w)
	{
		boolean b = ic.getStackInSlot(0) != null;
		b = b && ic.getStackInSlot(1) != null;
		b = b && ic.getStackInSlot(2) != null;
		b = b && ic.getStackInSlot(3) == null;
		if (!b)
		{
			return false;
		}
		b = b && ic.getStackInSlot(0).getItem() instanceof IUpgradableItem;
		b = b && ic.getStackInSlot(1).getItem() == ModItems.ModificationCatalyst;
		if (!b)
		{
			return false;
		}
		IUpgradableItem ui = (IUpgradableItem) ic.getStackInSlot(0).getItem();
		int level = ui.getUpgradeLevel(ic.getStackInSlot(0));
		int maxLevel = ui.getMaxLevel(ic.getStackInSlot(0));
		int catLevel = ic.getStackInSlot(1).getItemDamage();
		int modLevel = MathUtils.isInLimit(catLevel, 0, 4) ? 1 : MathUtils.isInLimit(catLevel, 5, 9) ? 2 : catLevel >= 10 ? 3 : 0;
		if (ic.getStackInSlot(0).getItem() == ModItems.SoulSword)
		{
			String name = ic.getStackInSlot(0).getTagCompound().getString("Owner");
			if (ic.getWorldObj().getPlayerEntityByName(name) == null)
			{
				return false;
			}
		}
		b = b && level < maxLevel && (MathUtils.isInLimit(level, 0, 4) && modLevel == 1 || MathUtils.isInLimit(level, 5, 9) && modLevel == 2 || level >= 10 && modLevel == 3);
		return b;
	}

	@Override
	public ItemStack getCraftingResult(TEModificationAnvil ic)
	{
		return null;
	}

	@Override
	public void onCrafted(TEModificationAnvil ic)
	{
		IUpgradableItem ui = (IUpgradableItem) ic.getStackInSlot(0).getItem();
		int level = ui.getUpgradeLevel(ic.getStackInSlot(0));
		ui.addUpgradeLevel(ic.getWorldObj(), ic.getStackInSlot(0));
		ic.decrStackSize(1, 1);// catalyst
		ic.getStackInSlot(2).setItemDamage(ic.getStackInSlot(2).getItemDamage() + level + 1);
		ic.setInventorySlotContents(3, ic.getStackInSlot(0));
		ic.decrStackSize(0, 1);
	}

}
