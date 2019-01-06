package aam.common.transmutations;

import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import aam.common.transmutations.actions.entity.ActShield;
import aam.common.transmutations.actions.extended.ActBlood;
import aam.common.transmutations.actions.extended.ActPhilo;
import aam.common.transmutations.actions.extended.ActTransm;
import aam.common.transmutations.actions.item.ActAirGem;
import aam.common.transmutations.actions.item.ActEarthGem;
import aam.common.transmutations.actions.item.ActFireGem;
import aam.common.transmutations.actions.item.ActIceGem;
import aam.common.transmutations.actions.self.*;
import aam.common.transmutations.actions.waves.*;
import aam.common.transmutations.actions.world.*;
import aam.utils.Color;
import aam.utils.MathUtils;
import aam.utils.TypeUtils;
import aam.utils.vectors.VectorWorld;
import aam.utils.vectors.Wec3;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ModCircles
{
	public static final float K = 20;

	// C
	public static CirclePart circle_big = new CirclePart("circle_big", "circle_big", "circle_big");
	public static CirclePart circle_med = new CirclePart("circle_medium", "circle_medium", "circle_medium");
	public static CirclePart circle_small = new CirclePart("circle_small", "circle_small", "circle_small");

	// R
	public static CirclePart rect_big = new CirclePart("rect_big", "rhomb_big", "rect_big");
	public static CirclePart rect_med = new CirclePart("rect_medium", "rhomb_medium", "rect_medium");
	public static CirclePart rect_small = new CirclePart("rect_small", "rhomb_small", "rect_small");

	// T
	public static CirclePart triangle_big = new CirclePart("triangle_big", "triangle_rev_big", "triangle_big");
	public static CirclePart triangle_med = new CirclePart("triangle_medium", "triangle_rev_medium", "triangle_medium");
	public static CirclePart triangle_small = new CirclePart("triangle_small", "triangle_rev_small", "triangle_small");

	// S
	public static CirclePart triangles_big = new CirclePart("triangles_big", "triangles_rev_big", "triangles_big");
	public static CirclePart triangles_medium = new CirclePart("triangles_medium", "triangles_rev_medium", "triangles_medium");
	public static CirclePart triangles_small = new CirclePart("triangles_small", "triangles_rev_small", "triangles_small");

	// M
	public static CirclePart troon_big = new CirclePart("moon_big", "tree_big", "troon_big");
	public static CirclePart troon_medium = new CirclePart("moon_medium", "tree_medium", "troon_medium");
	public static CirclePart troon_small = new CirclePart("moon_small", "tree_small", "troon_small");

	// L
	public static CirclePart shight_big = new CirclePart("shield_big", "light_big", "shight_big");
	public static CirclePart shight_medium = new CirclePart("shield_medium", "light_medium", "shight_medium");
	public static CirclePart shight_small = new CirclePart("shield_small", "light_small", "shight_small");

	// H
	public static CirclePart hex_big = new CirclePart("hex_big", "hex_rev_big", "hex_big");
	public static CirclePart hex_medium = new CirclePart("hex_medium", "hex_rev_medium", "hex_medium");
	public static CirclePart hex_small = new CirclePart("hex_small", "hex_rev_small", "hex_small");

	// P1-3
	public static CirclePart pentagon_big = new CirclePart("pentagon_big", "pentagon_big_rev", "pentagon_big");
	public static CirclePart pentagon_medium = new CirclePart("pentagon_medium", "pentagon_medium_rev", "pentagon_medium");
	public static CirclePart pentagon_small = new CirclePart("pentagon_small", "pentagon_small_rev", "pentagon_small");

	// P4-6
	public static CirclePart pentagram_big = new CirclePart("pentagram_big", "pentagram_big_rev", "pentagram_big");
	public static CirclePart pentagram_medium = new CirclePart("pentagram_medium", "pentagram_medium_rev", "pentagram_medium");
	public static CirclePart pentagram_small = new CirclePart("pentagram_small", "pentagram_small_rev", "pentagram_small");

	// E - extended
	public static CirclePart extTransm = new CirclePart("transm", "transm", true);
	public static CirclePart extPhilo = new CirclePart("philo", "philo", true);
	public static CirclePart extBlood = new CirclePart("blood", "blood", true);

	public static int nextInt(int next)
	{
		return MathUtils.r.nextInt(next);
	}

	public static List<CirclePart> parts = new ArrayList<>();
	public static List<String> partsr = new ArrayList<>();
	public static int count = 0;

	public static List<Transmutation> circles = new ArrayList<>();

	public static void loadChalks()
	{
		addPart(circle_big, "C1");
		addPart(circle_med, "C2");// C
		addPart(circle_small, "C3");

		addPart(rect_big, "R1");
		addPart(rect_med, "R2");// R
		addPart(rect_small, "R3");

		addPart(triangle_big, "T1");
		addPart(triangle_med, "T2");// T
		addPart(triangle_small, "T3");

		addPart(triangles_big, "S1");
		addPart(triangles_medium, "S2");// S
		addPart(triangles_small, "S3");

		addPart(troon_big, "M1");
		addPart(troon_medium, "M2");// M
		addPart(troon_small, "M3");

		addPart(shight_big, "L1");
		addPart(shight_medium, "L2");// L
		addPart(shight_small, "L3");

		addPart(hex_big, "H1");
		addPart(hex_medium, "H2");// H
		addPart(hex_small, "H3");

		addPart(pentagon_big, "P1");
		addPart(pentagon_medium, "P2");// H
		addPart(pentagon_small, "P3");

		addPart(pentagram_big, "P4");
		addPart(pentagram_medium, "P5");// H
		addPart(pentagram_small, "P6");

		addPart(extTransm, "E1");// E - extended
		addPart(extPhilo, "E2");
		addPart(extBlood, "E3");
	}

	public static boolean reloading = false;

	public static void loadCircles()
	{
		addForReversed("explosions", "C11nT11nS11rC21n", new ActExplosions(0), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "C21");
		addForReversed("explosions", "C11nT21rS21nC21n", new ActExplosions(1), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "C21");

		addForReversed("growth", "C11nM11rC21n", new ActGrowth(0), new Color(40, 255, 60), new Color(140, 255, 160), "C11", "C21");
		addForReversed("growth", "C11nM21rC21n", new ActGrowth(1), new Color(40, 255, 60), new Color(140, 255, 160), "C11", "C21");
		addForReversed("growth", "C11nM31rC21n", new ActGrowth(2), new Color(40, 255, 60), new Color(140, 255, 160), "C11", "C21");

		addForReversed("death", "C11nM11rC21nS11nS11r", new ActDeath(0), new Color(100, 100, 100), new Color(0, 0, 0), "C11", "C21");
		addForReversed("death", "C11nM21rC21nS21nS21r", new ActDeath(1), new Color(100, 100, 100), new Color(0, 0, 0), "C11", "C21");
		addForReversed("death", "C11nM31rC21nS31nS31r", new ActDeath(2), new Color(100, 100, 100), new Color(0, 0, 0), "C11", "C21");

		addForReversed("shield", "L11nC11nC21n", new ActShield(0), new Color(105, 105, 105), new Color(205, 205, 205), "C11", "C21");
		addForReversed("shield", "L21nC11nC21n", new ActShield(1), new Color(105, 105, 105), new Color(205, 205, 205), "C11", "C21");
		addForReversed("shield", "L31nC11nC21n", new ActShield(2), new Color(105, 105, 105), new Color(205, 205, 205), "C11", "C21");

		addForReversed("light", "C11nL11rC21n", new ActLight(0), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C21");
		addForReversed("light", "C11nL21rC21n", new ActLight(1), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C21");
		addForReversed("light", "C11nL31rC21n", new ActLight(2), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C21");

		addForReversed("dark", "C11nL11rC21nT31nT31r", new ActDark(0), new Color(105, 105, 105), new Color(205, 205, 205), "C11", "C21");
		addForReversed("dark", "C11nL21rC21nT31nT31r", new ActDark(1), new Color(105, 105, 105), new Color(205, 205, 205), "C11", "C21");
		addForReversed("dark", "C11nL21rC21nT31nT31r", new ActDark(2), new Color(105, 105, 105), new Color(205, 205, 205), "C11", "C21");

		addForReversed("barrier", "L11nC11nC21nL11r", new ActBarrier(0), "C11", "C21");
		addForReversed("barrier", "L21nC11nC21nL21r", new ActBarrier(1), "C11", "C21");
		addForReversed("barrier", "L31nC11nC21nL31r", new ActBarrier(2), "C11", "C21");

		addCircle("ext_transm", "E11n", new ActTransm(0), new Color(60, 40, 255), new Color(60, 40, 255));
		addCircle("ext_philo", "E21n", new ActPhilo(0), new Color(204, 5, 54), new Color(204, 5, 54));
		addCircle("ext_blood", "E31n", new ActBlood(0), new Color(152, 11, 11), new Color(152, 11, 11));

		addForReversed("sunny", "C11nL11rS11nS11rC31n", new ActSunny(0), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C31");
		addForReversed("sunny", "C11nL21rS21nS21rC31n", new ActSunny(1), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C31");
		addForReversed("sunny", "C11nL31rS31nS31rC31n", new ActSunny(2), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C31");

		addForReversed("rainy", "C11nS11nS11rM11n", new ActRainy(0), "C11");
		addForReversed("rainy", "C11nS21nS21rM21n", new ActRainy(1), "C11");
		addForReversed("rainy", "C11nS31nS31rM31n", new ActRainy(2), "C11");

		addForReversed("thunder", "C11nL11rM11nC31n", new ActThunder(0), "C11", "C31n");
		addForReversed("thunder", "C11nL21rM21nC31n", new ActThunder(1), "C11", "C31n");
		addForReversed("thunder", "C11nL31rM31nC31n", new ActThunder(2), "C11", "C31n");

		addForReversed("ice", "C11nH11rC21n", new ActIce(0), new Color(65, 195, 195), new Color(107, 206, 206), "C11", "C21");
		addForReversed("ice", "C11nH21rC21n", new ActIce(1), new Color(65, 195, 195), new Color(107, 206, 206), "C11", "C21");
		addForReversed("ice", "C11nH31rC21n", new ActIce(2), new Color(65, 195, 195), new Color(107, 206, 206), "C11", "C21");

		addForReversed("melt", "C11nH11nT11n", new ActMelt(0), new Color(255, 0, 32), new Color(255, 100, 132), "C11");
		addForReversed("melt", "C11nH21rT21n", new ActMelt(1), new Color(255, 0, 32), new Color(255, 100, 132), "C11");
		addForReversed("melt", "C11nH31rT31n", new ActMelt(2), new Color(255, 0, 32), new Color(255, 100, 132), "C11");

		addForReversed("energyConsume", "C11n", new ActEnergyConsume(0), "C11");
		addForReversed("energyConsume", "C21n", new ActEnergyConsume(1), "C21");
		addForReversed("energyConsume", "C31n", new ActEnergyConsume(2), "C31");

		addForReversed("fireGem", "C11nH31nT11nT11r", new ActFireGem(0), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "H31");
		addForReversed("fireGem", "C11nH31nT21nT21r", new ActFireGem(1), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "H31");
		addForReversed("fireGem", "C11nH31nT31nT31r", new ActFireGem(2), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "H31");

		addForReversed("iceGem", "C11nH31nH11r", new ActIceGem(0), new Color(65, 195, 195), new Color(107, 206, 206), "C11", "H31");
		addForReversed("iceGem", "C11nH31nH21r", new ActIceGem(1), new Color(65, 195, 195), new Color(107, 206, 206), "C11", "H31");

		addForReversed("earthGem", "C11nH31nR11rR21n", new ActEarthGem(1), new Color(190, 77, 0), new Color(255, 177, 100), "C11", "H31");
		addForReversed("earthGem", "C11nH31nR21rR31n", new ActEarthGem(2), new Color(190, 77, 0), new Color(255, 177, 100), "C11", "H31");

		addForReversed("airGem", "C11nH31nS11nS11r", new ActAirGem(0), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "H31");
		addForReversed("airGem", "C11nH31nS21nS21r", new ActAirGem(1), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "H31");
		addForReversed("airGem", "C11nH31nS31nS31r", new ActAirGem(2), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "H31");

		addForReversed("airWave", "C11nC21nS11n", new ActAirWave(0), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C21");
		addForReversed("airWave", "C11nC21nS21n", new ActAirWave(1), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C21");
		addForReversed("airWave", "C11nC21nS31n", new ActAirWave(2), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "C21");

		addForReversed("earthWave", "C11nC21nR11n", new ActEarthWave(0), new Color(190, 77, 0), new Color(255, 177, 100), "C11", "C21");
		addForReversed("earthWave", "C11nC21nR21n", new ActEarthWave(1), new Color(190, 77, 0), new Color(255, 177, 100), "C11", "C21");
		addForReversed("earthWave", "C11nC21nR31n", new ActEarthWave(2), new Color(190, 77, 0), new Color(255, 177, 100), "C11", "C21");

		addForReversed("pole", "C11nR11rR21n", new ActPole(0), new Color(190, 77, 0), new Color(255, 177, 100), "C11");
		addForReversed("pole", "C11nR21rR31n", new ActPole(1), new Color(190, 77, 0), new Color(255, 177, 100), "C11");

		addForReversed("airpole", "C11nR11rR21nT31nT31r", new ActAirPole(0), new Color(244, 192, 68), new Color(244, 217, 153), "C11");
		addForReversed("airpole", "C11nR21rR31nT31nT31r", new ActAirPole(1), new Color(244, 192, 68), new Color(244, 217, 153), "C11");

		addForReversed("fire", "C11nT11nS11nC21n", new ActFireWave(0), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "C21");
		addForReversed("fire", "C11nT21nS21nC21n", new ActFireWave(1), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "C21");

		addForReversed("extinguish", "C11nT11rS11rH11n", new ActExtinguishWave(0), "C11");
		addForReversed("extinguish", "C11nT21rS21rH21n", new ActExtinguishWave(1), "C11");

		addForReversed("fireSelfAttack", "C11nT11n", new ActFireSelfAttack(0), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "T11");
		addForReversed("fireSelfAttack", "C21nT21n", new ActFireSelfAttack(1), new Color(255, 0, 32), new Color(255, 100, 132), "C21", "T21");
		addForReversed("fireSelfAttack", "C31nT31n", new ActFireSelfAttack(2), new Color(255, 0, 32), new Color(255, 100, 132), "C31", "T31");

		addForReversed("airSelfAttack", "C11nS11n", new ActAirSelfAttack(0), new Color(244, 192, 68), new Color(244, 217, 153), "C11", "T11");
		addForReversed("airSelfAttack", "C21nS21n", new ActAirSelfAttack(1), new Color(244, 192, 68), new Color(244, 217, 153), "C21", "T21");
		addForReversed("airSelfAttack", "C31nS31n", new ActAirSelfAttack(2), new Color(244, 192, 68), new Color(244, 217, 153), "C31", "T31");

		addForReversed("earthSelfAttack", "C11nR11r", new ActEarthSelfAttack(0), new Color(190, 77, 0), new Color(255, 177, 100), "C11", "T11");
		addForReversed("earthSelfAttack", "C21nR21r", new ActEarthSelfAttack(1), new Color(190, 77, 0), new Color(255, 177, 100), "C21", "T21");
		addForReversed("earthSelfAttack", "C31nR31r", new ActEarthSelfAttack(2), new Color(190, 77, 0), new Color(255, 177, 100), "C31", "T31");

		addForReversed("iceSelfAttack", "C11nH11n", new ActIceSelfAttack(0), new Color(65, 195, 195), new Color(107, 206, 206), "C11", "T11");
		addForReversed("iceSelfAttack", "C21nH21n", new ActIceSelfAttack(1), new Color(65, 195, 195), new Color(107, 206, 206), "C21", "T21");
		addForReversed("iceSelfAttack", "C31nH31n", new ActIceSelfAttack(2), new Color(65, 195, 195), new Color(107, 206, 206), "C31", "T31");

	}

	public static void reloadCircles()
	{
		reloading = true;
		circles.clear();
		loadCircles();
		reloading = false;
	}

	@Deprecated
	public static void loadExtensions()
	{
		String[][] arr = new String[][] { { "C11n", "", "", "", "C11n" }, { "", "", "", "", "" }, { "", "", "C15nR15rR25n", "", "" }, { "", "", "", "", "" }, { "C11n", "", "", "", "C11n" } };
		ItemList ilist = new ItemList();
		ilist.add(Blocks.crafting_table, 1);
		ilist.add(Items.dye, 4, 8);
		// addExtension("transm", extTransm, arr, ilist);

		arr = new String[][] { { "C11n", "T11n", "", "T11n", "C11n" }, { "T11n", "", "", "", "T11n" }, { "", "", "C15n", "", "" }, { "T11n", "", "", "", "T11n" }, { "C11n", "T11n", "", "T11n", "C11n" } };
		ilist = new ItemList();
		ilist.add(ModItems.BloodBucket, 1);
		ilist.add(ModBlocks.miniumBlock, 4);
		// addExtension("philo", extPhilo, arr, ilist);

		arr = new String[][] { { "", "", "", "", "" }, { "", "", "T11n", "", "" }, { "", "T11n", "C13nC23n", "T11n", "" }, { "", "", "T11n", "", "" }, { "", "", "", "", "" } };
		ilist = new ItemList();
		ilist.add(ModBlocks.miniumBlock, 1);
		ilist.add(Items.flint, 8);
		// addExtension("blood", extBlood, arr, ilist, 3);
	}

	public static void load()
	{
		loadChalks();
		loadExtensions();
		loadCircles();
		setupBlocks();
	}

	/**
	 * List of Strings like: "minecraft:stone|1", where first part(before "|")
	 * is a blockname and second part(after "|") is a block meta. "-1" meta is
	 * for any metablock
	 */
	public static List<String> allowedblocks = new ArrayList<>();

	public static void setupBlocks()
	{
		allowBlock(Blocks.stone, 0);
		allowBlock(Blocks.grass);
		allowBlock(Blocks.grass);
		allowBlock(Blocks.dirt);
		allowBlock(Blocks.sand);
		allowBlock(Blocks.gravel);
		allowBlock(Blocks.cobblestone);
		allowBlock(Blocks.water);
	}

	public static boolean isAllowed(Block b)
	{
		return allowedblocks.contains(GameRegistry.findUniqueIdentifierFor(b).toString() + "|-1");
	}

	public static boolean isAllowed(Block b, int meta)
	{
		return allowedblocks.contains(GameRegistry.findUniqueIdentifierFor(b).toString() + "|" + meta) || allowedblocks.contains(GameRegistry.findUniqueIdentifierFor(b).toString() + "|-1");
	}

	public static boolean isAllowed(VectorWorld pw, int x, int y, int z)
	{
		return isAllowed(pw.getBlock(new Wec3(x, y, z)), pw.getBlockMetadata(new Wec3(x, y, z)));
	}

	public static String getSFromWorldBlock(World w, int x, int y, int z)
	{
		return GameRegistry.findUniqueIdentifierFor(w.getBlock(x, y, z)).toString() + "|" + w.getBlockMetadata(x, y, z);
	}

	public static void allowBlock(Block b)
	{
		allowedblocks.add(GameRegistry.findUniqueIdentifierFor(b).toString() + "|-1");
	}

	public static void allowBlock(Block b, int meta)
	{
		allowedblocks.add(GameRegistry.findUniqueIdentifierFor(b).toString() + "|" + meta);
	}

	public static List<String> variants = new ArrayList<>();

	public static String getvar(int d, String old, int size)
	{
		for (int i = 0; i < 2; i++)
		{
			if (d == size - 1)
			{
				variants.add(old + (i == 0 ? "n" : "r"));
			}
			if (d < size - 1)
			{
				getvar(d + 1, old + (i == 0 ? "n" : "r"), size);
			}
		}
		return "";
	}

	/**
	 * uses recursive algorithm to get variants.
	 * 
	 * @param name
	 * @param partses
	 *            usually only the circles and flowers are symmetrical
	 * @param action
	 */
	public static void addForReversed(String name, String partses, TransAction action, String... rev)
	{
		getvar(0, "", rev.length);

		for (String str : variants)
		{// "C11nR11rR21nC31n"
			String s = partses;
			for (int i = 0; i < rev.length; i++)
			{
				s = s.replaceFirst(rev[i] + s.substring(s.indexOf(rev[i]) + 3, s.indexOf(rev[i]) + 4), rev[i] + str.charAt(i));
			}
			addCircle(name, s, action, new Color(60, 40, 255), new Color(160, 140, 255));
		}
		variants.clear();
	}

	/**
	 * uses recursive algorithm to get variants.
	 * 
	 * @param name
	 * @param partses
	 *            usually only the circles are simmertical
	 * @param action
	 */
	public static void addForReversed(String name, String partses, TransAction action, Color prep, Color act, String... rev)
	{
		getvar(0, "", rev.length);

		for (String str : variants)
		{
			String s = partses;
			for (int i = 0; i < rev.length; i++)
			{
				s = s.replaceFirst(rev[i] + s.substring(s.indexOf(rev[i]) + 3, s.indexOf(rev[i]) + 4), rev[i] + str.charAt(i));
			}
			addCircle(name, s, action, prep, act);
		}
		variants.clear();
	}

	public static int id = 0;

	public static void addPart(CirclePart p, String code)
	{
		parts.add(p);
		p.ptId = code;
		partsr.add(code);
		// if (!p.extended)
		count += 1;
	}

	public static CirclePart getprtsr(String code)
	{
		return parts.get(partsr.indexOf(code));
	}

	public static String getprts(CirclePart p)
	{
		return partsr.get(parts.indexOf(p));
	}

	public static String getCodeStr(Circle c)
	{
		return getprts(c.pt) + (int) c.scale + (c.rev ? "r" : "n");
	}

	public static List<String> getCodeStr(List<Circle> l)
	{
		List<String> ret = new ArrayList<>();

		for (int i = 0; i < l.size(); i++)
		{
			Circle c = l.get(i);
			ret.add("" + getprts(c.pt) + (int) c.scale + (c.rev ? "r" : "n"));
		}

		return ret;
	}

	public static List<String> getList(String l)
	{
		List<String> lst = new ArrayList<>();
		while (TypeUtils.indexOfNum(l) != -1)
		{
			String s = "";
			if (TypeUtils.indexOfNum(l, 4) != -1)
			{
				s = l.substring(0, TypeUtils.indexOfNum(l, 4) - 1);
			}
			else
			{
				if (TypeUtils.indexOfNum(l, 4) == -1)
				{
					s = l;
				}
			}
			lst.add(s);

			l = l.replace(s, "");
		}
		return lst;
	}

	/**
	 * 
	 * @param name
	 * @param parts
	 *            - C21r- Reversed Medim Circle Size = 1 - C21n- Medim Circle
	 *            Size = 1
	 * @param act
	 * @param prep
	 */
	public static Transmutation addCircle(String name, String parts, TransAction action, Color prep, Color act)
	{
		Transmutation tr = new Transmutation(name, parts, action);
		tr.prepCol = prep;
		tr.actCol = act;
		circles.add(tr);
		return tr;
	}

	public static List<Circle> getCircles(List<String> codes)
	{
		List<Circle> ret = new ArrayList<>();

		for (int i = 0; i < codes.size(); i++)
		{
			String c = codes.get(i);
			String pt = c.substring(0, 2);
			boolean rev = c.substring(c.length() - 1).equals("r");
			double size = Double.parseDouble(c.substring(2, c.length() - 1));
			ret.add(new Circle(getprtsr(pt), size, rev));
		}
		return ret;
	}
}
