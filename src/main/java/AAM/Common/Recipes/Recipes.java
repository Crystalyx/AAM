package AAM.Common.Recipes;

import AAM.Common.Blocks.ModBlocks;
import AAM.Common.Items.ModItems;
import AAM.Utils.Soul;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
	public static void load()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.materials, 3, 3), new Object[]
		{ "gpg", "aga", 'g', Blocks.glass, 'p', Items.paper });
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.Cauldron, 1), new Object[]
		{ "iai", "ici", "iii", 'i', Items.iron_ingot, 'c', Items.cauldron });
		
	}
}
