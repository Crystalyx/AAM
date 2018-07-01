package AAM.Common.Transmutations;

import java.util.Hashtable;
import java.util.List;

import AAM.Common.Tiles.TETransCircle;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.VectorWorld;
import AAM.Utils.Wec3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ExtAction
{
	public boolean check(VectorWorld w, String[][] circles, ItemList el)
	{
		boolean complete = true;
		for (int i = -2; i <= 2; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				if (w.getTile(new Wec3(i, 0, j)) != null)
				{
					if (w.getTile(new Wec3(i, 0, j)) instanceof TETransCircle)
					{
						TETransCircle te = (TETransCircle) w.getTile(new Wec3(i, 0, j));
						complete = MiscUtils.containsOnly(ModCircles.getCodeStr(te.circle), ModCircles.getList(circles[i + 2][j + 2]));
					}
					else
					{
						complete = false;
					}
				}
				else
				{
					complete = circles[i + 2][j + 2].equals("");
				}

				if (!complete)
				{
					return false;
				}
			}
		}
		List<EntityItem> l = w.getEntitiesWithinAABB(EntityItem.class, new Wec3().centralize().extendBoth(1.5f));
		Hashtable<ItemStack, Integer> lis = new Hashtable<ItemStack, Integer>();
		Hashtable<ItemStack, ItemStack> entStacks = new Hashtable<ItemStack, ItemStack>();

		if (!l.isEmpty())
			for (int i = 0; i < l.size(); i++)
			{
				EntityItem ei = l.get(i);
				if (ei.getEntityItem() != null)
				{
					int count = ei.getEntityItem().stackSize;
					ItemStack key = EnergyProvider.findIS(ei.getEntityItem().getItem(), ei.getEntityItem().getItemDamage());
					lis.put(key, count);
					entStacks.put(key, ei.getEntityItem());
				}
			}
		if (!el.items.isEmpty())
			for (int i = 0; i < el.items.size(); i++)
			{
				ItemStack is = el.items.get(i);
				if (is.getItem() != null)
				{
					ItemStack key = EnergyProvider.findIS(is.getItem(), is.getItemDamage());
					if (lis.containsKey(key))
					{
						int count = lis.get(key);
						if (count < is.stackSize)
						{
							return false;
						}
					}
					else
						return false;

				}
			}
		// if (!el.items.isEmpty())
		// for (int i = 0; i < el.items.size(); i++)
		// {
		// ItemStack is = el.items.get(i);
		// if (is.getItem() != null)
		// {
		// ItemStack key = EnergyProvider.findIS(is.getItem(),
		// is.getItemDamage());
		// if (entStacks.containsKey(key))
		// {
		// entStacks.get(key).stackSize -= is.stackSize;
		// entStacks.remove(key);
		// Logger.info("Removing");
		// }
		// }
		// }
		return true;
	}

	public boolean print(VectorWorld w, CirclePart part, ItemList el)
	{
		for (int i = -2; i <= 2; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				if (!(i == 0 && j == 0) && w.getTile(new Wec3(i, 0, j)) != null)
					w.setBlock(new Wec3(i, 0, j), Blocks.air);
			}
		}

		List<EntityItem> l = w.getEntitiesWithinAABB(EntityItem.class, new Wec3().centralize().extendBoth(1.5f));
		Hashtable<ItemStack, Integer> lis = new Hashtable<ItemStack, Integer>();
		Hashtable<ItemStack, ItemStack> entStacks = new Hashtable<ItemStack, ItemStack>();

		if (!w.isRemote)
		{
			if (!l.isEmpty())
				for (int i = 0; i < l.size(); i++)
				{
					EntityItem ei = l.get(i);
					if (ei.getEntityItem() != null)
					{
						int count = ei.getEntityItem().stackSize;
						ItemStack key = EnergyProvider.findIS(ei.getEntityItem().getItem(), ei.getEntityItem().getItemDamage());
						lis.put(key, count);
						entStacks.put(key, ei.getEntityItem());
					}
				}
			if (!el.items.isEmpty())
				for (int i = 0; i < el.items.size(); i++)
				{
					ItemStack is = el.items.get(i);
					if (is.getItem() != null)
					{
						ItemStack key = EnergyProvider.findIS(is.getItem(), is.getItemDamage());
						if (lis.containsKey(key))
						{
							int count = lis.get(key);
							if (count < is.stackSize)
							{
								return false;
							}
						}
						else
							return false;

					}
				}
			if (!el.items.isEmpty())
				for (int i = 0; i < el.items.size(); i++)
				{
					ItemStack is = el.items.get(i);
					if (is.getItem() != null)
					{
						ItemStack key = EnergyProvider.findIS(is.getItem(), is.getItemDamage());
						if (entStacks.containsKey(key))
						{
							entStacks.get(key).stackSize -= is.stackSize;
							entStacks.remove(key);
							Logger.info("Removing - " + is.stackSize);
						}
					}
				}
		}
		TETransCircle te = (TETransCircle) w.getTile(new Wec3());
		te.circle.clear();
		te.circle.add(new Circle(part, 1, false));
		return true;
	}
}
