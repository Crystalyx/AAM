package AAM.Utils;

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
		this.meta = 0;
	}

	public BlockState(Block b, WorldPos wp, int meta)
	{
		this.b = b;
		this.x = (int) wp.x;
		this.y = (int) wp.y;
		this.z = (int) wp.z;
		this.meta = meta;
	}

	public BlockState(Block b, WorldPos wp)
	{
		this.b = b;
		this.x = (int) wp.x;
		this.y = (int) wp.y;
		this.z = (int) wp.z;
		this.meta = 0;
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
		w.setBlock(this.x, this.y, this.z, this.b);
		w.setBlockMetadataWithNotify(this.x, this.y, this.z, this.meta, 2);
	}
	public void print(PositionedWorld w)
	{
		w.setBlock(this.x, this.y, this.z, this.b);
		w.setBlockMetadataWithNotify(this.x, this.y, this.z, this.meta, 2);
	}

	public Block b;
	public int meta;
	public int x;
	public int y;
	public int z;

}
