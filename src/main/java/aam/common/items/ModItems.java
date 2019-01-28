package aam.common.items;

import java.util.ArrayList;
import java.util.List;

import aam.api.abstraction.GameWeapon;
import aam.api.abstraction.MeleeWeapon;
import aam.api.abstraction.RangedWeapon;
import aam.api.abstraction.Sheath;
import aam.api.fluent.PreparedMeleeWeapon;
import aam.api.fluent.PreparedRangedWeapon;
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
import aam.common.items.sheaths.*;
import aam.common.items.soul.Artifact;
import aam.common.items.soul.SoulSword;
import aam.common.items.soul.SoulUpgradeItem;
import aam.common.items.weapon.*;
import aam.common.tabs.MeleeTab;
import aam.common.tabs.RangedTab;
import aam.core.AAMCore;
import static aam.utils.EnumRarity.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems
{
    static AAMCore core = AAMCore.instance;
    public static String idn = AAMCore.modid + ".";
    public static CreativeTabs alchemy = ModBlocks.alchemy;
    public static CreativeTabs soul = ModBlocks.soul;
    public static CreativeTabs misc = ModBlocks.misc;
    public static CreativeTabs arts = ModBlocks.arts;
    public static CreativeTabs meleetab = new MeleeTab();
    public static CreativeTabs rangedtab = new RangedTab();

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

    // =======================potions===========================
    public static Item Berry = new Berry().setUnlocalizedName(idn + "berry");
    public static Item BerryDust = new BerryDust().setUnlocalizedName(idn + "berry_dust");
    public static Item MortarAndPestle = new MortarAndPestle().setUnlocalizedName(idn + "mortar_and_pestle");
    public static Item Potion = new AlchemicalPotionItem().setUnlocalizedName(idn + "alchemical_potion");

    // =======================soul===========================
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
    public static MeleeWeapon CommonBastardSwords = new CommonBastardSword();
    public static MeleeWeapon UncommonBastardSword = new UncommonBastardSword();
    public static MeleeWeapon RareBastardSword = new RareBastardSword();
    public static MeleeWeapon UniqueBastardSword = new UniqueBastardSword();
    public static Sheath CommonBastardSheath = new CommonBastardSheath();
    public static Sheath UncommonBastardSheath= new UncommonBastardSheath();
    public static Sheath RareBastardSheath = new RareBastardSheath();
    public static Sheath UniqueBastardSheath = new UniqueBastardSheath();
    public static Item flux = new Item().setUnlocalizedName(idn + "flux").setTextureName("aam:flux");
    public static Item emptyPhial = new Item().setUnlocalizedName(idn + "empty_phial").setTextureName("aam:/potions/empty_phial");

    public static List<GameWeapon> miscWeapon = new ArrayList<>();
    public static List<MeleeWeapon> melee = new ArrayList<>();
    public static List<RangedWeapon> ranged = new ArrayList<>();

    public static void load()
    {
        // =======================potions===========================

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
        registerItem(Berry, alchemy);
        registerItem(BerryDust, alchemy);
        registerItem(MortarAndPestle, alchemy);
        registerItem(emptyPhial, alchemy);
        registerItem(Potion, alchemy);
        registerItem(smallConcentrate, alchemy);
        registerItem(mediumConcentrate, alchemy);
        registerItem(bigConcentrate, alchemy);
        registerItem(TeleportationCrystal, alchemy);
        registerItem(AlchPaper, alchemy);
        registerItem(ItemChalk, alchemy);
        registerItem(MiniumShard, alchemy);
        registerItem(MiniumStone, alchemy);
        registerItem(PhilosophersStone, alchemy);
        registerItem(BloodBucket, alchemy);
        registerItem(LinkObol, alchemy);
        registerItem(SilverClock, alchemy);
        registerItem(BerryAssorty, alchemy);
        registerItem(AlchemicalBucket, alchemy);
        registerItem(ConcentratePhial, alchemy);
        registerItem(FireGem, alchemy);
        registerItem(IceGem, alchemy);
        registerItem(EarthGem, alchemy);
        registerItem(AirGem, alchemy);

        // =======================soul===========================
        registerItem(LeatherPouch, soul);
        registerItem(SoulLens, soul);
        registerItem(SoulSword, soul);
        materials.addMaterial("atrillium_ingot");//0
        materials.addMaterial("altersteel_ingot");//1
        materials.addMaterial("brass_ingot");//2
        materials.addMaterial("antibrass_ingot");//3
        materials.addMaterial("mithril_ingot");//4
        materials.addMaterial("quantum_steel_ingot");//5
        materials.addMaterial("steel_ingot");//6

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

        PreparedMeleeWeapon.newInstance()
                .withName("sword.blood")
                .withBaseDamage(8)
                .withRarity(Rare)
                .withTexture("aam:sword/blood")
                .withDurability(2000)
                .withSlotsRanged(1, 3)
                .withRepairItem(ModItems.MiniumShard, 0).
                register();
        PreparedMeleeWeapon.newInstance()
                .withName("sword.brass")
                .withBaseDamage(17)
                .withRarity(Uncommon)
                .withTexture("aam:sword/brass")
                .withDurability(600)
                .withSlotsRanged(0, 2)
                .withRepairItem(materials, 2)
                .register();
        PreparedRangedWeapon.newInstance()
                .withName("staff.blood")
                .withMeleeDamage(6)
                .withRangedDamage(12)
                .withSoulConsumed(8)
                .withRarity(Rare)
                .withTexture("aam:staff/blood")
                .withDurability(2000)
                .withSlotsRanged(1, 3)
                .withRepairItem(ModItems.MiniumShard, 0);

        // Leaders set
        PreparedMeleeWeapon leadersMeleeBase = PreparedMeleeWeapon.newInstance()
                .withRarity(Rare)
                .withDurability(3000)
                .withSlotsRanged(0, 3)
                .withRepairItem(ModItems.MiniumShard, 0);

        leadersMeleeBase.withTexture("aam:sword/leader_broadsword")
                .withName("sword.leader.broad")
                .withBaseDamage(32)
                .register();
        leadersMeleeBase.withTexture("aam:tank/leader_axe")
                .withName("swordType.leader.battleaxe")
                .asHammer(true)
                .withReach(4)
                .register();
        leadersMeleeBase.withTexture("aam:sword/leader_rapier")
                .setBypassesArmor()
                .asHammer(false)
                .withName("sword.leader.rapier")
                .withBaseDamage(28)
                .register();
        leadersMeleeBase.withTexture("aam:tank/leader_spear")
                .withName("spear.leader.spear")
                .withReach(8)
                .register();

        PreparedRangedWeapon leadersRangedBase = PreparedRangedWeapon.newInstance()
                .withRarity(Rare)
                .withDurability(3000)
                .withSlotsRanged(0, 3)
                .withRepairItem(ModItems.MiniumShard, 0);
        leadersRangedBase.withTexture("aam:staff/leader_staff")
                .withName("staff.leader.staff")
                .withMeleeDamage(20)
                .withRangedDamage(32)
                .register();
        leadersRangedBase.withTexture("aam:staff/leader_wand")
                .withName("staff.leader.wand")
                .withMeleeDamage(17)
                .withRangedDamage(28)
                .setBypassesArmor()
                .register();
        // Leaders end

        registerGameWeapon(KingsSword, meleetab);
        registerGameWeapon(EnderBroadsword, meleetab);
        registerGameWeapon(BlueEyedSword, meleetab);

        registerGameWeapon(CommonBastardSwords, meleetab);
        registerGameWeapon(UncommonBastardSword, meleetab);
        registerGameWeapon(RareBastardSword, meleetab);
        registerGameWeapon(UniqueBastardSword, meleetab);

        registerItem(CommonBastardSheath, meleetab);
        registerItem(UncommonBastardSheath, meleetab);
        registerItem(RareBastardSheath, meleetab);
        registerItem(UniqueBastardSheath, meleetab);
        SheathRegistry.registerSheath(CommonBastardSheath);
        SheathRegistry.registerSheath(UncommonBastardSheath);
        SheathRegistry.registerSheath(RareBastardSheath);
        SheathRegistry.registerSheath(UniqueBastardSheath);
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