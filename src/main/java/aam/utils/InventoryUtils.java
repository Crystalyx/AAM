package aam.utils;

import aam.utils.vectors.VectorWorld;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils
{
	public static void tryToPlaceInInventoryOrDrop(VectorWorld w, Wec3 v, ItemStack is, ForgeDirection dir)
	{
		if (w.getTile(new Wec3()) instanceof IInventory)
		{
			IInventory ii = (IInventory) w.getTile(new Wec3());
			for (int i = 0; i < ii.getSizeInventory(); i++)
			{
				if (ii.isItemValidForSlot(i, is))
				{
					if (ii.getStackInSlot(i) != null)
					{
						int stackSize = ii.getStackInSlot(i).stackSize;
						if (stackSize + is.stackSize <= 64)
						{
							ii.getStackInSlot(i).stackSize += is.stackSize;
							return;
						}
						else
						{
							int aStack = stackSize + is.stackSize - 64;
							ii.getStackInSlot(i).stackSize = 64;
							is.stackSize = aStack;
						}
					}
					else
					{
						ii.setInventorySlotContents(i, is);
						return;
					}
				}
			}
		}
		dropStack(w, v, is);
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return Does b contains each value that a contains
	 */
	public static boolean containsOnly(List a, List b)
	{
		return a.size() == b.size() && b.containsAll(a);
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
				{
					if (p.inventory.getStackInSlot(l).getItem() == i.getItem() && p.inventory.getStackInSlot(l).getItemDamage() == i.getItemDamage() && p.inventory.getStackInSlot(l).stackSize + i.stackSize <= i.getMaxStackSize())
					{
						if (i.hasTagCompound() == false && p.inventory.getStackInSlot(l).hasTagCompound() == false || i.getTagCompound() == p.inventory.getStackInSlot(l).getTagCompound())
						{
							ItemStack il = new ItemStack(i.getItem(), p.inventory.getStackInSlot(l).stackSize + i.stackSize, i.getItemDamage());
							p.inventory.setInventorySlotContents(l, il);
							return true;
						}
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
		if (p.getCurrentEquippedItem().stackSize <= 0)
		{
			p.destroyCurrentEquippedItem();
		}
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
						ret += ic.getStackInSlot(i).stackSize;
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
					ret += ic.getStackInSlot(i).stackSize;
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
		List<Integer> ret = new ArrayList<>();
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
		List<Integer> ret = new ArrayList<>();
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
		for (Pair<Item, Integer> element : only)
		{
			b = b && contains(ic, element.key, element.value);
		}
		if (b)
		{
			b = b && count(ic, null) == k;
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
					et.onCollideWithPlayer(p);
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
	 * Creates EntityItem and drops it to the world
	 * 
	 * @param w
	 * @param p
	 * @param is
	 */
	public static void dropStack(VectorWorld w, Wec3 p, ItemStack is)
	{
		Wec3 pv = w.getRealWec3(p);
		if (is != null)
		{
			if (!w.isRemote)
			{
				EntityItem et = new EntityItem(w.w, pv.ix, pv.iy, pv.iz, is);
				et.setVelocity(w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05, w.rand.nextDouble() * 0.1 - 0.05);
				w.w.spawnEntityInWorld(et);
			}
		}
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
					dropStack(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, tile.getStackInSlot(slot));
					tile.setInventorySlotContents(slot, null);
				}
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
}
