/**
 * This Class Created By Lord_Crystalyx.
 */
package aam.utils;

import aam.common.potions.Ingridient;
import aam.utils.vectors.BlockState;
import aam.utils.vectors.Wec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Lord_Crystalyx
 */
public class MiscUtils
{
	public static Hashtable<String, ResourceLocation> res = new Hashtable<>();

	public static List<String> unpact(String s)
	{
		List<String> l = new ArrayList<>();

		while (s.indexOf(d) > -1)
		{
			String ds = s.substring(0, s.indexOf(d));
			l.add(ds);
			s = s.substring(s.indexOf(d) + 1);
		}
		l.add(s);

		return l;
	}

	public static String toString(Object o)
	{
		return o != null ? o + "" : "null";
	}

	public static String d = "|";

	public static String compact(Object... ss)
	{
		String s = "";
		for (int i = 0; i < ss.length; i++)
		{
			s += toString(ss[i]);
			if (i != ss.length - 1)
			{
				s += d;
			}
		}

		return s;
	}

	public static String compact(String d, Object... ss)
	{
		String s = "";
		for (int i = 0; i < ss.length; i++)
		{
			s += toString(ss[i]);
			if (i != ss.length - 1)
			{
				s += d;
			}
		}

		return s;
	}

	public static String acompact(Object[] ss)
	{
		String s = "";
		for (int i = 0; i < ss.length; i++)
		{
			s += toString(ss[i]);
			if (i != ss.length - 1)
			{
				s += d;
			}
		}

		return s;
	}

	public static String acompact(String d, Object[] ss)
	{
		String s = "";
		for (int i = 0; i < ss.length; i++)
		{
			s += toString(ss[i]);
			if (i != ss.length - 1)
			{
				s += d;
			}
		}

		return s;
	}

