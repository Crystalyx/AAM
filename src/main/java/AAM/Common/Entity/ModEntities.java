package AAM.Common.Entity;

import AAM.Core.AAMCore;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;

public class ModEntities
{
	public static void init()
	{
		// Mod Entities
		EntityRegistry.registerModEntity(Elemental.class, "elemental", 240, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(ElementalGuard.class, "elemguard", 241, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(SoulCharge.class, "soulcharge", 242, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBloodball.class, "bloodball", 243, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(WastelandCreature.class, "wasteland", 244, AAMCore.instance, 80, 3, true);
		EntityRegistry.registerModEntity(Subwer.class, "subwer", 245, AAMCore.instance, 80, 3, true);

		// Mapping
		EntityList.addMapping(Elemental.class, "elemental", 240, 0x169b93, 0x535353);
		EntityList.addMapping(ElementalGuard.class, "elemguard", 241, 0x1a1a1a, 0xb7191c);
		EntityList.addMapping(WastelandCreature.class, "wasteland", 244, 0x5e0000, 0x000000);
		EntityList.addMapping(Subwer.class, "subwer", 245, 0x04affb, 0x1722d1);
	}
}
