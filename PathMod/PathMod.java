package PathMod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "pathmod", name = "Mod Path", version = "1.7.10")
public class PathMod
{
	public static Block path = new PathBlock().setBlockName("pathmod.path");
	public static Item shovel = new PathSpade().setUnlocalizedName("pathmod.shovel");
	public static Item knife = new GoldKnife().setUnlocalizedName("pathmod.knife");
	public static Item guitar = new Guitar().setUnlocalizedName("pathmod.guitar");
	public static TickHandler tickHandler = new TickHandler();
	public static PathEventHandler pathHandler = new PathEventHandler();

	@EventHandler
	public static void preInit(FMLPreInitializationEvent e)
	{
		GameRegistry.registerBlock(path, path.getUnlocalizedName());
		GameRegistry.registerItem(shovel, shovel.getUnlocalizedName());
		GameRegistry.registerItem(knife, knife.getUnlocalizedName());
		GameRegistry.registerItem(guitar, guitar.getUnlocalizedName());
		GameRegistry.addRecipe(new ItemStack(shovel), "00i", "0s0", "s00", 'i', Items.iron_ingot, 's', Items.stick);
		GameRegistry.addRecipe(new ItemStack(knife), "pGp", "nDn", "sEs", 'p', Items.ender_pearl, 'G', Items.gold_ingot, 'n', Items.gold_nugget, 'D', Items.diamond, 's', Items.stick, 'E', Items.emerald);
		GameRegistry.addRecipe(new ItemStack(guitar), "0pl", "p0p", "0p0", 'p', Blocks.planks, 'l', Blocks.log);
		FMLCommonHandler.instance().bus().register(tickHandler);
		MinecraftForge.EVENT_BUS.register(pathHandler);
	}
}
