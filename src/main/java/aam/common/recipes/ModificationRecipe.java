package aam.common.recipes;

import aam.api.AnvilRecipe;
import aam.api.Interface.IUpgradableItem;
import aam.common.items.AnvilHammer;
import aam.common.items.ModItems;
import aam.common.tiles.TEModificationAnvil;
import aam.common.weapon.WeaponManager;
import aam.common.weapon.WeaponUpgrades;
import aam.utils.Pair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModificationRecipe extends AnvilRecipe
{

	@Override
	public boolean matches(TEModificationAnvil ic, World w)
	{
		ItemStack upg = ic.getStackInSlot(4);
		boolean b = upg != null;
		b = b && ic.getStackInSlot(2) != null;
		b = b && ic.getStackInSlot(1) != null;
		b = b && ic.getStackInSlot(0) != null;
		if (!b)
		{
			return false;
		}
		Pair<Item, Integer> pair = new Pair(upg.getItem(), upg.getItemDamage());
		b = b && WeaponUpgrades.upgrades.stream().anyMatch(wu -> wu.item == pair.key && wu.meta == pair.value);
		b = b && ic.getStackInSlot(0).getItem() instanceof IUpgradableItem;
		b = b && ic.getStackInSlot(1).getItem() == ModItems.ModificationCatalyst;
		b = b && ic.getStackInSlot(1).getItemDamage() < 5;
		b = b && ic.getStackInSlot(2).getItem() == ModItems.AnvilHammer;
		b = b && ic.getStackInSlot(3) == null;
		b = b && WeaponManager.getEmptySlotCount(ic.getStackInSlot(0)) > 0;
		return b;
	}

	@Override
	public ItemStack getCraftingResult(TEModificationAnvil ic)
	{
		ItemStack weapon = ic.getStackInSlot(0);
		ItemStack upg = ic.getStackInSlot(4);

		WeaponManager.addUpgrade(weapon, WeaponUpgrades.getUpgrade(upg.getItem(), upg.getItemDamage()));

		return weapon;
	}

	@Override
	public void onCrafted(TEModificationAnvil ic)
	{
		ic.setInventorySlotContents(3, this.getCraftingResult(ic));
		ic.decrStackSize(4, 1);
		ic.decrStackSize(0, 1);
		ic.decrStackSize(1, 1);
		int metadata = ic.getStackInSlot(2).getItemDamage() + Math.max(0, 10 - ((AnvilHammer) ModItems.AnvilHammer).getUpgradeLevel(ic.getStackInSlot(2)));
		if (metadata >= 450)
		{
			ic.setInventorySlotContents(2, null);
		}
	}
}
