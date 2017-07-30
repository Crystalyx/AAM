package AAM.Common.Tiles;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles
{
	public static void load()
	{
		String id = "aam.";

		GameRegistry.registerTileEntity(CauldronTileEntity.class, id + "cauldron");
		GameRegistry.registerTileEntity(CreativeCauldronTileEntity.class, id + "cauldron_creative");
		GameRegistry.registerTileEntity(TileSpellTable.class, id + "spelltable");
		GameRegistry.registerTileEntity(TETransCircle.class, id + "transcircle");
		GameRegistry.registerTileEntity(TileSoulAltar.class, id + "soulaltar");
		GameRegistry.registerTileEntity(TileArmoury.class, id + "armoury");
		GameRegistry.registerTileEntity(TEModificationAnvil.class, id + "modanvil");
		GameRegistry.registerTileEntity(VoiderTileEntity.class, id + "voider");
		GameRegistry.registerTileEntity(AGraviterTileEntity.class, id + "agraviter");
		GameRegistry.registerTileEntity(GraviterTileEntity.class, id + "rgraviter");
		GameRegistry.registerTileEntity(TEPillar.class, id + "pillar");
		GameRegistry.registerTileEntity(TECrystal.class, id + "pillarcrystal");
		GameRegistry.registerTileEntity(MechanicalBaseTE.class, id + "mechbase");
		GameRegistry.registerTileEntity(TeleporterTileEntity.class, id + "dungteleporter");
	}
}
