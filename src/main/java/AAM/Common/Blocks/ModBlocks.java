package AAM.Common.Blocks;

import AAM.Common.Blocks.Building.AltarBase;
import AAM.Common.Blocks.Building.AltarBasedStairs;
import AAM.Common.Blocks.Circles.CircleBase;
import AAM.Common.Blocks.Circles.MechanicalBase;
import AAM.Common.Blocks.Circles.TransCircle;
import AAM.Common.Blocks.Mechanical.AGraviterBlock;
import AAM.Common.Blocks.Mechanical.Armoury;
import AAM.Common.Blocks.Mechanical.BlockTeleporter;
import AAM.Common.Blocks.Mechanical.Cauldron;
import AAM.Common.Blocks.Mechanical.CreativeCauldron;
import AAM.Common.Blocks.Mechanical.CrystalBlock;
import AAM.Common.Blocks.Mechanical.GraviterBlock;
import AAM.Common.Blocks.Mechanical.ModificationAnvil;
import AAM.Common.Blocks.Mechanical.SoulAltar;
import AAM.Common.Blocks.Mechanical.SpellTable;
import AAM.Common.Blocks.Mechanical.VoiderBlock;
import AAM.Common.Items.AAMItemBlock;
import AAM.Common.Items.BushItemBlock;
import AAM.Core.AAMConfig;
import AAM.Core.AAMCore;
import DummyCore.Blocks.BlocksRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks
{
	static AAMCore core = AAMCore.instance;
	public static String modid = AAMCore.modid + ".";

	public static Block ShadowveilPlant = (new ShadowveilPlant(-1)).setBlockName(modid + "shadowveil");
	public static Block ShroomPlant = (new ShroomPlant()).setBlockName(modid + "shroom");

	public static int trees = 0;
	public static ModSapling[] ModSaplings = new ModSapling[0];
	public static ModLog[] ModLogs = new ModLog[0];
	public static ModLeaves[] ModLeaves = new ModLeaves[0];
	public static ModPlank[] ModPlanks = new ModPlank[0];
	public static ModPlank2[] ModPlanks2 = new ModPlank2[0];

	public static Block BerryBush = (new BerryBush(Material.leaves)).setBlockName(modid + "berryBush");

	public static Block Cauldron = (new Cauldron(Material.iron)).setBlockName(modid + "cauldron");
	public static Block CauldronCreat = (new CreativeCauldron(Material.iron)).setBlockName(modid + "cauldron.creative");

	public static Block Expander = (new Expander(Material.iron)).setBlockName(modid + "expander");

	public static Block SpellTable = new SpellTable(Material.wood).setBlockName(modid + "spelltable");

	public static Block TransCircle = new TransCircle();
	public static Block CircleBase = new CircleBase();
	public static Block StructureBlock = new StructureBlock(Material.rock).setBlockName(modid + "struct");
	public static Block SoulAltar = new SoulAltar().setBlockName(modid + "soulaltar");
	public static Block Armoury = new Armoury().setBlockName(modid + "armoury");
	public static Storage Bricks = (Storage) new Storage(Material.rock).setBlockName(modid + "brick");
	public static Block PHBlock = new URBlock().setBlockName(modid + "placeholder");

	public static Block ModAnvil = new ModificationAnvil(net.minecraft.block.material.Material.iron).setBlockName(modid + "anvil");
	public static Block altar_base = new AltarBase(net.minecraft.block.material.Material.iron).setHardness(2.0F).setBlockName(modid + "abase");
	public static Block altar_based_stairs = new AltarBasedStairs().setHardness(2.0F).setBlockName(modid + "astairs");
	public static Block GClearer = new GrassClearer();

	public static Block voider = new VoiderBlock();
	public static Block grav = new GraviterBlock();
	public static Block agrav = new AGraviterBlock();
	public static Block pillar = new PillarBlock();
	public static Block crystal = new CrystalBlock();
	public static Block MechanicalBase = new MechanicalBase(Material.iron).setBlockName(modid + "mechbase");
	public static Block LightBlock = new LightBlock().setBlockName(modid + "light");
	public static Block BlockTeleporter = new BlockTeleporter().setBlockName(modid + "dungteleporter");

	public static void load()
	{

		BlocksRegistry.registerBlock(ShadowveilPlant, ShadowveilPlant.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(ShroomPlant, ShroomPlant.getUnlocalizedName(), AAMCore.class, null);

		addTree("wormwood/");// 0
		addTree("darkwood/");// 1
		addCutPlank(1, "_plank_cut");
		addCutPlank(1, "_head");
		for (int i = 0; i < trees; i++)
		{
			BlocksRegistry.registerBlock(ModSaplings[i], ModSaplings[i].getUnlocalizedName(), AAMCore.class, null);
			BlocksRegistry.registerBlock(ModLogs[i], ModLogs[i].getUnlocalizedName(), AAMCore.class, null);
			BlocksRegistry.registerBlock(ModLeaves[i], ModLeaves[i].getUnlocalizedName(), AAMCore.class, AAMItemBlock.class);
			BlocksRegistry.registerBlock(ModPlanks[i], ModPlanks[i].getUnlocalizedName(), AAMCore.class, AAMItemBlock.class);
			BlocksRegistry.registerBlock(ModPlanks2[i], ModPlanks2[i].getUnlocalizedName(), AAMCore.class, AAMItemBlock.class);
		}

		BlocksRegistry.registerBlock(BerryBush, BerryBush.getUnlocalizedName(), AAMCore.class, BushItemBlock.class);
		BlocksRegistry.registerBlock(Cauldron, Cauldron.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(CauldronCreat, CauldronCreat.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(SpellTable, SpellTable.getUnlocalizedName(), AAMCore.class, null);
		GameRegistry.registerBlock(TransCircle, TransCircle.getUnlocalizedName());
		BlocksRegistry.registerBlock(CircleBase, CircleBase.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(StructureBlock, StructureBlock.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(SoulAltar, SoulAltar.getUnlocalizedName(), AAMCore.class, null);
		Bricks.addStorage("dung_brick");
		Bricks.addStorage("dung_brick_crac");
		Bricks.addStorage("dung_brick_moss");
		Bricks.addStorage("dung_brick_carv");

		if (AAMConfig.enableExpander)
			BlocksRegistry.registerBlock(Expander, Expander.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(Armoury, Armoury.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(Bricks, Bricks.getUnlocalizedName(), AAMCore.class, AAMItemBlock.class);
		GameRegistry.registerBlock(PHBlock, PHBlock.getUnlocalizedName());
		BlocksRegistry.registerBlock(altar_base, altar_base.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(altar_based_stairs, altar_based_stairs.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(GClearer, GClearer.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(voider, voider.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(grav, grav.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(agrav, agrav.getUnlocalizedName(), AAMCore.class, null);

		BlocksRegistry.registerBlock(ModAnvil, ModAnvil.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(pillar, pillar.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(crystal, crystal.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(MechanicalBase, MechanicalBase.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(LightBlock, LightBlock.getUnlocalizedName(), AAMCore.class, null);
		BlocksRegistry.registerBlock(BlockTeleporter, BlockTeleporter.getUnlocalizedName(), AAMCore.class, null);
	}

	public static void addTree(String name)
	{
		String texture = "trees/" + name;
		expandArrays();
		ModSaplings[trees - 1] = (AAM.Common.Blocks.ModSapling) new ModSapling(texture, trees - 1).setBlockName(modid + "sapling_" + (trees - 1));
		ModLogs[trees - 1] = (AAM.Common.Blocks.ModLog) new ModLog(Material.wood, texture).setBlockName(modid + "log" + (trees - 1));
		ModLeaves[trees - 1] = (AAM.Common.Blocks.ModLeaves) new ModLeaves(texture).setBlockName(modid + "leaves" + (trees - 1));
		ModPlanks[trees - 1] = (AAM.Common.Blocks.ModPlank) new ModPlank(texture).setBlockName(modid + "plank" + (trees - 1));
		ModPlanks2[trees - 1] = (AAM.Common.Blocks.ModPlank2) new ModPlank2(texture, new String[0]).setBlockName(modid + "plank2_" + (trees - 1));

	}

	public static void addCutPlank(int id, String name)
	{
		ModPlanks2[id].addStorage(name);
	}

	public static void expandArrays()
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

}
