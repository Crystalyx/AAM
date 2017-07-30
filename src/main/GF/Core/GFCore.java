/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Core;

import GF.Network.CommonProxy;
import GF.Registry.Registry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "goldflushing",name = "Gold Flushing")
public class GFCore 
{
	
	@SidedProxy(clientSide="GF.Network.ClientProxy",serverSide="GF.Network.ClientProxy",modId="goldflushing")
	public static CommonProxy proxy;
	
	@Instance("goldflushing")
	public static GFCore core;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Registry.init();
		proxy.registerRenderThings();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
