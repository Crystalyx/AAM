package AAM.Common.Blocks.Building;

import AAM.Common.Blocks.Circles.BloodAltar;
import AAM.Common.Blocks.Circles.BloodBlock;
import AAM.Common.Blocks.Circles.CircleBase;
import AAM.Common.Blocks.Circles.MechanicalBase;
import AAM.Common.Blocks.Circles.TransCircle;
import AAM.Common.Blocks.Mechanical.Armoury;
import AAM.Common.Blocks.Mechanical.BlockTeleporter;
import AAM.Common.Blocks.Mechanical.Cauldron;
import AAM.Common.Blocks.Mechanical.CreativeCauldron;
import AAM.Common.Blocks.Mechanical.CrystalBlock;
import AAM.Common.Blocks.Mechanical.Expander;
import AAM.Common.Blocks.Mechanical.GrassClearer;
import AAM.Common.Blocks.Mechanical.GraviterBlock;
import AAM.Common.Blocks.Mechanical.ModificationAnvil;
import AAM.Common.Blocks.Mechanical.SoulAltar;
import AAM.Common.Blocks.Mechanical.SpellTable;
import AAM.Common.Blocks.Plants.BerryBush;
import AAM.Common.Blocks.Plants.ModLeaves;
import AAM.Common.Blocks.Plants.ModLog;
import AAM.Common.Blocks.Plants.ModPlank;
import AAM.Common.Blocks.Plants.ModPlank2;
import AAM.Common.Blocks.Plants.ModSapling;
import AAM.Common.Blocks.Plants.ShadowveilPlant;
import AAM.Common.Blocks.Plants.ShroomPlant;
import AAM.Common.Items.AAMItemBlock;
import AAM.Common.Items.BushItemBlock;
import AAM.Common.Items.ModItems;
import AAM.Common.Tabs.AlchemyTab;
import AAM.Common.Tabs.MiscTab;
import AAM.Common.Tabs.SoulTab;
import AAM.Common.Transmutations.FluidBlood;
import AAM.Core.AAMCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModBlocks
{
	static AAMCore core = AAMCore.instance;
	public static String modid = AAMCore.modid + ".";
	public static CreativeTabs alchemy = new AlchemyTab();
	public static CreativeTabs soul = new SoulTab();
	public static CreativeTabs misc = new MiscTab();

	// =======================Multi==========================
	public static int trees = 0;
	public static ModSapling[] ModSaplings = new ModSapling[0];
	public static ModLog[] ModLogs = new ModLog[0];
	public static ModLeaves[] ModLeaves = new ModLeaves[0];
	public static ModPlank[] ModPlanks = new ModPlank[0];
	public static ModPlank2[] ModPlanks2 = new ModPlank2[0];

	// =======================Plants==========================
	public static Block ShadowveilPlant = (new ShadowveilPlant(-1)).setBlockName(modid + "shadowveil");
	public static Block ShroomPlant = (new ShroomPlant()).setBlockName(modid + "shroom");
	public static Block BerryBush = (new BerryBush(Material.leaves)).setBlockName(modid + "berryBush");

	// =======================Potions==========================
	public static Block Cauldron = (new Cauldron(Material.iron)).setBlockName(modid + "cauldron");
	public static Block CauldronCreat = (new CreativeCauldron(Material.iron)).setBlockName(modid + "cauldron.creative");
	public static Block SpellTable = new SpellTable(Material.wood).setBlockName(modid + "spelltable");
	public static Block ReinforcedGlass = new ReinforcedGlass(Material.glass).setBlockName(modid + "reinforced_glass");
	public static Block SandyGlass = new SandyGlass().setBlockName(modid + "sandy_glass");

	// =======================Debug==========================
	public static Block Expander = (new Expander(Material.iron)).setBlockName(modid + "expander");
	public static Block StructureBlock = new StructureBlock(Material.rock).setBlockName(modid + "struct");

	// =======================Mechanical==========================
	public static Block GClearer = new GrassClearer();

	// =======================OLD==========================
	public static Block ModAnvil = new ModificationAnvil().setBlockName(modid + "anvil");
	public static Block grav = new GraviterBlock();

	// =======================Alchemy===========================
	public static Fluid blood = new FluidBlood().setUnlocalizedName("aam.blood.fluid");
	public static Block BloodBlock;
	public static Block MechanicalBase = new MechanicalBase(Material.iron).setBlockName(modid + "mechbase");
	public static Block LightBlock = new LightBlock().setBlockName(modid + "light");
	public static Block CircleBase = new CircleBase().setBlockName(modid + "circleBase");;
	public static Block TransCircle = new TransCircle().setBlockName(modid + "transcircle");;
	public static Block BloodAltar = new BloodAltar().setBlockName(modid + "bloodaltar");
	public static Block miniumBlock = new TexturedBlock(Material.rock, modid + "miniumblock", "aam:minium_block", 1, 1);
	public static Block miniumOre = new ModOreBlock("minium", 1, 1, ModItems.MiniumShard, 1, 2, 2);

	// =======================Soul==========================
	public static Block SoulAltar = new SoulAltar().setBlockName(modid + "soulaltar");
	public static Block Armoury = new Armoury().setBlockName(modid + "armoury");
	public static Block crystal = new CrystalBlock().setBlockName(modid + "crystal");
	public static Block PHBlock = new URBlock().setBlockName(modid + "placeholder");
	public static Block pillar = new PillarBlock().setBlockName(modid + "pillar");
	public static Block altar_base = new AltarBase(Material.iron).setHardness(2.0F).setBlockName(modid + "abase");
	public static Block altar_based_stairs = new AltarBasedStairs().setHardness(2.0F).setBlockName(modid + "astairs");

	// =======================Dungeon==========================
	public static Block BlockTeleporter = new BlockTeleporter().setBlockName(modid + "dungteleporter");
	public static Storage Bricks = (Storage) new Storage(Material.rock).setBlockName(modid + "brick");

	public static void load()
	{
		// =======================Trees==========================
		addTree("wormwood/");// 0
		addTree("darkwood/");// 1
		addCutPlank(1, "_plank_cut");
		addCutPlank(1, "_head");
		for (int i = 0; i < trees; i++)
		{
			registerBlock(ModSaplings[i], misc);
			registerBlock(ModLogs[i], misc);
			registerBlock(ModLeaves[i], misc, AAMItemBlock.class);
			registerBlock(ModPlanks[i], misc, AAMItemBlock.class);
			registerBlock(ModPlanks2[i], misc, AAMItemBlock.class);
		}

		// =======================Plants==========================
		registerBlock(ShadowveilPlant, misc);
		registerBlock(ShroomPlant, misc);
		registerBlock(BerryBush, misc, BushItemBlock.class);

		// =======================Potions==========================
		registerBlock(Cauldron, misc);
		registerBlock(CauldronCreat, misc);
		registerBlock(SpellTable, misc);
		registerBlock(ReinforcedGlass, misc);
		registerBlock(SandyGlass, misc);

		// =======================Alchemy===========================
		registerBlock(TransCircle);
		registerBlock(CircleBase, alchemy);
		registerBlock(MechanicalBase, alchemy);
		registerBlock(LightBlock);
		if (FluidRegistry.registerFluid(blood))
		{
			BloodBlock = new BloodBlock(blood).setBlockName(modid + "bloodblock");
			registerBlock(BloodBlock, alchemy);
		}
		registerBlock(BloodAltar, alchemy);
		registerBlock(miniumBlock, alchemy);
		registerBlock(miniumOre, alchemy);

		// =======================Soul==========================
		registerBlock(SoulAltar, soul);
		registerBlock(Armoury, soul);
		registerBlock(PHBlock);
		registerBlock(altar_base, soul);
		registerBlock(altar_based_stairs, soul);
		registerBlock(pillar, soul);
		registerBlock(crystal, soul);

		// =======================Dungeon==========================
		registerBlock(BlockTeleporter, misc);
		Bricks.addStorage("dung_brick");
		Bricks.addStorage("dung_brick_crac");
		Bricks.addStorage("dung_brick_moss");
		Bricks.addStorage("dung_brick_carv");
		registerBlock(Bricks, misc, AAMItemBlock.class);

		// =======================Debug==========================
		registerBlock(StructureBlock, misc);
		registerBlock(Expander, misc);

		// =======================Mechanical==========================
		registerBlock(GClearer, misc);

		// =======================OLD==========================
		registerBlock(ModAnvil, misc);
		registerBlock(grav, misc);

	}

	public static void addTree(String name)
	{
		String texture = "trees/" + name;
		expandTreeArrays();
		ModSaplings[trees - 1] = (ModSapling) new ModSapling(texture, trees - 1).setBlockName(modid + "sapling_" + (trees - 1));
		ModLogs[trees - 1] = (ModLog) new ModLog(Material.wood, texture).setBlockName(modid + "log" + (trees - 1));
		ModLeaves[trees - 1] = (ModLeaves) new ModLeaves(texture).setBlockName(modid + "leaves" + (trees - 1));
		ModPlanks[trees - 1] = (ModPlank) new ModPlank(texture).setBlockName(modid + "plank" + (trees - 1));
		ModPlanks2[trees - 1] = (ModPlank2) new ModPlank2(texture, new String[0]).setBlockName(modid + "plank2_" + (trees - 1));

	}

	public static void addCutPlank(int id, String name)
	{
		ModPlanks2[id].addStorage(name);
	}

	public static void expandTreeArrays()
	{
		ModSapling[] retS = new ModSapling[trees + 1];
		for (int i = 0; i < trees; i++)
		{
			retS[i] = ModSaplings[i];
		}
		ModSaplings = retS;

		ModLog[] retL = new ModLog[trees + 1];
		for (int i = 0; i < trees; i++)
		{
			retL[i] = ModLogs[i];
		}
		ModLogs = retL;

		ModLeaves[] retLs = new ModLeaves[trees + 1];
		for (int i = 0; i < trees; i++)
		{
			retLs[i] = ModLeaves[i];
		}
		ModLeaves = retLs;

		ModPlank[] retP = new ModPlank[trees + 1];
		for (int i = 0; i < trees; i++)
		{
			retP[i] = ModPlanks[i];
		}
		ModPlanks = retP;

		ModPlank2[] retP2 = new ModPlank2[trees + 1];
		for (int i = 0; i < trees; i++)
		{
			retP2[i] = ModPlanks2[i];
		}
		ModPlanks2 = retP2;
		trees += 1;
	}

	public static void registerBlock(Block b)
	{
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}

	public static void registerBlock(Block b, CreativeTabs tab)
	{
		b.setCreativeTab(tab);
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}

	public static void registerBlock(Block b, Class<? extends ItemBlock> ib)
	{
		GameRegistry.registerBlock(b, ib, b.getUnlocalizedName());
	}

	public static void registerBlock(Block b, CreativeTabs tab, Class<? extends ItemBlock> ib)
	{
		b.setCreativeTab(tab);
		GameRegistry.registerBlock(b, ib, b.getUnlocalizedName());
	}

}
