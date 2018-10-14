package aam.common.recipes;

import aam.api.AnvilRecipe;
import aam.common.items.ModItems;
import aam.common.items.weapon.anvil.ForgingMaterialItem;
import aam.common.tiles.TEModificationAnvil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MaterialMergeRecipe extends AnvilRecipe
{

	@Override
	public boolean matches(TEModificationAnvil ic, World w)
	{
		boolean b = ic.getStackInSlot(0) != null;
		b = b && ic.getStackInSlot(1) != null;
		b = b && ic.getStackInSlot(2) != null;
		b = b && ic.getStackInSlot(3) == null;
		b = b && ic.getStackInSlot(4) != null;
		if (!b)
		{
			return false;
		}
		b = b && ic.getStackInSlot(0).getItem() instanceof ForgingMaterialItem;
		b = b && ic.getStackInSlot(0).getItemDamage() > 0;
		b = b && ic.getStackInSlot(4).getItem() instanceof ForgingMaterialItem;
		b = b && ic.getStackInSlot(0).getItem() == ic.getStackInSlot(4).getItem();
		b = b && ic.getStackInSlot(1).getItem() == ModItems.flux;
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
		ForgingMaterialItem fi1 = (ForgingMaterialItem) ic.getStackInSlot(0).getItem();
		ForgingMaterialItem fi2 = (ForgingMaterialItem) ic.getStackInSlot(0).getItem();

		int maxValue = fi1.material.maxDamage;
		int fcount = fi1.material.maxDamage - ic.getStackInSlot(0).getItemDamage();
		int scount = fi2.material.maxDamage - ic.getStackInSlot(4).getItemDamage();

		ic.decrStackSize(1, (int) Math.round(1 + fcount / 100d + scount / 100d));// flux
		ic.getStackInSlot(2).setItemDamage(ic.getStackInSlot(2).getItemDamage() + 10);
		ItemStack newMat = ic.getStackInSlot(0);
		int excess = fcount + scount - maxValue;
		if (excess > 0)
		{
			ic.decrStackSize(0, 1);
			newMat.setItemDamage(0);
			ic.getStackInSlot(4).setItemDamage(maxValue - excess);
			ic.setInventorySlotContents(3, newMat);
		}
		else
		{
			newMat.setItemDamage(maxValue - (fcount + scount));
			ic.decrStackSize(0, 1);
			ic.setInventorySlotContents(3, newMat);
			ic.decrStackSize(4, 1);
		}
	}
}
