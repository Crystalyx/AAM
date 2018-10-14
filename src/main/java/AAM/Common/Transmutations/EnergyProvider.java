package aam.common.transmutations;

import static aam.common.transmutations.EnergyType.Blood;
import static aam.common.transmutations.EnergyType.Fluid;
import static aam.common.transmutations.EnergyType.Fuel;
import static aam.common.transmutations.EnergyType.Matter;
import static aam.common.transmutations.EnergyType.Unknown;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import aam.api.Interface.IEnergyStorage;
import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnergyProvider
{
	public static Hashtable<ItemStack, Double> values = new Hashtable<>();
	public static Hashtable<ItemStack, EnergyType> types = new Hashtable<>();
	public static List<ItemStack> itemstacks = new ArrayList<>();

	public static void load()
	{
		// ==============Items=================
		setEnergyValue(Items.apple, 16, Matter);
		setEnergyValue(Items.wheat, 16, Matter);
		setEnergyValue(Items.diamond, 4096, Matter);
		setEnergyValue(Items.gold_ingot, 2048, Matter);
		setEnergyValue(Items.iron_ingot, 128, Matter);
		setEnergyValue(Items.ender_pearl, 512, Matter);
		setEnergyValue(Items.blaze_rod, 768, Fuel);
		setEnergyValue(Items.bone, 40, Matter);
		setEnergyValue(Items.bucket, 384, Matter);
		setEnergyValue(Items.carrot, 16, Matter);
		setEnergyValue(Items.chicken, 32, Matter);
		setEnergyValue(Items.clay_ball, 8, Matter);
		setEnergyValue(Items.coal, 64, Fuel);
		setEnergyValue(Items.dye, 4, 384, Matter);
		setEnergyValue(Items.egg, 20, Matter);
		setEnergyValue(Items.emerald, 8192, Matter);
		setEnergyValue(Items.feather, 20, Matter);
		setEnergyValue(Items.fish, 32, Matter);
		setEnergyValue(Items.flint, 8, Matter);
		setEnergyValue(Items.beef, 40, Matter);
		setEnergyValue(Items.ghast_tear, 4096, Matter);
		setEnergyValue(Items.glowstone_dust, 1024, Fuel);
		setEnergyValue(Items.gunpowder, 128, Fuel);
		setEnergyValue(Items.lava_bucket, 32 + 128 * 3, Matter);// Fluid);
		setEnergyValue(Items.leather, 20, Matter);
		setEnergyValue(Items.melon, 32, Matter);
		setEnergyValue(Items.milk_bucket, 20 + 128 * 3, Matter);// Fluid
		setEnergyValue(Items.nether_star, 69632, Matter);
		setEnergyValue(Items.nether_wart, 32, Matter);
		setEnergyValue(Items.poisonous_potato, 16, Matter);
		setEnergyValue(Items.porkchop, 40, Matter);
		setEnergyValue(Items.potato, 16, Matter);
		setEnergyValue(Items.quartz, 48, Matter);
		setEnergyValue(Items.record_11, 1024, Matter);
		setEnergyValue(Items.record_13, 1024, Matter);
		setEnergyValue(Items.record_blocks, 1024, Matter);
		setEnergyValue(Items.record_cat, 1024, Matter);
		setEnergyValue(Items.record_chirp, 1024, Matter);
		setEnergyValue(Items.record_far, 1024, Matter);
		setEnergyValue(Items.record_mall, 1024, Matter);
		setEnergyValue(Items.record_mellohi, 1024, Matter);
		setEnergyValue(Items.record_stal, 1024, Matter);
		setEnergyValue(Items.record_strad, 1024, Matter);
		setEnergyValue(Items.record_wait, 1024, Matter);
		setEnergyValue(Items.record_ward, 1024, Matter);
		setEnergyValue(Items.redstone, 16, Fuel);
		setEnergyValue(Items.reeds, 16, Matter);
		setEnergyValue(Items.rotten_flesh, 16, Matter);
		setEnergyValue(Items.skull, 32768, Matter);
		setEnergyValue(Items.slime_ball, 16, Matter);
		setEnergyValue(Items.snowball, 4, Matter);
		setEnergyValue(Items.spider_eye, 32, Matter);
		setEnergyValue(Items.string, 12, Matter);
		setEnergyValue(Items.water_bucket, 0.1 + 128 * 3, Matter);// Fluid
		setEnergyValue(Items.wheat_seeds, 4, Matter);

		// ==============BLOCKS=================
		setEnergyValue(Blocks.bedrock, Integer.MAX_VALUE / 1024, Matter);
		setEnergyValue(Blocks.brown_mushroom, 16, Matter);
		setEnergyValue(Blocks.cactus, 24, Matter);
		setEnergyValue(Blocks.cobblestone, 0.5, Matter);
		setEnergyValue(Blocks.dirt, 0.25, Matter);
		setEnergyValue(Blocks.dragon_egg, 131_072, Matter);
		setEnergyValue(Blocks.end_stone, 1, Matter);
		setEnergyValue(Blocks.glass, 2, Matter);
		setEnergyValue(Blocks.gravel, 4, Matter);
		setEnergyValue(Blocks.ice, 1, Matter);
		setEnergyValue(Blocks.lava, 2, Fluid);
		setEnergyValue(Blocks.log, 16, Matter);
		setEnergyValue(Blocks.log2, 16, Matter);
		setEnergyValue(Blocks.mossy_cobblestone, 4, Matter);
		setEnergyValue(Blocks.netherrack, 0.5, Matter);
		setEnergyValue(Blocks.obsidian, 32, Matter);
		setEnergyValue(Blocks.packed_ice, 2, Matter);
		setEnergyValue(Blocks.grass, 1, Matter);
		setEnergyValue(Blocks.pumpkin, 32, Matter);
		setEnergyValue(Blocks.red_mushroom, 16, Matter);
		setEnergyValue(Blocks.sapling, 8, Matter);
		setEnergyValue(Blocks.soul_sand, 24.5, Matter);
		setEnergyValue(Blocks.stone, 1, Matter);
		setEnergyValue(Blocks.vine, 12, Matter);
		setEnergyValue(Blocks.water, 0.1, Fluid);
		setEnergyValue(Blocks.waterlily, 12, Matter);
		setEnergyValue(Blocks.wool, 48, Matter);
		setEnergyValue(Blocks.double_plant, 16, Matter);
		setEnergyValue(Blocks.leaves, 0.5, Matter);
		setEnergyValue(Blocks.leaves2, 0.5, Matter);
		setEnergyValue(Blocks.mycelium, 4, Matter);
		setEnergyValue(Blocks.red_flower, 8, Matter);
		setEnergyValue(Blocks.sand, 1, Matter);
		setEnergyValue(Blocks.tallgrass, 0.5, Matter);
		setEnergyValue(Blocks.yellow_flower, 8, Matter);

		// ==============MODITEMS=================
		setEnergyValue(ModItems.Berry, 4, Matter);
		setEnergyValue(ModItems.ItemChalk, 128, Matter);
		setEnergyValue(ModItems.STea, 48, Matter);
		setEnergyValue(ModItems.BloodBucket, 1024 + 128 * 3, Blood);
		setEnergyValue(ModItems.MiniumShard, 1024, Matter);

		// ==============MODBLOCKS=================
		setEnergyValue(ModBlocks.BerryBush, 32, Matter);
		setEnergyValue(ModBlocks.ShadowveilPlant, 16, Matter);
		setEnergyValue(ModBlocks.ShroomPlant, 16, Matter);
		setEnergyValue(ModBlocks.Bricks, 1, Matter);

		setEnergyValue(ModBlocks.ModLeaves[0], 0.5, Matter);
		setEnergyValue(ModBlocks.ModLogs[0], 16, Matter);
		setEnergyValue(ModBlocks.ModPlanks[0], 4, Matter);
		setEnergyValue(ModBlocks.ModSaplings[0], 8, Matter);

		setEnergyValue(ModBlocks.ModLeaves[1], 1, Matter);
		setEnergyValue(ModBlocks.ModLogs[1], 32, Matter);
		setEnergyValue(ModBlocks.ModPlanks[1], 8, Matter);
		setEnergyValue(ModBlocks.ModSaplings[1], 16, Matter);
		setEnergyValue(ModBlocks.miniumBlock, 9 * 1024, Matter);

	}

	public static void setEnergyValue(Block i, int meta, double value, EnergyType type)
	{
		ItemStack preKey = findIS(Item.getItemFromBlock(i), meta);
		if (!values.containsKey(preKey))
		{
			values.put(preKey, value);
			types.put(preKey, type);
		}
		else
		{
			values.replace(preKey, value);
			types.replace(preKey, type);
		}
		itemstacks.add(preKey);
	}

	public static void setEnergyValue(Block i, double value, EnergyType type)
	{
		ItemStack preKey = findIS(Item.getItemFromBlock(i));
		if (!values.containsKey(preKey))
		{
			values.put(preKey, value);
			types.put(preKey, type);
		}
		else
		{
			values.replace(preKey, value);
			types.replace(preKey, type);
		}
		itemstacks.add(preKey);
	}

	public static void setEnergyValue(Item i, int meta, double value, EnergyType type)
	{
		ItemStack preKey = findIS(i, meta);
		if (!values.containsKey(preKey))
		{
			values.put(preKey, value);
			types.put(preKey, type);
		}
		else
		{
			values.replace(preKey, value);
			types.replace(preKey, type);
		}
		itemstacks.add(preKey);
	}

	public static void setEnergyValue(Item i, double value, EnergyType type)
	{
		ItemStack preKey = findIS(i);
		if (!values.containsKey(preKey))
		{
			values.put(preKey, value);
			types.put(preKey, type);
		}
		else
		{
			values.replace(preKey, value);
			types.replace(preKey, type);
		}
		itemstacks.add(preKey);
	}

	public void unregisterItem(Item i, int meta)
	{
		ItemStack Key = findIS(i, meta);
		values.remove(Key);
	}

	public void unregisterBlock(Block i, int meta)
	{
		ItemStack Key = findIS(Item.getItemFromBlock(i), meta);
		values.remove(Key);
	}

	public static ItemStack findIS(Item i, int meta)
	{
		List<ItemStack> rml = new ArrayList<>();
		for (ItemStack key : itemstacks)
		{
			if (key.stackSize <= 0 || key.getItem() == null)
			{
				rml.add(key);
			}
			if (key.getItem() == i && (key.getItemDamage() == meta || key.getItemDamage() == 42))
			{
				return key;
			}
		}
		ItemStack postKey = new ItemStack(i, 1, meta);
		itemstacks.removeAll(rml);
		itemstacks.add(postKey);
		return postKey;
	}

	public static ItemStack findIS(Item i)
	{
		List<ItemStack> rml = new ArrayList<>();
		for (ItemStack key : itemstacks)
		{
			if (key.getItem() == i)
			{
				return key;
			}
		}
		ItemStack postKey = new ItemStack(i, 1, 42);
		itemstacks.removeAll(rml);
		itemstacks.add(postKey);
		return postKey;
	}

	public static boolean hasValue(Item I, int meta)
	{
		return getValue(I, meta) != 0;
	}

	public static boolean hasValue(ItemStack I)
	{
		return getValue(I) != 0;
	}

	public static boolean hasEnergy(Item I, int meta)
	{
		return getFullEnergy(findIS(I, meta)) != 0;
	}

	public static boolean hasEnergy(ItemStack I)
	{
		if (I != null)
		{
			return getFullEnergy(I) != 0;
		}
		return false;
	}

	public static boolean canStoreEnergy(ItemStack I)
	{
		return I != null && I.getItem() instanceof IEnergyStorage;
	}

	public static double getStoredEnergy(ItemStack I)
	{
		double e = 0;

		if (I != null)
		{
			if (I.getItem() instanceof IEnergyStorage)
			{
				e = ((IEnergyStorage) I.getItem()).getStoredEnergy(I);
			}
		}

		return e;
	}

	public static double getValue(ItemStack I)
	{
		double e = 0;

		if (I != null)
		{
			ItemStack key = findIS(I.getItem(), I.getItemDamage());
			if (values.containsKey(key))
			{
				e = values.get(key);
			}
		}

		return e;
	}

	public static double getValue(Item I, int meta)
	{
		double e = 0;

		if (I != null)
		{
			if (values.containsKey(findIS(I, meta)))
			{
				e = values.get(findIS(I, meta));
			}
		}

		return e;
	}

	public static double getFullEnergy(ItemStack I)
	{
		return getValue(I) + getStoredEnergy(I);
	}

	public static EnergyType getType(ItemStack I)
	{
		if (I != null)
		{
			ItemStack key = findIS(I.getItem(), I.getItemDamage());
			if (types.containsKey(key))
			{
				return types.get(key);
			}
		}
		return Unknown;
	}
}
