package aam.common.recipes;

import java.util.ArrayList;
import java.util.List;

import aam.api.AnvilRecipe;
import aam.common.items.ModItems;
import aam.common.items.weapon.anvil.ForgingMaterialItem;
import aam.common.tiles.TEModificationAnvil;
import aam.common.weapon.anvil.AnvilRegistry;
import aam.common.weapon.anvil.WeaponPart;
import aam.utils.MathUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ToolPartFormingRecipe extends AnvilRecipe
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
		b = b && ic.getStackInSlot(0).getItem() instanceof ForgingMaterialItem;
		b = b && ic.getStackInSlot(1).getItem() == ModItems.flux;
		if (!b)
		{
			return false;
		}
		ForgingMaterialItem fi = (ForgingMaterialItem) ic.getStackInSlot(0).getItem();
		List<WeaponPart> parts = new ArrayList();
		b = b && AnvilRegistry.matTable.get(fi.material) != null;
		if (!b)
		{
			return false;
		}
		for (WeaponPart wps : AnvilRegistry.matTable.get(fi.material))
		{
			if (wps.materialCost <= fi.material.maxDamage - ic.getStackInSlot(0).getItemDamage())
			{
				parts.add(wps);
			}
		}
		b = b && !parts.isEmpty();
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
		ForgingMaterialItem fi = (ForgingMaterialItem) ic.getStackInSlot(0).getItem();

		List<WeaponPart> parts = new ArrayList();
		for (WeaponPart wps : AnvilRegistry.matTable.get(fi.material))
		{
			if (wps.materialCost <= fi.material.maxDamage - ic.getStackInSlot(0).getItemDamage())
			{
				parts.add(wps);
			}
		}

		WeaponPart wp = parts.get(MathUtils.r.nextInt(parts.size()));

		int matCount = wp.materialCost;

		ic.decrStackSize(1, (int) Math.round(1 + matCount / 100d));// flux
		ic.getStackInSlot(2).setItemDamage(ic.getStackInSlot(2).getItemDamage() + 10);
		ic.setInventorySlotContents(3, new ItemStack(ModItems.parts.get(wp.id)));
		if (matCount == fi.material.maxDamage - ic.getStackInSlot(0).getItemDamage())
			ic.decrStackSize(0, 1);
		else
			ic.getStackInSlot(0).setItemDamage(ic.getStackInSlot(0).getItemDamage() + matCount);
	}

}
