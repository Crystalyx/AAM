package AAM.Common.Potions;

import AAM.Utils.Color;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Booster extends Ingridient
{
	public Booster(Item i, int meta, Color col, int id, int boost)
	{
		super(i, meta, col, id);
		this.boost = boost;
	}

	public Booster(ItemStack i, Color col, int id, int boost)
	{
		super(i, col, id);
		this.boost = boost;
	}

	public int boost;

}