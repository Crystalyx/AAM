package AAM.Common.Items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class StorageItemBlock extends ItemBlock
{
	public Block item;

	public StorageItemBlock(Block b)
	{
		super(b);
		this.item = b;
		this.setHasSubtypes(true);
	}

	public int getMetadata(int i)
	{
		return i;
	}

	public String getUnlocalizedName(ItemStack i)
	{
		return this.item.getUnlocalizedName() + "_" + i.getItemDamage();
	}

}
