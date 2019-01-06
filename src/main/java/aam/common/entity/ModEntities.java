package aam.common.entity;

import aam.common.entity.elemental.*;
import aam.common.entity.golem.GolemBoss;
import aam.core.AAMCore;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;

public class ModEntities
{
	public static void load()
	{
		// Mod Entities
		EntityRegistry.registerModEntity(ElementalBoss.class, "elementalboss", 240, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(ElementalGuard.class, "elemguard", 241, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(SoulCharge.class, "soulcharge", 242, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBloodball.class, "bloodball", 243, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(WastelandCreature.class, "wasteland", 244, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(Subwer.class, "subwer", 245, AAMCore.instance, 80, 3, true);
		// EntityRegistry.registerModEntity(EntityMinium.class, "minium", 246,
		// AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(GolemBoss.class, "golemboss", 246, AAMCore.instance, 80, 3, true);

		// Mapping
		EntityList.addMapping(ElementalBoss.class, "elemental", 240, 0x169b93, 0x535353);
		EntityList.addMapping(ElementalGuard.class, "elemguard", 241, 0x1a1a1a, 0xb7191c);
		EntityList.addMapping(WastelandCreature.class, "wasteland", 244, 0x5e0000, 0x000000);
		EntityList.addMapping(Subwer.class, "subwer", 245, 0x04affb, 0x1722d1);

		EntityList.addMapping(GolemBoss.class, "golemboss", 246, 0xdecfcf, 0x9b1515);
		EntityRegistry.registerModEntity(StaffCharge.class, "staffcharge", 247, AAMCore.instance, 80, 3, true);

	}
}
