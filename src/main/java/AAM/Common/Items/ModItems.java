package AAM.Common.Items;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Items.Alchemy.AlchPaper;
import AAM.Common.Items.Alchemy.AlchemicalConcentrateItem;
import AAM.Common.Items.Alchemy.AlchemicalPotionItem;
import AAM.Common.Items.Alchemy.BloodBucket;
import AAM.Common.Items.Alchemy.ChalkPattern;
import AAM.Common.Items.Alchemy.CircleDust;
import AAM.Common.Items.Alchemy.ItemChalk;
import AAM.Common.Items.Alchemy.MiniumShard;
import AAM.Common.Items.Alchemy.MiniumStone;
import AAM.Common.Items.Alchemy.PhilosophersStone;
import AAM.Common.Items.Alchemy.SilverClock;
import AAM.Common.Items.Artifacts.ClockOfTime;
import AAM.Common.Items.Artifacts.CrystalBow;
import AAM.Common.Items.Artifacts.ElementalHeart;
import AAM.Common.Items.Artifacts.FacelessCharm;
import AAM.Common.Items.Artifacts.KingsStone;
import AAM.Common.Items.Artifacts.LuckyCoin;
import AAM.Common.Items.Artifacts.MassRessurectionStone;
import AAM.Common.Items.Artifacts.RessurectionStone;
import AAM.Common.Items.Artifacts.SeaShard;
import AAM.Common.Items.Debug.BoundSphere;
import AAM.Common.Items.Debug.CliserActivator;
import AAM.Common.Items.Debug.MagicWand;
import AAM.Common.Items.Debug.RedRadio;
import AAM.Common.Items.Resources.Berry;
import AAM.Common.Items.Resources.Coin;
import AAM.Common.Items.Resources.Material;
import AAM.Common.Items.Resources.SwordDye;
import AAM.Common.Items.Soul.Artifact;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Core.AAMCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems
{
	static AAMCore core = AAMCore.instance;
	static String idn = AAMCore.modid + ".";
	static CreativeTabs alchemy = ModBlocks.alchemy;
	static CreativeTabs soul = ModBlocks.soul;
	static CreativeTabs misc = ModBlocks.misc;

	// =======================RogueWorld===========================
	public static Item RiteBook = new RiteBook();
	public static Item STea = new SoothingTea();

	// =======================Artifacts===========================
	// public static Item RainbowSword = new
	// RainbowSword(EnumHelper.addToolMaterial("Rainbow", 121, 2500, 5.0F,
	// 60.0F, 100)).setUnlocalizedName(idn + "rainbowsword");
	public static Item coins = new Coin().setUnlocalizedName(idn + "coin");
	public static Item LuckyCoin = new LuckyCoin().setUnlocalizedName(idn + "luckycoin");
	public static Item AuraRing = new AuraRing().setUnlocalizedName(idn + "ring_aura");

	public static Item FacelessCharm = new FacelessCharm(1).setUnlocalizedName(idn + "facelesscharm");
	public static Item ElementalHeart = new ElementalHeart();
	public static Item SeaShard = new SeaShard().setTextureName("aam:shard_sea");
	public static Item ClockOfTime = new ClockOfTime().setUnlocalizedName(idn + "clocktime");
	public static Item KingsStone = new KingsStone();

	public static Item RessurectionStone = new RessurectionStone().setUnlocalizedName(idn + "ressstone");
	public static Item MassRessurectionStone = new MassRessurectionStone().setUnlocalizedName(idn + "massressstone");

	// =======================Debug===========================
	public static Item MagicWand = new MagicWand().setUnlocalizedName(idn + "magicwand");
	public static Item CliserActivator = new CliserActivator().setUnlocalizedName(idn + "cliser");
	public static Item RedRadio = new RedRadio().setUnlocalizedName(idn + "redradio");
	public static Item boundsph = new BoundSphere();

	// =======================Potions===========================
	public static Item Berry = new Berry().setHasSubtypes(true).setUnlocalizedName(idn + "berry");
	public static Item MortarAndPestle = new MortarAndPestle().setUnlocalizedName(idn + "MortarAndPestle");
	public static Item Potion = new AlchemicalPotionItem().setUnlocalizedName(idn + "alchpotion");

	// =======================Soul===========================
	public static Item Artifact = new Artifact().setUnlocalizedName(idn + "artifact");
	public static Item Shield = new SwordDye().setUnlocalizedName(idn + "shield");
	public static Item SoulSword = new SoulSword(EnumHelper.addToolMaterial("AAMSoul", 121, -1, 0.0F, -5.0F, 100)).setUnlocalizedName(idn + "soulsword");
	public static Material materials = (Material) new Material().setUnlocalizedName(idn + "material");
	public static Item CrystalBow = new CrystalBow().setUnlocalizedName(idn + "crystalbow");
	public static Item AnvilHammer = new AnvilHammer(0, EnumHelper.addToolMaterial("AAMAHammer", 4, 450, 10.0F, 6.0F, 15)).setUnlocalizedName(idn + "anvilhammer");
	public static Material soulGems = (Material) new Material().setUnlocalizedName(idn + "soulgem");
	public static Material soulUpgrades = (Material) new Material().setUnlocalizedName(idn + "soulupg");

	// =======================Alchemy===========================
	public static Item TeleportationCrystal = new TeleportationCrystal().setUnlocalizedName(idn + "crystaltp");
	public static Item LinkObol = new LinkObol().setUnlocalizedName(idn + "obol");
	public static Item ChalkPattern = new ChalkPattern().setUnlocalizedName(idn + "pattern");
	public static Item AlchPaper = new AlchPaper().setUnlocalizedName(idn + "alchpaper");
	public static Item CircleDust = new CircleDust().setUnlocalizedName(idn + "alchdust");
	public static Item ItemChalk = new ItemChalk().setUnlocalizedName(idn + "itemchalk");
	public static Item MiniumShard = new MiniumShard().setUnlocalizedName(idn + "miniumshard");
	public static Item MiniumStone = new MiniumStone().setUnlocalizedName(idn + "miniumstone");
	public static Item PhilosophersStone = new PhilosophersStone().setUnlocalizedName(idn + "philostone");
	public static Item BloodBucket = new BloodBucket().setUnlocalizedName(idn + "bloodbucket");
	public static Item SilverClock = new SilverClock();
	public static Item smallConcentrate = new AlchemicalConcentrateItem(0).setUnlocalizedName(idn + "alchconcentrate0");
	public static Item mediumConcentrate = new AlchemicalConcentrateItem(1).setUnlocalizedName(idn + "alchconcentrate1");
	public static Item bigConcentrate = new AlchemicalConcentrateItem(2).setUnlocalizedName(idn + "alchconcentrate2");

	public static void load()
	{
		// =======================Potions===========================
		registerItem(Berry, misc);
		registerItem(MortarAndPestle, misc);
		registerItem(Potion, misc);

		// =======================Artifacts===========================
		// registerItem(RainbowSword, misc);
		((Material) coins).addMaterial("coin_copper");
		((Material) coins).addMaterial("coin_silver");
		((Material) coins).addMaterial("coin_gold");
		((Material) coins).addMaterial("coin_spectral");
		registerItem(coins, misc);
		registerItem(LuckyCoin, misc);
		registerItem(AuraRing, misc);

		registerItem(FacelessCharm, misc);
		registerItem(ElementalHeart, misc);
		registerItem(SeaShard, misc);
		registerItem(KingsStone, misc);
		registerItem(ClockOfTime, misc);

		registerItem(RessurectionStone, misc);
		registerItem(MassRessurectionStone, misc);

		// =======================Debug===========================
		registerItem(MagicWand, misc);
		registerItem(boundsph, misc);
		registerItem(CliserActivator, misc);
		registerItem(RedRadio, misc);

		// =======================Alchemy===========================
		registerItem(TeleportationCrystal, alchemy);
		registerItem(ChalkPattern, alchemy);
		registerItem(AlchPaper, alchemy);
		registerItem(CircleDust, alchemy);
		registerItem(ItemChalk, alchemy);
		registerItem(MiniumShard, alchemy);
		registerItem(MiniumStone, alchemy);
		registerItem(PhilosophersStone, alchemy);
		registerItem(BloodBucket, alchemy);
		registerItem(LinkObol, alchemy);
		registerItem(SilverClock, alchemy);
		registerItem(smallConcentrate, alchemy);
		registerItem(mediumConcentrate, alchemy);
		registerItem(bigConcentrate, alchemy);

		// =======================Soul===========================
		registerItem(Shield, soul);
		registerItem(SoulSword, soul);
		materials.addMaterial("atrillium_ingot");
		materials.addMaterial("brass_ingot");
		materials.addMaterial("altersteel_ingot");
		materials.addMaterial("potions/emptyphial");
		materials.addMaterial("atrillium_scale");
		materials.addMaterial("brass_scale");
		materials.addMaterial("altersteel_scale");

		soulGems.addMaterial("cast_gem");
		soulGems.addMaterial("blood_gem");
		soulGems.addMaterial("moon");

		soulUpgrades.addMaterial("soulsword/blood_upgrade");
		soulUpgrades.addMaterial("soulsword/cast_upgrade");
		soulUpgrades.addMaterial("soulsword/moon_upgrade");

		registerItem(materials, soul);
		registerItem(Artifact, soul);
		registerItem(CrystalBow, soul);
		registerItem(AnvilHammer, soul);
		registerItem(soulGems, soul);
		registerItem(soulUpgrades, soul);

		// =======================RogueWorld===========================
		registerItem(RiteBook, misc);
		registerItem(STea, misc);

	}

	public static void registerItem(Item i)
	{
		GameRegistry.registerItem(i, i.getUnlocalizedName());
	}

	public static void registerItem(Item i, CreativeTabs tab)
	{
		i.setCreativeTab(tab);
		GameRegistry.registerItem(i, i.getUnlocalizedName());
	}

}
