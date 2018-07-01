package AAM.Common.Recipes;

import java.util.Hashtable;

import AAM.Common.Tiles.TEModificationAnvil;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Lord_Crystalyx
 */
public class ModificationAnvilRecipes
{
	public static Hashtable<Integer, ModificationAnvilRecipe> rlist = new Hashtable<Integer, ModificationAnvilRecipe>();
	public static int id = 0;

	public static void registerRecipes()
	{
		// TODO
	}

	public static void registerRecipe(ModificationAnvilRecipe rec)
	{
		rlist.put(id, rec);
		id++;
	}

	public static void registerRecipe(ItemStack[] ing, ItemStack out, int DUReq)
	{
		registerRecipe(new ModificationAnvilRecipe(ing, out, DUReq));
	}

	public static ModificationAnvilRecipe findRecipeFor(TEModificationAnvil ing)
	{
		for (int l = 0; l < rlist.size(); l++)
		{
			ModificationAnvilRecipe ites = rlist.get(l);
			if (ites.matches((IInventory) ing, ing.getWorldObj()))
			{
				return rlist.get(l);
			}
		}
		return null;
	}
}
