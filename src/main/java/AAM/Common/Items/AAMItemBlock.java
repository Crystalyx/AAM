package AAM.Common.Items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @author Lord_Crystalyx
 */
public class AAMItemBlock extends ItemBlock
{
	public Block item;

	public AAMItemBlock(Block b)
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
