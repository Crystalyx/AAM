/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import AAM.Common.Potions.Ingridient;
import AAM.Common.Tiles.MultiInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class MiscUtils
{

	public static Random r = new Random();

	public static Hashtable<String, ResourceLocation> res = new Hashtable<String, ResourceLocation>();

	public static List<String> unpact(String s)
	{
		List<String> l = new ArrayList<String>();

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
				s += d;
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
				s += d;
		}

		return s;
	}

	/**
	 * @param value
	 * @param max
	 * @return if value is bigger then half of max it will return 1-value else
	 *         return value
	 */
	public static double getSawValue(double value, double max)
	{
		return value > (max / 2) ? max - value : value;
	}

	/**
	 * @param speed
	 * @return Value changing in time. Usable in things like bobbing
	 */
	public static double getTimedValue(double speed)
	{
		return ((Minecraft.getMinecraft().getSystemTime() % speed) / speed);
	}

	/**
	 * @param speed
	 * @return deg angle changing in time
	 */
	public static double getTimedAngle(double speed)
	{
		return ((Minecraft.getMinecraft().getSystemTime() % (360 * speed)) / speed);
	}

	/**
	 * @param l
	 * @return mixed list
	 */
	public static <T> List<T> randomize(List<T> l)
	{
		List<T> ret = new ArrayList<T>();
		for (int i = 0; i < l.size(); i++)
		{
			ret.add(l.get(r.nextInt(l.size())));
		}
		return ret;
	}

	/**
	 * Creates EntityItem and drops it to the world
	 * 
	 * @param te
	 * @param slot
	 */
	public static void dropSlot(TileEntity te, int slot)
	{
		if (te instanceof IInventory)
		{
			IInventory tile = (IInventory) te;
			if (!te.getWorldObj().isRemote)
			{
				if (tile.getStackInSlot(slot) != null)
				{
					MiscUtils.dropStack(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, tile.getStackInSlot(slot));
					tile.setInventorySlotContents(slot, null);
				}
			}
		}
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return Does b contains each value that a contains
	 */
	public static boolean containsOnly(List a, List b)
	{
		return (a.size() == b.size()) && b.containsAll(a);
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
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static int limit(int a, int min, int max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
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
	 * 
	 * @param b
	 * @return numeric representation of b
	 */
	public static int boolToNum(boolean b)
	{
		return b ? 1 : 0;

	}

	/**
	 * @return numeric representation of random boolean
	 */
	public static int boolToNum()
	{
		return r.nextBoolean() ? 1 : 0;
	}

	/**
	 * 
	 * @return numeric representation of b b? tr : fl
	 */
	public static int boolToNum(boolean b, int tr, int fl)
	{
		return b ? tr : fl;
	}

	/**
	 * 
	 * @return numeric representation of random boolean b? tr : fl
	 */
	public static int boolToNum(int tr, int fl)
	{
		return r.nextBoolean() ? tr : fl;

	}

	/**
	 * Smart way to get random int
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getIntInRange(int a, int b)
	{
		if (b > a)
		{
			int raw = r.nextInt(b - a);
			return a + raw;
		}
		else
			if (a > b)
			{
				int raw = r.nextInt(a - b);
				return b + raw;
			}
			else
				return a;

	}

	/**
	 * @param percent
	 *            - 1% : percent=1
	 * @return are you lucky today?
	 */
	public static boolean randWPercent(double percent)
	{
		double ret = r.nextDouble();
		return ret <= percent / 100d;
	}

	/**
	 * 
	 * @param yaw
	 * @param pitch
	 * @param dist
	 * @return pos in orthogonal coords instead of polar
	 */
	public static Wec3 getPosBy3DAngle(double yaw, double pitch, double dist)
	{
		double rY = Math.sin(pitch) * dist;
		double mod = Math.cos(pitch) * dist;

		double rX = Math.cos(yaw) * mod;
		double rZ = Math.sin(yaw) * mod;

		return new Wec3(rX, rY, rZ);
	}

	/**
	 * @param angle
	 * @param dist
	 * @return pos in orthogonal coords instead of polar
	 */
	public static Wec3 getPosBy3DAngle(Angle3D angle, double dist)
	{
		double rY = Math.sin(angle.pitch) * dist;
		double mod = Math.cos(angle.pitch) * dist;

		double rX = Math.cos(angle.yaw) * mod;
		double rZ = Math.sin(angle.yaw) * mod;

		return new Wec3(rX, rY, rZ);
	}

	/**
	 * @param p
	 * @return 3d Angle in polar coords instead of orthogonal
	 */
	public static Angle3D get3DAngleByPos(Wec3 p)
	{
		double mod = Math.hypot(p.x, p.z);
		double pitch = 0, yaw = 0;

		pitch = Math.atan2(p.y, mod);

		yaw = Math.atan2(p.x, p.z);
		return new Angle3D(yaw, pitch);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return 3d Angle in polar coords instead of orthogonal
	 */
	public static Angle3D get3DAngleByPos(double x, double y, double z)
	{
		double mod = Math.hypot(x, z);
		double pitch = 0, yaw = 0;

		pitch = Math.atan2(y, mod);
		yaw = Math.atan2(x, z);

		return new Angle3D(yaw, pitch);
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static float limit(float a, float min, float max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static double limit(double a, double min, double max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	/**
	 * Checks if number lower then max and larger then min
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInLimit(int a, int min, int max)
	{
		if (a <= max && a >= min)
		{
			return true;
		}

		return false;
	}

	/**
	 * Checks if number lower then max and larger then min
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInLimit(double a, double min, double max)
	{
		if (a <= max && a >= min)
		{
			return true;
		}

		return false;
	}

	/**
	 * Wrapper method for WorldPos usage. Should be moved to the PositionedWorld
	 * 
	 * @param w
	 * @param pos
	 * @param tg
	 * @param particle
	 */
	@Deprecated
	public static void spawnParticle(World w, Wec3 pos, Wec3 tg, String particle)
	{
		double motionX, motionY, motionZ = 0;
		motionX = (tg.x - pos.x) / 5;
		motionZ = (tg.y - pos.y) / 5;
		motionY = (tg.z - pos.z) / 5;
		w.spawnParticle(particle, pos.x, pos.y, pos.z, motionX, motionY, motionZ);

	}

	/**
	 * Soft method for adding item to player's inventory
	 * 
	 * @param p
	 * @param i
	 * @return
	 */
	public static boolean addItemStack(EntityPlayer p, ItemStack i)
	{
		if (i != null)
		{
			for (int l = 0; l < p.inventory.getSizeInventory(); l++)
			{
				if (p.inventory.getStackInSlot(l) != null)
					if (p.inventory.getStackInSlot(l).getItem() == i.getItem() && p.inventory.getStackInSlot(l).getItemDamage() == i.getItemDamage() && p.inventory.getStackInSlot(l).stackSize + i.stackSize <= i.getMaxStackSize())
					{
						if ((i.hasTagCompound() == false && p.inventory.getStackInSlot(l).hasTagCompound() == false) || i.getTagCompound() == p.inventory.getStackInSlot(l).getTagCompound())
						{
							ItemStack il = new ItemStack(i.getItem(), p.inventory.getStackInSlot(l).stackSize + i.stackSize, i.getItemDamage());
							p.inventory.setInventorySlotContents(l, il);
							return true;
						}
					}
			}
			for (int l = 0; l < p.inventory.getSizeInventory(); l++)
			{
				if (p.inventory.getStackInSlot(l) == null)
				{
					p.inventory.setInventorySlotContents(l, i);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Decreases stacksize of player current equipped stack If you need to cut
	 * stack use: net.minecraft.entity.player.InventoryPlayer.decrStackSize
	 * 
	 * @param p
	 *            - Player
	 * @param c
	 *            - Count
	 */
	public static void decrPlayerStack(EntityPlayer p, int c)
	{
		if (p.getCurrentEquippedItem() != null)
		{
			p.getCurrentEquippedItem().stackSize -= c;
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
		int hex = ((short) i << 16) | ((short) j << 8) | ((short) k << 0);
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
		int hex = (c.red << 16) | (c.green << 8) | (c.blue << 0);
		return hex;
	}

	/**
	 * 
	 * @param hex
	 * @return rgb representation of hex color
	 */
	public static Color hexToRGB(int hex)
	{
		short r = ((short) (hex >> 16));
		short g = ((short) (hex >> 8));
		short b = ((short) (hex >> 0));
		return new Color(r, g, b);
	}

	/**
	 * Saves your IInventory. One line instead of many
	 * 
	 * @param inv
	 * @param tag
	 */
	public static void saveInventory(IInventory inv, NBTTagCompound tag)
	{
		NBTTagList list = new NBTTagList();

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			if (inv.getStackInSlot(i) != null)
			{
				ItemStack is = inv.getStackInSlot(i);

				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setInteger("Slot", i);
				is.writeToNBT(stackTag);

				list.appendTag(stackTag);
			}
		}

		tag.setTag("Items", list);
	}

	/**
	 * Saves your MultiInventory. One line instead of many
	 * 
	 * @param inv
	 * @param tag
	 */
	public static void saveMultiInventory(MultiInventory inv, NBTTagCompound tag)
	{
		NBTTagList list = new NBTTagList();

		for (int i = 0; i < inv.mltinv.size(); i++)
		{
			if (inv.mltinv.get(i) != null)
			{
				ItemStack is = inv.mltinv.get(i);

				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag = is.writeToNBT(stackTag);

				list.appendTag(stackTag);
			}
		}

		tag.setTag("MItems", list);
	}

	/**
	 * Loads your IInventory. One line instead of many
	 * 
	 * @param inv
	 * @param tag
	 */
	public static void readInventory(IInventory inv, NBTTagCompound tag)
	{
		NBTTagList list = tag.getTagList("Items", 10);

		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound tagComp = list.getCompoundTagAt(i);
			int slot = tagComp.getInteger("Slot");

			ItemStack is = ItemStack.loadItemStackFromNBT(tagComp);
			inv.setInventorySlotContents(slot, is);
		}
	}

	/**
	 * Loads your MultiInventory. One line instead of many
	 * 
	 * @param inv
	 * @param tag
	 */
	public static void readMultiInventory(MultiInventory inv, NBTTagCompound tag)
	{
		inv.clearInventory();
		NBTTagList list = tag.getTagList("MItems", 10);

		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound tagComp = list.getCompoundTagAt(i);
			ItemStack is = ItemStack.loadItemStackFromNBT(tagComp);
			inv.addMultiInventoryContent(is);
		}
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
						b.remove(aObj);
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
						b.remove(aObj);
				}
			}
			return true;
		}
		return ret;
	}

	/**
	 * Do not use it
	 * 
	 * @param s
	 * @return NullPointerException
	 */
	public static NBTTagCompound readTag(String s)
	{
		NBTTagCompound ret = new NBTTagCompound();

		String[] tag = s.substring(1, s.length() - 1).split(",");
		for (int i = 0; i < tag.length; i++)
		{
			String[] eq = tag[i].split(":");
			if (eq[1].charAt(0) == '"')
			{
				ret.setString(eq[0], eq[1].substring(1, eq[1].length() - 1));
			}
			else
			{
				if (eq[1].indexOf(".") != -1)
				{
					ret.setDouble(eq[0], Double.parseDouble(eq[1]));
				}
				else
				{

					if (eq[1].indexOf("true") != -1 || eq[1].indexOf("false") != -1)
					{
						ret.setBoolean(eq[0], Boolean.parseBoolean(eq[1]));
					}
					else
					{
						if (eq[1].indexOf("0b") != -1)
						{
							ret.setBoolean(eq[0], false);
						}
						else
							if (eq[1].indexOf("1b") != -1)
							{
								ret.setBoolean(eq[0], true);
							}
						if (eq[1].indexOf("true") == -1 && eq[1].indexOf("false") == -1)
						{
							ret.setInteger(eq[0], Integer.parseInt(eq[1]));
						}
						else
						{
						}
					}
				}
			}
		}
		return ret;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return If inventory contains ItemStack(item=is,itemDamage =meta)
	 */
	public static boolean contains(IInventory ic, Item is, int meta)
	{
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					if (ic.getStackInSlot(i).getItemDamage() == meta)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @param ic
	 * @param is
	 * @return If inventory contains ItemStack(item=is)
	 */
	public static boolean contains(IInventory ic, Item is)
	{
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return count of ItemStacks(item=is,itemDamage =meta)
	 */
	public static int count(IInventory ic, Item is, int meta)
	{
		int ret = 0;

		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					if (ic.getStackInSlot(i).getItemDamage() == meta)
					{
						ret += 1;
					}
				}
			}
			else
			{
				if (ic.getStackInSlot(i) == null && is == null)
				{
					ret += 1;
				}
			}
		}

		return ret;
	}

	/**
	 * @param ic
	 * @param is
	 * @return count of ItemStacks(item=is)
	 */
	public static int count(IInventory ic, Item is)
	{
		int ret = 0;

		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null && is != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					ret += 1;
				}
			}
			else
			{
				if (ic.getStackInSlot(i) == null && is == null)
				{
					ret += 1;
				}
			}
		}
		return ret;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return firts slot id of ItemStack(item=is,itemDamage =meta)
	 */
	public static int get(IInventory ic, Item is, int meta)
	{
		int ret = -1;
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null && is != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					if (ic.getStackInSlot(i).getItemDamage() == meta)
					{
						ret = i;
						return ret;
					}
				}
			}
			else
			{
				ret += 1;
			}
		}
		return ret;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return all slots id of ItemStacks(item=is,itemDamage =meta)
	 */
	public static List<Integer> getList(IInventory ic, Item is, int meta)
	{
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					if (ic.getStackInSlot(i).getItemDamage() == meta)
					{
						ret.add(i);
					}
				}
			}
		}
		return ret;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return ItemStack(item=is,itemDamage =meta)
	 */
	public static ItemStack getStack(IInventory ic, Item is, int meta)
	{
		int ret = -1;
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					if (ic.getStackInSlot(i).getItemDamage() == meta)
					{
						ret = i;
						return ic.getStackInSlot(ret);
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return firts slot id of ItemStack(item=is)
	 */
	public static int get(IInventory ic, Item is)
	{
		int ret = -1;
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					ret = i;
					return ret;
				}
			}
		}
		return ret;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return all slots id of ItemStacks(item=is)
	 */
	public static List<Integer> getList(IInventory ic, Item is)
	{
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					ret.add(i);
				}
			}
		}
		return ret;
	}

	/**
	 * @param ic
	 * @param is
	 * @param meta
	 * @return ItemStack(item=is)
	 */
	public static ItemStack getStack(IInventory ic, Item is)
	{
		int ret = -1;
		for (int i = 0; i < ic.getSizeInventory(); i++)
		{
			if (ic.getStackInSlot(i) != null)
			{
				if (ic.getStackInSlot(i).getItem() == is)
				{
					ret = i;
					return ic.getStackInSlot(ret);
				}
			}
		}
		return null;
	}

	/**
	 * @param ic
	 * @param only
	 * @return if inventory contains only ItemStack(item=key,damage=value)
	 */
	public static boolean containsOnly(IInventory ic, Pair<Item, Integer>... only)
	{
		int k = ic.getSizeInventory() - only.length;
		boolean b = true;
		for (int i = 0; i < only.length; i++)
		{
			b = b && contains(ic, only[i].key, only[i].value);
		}
		if (b)
		{
			b = b && MiscUtils.count(ic, null) == k;
		}

		return b;
	}

	/**
	 * Creates EntityItem and tries to put it to the player inventory
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @param is
	 * @param p
	 */
	public static void dropStackToPlayer(World w, double x, double y, double z, ItemStack is, EntityPlayer p)
	{
		if (!p.inventory.addItemStackToInventory(is))
		{
			if (is != null)
			{
				if (!w.isRemote)
				{
					EntityItem et = new EntityItem(w, x, y, z, is);
					et.setVelocity(w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05);
					w.spawnEntityInWorld(et);
				}
			}
		}
	}

	/**
	 * Creates EntityItem and drops it to the world
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @param is
	 */
	public static void dropStack(World w, double x, double y, double z, ItemStack is)
	{
		if (is != null)
		{
			if (!w.isRemote)
			{
				EntityItem et = new EntityItem(w, x, y, z, is);
				et.setVelocity(w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05);
				w.spawnEntityInWorld(et);
			}
		}
	}

	/**
	 * Creates EntityItem and drops it to the world
	 * 
	 * @param w
	 * @param p
	 * @param is
	 */
	public static void dropStack(World w, Wec3 p, ItemStack is)
	{
		if (is != null)
		{
			if (!w.isRemote)
			{
				EntityItem et = new EntityItem(w, p.x, p.y, p.z, is);
				et.setVelocity(w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05);
				w.spawnEntityInWorld(et);
			}
		}
	}

	/**
	 * Creates EntityItem for the each player slot and drops it to the world
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @param is
	 */
	public static void dropPlayer(World w, double x, double y, double z, EntityPlayer p)
	{
		for (int i = 0; i < 36; i++)
		{
			dropStack(w, x, y, z, p.inventory.getStackInSlot(i));
		}
	}

	/**
	 * Creates EntityItem for the each inventory slot and drops it to the world
	 * 
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 * @param is
	 */
	public static void dropInventory(World w, double x, double y, double z, IInventory inv)
	{
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			dropStack(w, x, y, z, inv.getStackInSlot(i));
		}
	}

	/**
	 * @param p
	 * @return WorldPos(Vector) rounded to the axis
	 */
	public static Wec3 getAxialVec(Wec3 p)
	{
		if (Math.abs(p.x) > Math.abs(p.y))
		{
			if (Math.abs(p.x) > Math.abs(p.z))
			{
				return new Wec3(p.x, 0, 0);
			}
			else
			{
				if (Math.abs(p.z) > Math.abs(p.x))
					return new Wec3(p.x, 0, 0);
			}
		}
		else
		{
			if (Math.abs(p.y) > Math.abs(p.x))
				if (Math.abs(p.y) > Math.abs(p.z))
				{
					return new Wec3(p.y, 0, 0);
				}
				else
				{
					if (Math.abs(p.z) > Math.abs(p.y))
						return new Wec3(p.z, 0, 0);
				}
		}
		return p;
	}

	/**
	 * @param value
	 * @param i
	 * @return value cut to i decimal numbers
	 */
	public static double round(double value, int i)
	{
		return ((int) (value * Math.pow(10, i))) / Math.pow(10, i);
	}

	/**
	 * @param value
	 * @param i
	 * @return String value cut to i decimal numbers
	 */
	public static String roundStr(double value, int i)
	{
		String s = (Math.round(value * Math.pow(10, i)) / Math.pow(10, i)) + "";
		while (s.endsWith("0") || s.endsWith("."))
		{
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	public static int cycle(int i, int min, int max)
	{
		if (i > max)
		{
			return min;
		}
		if (i < min)
		{
			return max;
		}
		return i;
	}

	public static double cycle(double i, double min, double max)
	{
		if (i > max)
		{
			return min;
		}
		if (i < min)
		{
			return max;
		}
		return i;
	}
}
