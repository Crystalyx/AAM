package AAM.Common.Transmutations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import AAM.Common.Blocks.ModBlocks;
import AAM.Common.Items.ModItems;
import AAM.Common.Tiles.TETransCircle;
import AAM.Utils.BlockState;
import AAM.Utils.Color;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.PositionedWorld;
import AAM.Utils.WorldPos;
import AAM.Utils.WorldRotation;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModCircles
{
	public static CirclePart circle_big = new CirclePart("circle_big", "circle_big", "circle_big");
	public static CirclePart circle_med = new CirclePart("circle_medium", "circle_medium", "circle_medium");
	public static CirclePart circle_small = new CirclePart("circle_small", "circle_small", "circle_small");

	public static CirclePart rect_big = new CirclePart("rect_big", "rhomb_big", "rect_big");
	public static CirclePart rect_med = new CirclePart("rect_medium", "rhomb_medium", "rect_medium");
	public static CirclePart rect_small = new CirclePart("rect_small", "rhomb_small", "rect_small");

	public static CirclePart triangle_big = new CirclePart("triangle_big", "triangle_rev_big", "triangle_big");
	public static CirclePart triangle_med = new CirclePart("triangle_medium", "triangle_rev_medium", "triangle_medium");
	public static CirclePart triangle_small = new CirclePart("triangle_small", "triangle_rev_small", "triangle_small");

	public static CirclePart triangles_big = new CirclePart("triangles_big", "triangles_rev_big", "triangles_big");
	public static CirclePart triangles_medium = new CirclePart("triangles_medium", "triangles_rev_medium", "triangles_medium");
	public static CirclePart triangles_small = new CirclePart("triangles_small", "triangles_rev_small", "triangles_small");

	public static CirclePart flower_big = new CirclePart("flower_big", "flower_big", "flower_big");
	public static CirclePart flower_medium = new CirclePart("flower_medium", "flower_medium", "flower_medium");
	public static CirclePart flower_small = new CirclePart("flower_small", "flower_small", "flower_small");

	public static CirclePart troon_big = new CirclePart("moon_big", "tree_big", "troon_big");
	public static CirclePart troon_medium = new CirclePart("moon_medium", "tree_medium", "troon_medium");
	public static CirclePart troon_small = new CirclePart("moon_small", "tree_small", "troon_small");

	public static CirclePart shight_big = new CirclePart("shield_big", "light_big", "shight_big");
	public static CirclePart shight_medium = new CirclePart("shield_medium", "light_medium", "shight_medium");
	public static CirclePart shight_small = new CirclePart("shield_small", "light_small", "shight_small");

	public static int nextInt(int next)
	{
		return (new Random()).nextInt(next);
	}

	public static List<CirclePart> parts = new ArrayList<CirclePart>();
	public static List<String> partsr = new ArrayList<String>();

	public static List<Transmutation> circles = new ArrayList<Transmutation>();

	public static void load()
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

		setupBlocks();

		TransAction act = new TransAction()
		{
			@Override
			public void act(World w, WorldPos tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
			{
				int h = w.getBlockMetadata((int) tile.x, (int) tile.y - 1, (int) tile.z);
				int d = (int) (Math.log(potency + 1) / Math.log(1.4)) - 2;
				int hg = (int) (Math.log(potency + 3) / Math.log(1.4)) + 1;
				PositionedWorld pw = new PositionedWorld(w);
				pw.translate(tile.subtruct(new WorldPos(dir)));
				
				if (dir.offsetX != 0)
				{
					WorldRotation wr1 = new WorldRotation(ForgeDirection.SOUTH, Math.PI/2*dir.offsetX);
					pw.applyRotation(wr1);
				}
				if (dir.offsetZ != 0)
				{
					WorldRotation wr1 = new WorldRotation(ForgeDirection.EAST, Math.PI/2*dir.offsetZ);
					pw.applyRotation(wr1);
				}
				
				if (dir.offsetY < 0)
				{
					WorldRotation wr1 = new WorldRotation(ForgeDirection.NORTH, Math.PI);
					pw.applyRotation(wr1);
				}
				Logger.info(pw.basX);
				Logger.info(pw.basY);
				Logger.info(pw.basZ);

				for (int i = 0; i < hg; i++)
				{
					for (int j = -d; j <= d; j++)
					{
						for (int k = -d; k <= d; k++)
						{
							if (isAllowed(pw, j, 0, k))
							{
								BlockState bs = new BlockState(pw.getBlock(new WorldPos(j, 0, k)), new WorldPos(j, i, k), h);
								bs.print(pw);
							}
						}
					}
				}
			}
		};
		addForReversed("pole", "C11nR11rR21n", 2l << 3, act, new Color(190, 77, 0), new Color(255, 177, 100), "C11");

		act = new TransAction()
		{
			@Override
			public boolean actTick(World w, WorldPos tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
			{
				int n = (int) (5 + (Math.log(potency + 1) / Math.log(1.4)));

				if (Math.floorDiv(time, n) >= n)
				{
					return false;
				}
				else
				{
					for (int i = -Math.floorDiv(time, n); i <= Math.floorDiv(time, n); i++)
					{
						for (int j = -Math.floorDiv(time, n); j <= Math.floorDiv(time, n); j++)
						{
							int h = MiscUtils.getLowerHighBlock(w, new WorldPos(i, 16, j).add(tile));
							if (w.getBlock((int) tile.x + i, h + 1, (int) tile.z + j).isAir(w, (int) tile.x + i, h + 1, (int) tile.z + j) && w.getBlock((int) tile.x + i, h, (int) tile.z + j).isNormalCube())
							{
								BlockState bs = new BlockState(Blocks.fire, (int) (i + tile.x), h + 1, (int) (j + tile.z));
								bs.print(w);
							}
						}
					}

				}
				return true;
			}
		};
		addForReversed("fire", "C11nT11nS11nC21n", 2l << 10, act, new Color(255, 0, 32), new Color(255, 100, 132), "C11", "C21");

		act = new TransAction()
		{

			@Override
			public boolean actTick(World w, WorldPos tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
			{
				int n = (int) (5 + (Math.log(potency + 1) / Math.log(1.4)));
				if (Math.floorDiv(time, n) >= n + 4)
				{
					return false;
				}
				else
				{
					for (int i = -Math.floorDiv(time, n); i <= Math.floorDiv(time, n); i++)
					{
						for (int j = -Math.floorDiv(time, n); j <= Math.floorDiv(time, n); j++)
						{
							for (int k = -Math.floorDiv(time, n); k <= Math.floorDiv(time, n); k++)
							{
								if (w.getBlock((int) tile.x + i, (int) tile.y + k, (int) tile.z + j) == Blocks.fire)
								{
									BlockState bs = new BlockState(Blocks.air, (int) (i + tile.x), (int) tile.y + k, (int) (j + tile.z));
									bs.print(w);
								}
							}
						}
					}

				}
				return true;
			}
		};
		addForReversed("extinguish", "C11nT11rS11rC21n", 2l << 8, act, "C11", "C21");

		act = new TransAction()
		{
			@Override
			public void act(World w, WorldPos tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
			{
				if (!w.isRemote)
				{
					for (int i = 0; i < potency; i++)
					{
						ItemStack is = new ItemStack(ModItems.TeleportationCrystal);
						tile = tile.centralize();
						EntityItem ei = new EntityItem(w, tile.x, tile.y, tile.z, is);

						w.spawnEntityInWorld(ei);
					}
				}
			}
		};
		addForReversed("createobol", "C11nR11rR21nC31n", 2l << 6, act, "C11n", "C31n");

		act = new TransAction()
		{
			@Override
			public boolean actTick(World w, WorldPos tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
			{
				int n = (int) (12 + (Math.log(potency + 1) / Math.log(1.4)));
				int t = Math.floorDiv(time, n);
				if (t >= n * 2)
				{
					return false;
				}
				else
				{
					WorldPos exp = tile.add(WorldPos.random(n, n, n));
					if (!w.isRemote)
						w.createExplosion(null, exp.x, exp.y, exp.z, Math.max(t, 1), true);
				}
				return true;
			}
		};
		addForReversed("explosions", "C11nT11nS11rC31n", 2l << 16, act, new Color(255, 0, 32), new Color(255, 100, 132), "C11n", "C31n");
		addForReversed("explosions", "C11nT11rS11nC31n", 2l << 16, act, new Color(255, 0, 32), new Color(255, 100, 132), "C11n", "C31n");

		act = new TransAction()
		{
			@Override
			public boolean actTick(World w, WorldPos tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
			{
				WorldPos pp = tile.centralize().add(ForgeDirection.UP);
				float r = (float) ((MiscUtils.limit(time - 40, 0, 200) / 3d) * Math.log(potency + 1) / Math.log(3.4));
				AxisAlignedBB aabb = pp.getAABB(r);

				List<Entity> es = w.getEntitiesWithinAABB(Entity.class, aabb);
				for (Entity e : es)
				{
					WorldPos ep = new WorldPos(e);
					WorldPos vec = pp.subtruct(ep);
					vec = vec.power(1).modify(0.1f);
					vec.ptm(e);
				}
				if (te.completeTimer > 400)
				{
					return false;
				}
				return true;
			}

			@Override
			public boolean renderTick(World w, WorldPos tile, TETransCircle te, int time)
			{
				Tessellator t = Tessellator.instance;
				MiscUtils.bindTexture("aam:textures/items/blank_upgrade.png");
				t.startDrawing(GL11.GL_QUADS);

				int n = 50;
				double r = (MiscUtils.limit(time - 40, 0, 200) / 5d);
				for (int i = 0; i < n; i++)
				{
					double a = 2 * Math.PI / n;
					for (int j = -n; j <= n; j++)
					{
						t.addVertexWithUV(r * Math.sin(a * i) * Math.cos(a * j), r * Math.sin(a * j), r * Math.cos(a * i) * Math.cos(a * j), 0, 0);
						t.addVertexWithUV(r * Math.sin(a * (i + 1)) * Math.cos(a * j), r * Math.sin(a * j), r * Math.cos(a * (i + 1)) * Math.cos(a * j), 1, 0);
						t.addVertexWithUV(r * Math.sin(a * (i + 1)) * Math.cos(a * (j + 1)), r * Math.sin(a * (j + 1)), r * Math.cos(a * (i + 1)) * Math.cos(a * (j + 1)), 1, 1);
						t.addVertexWithUV(r * Math.sin(a * i) * Math.cos(a * (j + 1)), r * Math.sin(a * (j + 1)), r * Math.cos(a * i) * Math.cos(a * (j + 1)), 0, 1);
					}
				}

				t.draw();

				return true;
			}
		};
		addForReversed("void", "C11nC21nT11nT11rS11nS11r", 2l << 16, act, new Color(71, 0, 156), new Color(171, 100, 255), "C11n", "C31n");

		act = new TransAction()
		{
			@Override
			public boolean actTick(World w, WorldPos tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
			{
				int n = (int) (5 + (Math.log(potency + 1) / Math.log(1.4)));

				for (int i = -n; i <= n; i++)
				{
					for (int j = -n; j <= n; j++)
					{
						for (int k = -n; k <= n; k++)
						{
							Block b = w.getBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k);
							if (b instanceof IGrowable)
							{
								b.updateTick(w, (int) tile.x + i, (int) tile.y + j, (int) tile.z + k, w.rand);
							}
						}
					}
				}

				if (te.completeTimer > 400)
				{
					return false;
				}
				return true;
			}

			@Override
			public boolean renderTick(World w, WorldPos tile, TETransCircle te, int time)
			{

				return true;
			}
		};
		addForReversed("growth", "C11nM11rC21n", 2l << 16, act, new Color(40, 255, 60), new Color(140, 255, 160), "C11n", "C21n");

		act = new TransAction()
		{
			@Override
			public boolean renderTick(World w, WorldPos tile, TETransCircle te, int time)
			{

				return true;
			}

			@Override
			public void act(World w, WorldPos tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
			{
				p.setAbsorptionAmount(15f);
			}
		};
		addForReversed("shield", "L21nC11nC21n", 2l << 16, act, new Color(105, 105, 105), new Color(205, 205, 205), "C11n", "C21n");

		act = new TransAction()
		{
			@Override
			public boolean renderTick(World w, WorldPos tile, TETransCircle te, int time)
			{
				return true;
			}

			@Override
			public void act(World w, WorldPos tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
			{
				int n = (int) (5 + (Math.log(potency + 1) / Math.log(1.4))) * 2;

				for (int i = -n; i <= n; i++)
				{
					for (int j = -n; j <= n; j++)
					{
						for (int k = -n; k <= n; k++)
						{
							if (w.getBlockLightValue((int) tile.x + i, (int) tile.y + j, (int) tile.z + k) < 7 && w.isAirBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k))
							{
								w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, ModBlocks.LightBlock);
							}
						}
					}
				}
			}
		};
		addForReversed("light", "C11nL11rC31n", 2l << 16, act, new Color(105, 105, 105), new Color(205, 205, 205), "C11n", "C31n");

		act = new TransAction()
		{
			@Override
			public boolean renderTick(World w, WorldPos tile, TETransCircle te, int time)
			{
				return true;
			}

			@Override
			public void act(World w, WorldPos tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
			{
				int n = (int) (5 + (Math.log(potency + 1) / Math.log(1.4))) * 2;

				for (int i = -n; i <= n; i++)
				{
					for (int j = -n; j <= n; j++)
					{
						for (int k = -n; k <= n; k++)
						{
							if (w.getBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k) == ModBlocks.LightBlock)
							{
								w.setBlockToAir((int) tile.x + i, (int) tile.y + j, (int) tile.z + k);
							}
						}
					}
				}
			}
		};
		addForReversed("dark", "C11nL11rC31nT11n", 2l << 16, act, new Color(105, 105, 105), new Color(205, 205, 205), "C11n", "C31n");
	}

	/**
	 * List of Strings like: "minecraft:stone|1", where first part(before "|")
	 * is a blockname and second part(after "|") is a block meta "-1" meta is
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

	public static boolean isAllowed(PositionedWorld pw, int x, int y, int z)
	{
		return isAllowed(pw.getBlock(new WorldPos(x, y, z)), pw.getBlockMetadata(new WorldPos(x, y, z)));
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
	 * @param name
	 * @param partses
	 *            usually only the circles are simmertical
	 * @param action
	 */
	public static void addForReversed(String name, String partses, long price, TransAction action, String... rev)
	{
		getvar(0, "", rev.length);

		for (String str : variants)
		{// "C11nR11rR21nC31n"
			String s = partses;
			for (int i = 0; i < rev.length; i++)
			{
				s = s.replaceFirst(rev[i] + s.substring(s.indexOf(rev[i]) + 3, s.indexOf(rev[i]) + 4), rev[i] + str.charAt(i));
			}
			addCircle(name, s, price, action, new Color(60, 40, 255), new Color(160, 140, 255));
		}
		variants.clear();
	}

	/**
	 * @param name
	 * @param partses
	 *            usually only the circles are simmertical
	 * @param action
	 */
	public static void addForReversed(String name, String partses, long price, TransAction action, Color prep, Color act, String... rev)
	{
		getvar(0, "", rev.length);

		for (String str : variants)
		{// "C11nR11rR21nC31n"
			String s = partses;
			for (int i = 0; i < rev.length; i++)
			{
				s = s.replaceFirst(rev[i] + s.substring(s.indexOf(rev[i]) + 3, s.indexOf(rev[i]) + 4), rev[i] + str.charAt(i));
			}
			addCircle(name, s, price, action, prep, act);
		}
		variants.clear();
	}

	public static int id = 0;

	public static void addPart(CirclePart p, String code)
	{
		parts.add(p);
		partsr.add(code);
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

	/**
	 * 
	 * @param name
	 * @param parts
	 *            - C21r- Reversed Medim Circle Size = 1 - C21n- Medim Circle
	 *            Size = 1
	 * @param act
	 * @param prep
	 */
	public static Transmutation addCircle(String name, String parts, long price, TransAction action, Color prep, Color act)
	{
		Transmutation tr = new Transmutation(name, parts, price, action);
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
	public static Transmutation addCircle(String name, String parts, long price, TransAction action, Color prep, Color act, boolean tick)
	{
		Transmutation tr = new Transmutation(name, parts, price, action);
		tr.setTick(tick);
		circles.add(tr);
		return tr;
	}
}
