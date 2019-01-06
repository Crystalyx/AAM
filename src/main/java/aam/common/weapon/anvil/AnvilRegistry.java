package aam.common.weapon.anvil;

import aam.common.items.ModItems;
import aam.common.items.weapon.anvil.WeaponPartItem;
import net.minecraft.item.EnumRarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnvilRegistry
{
	public static List<WeaponPart> parts = new ArrayList<>();
	public static List<ForgingMaterial> materials = new ArrayList<>();
	public static HashMap<ForgingMaterial, List<WeaponPart>> matTable = new HashMap<ForgingMaterial, List<WeaponPart>>();

	public static ForgingMaterial steel;
	public static ForgingMaterial mithril;
	public static ForgingMaterial quantumSteel;
	public static ForgingMaterial brass;
	public static ForgingMaterial antibrass;
	public static ForgingMaterial soulcrystal;
	public static ForgingMaterial redcrystal;
	public static ForgingMaterial wormwood;

	public static void load()
	{
		steel = registerMaterial("steel", "aam:steel_ingot", 300, 12);
		mithril = registerMaterial("mithril", "aam:mithril_ingot", 300, 24);
		quantumSteel = registerMaterial("quantumSteel", "aam:quantum_steel_ingot", 300, 30);
		brass = registerMaterial("brass", "aam:brass_ingot", 300, 20);
		antibrass = registerMaterial("antibrass", "aam:antibrass_ingot", 300, 10);
		soulcrystal = registerMaterial("soulcrystal", "aam:soul_crystal", 250, 24);
		redcrystal = registerMaterial("redcrystal", "aam:redstone_crystal", 250, 12);
		wormwood = registerMaterial("wormwood", "aam:polished_wormwood", 100, 12);

		registerPart("broad_blade", PartType.blade, "aam:anvil/broad_blade", "aam:anvil/passes/broad_blade", false, steel, 200).setAttack(8).setDurability(2000).setRepairs(1).setSlots(1);
		registerPart("rapier_blade", PartType.blade, "aam:anvil/rapier_blade", "aam:anvil/passes/rapier_blade", true, steel, 150).setAttack(6).setDurability(2000).setRepairs(1).setSlots(1);
		registerPart("metal_handle", PartType.handle, "aam:anvil/metal_handle", "aam:anvil/passes/metal_handle", false, steel, 75).setDurability(1000).setRepairs(1).setSlots(1);
		registerPart("curly_metal_handle", PartType.handle, "aam:anvil/curly_metal_handle", "aam:anvil/passes/curly_metal_handle", false, steel, 100).setDurability(1000).setRepairs(1).setSlots(1);
		registerPart("saw_broad_blade", PartType.blade, "aam:anvil/saw_broad_blade", "aam:anvil/passes/saw_broad_blade", true, steel, 300).setAttack(12).setDurability(2000).setRepairs(1).setSlots(1);

		registerPart("mechanical_collector", PartType.collector, "aam:staff_parts/mechanical_collector", "aam:staff_parts/passes/mechanical_collector", false, steel, 150).setAttack(4).setDurability(750).setRepairs(1)
				.setRarity(EnumRarity.rare).setSlots(1).setSoulConsumed(2);
		registerPart("soul_collector", PartType.collector, "aam:staff_parts/soul_collector", "aam:staff_parts/passes/soul_collector", false, brass, 150).setAttack(6).setDurability(1500).setRepairs(1).setRarity(EnumRarity.rare).setSlots(2)
				.setSoulConsumed(4);
		registerPart("perfect_collector", PartType.collector, "aam:staff_parts/perfect_collector", "aam:staff_parts/passes/perfect_collector", true).setAttack(12).setDurability(6000).setRepairs(3).setRarity(EnumRarity.epic).setSlots(2)
				.setSoulConsumed(8);

		registerPart("mechanical_emitter", PartType.emitter, "aam:staff_parts/mechanical_emitter", "aam:staff_parts/passes/mechanical_emitter", false, steel, 100).setAttack(3).setDurability(1500).setRepairs(1).setRarity(EnumRarity.rare)
				.setSlots(1).setSoulConsumed(2);
		registerPart("soul_emitter", PartType.emitter, "aam:staff_parts/soul_emitter", "aam:staff_parts/passes/soul_emitter", false, brass, 100).setAttack(4).setDurability(2250).setRepairs(1).setRarity(EnumRarity.rare).setSlots(2)
				.setSoulConsumed(4);
		registerPart("perfect_emitter", PartType.emitter, "aam:staff_parts/perfect_emitter", "aam:staff_parts/passes/perfect_emitter", true).setAttack(8).setDurability(4000).setRepairs(3).setRarity(EnumRarity.epic).setSlots(1)
				.setSoulConsumed(8);

		registerPart("mechanical_gem", PartType.gem, "aam:staff_parts/mechanical_gem", "aam:staff_parts/passes/mechanical_gem", false, redcrystal, 250).setAttack(8).setDurability(400).setRepairs(1).setRarity(EnumRarity.rare).setSlots(0)
				.setSoulConsumed(2);
		registerPart("soul_gem", PartType.gem, "aam:staff_parts/soul_gem", "aam:staff_parts/passes/soul_gem", false, soulcrystal, 250).setAttack(12).setDurability(1500).setRepairs(1).setRarity(EnumRarity.rare).setSlots(1)
				.setSoulConsumed(4);
		registerPart("perfect_gem", PartType.gem, "aam:staff_parts/perfect_gem", "aam:staff_parts/passes/perfect_gem", true).setAttack(20).setDurability(2000).setRepairs(3).setRarity(EnumRarity.epic).setSlots(0).setSoulConsumed(8);

		registerPart("mechanical_stick", PartType.wandrod, "aam:staff_parts/mechanical_stick", "aam:staff_parts/passes/mechanical_stick", false, steel, 100).setAttack(8).setDurability(800).setRepairs(1).setRarity(EnumRarity.rare)
				.setSlots(1).setSoulConsumed(2);
		registerPart("soul_stick", PartType.wandrod, "aam:staff_parts/soul_stick", "aam:staff_parts/passes/soul_stick", false, wormwood, 100).setAttack(12).setDurability(2000).setRepairs(1).setRarity(EnumRarity.rare).setSlots(2)
				.setSoulConsumed(4);
		registerPart("perfect_stick", PartType.wandrod, "aam:staff_parts/perfect_stick", "aam:staff_parts/passes/perfect_stick", true).setAttack(20).setDurability(5000).setRepairs(3).setRarity(EnumRarity.epic).setSoulConsumed(8)
				.setSlots(3);

	}

	public static int lastId = 0;

	public static WeaponPart registerPart(String name, PartType type, String path, String pass, boolean bypasses)
	{
		WeaponPart pt = new WeaponPart(lastId++, name, type, path, pass, bypasses);
		WeaponPartItem pti = new WeaponPartItem(pt);
		ModItems.parts.add(pti);
		parts.add(pt);
		return pt;
	}

	public static WeaponPart registerPart(String name, PartType type, String path, String pass, boolean bypasses, ForgingMaterial fm, int materialCost)
	{
		WeaponPart pt = new WeaponPart(lastId++, name, type, path, pass, bypasses, fm, materialCost);
		WeaponPartItem pti = new WeaponPartItem(pt);
		ModItems.parts.add(pti);
		parts.add(pt);
		if (matTable.containsKey(fm))
		{
			matTable.get(fm).add(pt);
		}
		else
		{
			List l = new ArrayList();
			l.add(pt);
			matTable.put(fm, l);
		}
		return pt;
	}

	public static ForgingMaterial registerMaterial(String name, String icon, int maxDamage, int enchantability)
	{
		ForgingMaterial Fm = new ForgingMaterial(name, icon, maxDamage, enchantability);
		materials.add(Fm);
		return Fm;
	}

}
