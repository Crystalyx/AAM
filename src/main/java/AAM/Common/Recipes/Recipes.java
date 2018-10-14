package aam.common.recipes;

import java.util.ArrayList;
import java.util.List;

import aam.api.AnvilRecipe;
import aam.common.blocks.building.ModBlocks;
import aam.common.blocks.plants.BerryBush;
import aam.common.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
	public static ShapedOreRecipe phials;
	public static ShapedOreRecipe cauldron;
	public static ShapelessOreRecipe sandy_glass;
	public static ShapedOreRecipe reinforced_glass;
	public static ShapelessOreRecipe[] crushed_berries = new ShapelessOreRecipe[5];

	public static AnvilRecipe modification;
	public static AnvilRecipe repair;
	public static AnvilRecipe infusion;
	public static AnvilRecipe upgrade;
	public static AnvilRecipe philoMerge;
	public static AnvilRecipe partForming;
	public static AnvilRecipe materialMerge;

	public static List<AnvilRecipe> anvilRecipes = new ArrayList<>();

	public static void load()
	{
		modification = new ModificationRecipe();
		repair = new RepairRecipe();
		infusion = new InfusionRecipe();
		upgrade = new UpgradeRecipe();
		philoMerge = new PhiloMerge();
		partForming = new ToolPartFormingRecipe();
		materialMerge = new MaterialMergeRecipe();

		anvilRecipes.add(modification);
		anvilRecipes.add(repair);
		anvilRecipes.add(infusion);
		anvilRecipes.add(upgrade);
		anvilRecipes.add(philoMerge);
		anvilRecipes.add(materialMerge);
		anvilRecipes.add(partForming);

		phials = new ShapedOreRecipe(new ItemStack(ModItems.materials, 3, 3), "gpg", "aga", 'g', ModBlocks.ReinforcedGlass, 'p', Items.paper);
		GameRegistry.addRecipe(phials);
		cauldron = new ShapedOreRecipe(new ItemStack(ModBlocks.Cauldron, 1), "iai", "ici", "iii", 'i', Items.iron_ingot, 'c', Items.cauldron);
		GameRegistry.addRecipe(cauldron);
		sandy_glass = new ShapelessOreRecipe(new ItemStack(ModBlocks.SandyGlass, 2, 0), Blocks.sand, Blocks.glass);
		GameRegistry.addRecipe(sandy_glass);
		GameRegistry.addSmelting(ModBlocks.SandyGlass, new ItemStack(ModBlocks.ReinforcedGlass), 0.4F);

		for (int i = 0; i < crushed_berries.length; i++)
		{
			OreDictionary.registerOre(BerryBush.names[i], new ItemStack(ModItems.Berry, 1, i));
			crushed_berries[i] = new ShapelessOreRecipe(new ItemStack(ModItems.BerryDust, 1, i), BerryBush.names[i], ModItems.MortarAndPestle);
			GameRegistry.addRecipe(crushed_berries[i]);
		}

	}
}
