package aam.common.blocks.building;

import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;

import java.util.Random;

public class ModOreBlock extends BlockOre
{
	public Item drop;
	/**
	 * +count per each fortune level
	 */
	public int fortune = 0;
	public int count = 1;
	public int dcount = 0;

	public ModOreBlock(String name, float hardness, float resist, Item drop, int count, int dcount, int fortune)
	{
		super();
		this.setBlockName(name);
		this.setBlockTextureName("aam:ores/" + name + "_ore");
		this.setHardness(hardness);
		this.setResistance(resist);
		this.drop = drop;
		this.count = count;
		this.fortune = fortune;
		this.dcount = dcount;
	}

	public ModOreBlock(String name, float hardness, float resist)
	{
		super();
		this.setBlockName(name);
		this.setBlockTextureName("aam:ores/" + name + "_ore");
		this.setHardness(hardness);
		this.setResistance(resist);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random r, int p_149650_3_)
	{
		return drop != null ? drop : Item.getItemFromBlock(this);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random r)
	{
		return count + r.nextInt(dcount);
	}

	/**
	 * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
	 * (inclusive).
	 */
	@Override
	public int quantityDroppedWithBonus(int fortune, Random r)
	{
		return this.quantityDropped(r) + fortune * this.fortune;
	}
}
