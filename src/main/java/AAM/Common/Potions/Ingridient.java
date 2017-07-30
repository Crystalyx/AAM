package AAM.Common.Potions;

import AAM.Utils.Color;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Ingridient
{
	public Ingridient(Item i, int meta, Color col, int id)
	{
		this.item = i;
		this.meta = meta;
		this.color = col;
		this.id = id;
	}

	public Ingridient(ItemStack i, Color col, int id)
	{
		this.item = i.getItem();
		this.meta = i.getItemDamage();
		this.color = col;
		this.id = id;
	}

	public Item item;
	public int meta;
	public Color color;
	public int id;
}
