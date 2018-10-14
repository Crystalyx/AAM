package aam.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class StorageItemBlock extends ItemBlock
{
	public Block item;

	public StorageItemBlock(Block b)
	{
		super(b);
		item = b;
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int i)
	{
		return i;
	}

	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		return item.getUnlocalizedName() + "_" + i.getItemDamage();
	}

}
