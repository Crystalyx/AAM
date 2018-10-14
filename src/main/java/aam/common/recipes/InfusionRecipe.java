package aam.common.recipes;

import aam.api.AnvilRecipe;
import aam.api.GameWeapon;
import aam.common.items.ModItems;
import aam.common.tiles.TEModificationAnvil;
import aam.common.weapon.WeaponManager;
import aam.utils.MathUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InfusionRecipe extends AnvilRecipe
{

	@Override
	public boolean matches(TEModificationAnvil ic, World w)
	{
		boolean b = ic.getStackInSlot(4) != null;
		b = b && ic.getStackInSlot(2) != null;
		b = b && ic.getStackInSlot(1) != null;
		b = b && ic.getStackInSlot(0) != null;
		if (!b)
		{
			return false;
		}
		b = b && ic.getStackInSlot(1).getItem() == ModItems.mediumConcentrate;
		b = b && ic.getStackInSlot(1).getItemDamage() == 3;
		b = b && ic.getStackInSlot(2).getItem() == ModItems.AnvilHammer;
		b = b && ic.getStackInSlot(3) == null;
		b = b && ic.getStackInSlot(0).getItem() instanceof GameWeapon;
		b = b && ic.getStackInSlot(4).getItem() instanceof GameWeapon;
		if (!b)
		{
			return false;
		}
		GameWeapon base = (GameWeapon) ic.getStackInSlot(0).getItem();
		GameWeapon upg = (GameWeapon) ic.getStackInSlot(4).getItem();
		int baseLevel = base.getUpgradeLevel(ic.getStackInSlot(0));
		int baseRarity = base.rarity.ordinal();
		int upgLevel = upg.getUpgradeLevel(ic.getStackInSlot(4));
		int upgRarity = upg.rarity.ordinal();
		int upgValue = upgRarity - baseRarity + upgLevel / 2 + baseLevel;
		if (upgLevel == 0 && baseLevel == 0 && base == upg)
		{
			upgValue += 1;
		}
		b = b && baseLevel < base.getMaxLevel(ic.getStackInSlot(0));
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
		GameWeapon base = (GameWeapon) ic.getStackInSlot(0).getItem();
		GameWeapon upg = (GameWeapon) ic.getStackInSlot(4).getItem();
		int baseLevel = base.getUpgradeLevel(ic.getStackInSlot(0));
		int baseRarity = base.rarity.ordinal();
		int upgLevel = upg.getUpgradeLevel(ic.getStackInSlot(4));
		int upgRarity = upg.rarity.ordinal();
		int upgValue = upgRarity - baseRarity + upgLevel / 2 + baseLevel;
		if (upgLevel <= 1 && baseLevel <= 1 && base == upg)
		{
			upgValue += 1;
		}
		upgValue = MathUtils.limit(upgValue, 0, base.getMaxLevel(ic.getStackInSlot(0)));
		base.setUpgradeLevel(ic.getWorldObj(), ic.getStackInSlot(0), upgValue);

		if (base.repairItem == upg.repairItem && base.meta == upg.meta)
		{
			WeaponManager.repair(ic.getStackInSlot(0));
		}

		ic.setInventorySlotContents(3, ic.getStackInSlot(0));
		ic.decrStackSize(4, 1);
		ic.decrStackSize(0, 1);
		ic.decrStackSize(1, 1);
		int metadata = ic.getStackInSlot(2).getItemDamage() + 10;
		ic.getStackInSlot(2).setItemDamage(metadata);
	}

}
