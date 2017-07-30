package AAM.Common.Potions;

import AAM.Utils.Color;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Prolonger extends Ingridient
{
	public Prolonger(Item i, int meta, Color col, int id, int time)
	{
		super(i, meta, col, id);
		this.time = time;
	}

	public Prolonger(ItemStack i, Color col, int id, int time)
	{
		super(i, col, id);
		this.time = time;
	}

	public int time;

}
