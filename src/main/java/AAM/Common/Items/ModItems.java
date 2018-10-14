package aam.common.items;

import java.util.ArrayList;
import java.util.List;

import aam.api.GameWeapon;
import aam.api.abstraction.MeleeWeapon;
import aam.api.abstraction.RangedWeapon;
import aam.common.blocks.building.ModBlocks;
import aam.common.items.alchemy.AirGem;
import aam.common.items.alchemy.AlchPaper;
import aam.common.items.alchemy.AlchemicalBucket;
import aam.common.items.alchemy.AlchemicalConcentrateItem;
import aam.common.items.alchemy.AlchemicalPotionItem;
import aam.common.items.alchemy.BloodBucket;
import aam.common.items.alchemy.ConcentratePhial;
import aam.common.items.alchemy.EarthGem;
import aam.common.items.alchemy.FireGem;
import aam.common.items.alchemy.IceGem;
import aam.common.items.alchemy.ItemChalk;
import aam.common.items.alchemy.MiniumShard;
import aam.common.items.alchemy.MiniumStone;
import aam.common.items.alchemy.MortarAndPestle;
import aam.common.items.alchemy.PhilosophersStone;
import aam.common.items.alchemy.SilverClock;
import aam.common.items.artifacts.CrystalBow;
import aam.common.items.artifacts.ElementalHeart;
import aam.common.items.artifacts.FacelessCharm;
import aam.common.items.artifacts.KingsStone;
import aam.common.items.artifacts.LuckyCoin;
import aam.common.items.artifacts.MassRessurectionStone;
import aam.common.items.artifacts.RessurectionStone;
import aam.common.items.artifacts.SeaShard;
import aam.common.items.artifacts.WatchOfTime;
import aam.common.items.debug.BoundSphere;
import aam.common.items.debug.CliserActivator;
import aam.common.items.debug.MagicWand;
import aam.common.items.debug.RedRadio;
import aam.common.items.debug.RiteBook;
import aam.common.items.pouches.LeatherPouch;
import aam.common.items.resources.Berry;
import aam.common.items.resources.BerryDust;
import aam.common.items.resources.Coin;
import aam.common.items.resources.Material;
import aam.common.items.resources.SwordDye;
import aam.common.items.soul.Artifact;
import aam.common.items.soul.SoulSword;
import aam.common.items.soul.SoulUpgradeItem;
import aam.common.items.weapon.BlueEyedSword;
import aam.common.items.weapon.EnderBroadsword;
import aam.common.items.weapon.KingsSword;
import aam.common.items.weapon.ModificationCatalyst;
import aam.common.items.weapon.anvil.ForgedMeleeWeapon;
import aam.common.items.weapon.anvil.ForgedRangedWeapon;
import aam.common.items.weapon.anvil.ForgingMaterialItem;
import aam.common.items.weapon.anvil.WeaponPartItem;
import aam.common.tabs.MeleeTab;
import aam.common.tabs.RangedTab;
import aam.common.weapon.anvil.AnvilRegistry;
import aam.common.weapon.anvil.ForgingMaterial;
import aam.core.AAMCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
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
	static CreativeTabs meleetab = new MeleeTab();
	static CreativeTabs rangedtab = new RangedTab();

	// =======================RogueWorld===========================
	public static Item RiteBook = new RiteBook();
	public static Item STea = new SoothingTea();

	// =======================Artifacts===========================
	public static Item coins = new Coin().setUnlocalizedName(idn + "coin");
	public static Item LuckyCoin = new LuckyCoin().setUnlocalizedName(idn + "lucky_coin");

	public static Item FacelessCharm = new FacelessCharm(1).setUnlocalizedName(idn + "faceless_charm");
	public static Item ElementalHeart = new ElementalHeart();
	public static Item SeaShard = new SeaShard().setTextureName("aam:shard_sea");
	public static Item WatchOfTime = new WatchOfTime().setUnlocalizedName(idn + "clock_of_time");
	public static Item KingsStone = new KingsStone();

	public static Item RessurectionStone = new RessurectionStone().setUnlocalizedName(idn + "ressurection_stone");
	public static Item MassRessurectionStone = new MassRessurectionStone().setUnlocalizedName(idn + "mass_ressurection_stone");

	// =======================Debug===========================
	public static Item MagicWand = new MagicWand().setUnlocalizedName(idn + "magic_wand");
	public static Item CliserActivator = new CliserActivator().setUnlocalizedName(idn + "cliser");
	public static Item RedRadio = new RedRadio().setUnlocalizedName(idn + "red_radio");
	public static Item boundsph = new BoundSphere();

	// =======================Potions===========================
	public static Item Berry = new Berry().setUnlocalizedName(idn + "berry");
	public static Item BerryDust = new BerryDust().setUnlocalizedName(idn + "berry_dust");
	public static Item MortarAndPestle = new MortarAndPestle().setUnlocalizedName(idn + "mortar_and_pestle");
	public static Item Potion = new AlchemicalPotionItem().setUnlocalizedName(idn + "alchemical_potion");

	// =======================Soul===========================
	public static Item Artifact = new Artifact().setUnlocalizedName(idn + "artifact");
	public static Item SoulLens = new SwordDye().setUnlocalizedName(idn + "shield");
	public static Item SoulSword = new SoulSword();
	public static Material materials = (Material) new Material().setUnlocalizedName(idn + "material");
	public static Item CrystalBow = new CrystalBow().setUnlocalizedName(idn + "crystal_bow");
	public static Item AnvilHammer = new AnvilHammer(0, EnumHelper.addToolMaterial("AAMAHammer", 4, 450, 10.0F, 6.0F, 15)).setUnlocalizedName(idn + "anvil_hammer");
	public static Item SoulUpgradeItem = new SoulUpgradeItem().setUnlocalizedName(idn + "soul_upgrade");

	// =======================Alchemy===========================
	public static Item TeleportationCrystal = new TeleportationCrystal().setUnlocalizedName(idn + "teleportation_crystal");
	public static Item LinkObol = new LinkObol().setUnlocalizedName(idn + "obol");
	public static Item AlchPaper = new AlchPaper().setUnlocalizedName(idn + "alch_paper");
	public static Item ItemChalk = new ItemChalk().setUnlocalizedName(idn + "item_chalk");
	public static Item MiniumShard = new MiniumShard().setUnlocalizedName(idn + "minium_shard");
	public static Item MiniumStone = new MiniumStone().setUnlocalizedName(idn + "minium_stone");
	public static Item PhilosophersStone = new PhilosophersStone().setUnlocalizedName(idn + "philosophers_stone");
	public static Item BloodBucket = new BloodBucket().setUnlocalizedName(idn + "blood_bucket");
	public static Item SilverClock = new SilverClock();
	public static Item smallConcentrate = new AlchemicalConcentrateItem(0).setUnlocalizedName(idn + "alchemical_concentrate0");
	public static Item mediumConcentrate = new AlchemicalConcentrateItem(1).setUnlocalizedName(idn + "alchemical_concentrate1");
	public static Item bigConcentrate = new AlchemicalConcentrateItem(2).setUnlocalizedName(idn + "alchemical_concentrate2");
	public static Item ModificationCatalyst = new ModificationCatalyst();
	public static Item BerryAssorty = new Item().setUnlocalizedName(idn + "berry_assorty").setTextureName("aam:ingredients/assorti");
	public static Item AlchemicalBucket = new AlchemicalBucket();
	public static Item ConcentratePhial = new ConcentratePhial();
	public static Item FireGem = new FireGem();
	public static Item IceGem = new IceGem();
	public static Item EarthGem = new EarthGem();
	public static Item AirGem = new AirGem();
	public static Item LeatherPouch = new LeatherPouch();
	public static MeleeWeapon KingsSword = new KingsSword();
	public static MeleeWeapon EnderBroadsword = new EnderBroadsword();
	public static MeleeWeapon BlueEyedSword = new BlueEyedSword();
	public static MeleeWeapon ForgedMeleeWeapon = new ForgedMeleeWeapon();
	public static RangedWeapon ForgedRangedWeapon = new ForgedRangedWeapon();
	public static Item flux = new Item().setUnlocalizedName(idn + "flux").setTextureName("aam:flux");

	public static List<GameWeapon> miscWeapon = new ArrayList<>();
	public static List<MeleeWeapon> melee = new ArrayList<>();
	public static List<RangedWeapon> ranged = new ArrayList<>();
	public static List<WeaponPartItem> parts = new ArrayList<>();
	public static List<ForgingMaterialItem> toolMaterials = new ArrayList<>();

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
		registerItem(WatchOfTime, arts);

		registerItem(RessurectionStone, arts);
		registerItem(MassRessurectionStone, arts);

		// =======================Debug===========================
		registerItem(MagicWand, misc);
		registerItem(boundsph, misc);
		registerItem(CliserActivator, misc);
		registerItem(RedRadio, misc);

		// =======================Alchemy===========================
		registerItem(TeleportationCrystal, alchemy);
		registerItem(AlchPaper, alchemy);
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
		registerItem(BerryAssorty, alchemy);
		registerItem(AlchemicalBucket, alchemy);
		registerItem(ConcentratePhial, alchemy);
		registerItem(FireGem, alchemy);
		registerItem(IceGem, alchemy);
		registerItem(EarthGem, alchemy);
		registerItem(AirGem, alchemy);

		// =======================Soul===========================
		registerItem(LeatherPouch, soul);
		registerItem(SoulLens, soul);
		registerItem(SoulSword, soul);
		materials.addMaterial("atrillium_ingot");
		materials.addMaterial("brass_ingot");
		materials.addMaterial("altersteel_ingot");
		materials.addMaterial("potions/empty_phial");
		materials.addMaterial("atrillium_scale");
		materials.addMaterial("brass_scale");
		materials.addMaterial("altersteel_scale");

		registerItem(materials, soul);
		registerItem(Artifact, soul);
		registerItem(CrystalBow, soul);
		registerItem(AnvilHammer, soul);
		registerItem(SoulUpgradeItem, soul);
		registerItem(ModificationCatalyst, soul);
		registerItem(flux, soul);

		// =======================RogueWorld===========================
		registerItem(RiteBook, misc);
		registerItem(STea, misc);

		for (WeaponPartItem pt : parts)
		{
			registerItem(pt, soul);
		}
		for (ForgingMaterial mat : AnvilRegistry.materials)
		{
			ForgingMaterialItem fmi = new ForgingMaterialItem(mat);
			toolMaterials.add(fmi);
			registerItem(fmi, soul);
		}

		registerItem(ForgedMeleeWeapon, null);
		registerItem(ForgedRangedWeapon, null);

		registerMeleeFully("aam.item.sword.blood", 8, false, EnumRarity.rare, "aam:sword/blood", 4000, 3, 1, 3, ModItems.MiniumShard, 0);
		registerMeleeFully("aam.item.sword.brass", 17, false, EnumRarity.uncommon, "aam:sword/brass", 3000, 3, 0, 2, Items.gold_ingot, 0);
		registerRangedFully("aam.item.staff.blood", 6, 12, 8, false, EnumRarity.rare, "aam:staff/blood", 4000, 3, 1, 3, ModItems.MiniumShard, 0);

		// Leaders set
		registerMeleeFully("aam.item.sword.leader.broad", 32, false, blood, "aam:sword/leader_broadsword", 8000, 3, 0, 3, ModItems.MiniumShard, 0);
		registerMeleeFully("aam.item.sword.leader.rapier", 28, true, blood, "aam:sword/leader_rapier", 8000, 3, 0, 3, ModItems.MiniumShard, 0);
		registerMeleeFully("aam.item.spear.leader.spear", 28, true, blood, "aam:tank/leader_spear", 8000, 3, 0, 3, ModItems.MiniumShard, 0).setReach(8);
		registerMeleeFully("aam.item.axe.leader.battleaxe", 32, false, blood, "aam:tank/leader_axe", 8000, 3, 0, 3, ModItems.MiniumShard, 0).setReach(8);
		registerRangedFully("aam.item.staff.leader.wand", 17, 28, 8, true, blood, "aam:staff/leader_wand", 8000, 3, 0, 3, ModItems.MiniumShard, 0);
		registerRangedFully("aam.item.staff.leader.staff", 20, 32, 8, false, blood, "aam:staff/leader_staff", 8000, 3, 0, 3, ModItems.MiniumShard, 0);
		// Leaders end

		registerGameWeapon(KingsSword, meleetab);
		registerGameWeapon(EnderBroadsword, meleetab);
		registerGameWeapon(BlueEyedSword, meleetab);

	}

	public static RangedWeapon registerRangedFully(String name, int meleeDmg, int rangedDmg, int soulConsumed, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots,
			Item repairItem, int meta)
	{
		RangedWeapon ms = new RangedWeapon(name, meleeDmg, rangedDmg, soulConsumed, minSlots, maxSlots);
		ms.setBypassesArmor(bypassesArmor).setRarity(rarity).setTexture(texture).setDurability(durability).setRepairs(repairs).setRepairItem(repairItem, meta);
		ranged.add(ms);
		registerItem(ms, rangedtab);
		return ms;
	}

	public static RangedWeapon registerRanged(String name, int meleeDmg, int rangedDmg, int soulConsumed, int cd, int minSlots, int maxSlots)
	{
		RangedWeapon ms = new RangedWeapon(name, meleeDmg, rangedDmg, soulConsumed, minSlots, maxSlots);
		ranged.add(ms);
		registerItem(ms, rangedtab);
		return ms;
	}

	public static MeleeWeapon registerMeleeFully(String name, int baseDmg, boolean bypassesArmor, EnumRarity rarity, String texture, int durability, int repairs, int minSlots, int maxSlots, Item repairItem, int meta)
	{
		MeleeWeapon ms = new MeleeWeapon(name, minSlots, maxSlots);
		ms.setBaseDmg(null, baseDmg).setBypassesArmor(bypassesArmor).setRarity(rarity).setTexture(texture).setDurability(durability).setRepairs(repairs).setRepairItem(repairItem, meta);
		melee.add(ms);
		registerItem(ms, meleetab);
		return ms;
	}

	public static MeleeWeapon registerMeleeFully(String name, int minSlots, int maxSlots)
	{
		MeleeWeapon ms = new MeleeWeapon(name, minSlots, maxSlots);
		melee.add(ms);
		registerItem(ms, meleetab);
		return ms;
	}

	public static GameWeapon registerGameWeapon(GameWeapon weap, CreativeTabs tab)
	{
		miscWeapon.add(weap);
		registerItem(weap, tab);
		return weap;
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
