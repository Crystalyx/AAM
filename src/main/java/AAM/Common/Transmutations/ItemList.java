package AAM.Common.Transmutations;

import java.util.ArrayList;
import java.util.List;

import AAM.Utils.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemList
{
	public List<ItemStack> items = new ArrayList<ItemStack>();

	public void add(Item i, int count)
	{
		if (count > 0)
		{
			ItemStack ley = EnergyProvider.findIS(i).copy();
			ley.stackSize = count;
			this.items.add(ley);
		}
	}

	public void add(Item i, int meta, int count)
	{
		if (count > 0)
		{
			ItemStack ley = EnergyProvider.findIS(i, meta).copy();
			ley.stackSize = count;
			this.items.add(ley);
		}
	}

	public void add(Block i, int count)
	{
		if (count > 0)
		{
			if (Item.getItemFromBlock(i) != null)
			{
				ItemStack ley = EnergyProvider.findIS(Item.getItemFromBlock(i)).copy();
				ley.stackSize = count;
				this.items.add(ley);
			}
		}
	}

	public void add(Block i, int meta, int count)
	{
		if (count > 0)
		{
			ItemStack ley = EnergyProvider.findIS(Item.getItemFromBlock(i), meta).copy();
			ley.stackSize = count;
			this.items.add(ley);
		}
	}

}
