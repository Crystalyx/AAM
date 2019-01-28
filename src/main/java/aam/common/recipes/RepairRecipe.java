package aam.common.recipes;

import aam.api.AnvilRecipe;
import aam.api.abstraction.GameWeapon;
import aam.common.items.ModItems;
import aam.common.tiles.TEModificationAnvil;
import aam.common.weapon.WeaponManager;
import aam.utils.MathUtils;
import aam.utils.Pair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RepairRecipe extends AnvilRecipe
{

	@Override
	public boolean matches(TEModificationAnvil ic, World w)
	{
		ItemStack upg = ic.getStackInSlot(4);
		boolean b = upg != null;
		b = b && ic.getStackInSlot(0) != null;
		b = b && ic.getStackInSlot(1) != null;
		b = b && ic.getStackInSlot(2) != null;
		if (!b)
		{
			return false;
		}
		b = b && ic.getStackInSlot(0).getItem() instanceof GameWeapon;
		if (!b)
		{
			return false;
		}
		b = b && ic.getStackInSlot(1).getItem() == ModItems.smallConcentrate;
		b = b && ic.getStackInSlot(1).getItemDamage() == 3;
		b = b && ic.getStackInSlot(2).getItem() == ModItems.AnvilHammer;
		b = b && ic.getStackInSlot(3) == null;
		if (!b)
		{
			return false;
		}
		GameWeapon gw = (GameWeapon) ic.getStackInSlot(0).getItem();
		Pair<Item, Integer> upgPair = new Pair(upg.getItem(), upg.getItemDamage());
		Pair<Item, Integer> repPair = new Pair(gw.repairItem, gw.repairItemMeta);
		if (upgPair.equals(repPair))
		{
			return true;
		}
		else
		{
			int level = gw.getUpgradeLevel(ic.getStackInSlot(0));
			int catLevel = ic.getStackInSlot(4).getItemDamage();
			int modLevel = MathUtils.isInLimit(catLevel, 0, 4) ? 1 : MathUtils.isInLimit(catLevel, 5, 9) ? 2 : catLevel >= 10 ? 3 : 0;
			return MathUtils.isInLimit(level, 0, 4) && modLevel == 1 || MathUtils.isInLimit(level, 5, 9) && modLevel == 2 || level >= 10 && modLevel == 3;
		}
	}

	@Override
	public ItemStack getCraftingResult(TEModificationAnvil ic)
	{
		ItemStack weapon = ic.getStackInSlot(0);
		WeaponManager.repair(weapon);
		return weapon;
	}

	@Override
	public void onCrafted(TEModificationAnvil ic)
	{
		ic.setInventorySlotContents(3, this.getCraftingResult(ic));
		ic.decrStackSize(4, 1);
		ic.decrStackSize(0, 1);
		ic.decrStackSize(1, 1);
		int metadata = ic.getStackInSlot(2).getItemDamage() + 10;
		ic.getStackInSlot(2).setItemDamage(metadata);
	}

}
