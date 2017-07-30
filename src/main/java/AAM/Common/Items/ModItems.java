package AAM.Common.Items;

import AAM.Common.Items.Resources.AltersteelIngot;
import AAM.Common.Items.Resources.AtrilliumIngot;
import AAM.Common.Items.Resources.Berry;
import AAM.Common.Items.Resources.Coin;
import AAM.Common.Items.Resources.ElementalShard;
import AAM.Common.Items.Resources.Material;
import AAM.Common.Items.Resources.SwordDye;
import AAM.Core.AAMCore;
import DummyCore.Items.ItemRegistry;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems
{
	static AAMCore core = AAMCore.instance;
	static String idn = AAMCore.modid + ".";

	public static Item Berry = new Berry().setHasSubtypes(true).setUnlocalizedName(idn + "berry");
	public static Item MortarAndPestle = new MortarAndPestle().setUnlocalizedName(idn + "MortarAndPestle");
	public static Item Potion = new AlchPotion().setUnlocalizedName(idn + "alchpotion");
	public static Item RainbowSword = new RainbowSword(EnumHelper.addToolMaterial("Rainbow", 121, 2500, 5.0F, 60.0F, 100)).setUnlocalizedName(idn + "rainbowsword");
	public static Item Nullifier = new Nullifier().setUnlocalizedName(idn + "nullifier");
	public static Item ChalkPattern = new ChalkPattern().setUnlocalizedName(idn + "pattern");
	public static Item Shield = new SwordDye().setUnlocalizedName(idn + "shield");
	public static Item SoulSword = new SoulSword(EnumHelper.addToolMaterial("AAMSoul", 121, -1, 0.0F, -5.0F, 100)).setUnlocalizedName(idn + "soulsword");

	public static Item Structurer = new Structurer().setUnlocalizedName(idn + "structurer");

	public static Item materials = new Material().setUnlocalizedName(idn + "material");
	public static Item FacelessCharm = new FacelessCharm(1).setUnlocalizedName(idn + "facelesscharm");

	public static Item Artifact = new Artifact().setUnlocalizedName(idn + "artifact");
	public static Item TeleportationCrystal = new TeleportationCrystal().setUnlocalizedName(idn + "crystaltp");
	public static Item RessurectionStone = new RessurectionStone().setUnlocalizedName(idn + "ressstone");
	public static Item MassRessurectionStone = new MassRessurectionStone().setUnlocalizedName(idn + "massressstone");

	public static Item coins = new Coin().setUnlocalizedName(idn + "coin");
	public static Item KingsStone = new KingsStone();

	// =======================RogueWorld===========================
	public static Item RiteBook = new RiteBook();
	public static Item STea = new SoothingTea();
	public static Item boundsph = new BoundSphere();

	public static Item AuraRing = new AuraRing().setUnlocalizedName(idn + "ring_aura");
	public static Item LuckyCoin = new LuckyCoin().setUnlocalizedName(idn + "luckycoin");
	public static Item ElementalHeart = new ElementalHeart();
	public static Item SeaShard = new SeaShard().setTextureName("aam:shard_sea");
	public static Item CliserActivator = new CliserActivator().setUnlocalizedName(idn + "cliser");
	public static Item RedRadio = new RedRadio().setUnlocalizedName(idn + "redradio");
	public static Item ClockOfTime = new ClockOfTime().setUnlocalizedName(idn + "clocktime");
	public static Item CrystalBow = new CrystalBow().setUnlocalizedName(idn + "crystalbow");
	public static Item AnvilHammer = new AnvilHammer(0, EnumHelper.addToolMaterial("AAMAHammer", 4, 450, 10.0F, 6.0F, 15)).setUnlocalizedName(idn + "anvilhammer");
	public static Item AlchPaper = new AlchPaper().setUnlocalizedName(idn + "alchpaper");
	public static Item CircleDust = new CircleDust().setUnlocalizedName(idn + "alchdust");
	public static Item ItemChalk = new ItemChalk().setUnlocalizedName(idn + "itemchalk");

	public static void load()
	{
		ItemRegistry.registerItem(Berry, Berry.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(MortarAndPestle, MortarAndPestle.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(Potion, Potion.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(RainbowSword, RainbowSword.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(Nullifier, Nullifier.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(ChalkPattern, ChalkPattern.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(Shield, Shield.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(SoulSword, SoulSword.getUnlocalizedName(), AAMCore.class);

		ItemRegistry.registerItem(Structurer, Structurer.getUnlocalizedName(), AAMCore.class);

		ItemRegistry.registerItem(FacelessCharm, FacelessCharm.getUnlocalizedName(), AAMCore.class);

		((Material) materials).addMaterial("blood_upgrade");
		((Material) materials).addMaterial("cast_upgrade");
		((Material) materials).addMaterial("moon_upgrade");
		((Material) materials).addMaterial("emptyphial");
		((Material) materials).addMaterial("soul_slag");
		((Material) materials).addMaterial("soul_slag_inf");

//		((Material) materials).addMaterial("iron_plate");
//		((Material) materials).addMaterial("magical_core");

		((Material) coins).addMaterial("coin_copper");
		((Material) coins).addMaterial("coin_silver");
		((Material) coins).addMaterial("coin_gold");
		((Material) coins).addMaterial("coin_spectral");

		ItemRegistry.registerItem(materials, materials.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(Artifact, Artifact.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(TeleportationCrystal, TeleportationCrystal.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(RessurectionStone, RessurectionStone.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(MassRessurectionStone, MassRessurectionStone.getUnlocalizedName(), AAMCore.class);

		ItemRegistry.registerItem(RiteBook, "rogueWorldRiteBook", AAMCore.class);
		ItemRegistry.registerItem(STea, "rogueWorldSTea", AAMCore.class);

		if (Loader.isModLoaded("DummyCore"))
			ItemRegistry.registerItem(boundsph, boundsph.getUnlocalizedName(), AAMCore.class);

		ItemRegistry.registerItem(AuraRing, AuraRing.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(coins, coins.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(LuckyCoin, LuckyCoin.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(KingsStone, KingsStone.getUnlocalizedName(), AAMCore.class);

		ItemRegistry.registerItem(ElementalHeart, ElementalHeart.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(SeaShard, SeaShard.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(CliserActivator, CliserActivator.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(RedRadio, RedRadio.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(ClockOfTime, ClockOfTime.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(CrystalBow, CrystalBow.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(AnvilHammer, AnvilHammer.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(AlchPaper, AlchPaper.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(CircleDust, CircleDust.getUnlocalizedName(), AAMCore.class);
		ItemRegistry.registerItem(ItemChalk, ItemChalk.getUnlocalizedName(), AAMCore.class);

	}

}
