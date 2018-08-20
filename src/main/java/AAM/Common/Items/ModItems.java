package AAM.Common.Items;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Items.Alchemy.AlchPaper;
import AAM.Common.Items.Alchemy.AlchemicalBucket;
import AAM.Common.Items.Alchemy.AlchemicalConcentrateItem;
import AAM.Common.Items.Alchemy.AlchemicalPotionItem;
import AAM.Common.Items.Alchemy.BloodBucket;
import AAM.Common.Items.Alchemy.ChalkPattern;
import AAM.Common.Items.Alchemy.CircleDust;
import AAM.Common.Items.Alchemy.ConcentratePhial;
import AAM.Common.Items.Alchemy.ItemChalk;
import AAM.Common.Items.Alchemy.MiniumShard;
import AAM.Common.Items.Alchemy.MiniumStone;
import AAM.Common.Items.Alchemy.MortarAndPestle;
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
import AAM.Common.Items.Resources.BerryDust;
import AAM.Common.Items.Resources.Coin;
import AAM.Common.Items.Resources.Material;
import AAM.Common.Items.Resources.SwordDye;
import AAM.Common.Items.Soul.Artifact;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Common.Items.Soul.SoulUpgradeItem;
import AAM.Common.Items.Weapon.ModAxe;
import AAM.Common.Items.Weapon.ModHammer;
import AAM.Common.Items.Weapon.ModSpear;
import AAM.Common.Items.Weapon.ModStaff;
import AAM.Common.Items.Weapon.ModSword;
import AAM.Common.Items.Weapon.ModificationCatalyst;
import AAM.Common.Tabs.StaffTab;
import AAM.Common.Tabs.SwordTab;
import AAM.Common.Tabs.TankTab;
import AAM.Core.AAMCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems
{
	static AAMCore core = AAMCore.instance;
	static String idn = AAMCore.modid + ".";
	static CreativeTabs alchemy = ModBlocks.alchemy;
	static CreativeTabs soul = ModBlocks.soul;
	static CreativeTabs misc = ModBlocks.misc;
	static CreativeTabs arts = ModBlocks.arts;
	static CreativeTabs swordtab = new SwordTab();
	static CreativeTabs stafftab = new StaffTab();
	static CreativeTabs tanktab = new TankTab();

	// =======================RogueWorld===========================
	public static Item RiteBook = new RiteBook();
	public static Item STea = new SoothingTea();

	// =======================Artifacts===========================
	// public static Item RainbowSword = new
	// RainbowSword(EnumHelper.addToolMaterial("Rainbow", 121, 2500, 5.0F,
	// 60.0F, 100)).setUnlocalizedName(idn + "rainbowsword");
	public static Item coins = new Coin().setUnlocalizedName(idn + "coin");
	public static Item LuckyCoin = new LuckyCoin().setUnlocalizedName(idn + "luckycoin");

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
	public static Item Berry = new Berry().setUnlocalizedName(idn + "berry");
	public static Item BerryDust = new BerryDust().setUnlocalizedName(idn + "berry_dust");
	public static Item MortarAndPestle = new MortarAndPestle().setUnlocalizedName(idn + "MortarAndPestle");
	public static Item Potion = new AlchemicalPotionItem().setUnlocalizedName(idn + "alchpotion");

	// =======================Soul===========================
	public static Item Artifact = new Artifact().setUnlocalizedName(idn + "artifact");
	public static Item SoulLens = new SwordDye().setUnlocalizedName(idn + "shield");
	public static Item SoulSword = new SoulSword(EnumHelper.addToolMaterial("AAMSoul", 121, -1, 0.0F, -5.0F, 100)).setUnlocalizedName(idn + "soulsword");
	public static Material materials = (Material) new Material().setUnlocalizedName(idn + "material");
	public static Item CrystalBow = new CrystalBow().setUnlocalizedName(idn + "crystalbow");
	public static Item AnvilHammer = new AnvilHammer(0, EnumHelper.addToolMaterial("AAMAHammer", 4, 450, 10.0F, 6.0F, 15)).setUnlocalizedName(idn + "anvilhammer");
	public static Item SoulUpgradeItem = new SoulUpgradeItem().setUnlocalizedName(idn + "soulupg");

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
	public static Item ModificationCatalyst = new ModificationCatalyst();
	public static Item BerryAssort = new Item().setUnlocalizedName(idn + "berryassorty").setTextureName("aam:ingredients/assorti");
	public static Item AlchemicalBucket = new AlchemicalBucket();
	public static Item ConcentratePhial = new ConcentratePhial();

	public static List<ModSword> swords = new ArrayList<ModSword>();
	public static List<ModStaff> staffs = new ArrayList<ModStaff>();
	public static List<ModSpear> spears = new ArrayList<ModSpear>();
	public static List<ModHammer> hammers = new ArrayList<ModHammer>();
	public static List<ModAxe> axes = new ArrayList<ModAxe>();

	public static final EnumRarity blood = EnumHelper.addRarity("Blood", EnumChatFormatting.DARK_RED, "Blood");

	public static void load()
	{
		// =======================Potions===========================
		registerItem(Berry, misc);
		registerItem(BerryDust, misc);
		registerItem(MortarAndPestle, misc);
		registerItem(Potion, misc);

		// =======================Artifacts===========================
		// registerItem(RainbowSword, misc);
		((Material) coins).addMaterial("coin_copper");
		((Material) coins).addMaterial("coin_silver");
		((Material) coins).addMaterial("coin_gold");
		((Material) coins).addMaterial("coin_spectral");
		registerItem(coins, misc);
		registerItem(LuckyCoin, arts);

		registerItem(FacelessCharm, arts);
		registerItem(ElementalHeart, arts);
		registerItem(SeaShard, arts);
		registerItem(KingsStone, arts);
		registerItem(ClockOfTime, arts);

		registerItem(RessurectionStone, arts);
		registerItem(MassRessurectionStone, arts);

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
		registerItem(BerryAssort, alchemy);
		registerItem(AlchemicalBucket, alchemy);
		registerItem(ConcentratePhial, alchemy);

		// =======================Soul===========================
		registerItem(SoulLens, soul);
		registerItem(SoulSword, soul);
		materials.addMaterial("atrillium_ingot");
		materials.addMaterial("brass_ingot");
		materials.addMaterial("altersteel_ingot");
		materials.addMaterial("potions/emptyphial");
		materials.addMaterial("atrillium_scale");
		materials.addMaterial("brass_scale");
		materials.addMaterial("altersteel_scale");

		registerItem(materials, soul);
		registerItem(Artifact, soul);
		registerItem(CrystalBow, soul);
		registerItem(AnvilHammer, soul);
		registerItem(SoulUpgradeItem, soul);
		registerItem(ModificationCatalyst, soul);

		// =======================RogueWorld===========================
		registerItem(RiteBook, misc);
		registerItem(STea, misc);

		registerSword("aam.item.sword.blood", 8, false, EnumRarity.rare, "aam:sword/blood", 4000, 3, 1, 3);
		registerSword("aam.item.sword.brass", 17, false, EnumRarity.uncommon, "aam:sword/brass", 3000, 3, 0, 2);
		registerStaff("aam.item.staff.blood", 6, 12, 8, false, EnumRarity.rare, "aam:staff/blood", 4000, 3, 1, 3);

		// Leaders set
		registerSword("aam.item.sword.leader.broad", 32, false, blood, "aam:sword/leader_broadsword", 8, 3, 0, 3);
		registerSword("aam.item.sword.leader.rapier", 28, true, blood, "aam:sword/leader_rapier", 8000, 3, 0, 3);
		registerSpear("aam.item.spear.leader.spear", 28, true, blood, "aam:tank/leader_spear", 8000, 3, 0, 3);
		registerAxe("aam.item.axe.leader.battleaxe", 32, false, blood, "aam:tank/leader_axe", 8000, 3, 0, 3);
		registerStaff("aam.item.staff.leader.wand", 17, 28, 8, 12, true, blood, "aam:staff/leader_wand", 8000, 3, 0, 3);
		registerStaff("aam.item.staff.leader.staff", 20, 32, 8, 20, false, blood, "aam:staff/leader_staff", 8000, 3, 0, 3);
	}

	public static void registerAxe(String name, int baseDmg, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots)
	{
		ModAxe ms = new ModAxe(name, baseDmg, bypassesArmor, rarity, texture, durability, repairs, minSlots, maxSlots);
		axes.add(ms);
		registerItem(ms, tanktab);
	}

	public static void registerSpear(String name, int baseDmg, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots)
	{
		ModSpear ms = new ModSpear(name, baseDmg, bypassesArmor, rarity, texture, durability, repairs, minSlots, maxSlots);
		spears.add(ms);
		registerItem(ms, tanktab);
	}

	public static void registerHammer(String name, int baseDmg, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots)
	{
		ModHammer ms = new ModHammer(name, baseDmg, bypassesArmor, rarity, texture, durability, repairs, minSlots, maxSlots);
		hammers.add(ms);
		registerItem(ms, tanktab);
	}

	public static void registerStaff(String name, int meleeDmg, int rangedDmg, int soulConsumed, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots)
	{
		ModStaff ms = new ModStaff(name, meleeDmg, rangedDmg, soulConsumed, bypassesArmor, rarity, texture, durability, repairs, minSlots, maxSlots);
		staffs.add(ms);
		registerItem(ms, stafftab);
	}

	public static void registerStaff(String name, int meleeDmg, int rangedDmg, int soulConsumed, int cd, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots)
	{
		ModStaff ms = new ModStaff(name, meleeDmg, rangedDmg, soulConsumed, bypassesArmor, rarity, texture, durability, repairs, minSlots, maxSlots).setCooldown(cd);
		staffs.add(ms);
		registerItem(ms, stafftab);
	}

	public static void registerSword(String name, int baseDmg, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots)
	{
		ModSword ms = new ModSword(name, baseDmg, bypassesArmor, rarity, texture, durability, repairs, minSlots, maxSlots);
		swords.add(ms);
		registerItem(ms, swordtab);
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
