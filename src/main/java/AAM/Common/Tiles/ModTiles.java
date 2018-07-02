package AAM.Common.Tiles;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles
{
	public static void load()
	{
		String id = "aam.";

		GameRegistry.registerTileEntity(TECauldron.class, id + "cauldron");
		GameRegistry.registerTileEntity(TECreativeCauldron.class, id + "cauldron_creative");
		GameRegistry.registerTileEntity(TESpellTable.class, id + "spelltable");
		GameRegistry.registerTileEntity(TETransCircle.class, id + "transcircle");
		GameRegistry.registerTileEntity(TESoulAltar.class, id + "soulaltar");
		GameRegistry.registerTileEntity(TEModificationAnvil.class, id + "modanvil");
		GameRegistry.registerTileEntity(TEVoider.class, id + "voider");
		GameRegistry.registerTileEntity(TEAGraviter.class, id + "agraviter");
		GameRegistry.registerTileEntity(TEGraviter.class, id + "rgraviter");
		GameRegistry.registerTileEntity(TECrystal.class, id + "pillarcrystal");
		GameRegistry.registerTileEntity(TEMechanicalBase.class, id + "mechbase");
		GameRegistry.registerTileEntity(TETeleporter.class, id + "dungteleporter");
		GameRegistry.registerTileEntity(TEBloodAltar.class, id + "bloodaltar");
	}
}
