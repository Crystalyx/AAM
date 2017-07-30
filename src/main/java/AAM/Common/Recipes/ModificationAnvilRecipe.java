package AAM.Common.Recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class ModificationAnvilRecipe implements IRecipe
{
	public ItemStack[] ings;
	public ItemStack output;
	public int energy;

	public ModificationAnvilRecipe(ItemStack[] ing, ItemStack out, int DUReq)
	{
		this.ings = ing;
		this.output = out;
		this.energy = DUReq;
	}

	public boolean matches(IInventory inv, World w)
	{
		boolean ret = true;
		if (inv.getStackInSlot(0) != null)
		{
			if (inv.getStackInSlot(0).isItemEqual(this.ings[0]))
			{
				for (int i = 1; i < this.ings.length; i++)
				{
					if (inv.getStackInSlot(i) != null)
					{
						if (!inv.getStackInSlot(i).isItemEqual(this.ings[i]))
						{
							ret = false;
						}
					} else
					{
						if (this.ings[i] != null)
						{
							ret = false;
						}
					}

				}

			} else
			{
				ret = false;
			}
		} else
		{
			ret = false;
		}
		return ret;
	}

	public boolean matches(ModificationAnvilRecipe rec)
	{
		boolean ret = true;
		for (int i = 1; i < this.ings.length; i++)
		{
			if (rec.ings[i] != null)
			{
				if (!rec.ings[i].isItemEqual(this.ings[i]))
				{
					ret = false;
				}
			} else
				ret = false;

		}
		return ret;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		return this.output;
	}

	@Override
	public int getRecipeSize()
	{
		return this.ings.length;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return this.output;
	}

	@Override
	public boolean matches(InventoryCrafting i, World w)
	{
		return matches(i, w);
	}

}
