/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Utils;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Lord_Crystalyx
 */
public class WorldPos
{
	public double x;
	public double y;
	public double z;

	public Block getBlock(World w)
	{
		return w.getBlock((int) this.x, (int) this.y, (int) this.z);
	}

	public int getBlockMeta(World w)
	{
		return w.getBlockMetadata((int) this.x, (int) this.y, (int) this.z);
	}

	public TileEntity getTileEntity(World w)
	{
		return w.getTileEntity((int) this.x, (int) this.y, (int) this.z);
	}

	public AxisAlignedBB getAABB(float r)
	{
		return AxisAlignedBB.getBoundingBox(this.x - r, this.y - r, this.z - r, this.x + r, this.y + r, this.z + r);
	}

	public AxisAlignedBB getAABB(float rx, float ry, float rz)
	{
		return AxisAlignedBB.getBoundingBox(this.x - rx, this.y - ry, this.z - rz, this.x + rx, this.y + ry, this.z + rz);
	}

	/**
	 * 
	 * @param a
	 *            first Point
	 * @param b
	 *            second Point
	 * @return AxisAlignedBB
	 */
	public AxisAlignedBB getAABB(WorldPos b)
	{
		WorldPos p = b.subtruct(this);
		return AxisAlignedBB.getBoundingBox(this.x + p.x, this.y - p.y, this.z - p.z, this.x + p.x, this.y + p.y, this.z + p.z);
	}

	public WorldPos()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public WorldPos(MovingObjectPosition mop)
	{
		if (mop.typeOfHit.equals(MovingObjectType.BLOCK))
		{
			this.x = mop.blockX;
			this.y = mop.blockY;
			this.z = mop.blockZ;
		}
		if (mop.typeOfHit.equals(MovingObjectType.ENTITY))
		{
			this.x = mop.entityHit.posX;
			this.y = mop.entityHit.posY;
			this.z = mop.entityHit.posZ;
		}
	}

	public WorldPos(double px, double py, double pz)
	{
		this.x = px;
		this.y = py;
		this.z = pz;
	}

	public static WorldPos random(double sizex, double sizey, double sizez)
	{
		Random r = new Random();
		WorldPos ret = new WorldPos(sizex * r.nextDouble() * MiscUtils.boolToNum(), sizey * r.nextDouble() * MiscUtils.boolToNum(), sizez * r.nextDouble() * MiscUtils.boolToNum());
		return ret;
	}

	public double distance()
	{
		return MiscUtils.getDistance(this, new WorldPos());
	}

	public double distanceTo(WorldPos to)
	{
		return  MiscUtils.getDistance(this, to);
	}

	public void normalize()
	{
		double max = Math.max(Math.abs(this.x), Math.max(Math.abs(this.y), Math.abs(this.z)));
		this.x /= max;
		this.y /= max;
		this.z /= max;
	}

	public WorldPos(Entity p)
	{
		this.x = p.posX;
		this.y = p.posY;
		this.z = p.posZ;
	}

	public WorldPos(Vec3 v)
	{
		this.x = v.xCoord;
		this.y = v.yCoord;
		this.z = v.zCoord;
	}

	public static WorldPos getWorldPos(Entity p)
	{
		if (p != null)
		{
			return new WorldPos(p);
		} else
			return null;
	}

	public WorldPos(TileEntity tile)
	{
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
	}

	public WorldPos(ForgeDirection dir)
	{
		this.x = dir.offsetX;
		this.y = dir.offsetY;
		this.z = dir.offsetZ;
	}

	public WorldPos centralize()
	{
		this.x += 0.5;
		this.y += 0.5;
		this.z += 0.5;
		return this;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

	public double getZ()
	{
		return this.z;
	}

	public WorldPos setX(double i)
	{
		this.x = i;
		return this;
	}

	public WorldPos setY(double i)
	{
		this.y = i;
		return this;
	}

	public WorldPos setZ(double i)
	{
		this.z = i;
		return this;
	}

	public WorldPos subtruct(WorldPos what)
	{
		WorldPos ret = new WorldPos(this.x - what.x, this.y - what.y, this.z - what.z);
		return ret;
	}

	public WorldPos add(WorldPos what)
	{
		WorldPos ret = new WorldPos(this.x + what.x, this.y + what.y, this.z + what.z);
		return ret;
	}

	public WorldPos add(ForgeDirection what)
	{
		WorldPos ret = new WorldPos(this.x + what.offsetX, this.y + what.offsetY, this.z + what.offsetZ);
		return ret;
	}

	public WorldPos modify(double what)
	{
		WorldPos ret = new WorldPos(this.x * what, this.y * what, this.z * what);
		return ret;
	}

	public WorldPos divide(double what)
	{
		WorldPos ret = new WorldPos(this.x / what, this.y / what, this.z / what);
		return ret;
	}

	public WorldPos power(double what)
	{
		WorldPos ret = new WorldPos(this.x != 0 ? Math.pow(this.x, what) : 0, this.y != 0 ? Math.pow(this.y, what) : 0, this.z != 0 ? Math.pow(this.z, what) : 0);
		return ret;
	}

	public Vec3 toVec()
	{
		return Vec3.createVectorHelper(this.x, this.y, this.z);
	}

	public double[] toArray()
	{
		return new double[]
		{ this.x, this.y, this.z };
	}

	public int[] toIntArray()
	{
		return new int[]
		{ (int) this.x, (int) this.y, (int) this.z };
	}

	public String toString()
	{
		return "{" + this.x + ";" + this.y + ";" + this.z + "}";
	}

	public WorldPos flip()
	{
		WorldPos ret = new WorldPos(-this.x, -this.y, -this.z);
		return ret;
	}

	public void ptm(Entity e)
	{
		e.setVelocity(this.x, this.y, this.z);
	}

	public void ptp(Entity e)
	{
		e.setPosition(this.x, this.y, this.z);
	}

	public AxisAlignedBB toAABB()
	{
		return AxisAlignedBB.getBoundingBox(this.x, this.y, this.z, this.x, this.y, this.z);
	}
}
