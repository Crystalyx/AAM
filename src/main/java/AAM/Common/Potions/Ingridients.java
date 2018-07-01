package AAM.Common.Potions;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Items.ModItems;
import AAM.Utils.Color;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Ingridients
{
	public static List<Ingridient> ings = new ArrayList<Ingridient>();
	public static final Ingridient spider_eye = new Ingridient(Items.spider_eye, 0, new Color(255, 199, 102), 0);
	public static final Ingridient bone = new Ingridient(Items.bone, 0, new Color(255, 163, 171), 1);
	public static final Ingridient ender_pearl = new Ingridient(Items.ender_pearl, 0, new Color(0, 245, 86), 2);
	public static final Ingridient blackberry = new Ingridient(ModItems.Berry, 0, new Color(12, 0, 56), 3);
	public static final Ingridient blueberry = new Ingridient(ModItems.Berry, 1, new Color(92, 62, 194), 4);
	public static final Ingridient mortisberry = new Ingridient(ModItems.Berry, 2, new Color(255, 174, 0), 5);
	public static final Ingridient raspberry = new Ingridient(ModItems.Berry, 3, new Color(255, 0, 115), 6);
	public static final Ingridient wormwoodberry = new Ingridient(ModItems.Berry, 4, new Color(164, 1, 1), 29);
	public static final Ingridient wormwood = new Ingridient(Item.getItemFromBlock(ModBlocks.ModSaplings[0]), 0, new Color(200, 0, 200), 7);
	public static final Ingridient diamond = new Ingridient(Items.diamond, 0, new Color(0, 242, 255), 8);
	public static final Ingridient red_flower = new Colorer(Item.getItemFromBlock(Blocks.red_flower), 0, new Color(255, 0, 0), 9);
	public static final Ingridient cactus = new Colorer(Item.getItemFromBlock(Blocks.cactus), 0, new Color(0, 255, 0), 10);
	public static final Ingridient lapis = new Colorer(Items.dye, 4, new Color(0, 0, 255), 11);
	public static final Ingridient apple = new Ingridient(Items.apple, 0, new Color(255, 163, 171), 12);
	public static final Ingridient carrot = new Ingridient(Items.carrot, 0, new Color(255, 190, 0), 13);
	public static final Ingridient poisonous_potato = new Ingridient(Items.poisonous_potato, 0, new Color(255, 225, 190), 14);
	public static final Ingridient golden_apple = new Ingridient(Items.golden_apple, 0, new Color(255, 214, 76), 15);
	public static final Ingridient ghast_tear = new Ingridient(Items.ghast_tear, 0, new Color(255, 255, 255), 16);
	public static final Ingridient fermented_spider_eye = new Ingridient(Items.fermented_spider_eye, 0, new Color(200, 144, 47), 17);
	public static final Ingridient blaze_powder = new Ingridient(Items.blaze_powder, 0, new Color(255, 190, 0), 18);
	public static final Ingridient magma_cream = new Ingridient(Items.magma_cream, 0, new Color(255, 105, 0), 19);
	public static final Ingridient speckled_melon = new Ingridient(Items.speckled_melon, 0, new Color(255, 210, 20), 20);
	public static final Ingridient ender_eye = new Ingridient(Items.ender_eye, 0, new Color(62, 210, 170), 21);
	public static final Ingridient snowball = new Ingridient(Items.snowball, 0, new Color(255, 255, 255), 22);
	public static final Ingridient emerald = new Ingridient(Items.emerald, 0, new Color(60, 210, 60), 23);
	public static final Ingridient nether_star = new Ingridient(Items.nether_star, 0, new Color(230, 230, 230), 24);
	public static final Ingridient shroom = new Ingridient(Item.getItemFromBlock(ModBlocks.ShroomPlant), 0, new Color(60, 120, 230), 25);
	public static final Ingridient shadowveil = new Ingridient(Item.getItemFromBlock(ModBlocks.ShadowveilPlant), 0, new Color(50, 120, 55), 26);
	public static final Ingridient nether_wart = new Ingridient(Items.nether_wart, 0, new Color(200, 144, 47), 27);
	public static final Ingridient feather = new Ingridient(Items.feather, 0, new Color(200, 250, 220), 28);

	public static void load()
	{
		ings.add(spider_eye);// 0
		ings.add(bone);// 1
		ings.add(ender_pearl);// 2
		ings.add(blackberry);// 3
		ings.add(blueberry);// 4
		ings.add(mortisberry);// 5
		ings.add(raspberry);// 6
		ings.add(wormwoodberry);// 29
		ings.add(wormwood);// 7
		ings.add(diamond);// 8
		ings.add(red_flower);// 9
		ings.add(cactus);// 10
		ings.add(lapis);// 11
		ings.add(apple);// 12
		ings.add(carrot);// 13
		ings.add(poisonous_potato);// 14
		ings.add(golden_apple);// 15
		ings.add(ghast_tear);// 16
		ings.add(fermented_spider_eye);// 17
		ings.add(blaze_powder);// 18
		ings.add(magma_cream);// 19
		ings.add(speckled_melon);// 20
		ings.add(ender_eye);// 21
		ings.add(snowball);// 22
		ings.add(emerald);// 23
		ings.add(nether_star);// 24
		ings.add(shroom);// 25
		ings.add(shadowveil);// 26
		ings.add(nether_wart);// 27
		ings.add(feather);// 28
	}

	public static int lastId = 0;

	// Ingridients
	public static void addIngredient(Block i, Color col)
	{
		ings.add(new Ingridient(Item.getItemFromBlock(i), 0, col, lastId));
	}

	public static void addIngredient(Block i, int meta, Color col)
	{
		ings.add(new Ingridient(Item.getItemFromBlock(i), meta, col, lastId));
	}

	public static void addIngredient(Item i, Color col)
	{
		ings.add(new Ingridient(i, 0, col, lastId));
	}

	public static void addIngredient(Item i, int meta, Color col)
	{
		ings.add(new Ingridient(i, meta, col, lastId));
	}

	public static void addIngredient(ItemStack i, Color col)
	{
		ings.add(new Ingridient(i, col, lastId));
	}

	public static void addIngredient(Ingridient ing)
	{
		ings.add(ing);
	}

	// Colorers
	public static void addColorer(Block i, Color col)
	{
		ings.add(new Colorer(Item.getItemFromBlock(i), 0, col, lastId));
	}

	public static void addColorer(Block i, int meta, Color col)
	{
		ings.add(new Colorer(Item.getItemFromBlock(i), meta, col, lastId));
	}

	public static void addColorer(Item i, Color col)
	{
		ings.add(new Colorer(i, 0, col, lastId));
	}

	public static void addColorer(Item i, int meta, Color col)
	{
		ings.add(new Colorer(i, meta, col, lastId));
	}

	public static void addColorer(ItemStack i, Color col)
	{
		ings.add(new Colorer(i, col, lastId));
	}

	public static void addColorer(Colorer ing)
	{
		ings.add(ing);
	}

	// Boosters
	public static void addBooster(Block i, Color col, int boost)
	{
		ings.add(new Booster(Item.getItemFromBlock(i), 0, col, lastId, boost));
	}

	public static void addBooster(Block i, int meta, Color col, int boost)
	{
		ings.add(new Booster(Item.getItemFromBlock(i), meta, col, lastId, boost));
	}

	public static void addBooster(Item i, Color col, int boost)
	{
		ings.add(new Booster(i, 0, col, lastId, boost));
	}

	public static void addBooster(Item i, int meta, Color col, int boost)
	{
		ings.add(new Booster(i, meta, col, lastId, boost));
	}

	public static void addBooster(ItemStack i, Color col, int boost)
	{
		ings.add(new Booster(i, col, lastId, boost));
	}

	// Prolongers
	public static void addProlonger(Block i, Color col, int time)
	{
		ings.add(new Prolonger(Item.getItemFromBlock(i), 0, col, lastId, time));
	}

	public static void addProlonger(Block i, int meta, Color col, int time)
	{
		ings.add(new Prolonger(Item.getItemFromBlock(i), meta, col, lastId, time));
	}

	public static void addProlonger(Item i, Color col, int time)
	{
		ings.add(new Booster(i, 0, col, lastId, time));
	}

	public static void addProlonger(Item i, int meta, Color col, int time)
	{
		ings.add(new Prolonger(i, meta, col, lastId, time));
	}

	public static void addProlonger(ItemStack i, Color col, int time)
	{
		ings.add(new Prolonger(i, col, lastId, time));
	}

	public static void addBooster(Booster ing)
	{
		ings.add(ing);
	}

	public static int getId(ItemStack is)
	{
		for (int i = 0; i < ings.size(); i++)
		{
			if (ings.get(i) != null)
			{
				if (ings.get(i).item == is.getItem())
				{
					if (ings.get(i).meta == is.getItemDamage())
					{
						return i;
					}
				}
			}
		}
		return -1;
	}

	public static Ingridient get(int id)
	{
		return ings.get(id);
	}
}
