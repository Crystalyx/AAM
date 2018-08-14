package AAM.Common.Potions;

import java.util.ArrayList;
import java.util.List;

import AAM.Utils.Color;
import AAM.Utils.Pair;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Ingridient
{

	public Ingridient(Item i, int meta, Color col, int id, Object... args)
	{
		items.add(new Pair(i, meta));

		this.color = col;
		this.id = id;
		this.hasBetter = args.length > 0;

		for (int j = 0; j < args.length; j++)
		{
			if (args[j] instanceof Item)
			{
				Item ij = (Item) args[j];
				if (j + 1 < args.length)
				{
					int metaj = (int) args[j + 1];
					items.add(new Pair(ij, metaj));
				}
				else
					items.add(new Pair(ij, 0));
			}
			else
				if (args[j] instanceof Block)
				{
					Item ij = Item.getItemFromBlock((Block) args[j]);
					if (j + 1 < args.length)
					{
						int metaj = (int) args[j + 1];
						items.add(new Pair(ij, metaj));
					}
					else
						items.add(new Pair(ij, 0));
				}
				else
					if (args[j] instanceof ItemStack)
					{
						ItemStack is = (ItemStack) args[j];
						items.add(new Pair(is.getItem(), is.getItemDamage()));
					}
		}
	}

	public Ingridient(ItemStack i, Color col, int id, Object... args)
	{
		items.add(new Pair(i.getItem(), i.getItemDamage()));

		this.color = col;
		this.id = id;
		this.hasBetter = args.length > 0;

		for (int j = 0; j < args.length; j++)
		{
			if (args[j] instanceof Item)
			{
				Item ij = (Item) args[j];
				if (j + 1 < args.length)
				{
					int metaj = (int) args[j + 1];
					items.add(new Pair(ij, metaj));
					j++;
				}
				else
					items.add(new Pair(ij, 0));
			}
			else
				if (args[j] instanceof Block)
				{
					Item ij = Item.getItemFromBlock((Block) args[j]);
					if (j + 1 < args.length)
					{
						int metaj = (int) args[j + 1];
						items.add(new Pair(ij, metaj));
						j++;
					}
					else
						items.add(new Pair(ij, 0));
				}
				else
					if (args[j] instanceof ItemStack)
					{
						ItemStack is = (ItemStack) args[j];
						items.add(new Pair(is.getItem(), is.getItemDamage()));
					}
		}
	}

	public List<Pair<Item, Integer>> items = new ArrayList<Pair<Item, Integer>>();
	public boolean hasBetter = false;
	public Color color;
	public int id;

}
