package AAM.Common.Potions;

import AAM.Utils.Color;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Colorer extends Ingridient
{
	public Colorer(Item i, int meta, Color col, int id)
	{
		super(i, meta, col, id);
	}

	public Colorer(ItemStack i, Color col, int id)
	{
		super(i, col, id);
	}

	public Colorer(Item i, int meta, Color col, int id, Item ib, int metab)
	{
		super(i, meta, col, id, ib, metab);
	}

	public Colorer(ItemStack i, Color col, int id, Item ib, int metab)
	{
		super(i, col, id, ib, metab);
	}

}
