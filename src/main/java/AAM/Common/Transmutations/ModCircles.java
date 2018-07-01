package AAM.Common.Transmutations;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Items.ModItems;
import AAM.Common.Transmutations.Actions.ActBarrier;
import AAM.Common.Transmutations.Actions.ActBlood;
import AAM.Common.Transmutations.Actions.ActCreateObol;
import AAM.Common.Transmutations.Actions.ActDark;
import AAM.Common.Transmutations.Actions.ActExplosions;
import AAM.Common.Transmutations.Actions.ActExtinguish;
import AAM.Common.Transmutations.Actions.ActFire;
import AAM.Common.Transmutations.Actions.ActGrowth;
import AAM.Common.Transmutations.Actions.ActLight;
import AAM.Common.Transmutations.Actions.ActPhilo;
import AAM.Common.Transmutations.Actions.ActPole;
import AAM.Common.Transmutations.Actions.ActRainy;
import AAM.Common.Transmutations.Actions.ActShield;
import AAM.Common.Transmutations.Actions.ActSunny;
import AAM.Common.Transmutations.Actions.ActThunder;
import AAM.Common.Transmutations.Actions.ActTransm;
import AAM.Common.Transmutations.Actions.ActVoid;
import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
import AAM.Utils.TypeUtils;
import AAM.Utils.VectorWorld;
import AAM.Utils.Wec3;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class ModCircles
{
	public static final float K = 200;

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

	// F
	public static CirclePart flower_big = new CirclePart("flower_big", "flower_big", "flower_big");
	public static CirclePart flower_medium = new CirclePart("flower_medium", "flower_medium", "flower_medium");
	public static CirclePart flower_small = new CirclePart("flower_small", "flower_small", "flower_small");

	// M
	public static CirclePart troon_big = new CirclePart("moon_big", "tree_big", "troon_big");
	public static CirclePart troon_medium = new CirclePart("moon_medium", "tree_medium", "troon_medium");
	public static CirclePart troon_small = new CirclePart("moon_small", "tree_small", "troon_small");

	// L
	public static CirclePart shight_big = new CirclePart("shield_big", "light_big", "shight_big");
	public static CirclePart shight_medium = new CirclePart("shield_medium", "light_medium", "shight_medium");
	public static CirclePart shight_small = new CirclePart("shield_small", "light_small", "shight_small");

	// E - extended
	public static CirclePart extTransm = new CirclePart("transm", "transm", true);
	public static CirclePart extPhilo = new CirclePart("philo", "philo", true);
	public static CirclePart extBlood = new CirclePart("blood", "blood", true);

	public static int nextInt(int next)
	{
		return MiscUtils.r.nextInt(next);
	}

	public static List<CirclePart> parts = new ArrayList<CirclePart>();
	public static List<String> partsr = new ArrayList<String>();
	public static int count = 0;

	public static List<Transmutation> circles = new ArrayList<Transmutation>();
	public static List<Extension> extensions = new ArrayList<Extension>();

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

		addPart(flower_big, "F1");
		addPart(flower_medium, "F2");// F
		addPart(flower_small, "F3");

		addPart(troon_big, "M1");
		addPart(troon_medium, "M2");// M
		addPart(troon_small, "M3");

		addPart(shight_big, "L1");
		addPart(shight_medium, "L2");// L
		addPart(shight_small, "L3");

		addPart(extTransm, "E1");// E - extended
		addPart(extPhilo, "E2");
		addPart(extBlood, "E3");
	}

	public static void loadCircles()
	{
		addForReversed("pole", "C11nR11rR21n", new ActPole(), new Color(190, 77, 0), new Color(255, 177, 100), "C11");

		addForReversed("fire", "C11nT11nS11nC21n", new ActFire(), new Color(255, 0, 32), new Color(255, 100, 132), "C11", "C21");

		addForReversed("extinguish", "C11nT11rS11rC21n", new ActExtinguish(), "C11", "C21");

		addForReversed("createobol", "C11nR11rR21nC31n", new ActCreateObol(), "C11n", "C31n");

		addForReversed("explosions", "C11nT11nS11rC31n", new ActExplosions(), new Color(255, 0, 32), new Color(255, 100, 132), "C11n", "C31n");
		addForReversed("explosions", "C11nT11rS11nC31n", new ActExplosions(), new Color(255, 0, 32), new Color(255, 100, 132), "C11n", "C31n");

		addForReversed("void", "C11nC21nT11nT11rS11nS11r", new ActVoid(), new Color(71, 0, 156), new Color(171, 100, 255), "C11n", "C31n");

		addForReversed("growth", "C11nM11rC21n", new ActGrowth(), new Color(40, 255, 60), new Color(140, 255, 160), "C11n", "C21n");

		addForReversed("shield", "L21nC11nC21n", new ActShield(), new Color(105, 105, 105), new Color(205, 205, 205), "C11n", "C21n");

		addForReversed("light", "C11nL11rC31n", new ActLight(), new Color(105, 105, 105), new Color(205, 205, 205), "C11n", "C31n");

		addForReversed("dark", "C11nL11rC31nT11n", new ActDark(), new Color(105, 105, 105), new Color(205, 205, 205), "C11n", "C31n");

		addForReversed("barrier", "L11nC11nC21nL11r", new ActBarrier(), "C11n", "C21n");

		addCircle("ext_transm", "E11n", new ActTransm(), new Color(60, 40, 255), new Color(60, 40, 255));

		addCircle("ext_philo", "E21n", new ActPhilo(), new Color(204, 5, 54), new Color(204, 5, 54));

		addCircle("ext_blood", "E31n", new ActBlood(), new Color(152, 11, 11), new Color(152, 11, 11));

		addForReversed("sunny", "C11nL11rS11nS11rC31n", new ActSunny(), "C11n");
		addForReversed("rainy", "C11nS11nS11rM11n", new ActRainy(), "C11n", "C31n");
		addForReversed("thunder", "C11nL11rM11nC31n", new ActThunder(), "C11n", "C31n");

	}

	public static void loadExtensions()
	{
		String[][] arr = new String[][] { { "C11n", "", "", "", "C11n" }, { "", "", "", "", "" }, { "", "", "C15nR15rR25n", "", "" }, { "", "", "", "", "" }, { "C11n", "", "", "", "C11n" } };
		ItemList ilist = new ItemList();
		ilist.add(Blocks.crafting_table, 1);
		ilist.add(Items.dye, 4, 8);
		addExtension("transm", extTransm, arr, ilist);

		arr = new String[][] { { "C11n", "T11n", "", "T11n", "C11n" }, { "T11n", "", "", "", "T11n" }, { "", "", "C15n", "", "" }, { "T11n", "", "", "", "T11n" }, { "C11n", "T11n", "", "T11n", "C11n" } };
		ilist = new ItemList();
		ilist.add(ModItems.BloodBucket, 1);
		ilist.add(ModBlocks.miniumBlock, 4);
		addExtension("philo", extPhilo, arr, ilist);

		arr = new String[][] { { "", "", "", "", "" }, { "", "", "T11n", "", "" }, { "", "T11n", "C13nC23n", "T11n", "" }, { "", "", "T11n", "", "" }, { "", "", "", "", "" } };
		ilist = new ItemList();
		ilist.add(ModBlocks.miniumBlock, 1);
		ilist.add(Items.flint, 8);
		addExtension("blood", extBlood, arr, ilist, 3);
	}

	public static void load()
	{
		loadChalks();
		loadExtensions();
		loadCircles();
		setupBlocks();
	}

	/**
	 * @param name
	 * @param part
	 * @param circles
	 *            5x5 array of codestrings
	 */
	public static void addExtension(String name, CirclePart part, String[][] circles, ItemList items)
	{
		Extension e = new Extension(name, circles, part, items);
		extensions.add(e);
	}

	/**
	 * @param name
	 * @param part
	 * @param circles
	 *            5x5 array of codestrings
	 */
	public static void addExtension(String name, CirclePart part, String[][] circles, ItemList items, double size)
	{
		Extension e = new Extension(name, circles, part, items, size);
		extensions.add(e);
	}

	/**
	 * List of Strings like: "minecraft:stone|1", where first part(before "|")
	 * is a blockname and second part(after "|") is a block meta. "-1" meta is
	 * for any metablock
	 */
	public static List<String> allowedblocks = new ArrayList<String>();

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

	public static List<String> variants = new ArrayList<String>();

	public static String getvar(int d, String old, int size)
	{
		for (int i = 0; i < 2; i++)
		{
			if (d == size - 1)
				variants.add(old + (i == 0 ? "n" : "r"));
			if (d < size - 1)
				getvar(d + 1, old + (i == 0 ? "n" : "r"), size);
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
		if (!p.extended)
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
		return getprts(c.pt) + ((int) c.scale) + (c.rev ? "r" : "n");
	}

	public static List<String> getCodeStr(List<Circle> l)
	{
		List<String> ret = new ArrayList<String>();

		for (int i = 0; i < l.size(); i++)
		{
			Circle c = l.get(i);
			ret.add("" + getprts(c.pt) + ((int) c.scale) + (c.rev ? "r" : "n"));
		}

		return ret;
	}

	public static List<String> getList(String l)
	{
		List<String> lst = new ArrayList<String>();
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

	/**
	 * 
	 * @param name
	 * @param parts
	 *            - C21r- Reversed Medim Circle Size = 1 - C21n- Medim Circle
	 *            Size = 1
	 */
	public static Transmutation addCircle(String name, String parts, TransAction action, Color prep, Color act, boolean tick)
	{
		Transmutation tr = new Transmutation(name, parts, action);
		tr.setTick(tick);
		circles.add(tr);
		return tr;
	}

	public static List<Circle> getCircles(List<String> codes)
	{
		List<Circle> ret = new ArrayList<Circle>();

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
