package aam.utils.vectors;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockState
{
	public BlockState(Block b, int x, int y, int z)
	{
		this.b = b;
		this.x = x;
		this.y = y;
		this.z = z;
		meta = 0;
	}

	public BlockState(Block b, Wec3 wp, int meta)
	{
		this.b = b;
		x = (int) wp.x;
		y = (int) wp.y;
		z = (int) wp.z;
		this.meta = meta;
	}

	public BlockState(Block b, Wec3 wp)
	{
		this.b = b;
		x = (int) wp.x;
		y = (int) wp.y;
		z = (int) wp.z;
		meta = 0;
	}

	public BlockState(Block b, int x, int y, int z, int meta)
	{
		this.b = b;
		this.x = x;
		this.y = y;
		this.z = z;
		this.meta = meta;
	}

	public void print(World w)
	{
		w.setBlock(x, y, z, b);
		w.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}

	public void print(VectorWorld w)
	{
		w.setBlock(x, y, z, b);
		w.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}

	public Block b;
	public int meta;
	public int x;
	public int y;
	public int z;

}
