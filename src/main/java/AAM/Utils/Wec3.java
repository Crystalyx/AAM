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
public class Wec3
{
	public double x;
	public double y;
	public double z;

	public int ix;
	public int iy;
	public int iz;

	public Block getBlock(World w)
	{
		return w.getBlock(this.ix, this.iy, this.iz);
	}

	public int getBlockMeta(World w)
	{
		return w.getBlockMetadata(this.ix, this.iy, this.iz);
	}

	public TileEntity getTileEntity(World w)
	{
		return w.getTileEntity(this.ix, this.iy, this.iz);
	}

	public AxisAlignedBB extend(float r)
	{
		return AxisAlignedBB.getBoundingBox(this.x, this.y, this.z, this.x + r, this.y + r, this.z + r);
	}

	public AxisAlignedBB extend(float rx, float ry, float rz)
	{
		return AxisAlignedBB.getBoundingBox(this.x, this.y, this.z, this.x + rx, this.y + ry, this.z + rz);
	}

	/**
	 * 
	 * @param a
	 *            first Point
	 * @param b
	 *            second Point
	 * @return AxisAlignedBB
	 */
	public AxisAlignedBB extendTo(Wec3 b)
	{
		return AxisAlignedBB.getBoundingBox(this.x, this.y, this.z, b.x, b.y, b.z);
	}

	public AxisAlignedBB extendBoth(float r)
	{
		return AxisAlignedBB.getBoundingBox(this.x - r, this.y - r, this.z - r, this.x + r, this.y + r, this.z + r);
	}

	public AxisAlignedBB extendBoth(float rx, float ry, float rz)
	{
		return AxisAlignedBB.getBoundingBox(this.x - rx, this.y - ry, this.z - rz, this.x + rx, this.y + ry, this.z + rz);
	}

	/**
	 * 
	 * @param a
	 *            first Point
	 * @param b
	 *            vec
	 * @return AxisAlignedBB
	 */
	public AxisAlignedBB extendBoth(Wec3 b)
	{
		Wec3 p = b.subtruct(this);
		return AxisAlignedBB.getBoundingBox(this.x - p.x, this.y - p.y, this.z - p.z, this.x + p.x, this.y + p.y, this.z + p.z);
	}

	public Wec3()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public Wec3(MovingObjectPosition mop)
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

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public Wec3(double px, double py, double pz)
	{
		this.x = px;
		this.y = py;
		this.z = pz;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public static Wec3 random(double sizex, double sizey, double sizez)
	{
		Random r = new Random();
		Wec3 ret = new Wec3(sizex * r.nextDouble() * MiscUtils.boolToNum(), sizey * r.nextDouble() * MiscUtils.boolToNum(), sizez * r.nextDouble() * MiscUtils.boolToNum());
		return ret;
	}

	public double length()
	{
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}

	public double distanceTo(Wec3 to)
	{
		double dx = this.x - to.x;
		double dy = this.y - to.y;
		double dz = this.z - to.z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public void normalize()
	{
		double max = Math.max(Math.abs(this.x), Math.max(Math.abs(this.y), Math.abs(this.z)));
		this.x /= max;
		this.y /= max;
		this.z /= max;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public Wec3(Entity p)
	{
		this.x = p.posX;
		this.y = p.posY;
		this.z = p.posZ;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public Wec3(Vec3 v)
	{
		this.x = v.xCoord;
		this.y = v.yCoord;
		this.z = v.zCoord;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public static Wec3 getWorldPos(Entity p)
	{
		if (p != null)
		{
			return new Wec3(p);
		}
		else
			return null;
	}

	public Wec3(TileEntity tile)
	{
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public Wec3(ForgeDirection dir)
	{
		this.x = dir.offsetX;
		this.y = dir.offsetY;
		this.z = dir.offsetZ;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;
	}

	public Wec3 centralize()
	{
		this.x += 0.5;
		this.y += 0.5;
		this.z += 0.5;

		this.ix = (int) x;
		this.iy = (int) y;
		this.iz = (int) z;

		return this;
	}

	public Wec3 subtruct(Wec3 what)
	{
		Wec3 ret = new Wec3(this.x - what.x, this.y - what.y, this.z - what.z);
		return ret;
	}

	public Wec3 add(Wec3 what)
	{
		Wec3 ret = new Wec3(this.x + what.x, this.y + what.y, this.z + what.z);
		return ret;
	}

	public Wec3 add(ForgeDirection what)
	{
		Wec3 ret = new Wec3(this.x + what.offsetX, this.y + what.offsetY, this.z + what.offsetZ);
		return ret;
	}

	public Wec3 multiply(double what)
	{
		Wec3 ret = new Wec3(this.x * what, this.y * what, this.z * what);
		return ret;
	}

	public Wec3 divide(double what)
	{
		Wec3 ret = new Wec3(this.x / what, this.y / what, this.z / what);
		return ret;
	}

	public Wec3 power(double what)
	{
		Wec3 ret = new Wec3(this.x != 0 ? Math.pow(this.x, what) : 0, this.y != 0 ? Math.pow(this.y, what) : 0, this.z != 0 ? Math.pow(this.z, what) : 0);
		return ret;
	}

	public Vec3 toVec()
	{
		return Vec3.createVectorHelper(this.x, this.y, this.z);
	}

	public double[] toArray()
	{
		return new double[] { this.x, this.y, this.z };
	}

	public int[] toIntArray()
	{
		return new int[] { this.ix, this.iy, this.iz };
	}

	public String toString()
	{
		return "{" + this.x + ";" + this.y + ";" + this.z + "}";
	}

	public Wec3 flip()
	{
		Wec3 ret = new Wec3(-this.x, -this.y, -this.z);
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