	/**
	 * Short Bind texture method
	 * 
	 * @param mod
	 * @param texture
	 */
	@SideOnly(Side.CLIENT)
	public static void bindTexture(String mod, String texture)
	{
		if (res.containsKey(mod + texture))
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(res.get(mod + texture));
		}
		else
		{
			ResourceLocation r = new ResourceLocation(mod, texture);
			res.put(mod + texture, r);
			Minecraft.getMinecraft().getTextureManager().bindTexture(r);
		}
	}

	/**
	 * Short Bind texture method
	 * 
	 * @param mod
	 * @param texture
	 */
	@SideOnly(Side.CLIENT)
	public static void bindTexture(ResourceLocation text)
	{
		if (res.containsKey(text.getResourcePath()))
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(res.get(text.getResourcePath()));
		}
		else
		{
			res.put(text.getResourcePath(), text);
			Minecraft.getMinecraft().getTextureManager().bindTexture(text);
		}
	}

	/**
	 * Short Bind texture method
	 * 
	 * @param mod
	 * @param texture
	 */
	@SideOnly(Side.CLIENT)
	public static void bindTexture(String texture)
	{
		if (res.containsKey(texture))
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(res.get(texture));
		}
		else
		{
			ResourceLocation r = new ResourceLocation(texture);
			res.put(texture, r);
			Minecraft.getMinecraft().getTextureManager().bindTexture(r);
		}
	}

	/**
	 * Sets World block only if it is not air
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @param to
	 */
	public static void replaceBlock(World w, int x, int y, int z, Block to)
	{
		if (!w.isAirBlock(x, y, z))
		{
			w.setBlock(x, y, z, to);
		}
	}

	/**
	 * Sets World block only if it is not air
	 * 
	 * @param w
	 * @param wp
	 * @param to
	 */
	public static void replaceBlock(World w, Wec3 wp, Block to)
	{
		if (!w.isAirBlock((int) wp.x, (int) wp.y, (int) wp.z))
		{
			w.setBlock((int) wp.x, (int) wp.y, (int) wp.z, to);
		}
	}

	/**
	 * Sets World block only if it is not air
	 * 
	 * @param w
	 * @param bs
	 */
	public static void replaceBlock(World w, BlockState bs)
	{
		if (!w.isAirBlock(bs.x, bs.y, bs.z))
		{
			bs.print(w);
		}
	}

	/**
	 * @param b
	 * @param meta
	 * @return IIcon array w/ textures for each side
	 */
	public static IIcon[] getIconArray(Block b, int meta)
	{
		IIcon[] icons = new IIcon[6];
		for (int i = 0; i < 6; i++)
		{
			icons[i] = b.getIcon(i, meta);
		}
		return icons;
	}

	/**
	 * 
	 * @param array
	 * @return FirstNotOccupiedSlot for the array
	 */
	public static int getFirstNotOccupiedSlotFor(Object[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == null)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param array
	 * @return expanded array
	 */
	public static Object[] expandArray(Object[] array)
	{
		Object[] ret = new Object[array.length + 1];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * @param array
	 * @param length
	 * @return expanded array
	 */
	public static Object[] expandArray(Object[] array, int length)
	{
		Object[] ret = new Object[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * @param array
	 * @param length
	 * @return expanded array
	 */
	public static float[] expandArray(float[] array, int length)
	{
		float[] ret = new float[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * @param array
	 * @param length
	 * @return expanded array
	 */
	public static boolean[] expandArray(boolean[] array, int length)
	{
		boolean[] ret = new boolean[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * @param array
	 * @param length
	 * @return expanded array
	 */
	public static ItemStack[] expandArray(ItemStack[] array, int length)
	{
		ItemStack[] ret = new ItemStack[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * @param array
	 * @param length
	 * @return expanded array
	 */
	public static String[] expandArray(String[] array, int length)
	{
		String[] ret = new String[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * @param array
	 * @param length
	 * @return expanded array
	 */
	public static int[] expandArray(int[] array, int length)
	{
		int[] ret = new int[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @return hex representation of rgb color
	 */
	public static int rgbToHex(int i, int j, int k)
	{
		int hex = (short) i << 16 | (short) j << 8 | (short) k << 0;
		return hex;
	}

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @return hex representation of color
	 */
	public static int rgbToHex(Color c)
	{
		int hex = c.red << 16 | c.green << 8 | c.blue << 0;
		return hex;
	}

	/**
	 * 
	 * @param hex
	 * @return rgb representation of hex color
	 */
	public static Color hexToRGB(int hex)
	{
		short r = (short) (hex >> 16);
		short g = (short) (hex >> 8);
		short b = (short) (hex >> 0);
		return new Color(r, g, b);
	}

	/**
	 * 
	 * @param w
	 * @param x
	 * @param z
	 * @return y-coord of highest block at (x,z) block-pole
	 */
	public static int gethighestBlock(World w, int x, int z)
	{
		int ret = 0;
		for (int i = 255; i > 0; i--)
		{
			if (!w.getBlock(x, i, z).isAir(w, x, i, z))
			{
				ret = i;
				break;
			}
		}

		return ret;
	}

	/**
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @return y-coord of highest block at (x,z) block-pole starting from y
	 */
	public static int getLowerHighBlock(World w, int x, int y, int z)
	{
		int ret = 0;
		for (int i = y; i > 0; i--)
		{
			if (!w.getBlock(x, i, z).isAir(w, x, i, z))
			{
				ret = i;
				break;
			}
		}

		return ret;
	}

	/**
	 * WorldPos method
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @return y-coord of highest block at (x,z) block-pole starting from y
	 */
	public static int getLowerHighBlock(World w, Wec3 p)
	{
		int ret = 0;
		for (int i = (int) p.y; i > 0; i--)
		{
			if (!w.getBlock((int) p.x, i, (int) p.z).isAir(w, (int) p.x, i, (int) p.z))
			{
				ret = i;
				break;
			}
		}

		return ret;
	}

	/**
	 * WorldPos method
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @return y-coord of highest block at (x,z) block-pole starting from y
	 *         downto y-down
	 */
	public static int getLowerHighBlock(World w, Wec3 p, int down)
	{
		int ret = 0;
		for (int i = (int) p.y; i > p.y - down; i--)
		{
			if (!w.getBlock((int) p.x, i, (int) p.z).isAir(w, (int) p.x, i, (int) p.z))
			{
				ret = i;
				break;
			}
		}

		return ret;
	}

	/**
	 * 
	 * @param a
	 *            - lower list
	 * @param b
	 *            - higher list
	 * @param sbbc
	 *            - should b be changed
	 * @return
	 */
	public static boolean contains(List b, List a, boolean sbbc)
	{
		boolean ret = false;
		boolean crush = false;

		System.out.println("b :" + b.size() + "; a:" + a.size());

		if (b.size() >= a.size())
		{
			for (Object aObj : a)
			{
				for (Object bObj : b)
				{
					if (aObj.equals(bObj))
					{
						crush = false;
						break;
					}
					else
					{
						crush = true;
					}
				}
				if (crush)
				{
					return ret;
				}
				else
				{
					if (sbbc)
					{
						b.remove(aObj);
					}
				}
			}
		}

		return ret;
	}

	/**
	 * 
	 * @param a
	 *            - lower list
	 * @param b
	 *            - higher list
	 * @param sbbc
	 *            - should b be changed
	 * @return
	 */
	public static boolean containsIngs(List<Ingridient> b, List<Ingridient> a, boolean sbbc)
	{
		boolean ret = false;
		boolean crush = false;

		System.out.println("b :" + b.size() + "; a:" + a.size());

		if (b.size() >= a.size())
		{
			for (Ingridient aObj : a)
			{
				for (Ingridient bObj : b)
				{
					if (aObj.id != bObj.id)
					{
						crush = false;
						break;
					}
					else
					{
						crush = true;
					}
				}
				if (crush)
				{
					return ret;
				}
				else
				{
					if (sbbc)
					{
						b.remove(aObj);
					}
				}
			}
			return true;
		}
		return ret;
	}

	public static void attractEntitiesTo(World w, Wec3 tile, float r, float force, Entity... lr)
	{
		List<Entity> l = w.getEntitiesWithinAABB(Entity.class, tile.extendBoth(r));
		for (int i = 0; i < lr.length; i++)
		{
			l.remove(lr[i]);
		}
		for (Entity el : l)
		{
			Wec3 ew = new Wec3(el);

			if (ew.sub(tile).length() > 0.5)
			{
				Wec3 v = ew.sub(tile).pow(-2).flip();
				v = v.mult(20 * force);
				if (v.length() > 1)
				{
					v.normalize();
					v.mult(1);
				}
				v.ptm(el);
			}
		}
	}

	public static void retractEntitiesFrom(World w, Wec3 tile, float r, float force, Entity... lr)
	{
		List<Entity> l = w.getEntitiesWithinAABB(Entity.class, tile.extendBoth(r));
		for (int i = 0; i < lr.length; i++)
		{
			l.remove(lr[i]);
		}
		for (Entity el : l)
		{
			Wec3 ew = new Wec3(el);

			Wec3 v = ew.sub(tile).pow(-2);
			v = v.mult(20 * force);
			if (v.length() > 1)
			{
				v.normalize();
				v.mult(1);
			}
			v.ptm(el);
		}
	}

}
