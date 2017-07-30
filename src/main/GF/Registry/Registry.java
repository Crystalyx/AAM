/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Registry;

import GF.Common.Blocks.BlockOreSand;
import GF.Common.Blocks.SOWPBlock;
import GF.Common.Blocks.TimeAcceleratorBlock;
import GF.Common.Item.ItemDebugGenerator;
import GF.Common.Item.ItemDust;
import GF.Common.Item.ItemGoldPan;
import GF.Common.Item.SandOreBucket;
import GF.Common.Tile.SOWPTile;
import GF.Common.Tile.TileEntityAccelerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Registry
{
	public static String[] names = new String[]
	{ "Empty", "Iron", "Gold", "Tin", "Copper", "Lead", "Nickel", "Platinum", "Aluminium", "Osmium" };
	public static int[] colors = new int[]
	{ 0xa8a8a8, 0xffc134, 0xd1d1d1, 0xff7a00, 0x547790, 0xffde64, 0x5fcfff, 0xdedede, 0x0088ef };
	public static CreativeTabs gftab = new GFTab();
	public static Block[] sand = new BlockOreSand[]
	{ new BlockOreSand(1), new BlockOreSand(2), new BlockOreSand(3), new BlockOreSand(4), new BlockOreSand(5), new BlockOreSand(6), new BlockOreSand(7), new BlockOreSand(8), new BlockOreSand(9) };
	public static Item[] goldpanfull = new ItemGoldPan[]
	{ new ItemGoldPan(0), new ItemGoldPan(1), new ItemGoldPan(2), new ItemGoldPan(3), new ItemGoldPan(4), new ItemGoldPan(5), new ItemGoldPan(6), new ItemGoldPan(7), new ItemGoldPan(8), new ItemGoldPan(9) };
	public static Item[] dusts = new ItemDust[]
	{ new ItemDust(1), new ItemDust(2), new ItemDust(3), new ItemDust(4), new ItemDust(5), new ItemDust(6), new ItemDust(7), new ItemDust(8), new ItemDust(9) };
	public static SandOreBucket[] itemBs = new SandOreBucket[]
	{ new SandOreBucket(1), new SandOreBucket(2), new SandOreBucket(3), new SandOreBucket(4), new SandOreBucket(5), new SandOreBucket(6), new SandOreBucket(7), new SandOreBucket(8), new SandOreBucket(9) };
	public static boolean[] exists = new boolean[9];
	public static Item DebugGenerator = new ItemDebugGenerator();
	public static Block accel = new TimeAcceleratorBlock();
	public static Block sowp = new SOWPBlock();

	public static void init()
	{
		GameRegistry.registerWorldGenerator(new SandOreGenerator(), 100);
		GameRegistry.registerItem(goldpanfull[0], "goldpan");
		GameRegistry.registerItem(DebugGenerator, DebugGenerator.getUnlocalizedName());
		GameRegistry.registerBlock(accel, "TimeAccelerator");
		GameRegistry.registerTileEntity(TileEntityAccelerator.class, "goldflushing:TimeAccelerator");
		GameRegistry.registerTileEntity(SOWPTile.class, "goldflushing:SOWP");
		GameRegistry.registerBlock(sowp, sowp.getUnlocalizedName());

		ShapedOreRecipe gpan = new ShapedOreRecipe(new ItemStack(Registry.goldpanfull[0], 2, 0), new Object[]
		{ "   ", "@#@", " @ ", '@', "plankWood", '#', new ItemStack(Items.bowl, 1, 0) });
		GameRegistry.addRecipe(gpan);

		ShapedOreRecipe sowp = new ShapedOreRecipe(new ItemStack(Registry.sowp, 1, 0), new Object[]
		{ "  b", " pc", "p p", 'p', "plankWood", 'c', new ItemStack(Blocks.crafting_table), 'b', new ItemStack(Items.water_bucket) });
		GameRegistry.addRecipe(sowp);
		
		for (int i = 1; i < names.length; i++)
		{
			if (OreDictionary.doesOreNameExist("ore" + names[i]))
			{
				GameRegistry.registerBlock(sand[i-1], names[i] + "OreSand");
				GameRegistry.registerItem(goldpanfull[i], "goldpanfull" + names[i] + "sand");
				GameRegistry.registerItem(dusts[i-1], "dust" + names[i]);
				GameRegistry.registerItem(itemBs[i-1], "sandbucket" + names[i] + "sand");

				if (!OreDictionary.getOres("ingot" + names[i]).isEmpty())
				{
					OreDictionary.registerOre("dust" + names[i], dusts[i-1]);
					GameRegistry.addSmelting(dusts[i-1], OreDictionary.getOres("ingot" + names[i]).get(0), 4.0F);
				}

				exists[i-1] = true;
				for (int j = 0; j < 8; j++)
				{
					for (int l = 0; l < 8 - i; l++)
					{
						if (i + l <= 8)
							GameRegistry.addShapelessRecipe(new ItemStack(itemBs[i-1], 1, 8 - (j + l)), new Object[]
							{ new ItemStack(itemBs[i], 1, j), new ItemStack(itemBs[i-1], 1, l) });
					}
				}
			} else
				exists[i-1] = false;
		}
		

	}

	public static int getType(ItemStack is)
	{
		if (is != null)
		{
			if (is.getItem() instanceof ItemGoldPan)
			{
				for (int i = 0; i < 9; i++)
				{
					if (exists[i])
					{
						if (is.getItem() == goldpanfull[i])
						{
							return i;
						}
					}
				}
			}

			if (is.getItem() instanceof ItemDust)
			{
				for (int i = 0; i < 9; i++)
				{
					if (exists[i])
					{
						if (is.getItem() == dusts[i])
						{
							return i;
						}
					}
				}
			}

			if (is.getItem() instanceof SandOreBucket)
			{
				for (int i = 0; i < 9; i++)
				{
					if (exists[i])
					{
						if (is.getItem() == itemBs[i])
						{
							return i;
						}
					}
				}
			}
		}
		return 0;
	}
}
