package AAM.Utils;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class PositionedWorld
{
	public World w;

	public PositionedWorld(World w)
	{
		this.w = w;
	}

	public WorldPos origin = new WorldPos(0, 0, 0);

	public WorldPos basX = new WorldPos(1, 0, 0);
	public WorldPos basY = new WorldPos(0, 1, 0);
	public WorldPos basZ = new WorldPos(0, 0, 1);

	public void translate(WorldPos pos)
	{
		this.origin = this.origin.add(this.basX.modify(pos.x).add(this.basY.modify(pos.y).add(this.basZ.modify(pos.z))));
	}

	public void applyRotation(WorldRotation pos)
	{
		WorldPos rx = pos.rotX;
		WorldPos ry = pos.rotY;
		WorldPos rz = pos.rotZ;

		this.basX = new WorldPos(basX.x * rx.x + basX.y * ry.x + basX.z * rz.x, basX.x * rx.y + basX.y * ry.y + basX.z * rz.y, basX.x * rx.z + basX.y * ry.z + basX.z * rz.z);
		this.basY = new WorldPos(basY.x * rx.x + basY.y * ry.x + basY.z * rz.x, basY.x * rx.y + basY.y * ry.y + basY.z * rz.y, basY.x * rx.z + basY.y * ry.z + basY.z * rz.z);
		this.basZ = new WorldPos(basZ.x * rx.x + basZ.y * ry.x + basZ.z * rz.x, basZ.x * rx.y + basZ.y * ry.y + basZ.z * rz.y, basZ.x * rx.z + basZ.y * ry.z + basZ.z * rz.z);

	}

	public void clearRotation()
	{
		this.basX = new WorldPos(1, 0, 0);
		this.basY = new WorldPos(0, 1, 0);
		this.basZ = new WorldPos(0, 0, 1);
	}

	public void setBlock(WorldPos pos, Block b)
	{
		WorldPos Wp = origin.add(this.basX.modify(pos.x).add(this.basY.modify(pos.y).add(this.basZ.modify(pos.z))));
		w.setBlock((int) Wp.x, (int) Wp.y, (int) Wp.z, b);
	}

	public Block getBlock(WorldPos pos)
	{
		WorldPos Wp = origin.add(this.basX.modify(pos.x).add(this.basY.modify(pos.y).add(this.basZ.modify(pos.z))));
		return w.getBlock((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}
	
	public int getBlockMetadata(WorldPos pos)
	{
		WorldPos Wp = origin.add(this.basX.modify(pos.x).add(this.basY.modify(pos.y).add(this.basZ.modify(pos.z))));
		return w.getBlockMetadata((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	public void setBlock(int x, int y, int z, Block b)
	{
		WorldPos Wp = origin.add(this.basX.modify(x).add(this.basY.modify(y).add(this.basZ.modify(z))));
		w.setBlock((int) Wp.x, (int) Wp.y, (int) Wp.z, b);
	}

	public void setBlockMetadataWithNotify(int x, int y, int z, int meta, int flag)
	{
		WorldPos Wp = origin.add(this.basX.modify(x).add(this.basY.modify(y).add(this.basZ.modify(z))));
		w.setBlockMetadataWithNotify((int) Wp.x, (int) Wp.y, (int) Wp.z, meta, flag);
	}
}
