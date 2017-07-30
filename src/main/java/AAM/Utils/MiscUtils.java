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

	public static <T> List<T> randomize(List<T> l)
	{
		List<T> ret = new ArrayList<T>();
		for (int i = 0; i < l.size(); i++)
		{
			ret.add(l.get(r.nextInt(l.size())));
		}
		return ret;
	}

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

	public static boolean containsOnly(List a, List b)
	{
		return (a.size() == b.size()) && b.containsAll(a);
	}

	@SideOnly(Side.CLIENT)
	public static void bindTexture(String mod, String texture)
	{
		if (res.contains(mod + texture))
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

	@SideOnly(Side.CLIENT)
	public static void bindTexture(ResourceLocation text)
	{
		if (res.contains(text.getResourcePath()))
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(res.get(text.getResourcePath()));
		}
		else
		{
			res.put(text.getResourcePath(), text);
			Minecraft.getMinecraft().getTextureManager().bindTexture(text);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void bindTexture(String texture)
	{
		if (res.contains(texture))
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

	public static void replaceBlock(World w, int x, int y, int z, Block to)
	{
		if (!w.isAirBlock(x, y, z))
		{
			w.setBlock(x, y, z, to);
		}
	}

	public static void replaceBlock(World w, WorldPos wp, Block to)
	{
		if (!w.isAirBlock((int) wp.x, (int) wp.y, (int) wp.z))
		{
			w.setBlock((int) wp.x, (int) wp.y, (int) wp.z, to);
		}
	}

	public static void replaceBlock(World w, BlockState bs)
	{
		if (!w.isAirBlock(bs.x, bs.y, bs.z))
		{
			bs.print(w);
		}
	}

	public static int boolToNum(boolean b)
	{
		if (b)
			return 1;
		else
			return 0;
	}

	public static int boolToNum()
	{
		boolean b = r.nextBoolean();
		if (b)
			return 1;
		else
			return -1;
	}

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

	public static boolean randWPercent(double percent)
	{
		double ret = r.nextDouble();
		return ret <= percent / 100d;
	}

	public static WorldPos getPosBy3DAngle(double yaw, double pitch, double dist)
	{
		double rY = Math.sin(pitch) * dist;
		double mod = Math.cos(pitch) * dist;

		double rX = Math.cos(yaw) * mod;
		double rZ = Math.sin(yaw) * mod;

		return new WorldPos(rX, rY, rZ);
	}

	public static WorldPos getPosBy3DAngle(Angle3D angle, double dist)
	{
		double rY = Math.sin(angle.pitch) * dist;
		double mod = Math.cos(angle.pitch) * dist;

		double rX = Math.cos(angle.yaw) * mod;
		double rZ = Math.sin(angle.yaw) * mod;

		return new WorldPos(rX, rY, rZ);
	}

	public static Angle3D get3DAngleByPos(WorldPos p)
	{
		double mod = Math.hypot(p.x, p.z);
		double pitch = 0, yaw = 0;

		pitch = Math.atan2(p.y, mod);

		yaw = Math.atan2(p.x, p.z);
		return new Angle3D(yaw, pitch);
	}

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

	public static void spawnParticle(World w, WorldPos pos, WorldPos tg, String particle)
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
		// if (!p.isClientWorld())
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
		}
		return false;
	}

	/**
	 * Decreases stacksize of player current equipped stack
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

	public static IIcon[] getIconArray(Block b, int meta)
	{
		IIcon[] icons = new IIcon[6];
		for (int i = 0; i < 6; i++)
		{
			icons[i] = b.getIcon(i, meta);
		}
		return icons;
	}

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

	public static Object[] expandArray(Object[] array)
	{
		Object[] ret = new Object[array.length + 1];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static Object[] expandArray(Object[] array, int length)
	{
		Object[] ret = new Object[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static float[] expandArray(float[] array, int length)
	{
		float[] ret = new float[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static boolean[] expandArray(boolean[] array, int length)
	{
		boolean[] ret = new boolean[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static ItemStack[] expandArray(ItemStack[] array, int length)
	{
		ItemStack[] ret = new ItemStack[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static String[] expandArray(String[] array, int length)
	{
		String[] ret = new String[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static int[] expandArray(int[] array, int length)
	{
		int[] ret = new int[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static double getDistance(WorldPos p1, WorldPos p2)
	{
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y) + (p2.z - p1.z) * (p2.z - p1.z));
	}

	public static int rgbToHex(int i, int j, int k)
	{
		int hex = ((short) i << 16) | ((short) j << 8) | ((short) k << 0);
		return hex;
	}

	public static int rgbToHex(Color c)
	{
		int hex = (c.red << 16) | (c.green << 8) | (c.blue << 0);
		return hex;
	}

	public static Color hexToRGB(int hex)
	{
		short r = ((short) (hex >> 16));
		short g = ((short) (hex >> 8));
		short b = ((short) (hex >> 0));
		return new Color(r, g, b);
	}

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

	public static int getLowerHighBlock(World w, WorldPos p)
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

	public static int getLowerHighBlock(World w, WorldPos p, int down)
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

	public static boolean containsOnly(IInventory ic, Pair<Item, Integer>... only)
	{
		int k = ic.getSizeInventory() - only.length;
		boolean b = true;
		for (int i = 0; i < only.length; i++)
		{
			b = b && MiscUtils.contains(ic, only[i].key, only[i].value);
		}
		if (b)
		{
			b = b && MiscUtils.count(ic, null) == k;
		}

		return b;
	}

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

	public static void dropStack(World w, WorldPos p, ItemStack is)
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

	public static void dropPlayer(World w, double x, double y, double z, EntityPlayer p)
	{
		for (int i = 0; i < 36; i++)
		{
			dropStack(w, x, y, z, p.inventory.getStackInSlot(i));
		}
	}

	public static WorldPos getAxialVec(WorldPos p)
	{
		if (Math.abs(p.x) > Math.abs(p.y))
		{
			if (Math.abs(p.x) > Math.abs(p.z))
			{
				return new WorldPos(p.x, 0, 0);
			}
			else
			{
				if (Math.abs(p.z) > Math.abs(p.x))
					return new WorldPos(p.x, 0, 0);
			}
		}
		else
		{
			if (Math.abs(p.y) > Math.abs(p.x))
				if (Math.abs(p.y) > Math.abs(p.z))
				{
					return new WorldPos(p.y, 0, 0);
				}
				else
				{
					if (Math.abs(p.z) > Math.abs(p.y))
						return new WorldPos(p.z, 0, 0);
				}
		}
		return p;
	}
}
