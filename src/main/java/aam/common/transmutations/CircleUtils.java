package aam.common.transmutations;

import aam.common.blocks.circles.TransCircle;
import aam.common.tiles.TETransCircle;
import aam.common.tiles.TETransCircle.State;
import aam.utils.InventoryUtils;
import aam.utils.Logger;
import aam.utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CircleUtils
{

	public static String outputCircle(List<Circle> l)
	{
		String out = "S: " + l.size() + " C:[";
		for (Circle c : l)
		{
			out += ModCircles.getCodeStr(c);
		}
		out += "]";
		return out;
	}

	// =============Block===============
	public static void handleObolBehaviour(World w, EntityPlayer p, int x, int y, int z, TETransCircle te)
	{
		int r = TransCircle.range;
		for (int i = 1; i < r; i++)
		{
			if (w.getTileEntity(x + i, y, z) instanceof TETransCircle)
			{
				if (((TETransCircle) w.getTileEntity(x + i, y, z)).state.equals(State.idle))
				{
					((TransCircle) w.getBlock(x + i, y, z)).onBlockActivated(w, x + i, y, z, p, 0, 0, 0, 0);
				}
				break;
			}
		}
		for (int i = 1; i < r; i++)
		{
			if (w.getTileEntity(x - i, y, z) instanceof TETransCircle)
			{
				if (((TETransCircle) w.getTileEntity(x - i, y, z)).state.equals(State.idle))
				{
					((TransCircle) w.getBlock(x - i, y, z)).onBlockActivated(w, x - i, y, z, p, 0, 0, 0, 0);
				}
				break;
			}
		}

		for (int i = 1; i < r; i++)
		{
			if (w.getTileEntity(x, y + i, z) instanceof TETransCircle)
			{
				if (((TETransCircle) w.getTileEntity(x, y + i, z)).state.equals(State.idle))
				{
					((TransCircle) w.getBlock(x, y + i, z)).onBlockActivated(w, x, y + i, z, p, 0, 0, 0, 0);
				}
				break;
			}
		}
		for (int i = 1; i < r; i++)
		{
			if (w.getTileEntity(x, y - i, z) instanceof TETransCircle)
			{
				if (((TETransCircle) w.getTileEntity(x, y - i, z)).state.equals(State.idle))
				{
					((TransCircle) w.getBlock(x, y - i, z)).onBlockActivated(w, x, y - i, z, p, 0, 0, 0, 0);
				}
				break;
			}
		}

		for (int i = 1; i < r; i++)
		{
			if (w.getTileEntity(x, y, z + i) instanceof TETransCircle)
			{
				if (((TETransCircle) w.getTileEntity(x, y, z + i)).state.equals(State.idle))
				{
					((TransCircle) w.getBlock(x, y, z + i)).onBlockActivated(w, x, y, z + i, p, 0, 0, 0, 0);
				}
				break;
			}
		}
		for (int i = 1; i < r; i++)
		{
			if (w.getTileEntity(x, y, z - i) instanceof TETransCircle)
			{
				if (((TETransCircle) w.getTileEntity(x, y, z - i)).state.equals(State.idle))
				{
					((TransCircle) w.getBlock(x, y, z - i)).onBlockActivated(w, x, y, z - i, p, 0, 0, 0, 0);
				}
				break;
			}
		}
	}

	public static void handleAlchPaperTranscribing(EntityPlayer p, ItemStack is, TETransCircle te)
	{
		if (!te.circle.isEmpty())
		{
			NBTTagCompound tag = new NBTTagCompound();
			int sz = 0;
			for (int i = 0; i < te.circle.size(); i++)
			{
				// if (!te.circle.get(i).pt.extended)
				{
					sz += 1;
				}
			}
			if (sz > 0)
			{
				tag.setInteger("Size", sz);
				for (int i = 0; i < te.circle.size(); i++)
				{
					Circle c = te.circle.get(i);
					// if (!c.pt.extended)
					{
						tag.setString("Part_" + i, ModCircles.getprts(c.pt));
						tag.setDouble("Scale_" + i, c.scale);
						tag.setBoolean("rev_" + i, c.rev);
					}
				}
				is.setTagCompound(tag);
				is.setItemDamage(1);
			}
		}
	}

	public static void handleAlchPaperPlacing(EntityPlayer p, ItemStack is, TETransCircle te)
	{
		if (is.getItemDamage() == 1)
		{
			NBTTagCompound tag = is.getTagCompound();

			int count = tag.getInteger("Size");
			if (count > 0)
			{
				List<Circle> l = new ArrayList<>();
				for (int i = 0; i < count; i++)
				{
					String code = tag.getString("Part_" + i);
					boolean rev = tag.getBoolean("rev_" + i);
					if (p.isSneaking())
					{
						rev = !rev;
					}
					double scale = tag.getDouble("Scale_" + i);
					Circle c = new Circle(ModCircles.getprtsr(code), scale, rev);
					if (!l.contains(c))
					{
						l.add(c);
					}
				}
				if (te.circle.size() == l.size())
				{
					List<String> tl = new ArrayList<>();
					List<String> ll = new ArrayList<>();

					for (int i = 0; i < count; i++)
					{
						String sst = ModCircles.getCodeStr(te.circle.get(i));
						tl.add(sst.substring(0, 2) + sst.substring(sst.length() - 1, sst.length()));

						String ssl = ModCircles.getCodeStr(l.get(i));
						ll.add(sst.substring(0, 2) + sst.substring(sst.length() - 1, sst.length()));
					}
					Logger.info("TL" + tl);
					Logger.info("LL" + ll);
					if (InventoryUtils.containsOnly(tl, ll))
					{
						for (int i = 0; i < count; i++)
						{
							te.circle.get(i).scale += 1;
						}
					}
				}
				else
				{
					te.circle.clear();
					te.circle.addAll(l);
				}
			}
		}
	}

	// ==============TileEntity======================
	public static List<String> modify(List<String> l, double mod)
	{
		List<String> lst = new ArrayList<>();
		for (String c : l)
		{
			String num = c.substring(2, c.length() - 1);
			double i = Double.parseDouble(num);
			lst.add(c.substring(0, 2) + (int) (i * mod) + c.substring(c.length() - 1));
		}

		return lst;
	}

	public static double getMinScale(List<Circle> l)
	{
		double min = 100;

		for (int i = 0; i < l.size(); i++)
		{
			double s = l.get(i).scale;
			if (s < min)
			{
				min = s;
			}
		}

		return min;
	}

	public static double getMaxScale(List<Circle> l)
	{
		double maxSize = 1;

		for (Circle c : l)
		{
			if (c.scale > maxSize)
			{
				maxSize = c.scale;
			}
		}
		return maxSize;
	}

	public static void clearEnergy(TETransCircle te)
	{
		te.energy = 0;
		te.energyType = EnergyType.Unknown;
	}

	public static void bindBlockTexture(Circle c)
	{
		if (!c.rev)
		{
			MiscUtils.bindTexture("aam:" + c.pt.block.getResourcePath());
		}
		else
		{
			MiscUtils.bindTexture("aam:" + c.pt.blockRev.getResourcePath());
		}
	}

	// TODO
	public static void bindItemTexture(Circle c)
	{
		if (!c.rev)
		{
			MiscUtils.bindTexture("aam:" + c.pt.block.getResourcePath());
		}
		else
		{
			MiscUtils.bindTexture("aam:" + c.pt.blockRev.getResourcePath());
		}
	}

	/**
	 * @param te
	 * @param pr
	 * @retur whether circle had been added
	 */
	public static boolean tryToAddCircle(TETransCircle te, Circle pr)
	{
		boolean cont = false;
		for (Circle par : te.circle)
		{
			cont = par.pt.equals(pr.pt) && par.rev == pr.rev;
			if (cont)
			{
				pr = par;
				break;
			}
		}
		if (!cont)
		{
			te.circle.add(pr);
			if (pr.pt.extended)
			{
				te.extended = true;
			}
			return true;
		}
		else
		{
			pr.scale += 1.0;
			return false;
		}
	}
}
