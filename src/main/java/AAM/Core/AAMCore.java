package AAM.Core;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import AAM.Client.Gui.Base.ObjTypes;
import AAM.Common.Aura.AuraRegistry;
import AAM.Common.Blocks.ModBlocks;
import AAM.Common.Dungeon.DungeonProvider;
import AAM.Common.Entity.ModEntities;
import AAM.Common.Event.ClientRenderHelper;
import AAM.Common.Event.PlayerBlockEvent;
import AAM.Common.Event.PlayerDataEventHandler;
import AAM.Common.Event.PotionEventHandler;
import AAM.Common.Event.SoulEvent;
import AAM.Common.Event.WorldTracker;
import AAM.Common.Items.ModItems;
import AAM.Common.Potions.Ingridients;
import AAM.Common.Potions.ModPotions;
import AAM.Common.Recipes.Recipes;
import AAM.Common.Skills.ModSkills;
import AAM.Common.Tiles.ModTiles;
import AAM.Common.Transmutations.ModCircles;
import AAM.Common.WorldGen.DungGenerator;
import AAM.Common.WorldGen.PlantWorldGen;
import AAM.Network.ClientProxy;
import AAM.Network.CommonProxy;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Utils.TypeUtils;
import DummyCore.Core.Core;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IPage;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.base.EntryBase;
import amerifrance.guideapi.api.util.BookBuilder;
import amerifrance.guideapi.categories.CategoryItemStack;
import amerifrance.guideapi.pages.PageText;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = AAMCore.modid, version = AAMCore.version, name = AAMCore.name)
public class AAMCore
{
	public static final String modid = "aam";
	public static final String name = "AlchemyAndMagicReloaded";
	public static final String version = "0.1a";

	@Instance(AAMCore.modid)
	public static AAMCore instance;

	@SidedProxy(serverSide = "AAM.Network.CommonProxy", clientSide = "AAM.Network.ClientProxy")
	public static CommonProxy proxy;

	public static AAMConfig cfg;
	public static String cfgDir;

	public static KeyBinding soul = new KeyBinding("aam.soul", Keyboard.KEY_G, "key.categories.inventory");
	public static KeyBinding member = new KeyBinding("aam.addmember", Keyboard.KEY_H, "key.categories.inventory");
	public static KeyBinding arts = new KeyBinding("aam.arts", Keyboard.KEY_J, "key.categories.inventory");
	public static int dungdimid = 15;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		cfgDir = event.getModConfigurationDirectory().getAbsolutePath();
		File cfgFile = new File(cfgDir, name + ".cfg");
		Configuration cfgf = new Configuration(cfgFile);
		cfg = new AAMConfig(cfgf);
		TypeUtils.initStr();
		AlchemicalDispatcher.registerPackets();
		ClientRegistry.registerKeyBinding(soul);
		ClientRegistry.registerKeyBinding(member);
		ClientRegistry.registerKeyBinding(arts);

		Core.registerModAbsolute(AAMCore.class, name, event.getModConfigurationDirectory().getAbsolutePath(), cfg, true);

		MinecraftForge.EVENT_BUS.register(instance);
		MinecraftForge.EVENT_BUS.register(new SoulEvent());
		MinecraftForge.EVENT_BUS.register(new PotionEventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerDataEventHandler());
		MinecraftForge.EVENT_BUS.register(new ClientRenderHelper());
		MinecraftForge.EVENT_BUS.register(new PlayerBlockEvent());
		FMLCommonHandler.instance().bus().register(new WorldTracker());

		ModCircles.load();
		ModTiles.load();
		ModBlocks.load();
		ModSkills.register();
		ModEntities.init();
		buildBook();
		ModItems.load();
		Ingridients.load();
		AuraRegistry.register();
		ObjTypes.load();
		ModPotions.load();
		Recipes.load();

		DimensionManager.registerProviderType(dungdimid, DungeonProvider.class, false);
		DimensionManager.registerDimension(dungdimid, dungdimid);

		GameRegistry.registerWorldGenerator(new PlantWorldGen(), 0);
		GameRegistry.registerWorldGenerator(new DungGenerator(), 0);
		ClientProxy.registerRenders();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		if (instance == null)
			instance = this;

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	public static Book Enchir;

	public static void buildBook()
	{
		List<EntryAbstract> entries = new ArrayList<EntryAbstract>();

		ArrayList<IPage> e1 = new ArrayList<IPage>();
		e1.add(new PageText("aam.e1.txt1", 0));
		entries.add(new EntryBase(e1, "aam.e1.name"));
		ArrayList<IPage> e2 = new ArrayList<IPage>();
		e1.add(new PageText("aam.e2.txt1", 0));
		entries.add(new EntryBase(e1, "aam.e2.name"));
		ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
		categories.add(new CategoryItemStack(entries, "aam.magic.name", new ItemStack(Items.painting)));
		BookBuilder builder = new BookBuilder();
		builder.setCategories(categories);
		builder.setUnlocBookTitle("aam.enchir.title");
		builder.setUnlocDisplayName("aam.enchir.name");
		builder.setBookColor(Color.cyan);
		Enchir = builder.build();
		GuideRegistry.registerBook(Enchir);
	}

}
