package AAM.Common.Recipes;

import AAM.Common.Items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RainbowingSwordsRecipe implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting inv, World w)
	{
		for (int i = 0; i < 4; i++)
		{
			if (inv.getStackInSlot(i) != null)
			{
				if (inv.getStackInSlot(i).getItem() == ModItems.Potion)
				{
					continue;
				}
			} else
				return false;
		}

		for (int i = 5; i < 9; i++)
		{
			if (inv.getStackInSlot(i) != null)
			{
				if (inv.getStackInSlot(i).getItem() == ModItems.Potion)
				{
					continue;
				} else
					return false;
			} else
				return false;
		}

		if (inv.getStackInSlot(4) != null)
		{
			if (inv.getStackInSlot(4).getItem() == Items.diamond_sword)
			{
				return true;
			}
		}
		return false;

	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		return new ItemStack(ModItems.RainbowSword);
	}

	@Override
	public int getRecipeSize()
	{
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(ModItems.RainbowSword);
	}

}
