/**
 * This Class Created By Lord_Crystalyx.
 */
package aam.utils.vectors;

import aam.utils.MathUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

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
		return w.getBlock(ix, iy, iz);
	}

	public int getBlockMeta(World w)
	{
		return w.getBlockMetadata(ix, iy, iz);
	}

	public TileEntity getTileEntity(World w)
	{
		return w.getTileEntity(ix, iy, iz);
	}

	public AxisAlignedBB extend(float r)
	{
		return AxisAlignedBB.getBoundingBox(x, y, z, x + r, y + r, z + r);
	}

	public AxisAlignedBB extend(float rx, float ry, float rz)
	{
		return AxisAlignedBB.getBoundingBox(x, y, z, x + rx, y + ry, z + rz);
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
		return AxisAlignedBB.getBoundingBox(x, y, z, b.x, b.y, b.z);
	}

	public AxisAlignedBB extendBoth(float r)
	{
		return AxisAlignedBB.getBoundingBox(x - r, y - r, z - r, x + r, y + r, z + r);
	}

	public AxisAlignedBB extendBoth(float rx, float ry, float rz)
	{
		return AxisAlignedBB.getBoundingBox(x - rx, y - ry, z - rz, x + rx, y + ry, z + rz);
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
		Wec3 p = b.sub(this);
		return AxisAlignedBB.getBoundingBox(x - p.x, y - p.y, z - p.z, x + p.x, y + p.y, z + p.z);
	}

	public Wec3()
	{
		x = 0;
		y = 0;
		z = 0;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public Wec3(MovingObjectPosition mop)
	{
		if (mop.typeOfHit.equals(MovingObjectType.BLOCK))
		{
			x = mop.blockX;
			y = mop.blockY;
			z = mop.blockZ;
		}
		if (mop.typeOfHit.equals(MovingObjectType.ENTITY))
		{
			x = mop.entityHit.posX;
			y = mop.entityHit.posY;
			z = mop.entityHit.posZ;
		}

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public Wec3(double px, double py, double pz)
	{
		x = px;
		y = py;
		z = pz;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public static Wec3 random(double sizex, double sizey, double sizez)
	{
		Random r = new Random();
		Wec3 ret = new Wec3(sizex * r.nextDouble() * MathUtils.boolToNum(1, -1), sizey * r.nextDouble() * MathUtils.boolToNum(1, -1), sizez * r.nextDouble() * MathUtils.boolToNum(1, -1));
		return ret;
	}

	public double length()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double distanceTo(Wec3 to)
	{
		double dx = x - to.x;
		double dy = y - to.y;
		double dz = z - to.z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public void normalize()
	{
		double max = Math.max(Math.abs(x), Math.max(Math.abs(y), Math.abs(z)));
		x /= max;
		y /= max;
		z /= max;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public Wec3(Entity p)
	{
		x = p.posX;
		y = p.posY;
		z = p.posZ;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public Wec3(Vec3 v)
	{
		x = v.xCoord;
		y = v.yCoord;
		z = v.zCoord;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public static Wec3 getWorldPos(Entity p)
	{
		if (p != null)
		{
			return new Wec3(p);
		}
		else
		{
			return null;
		}
	}

	public Wec3(TileEntity tile)
	{
		x = tile.xCoord;
		y = tile.yCoord;
		z = tile.zCoord;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public Wec3(ForgeDirection dir)
	{
		x = dir.offsetX;
		y = dir.offsetY;
		z = dir.offsetZ;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;
	}

	public Wec3 centralize()
	{
		x += 0.5;
		y += 0.5;
		z += 0.5;

		ix = (int) x;
		iy = (int) y;
		iz = (int) z;

		return this;
	}

	public Wec3 sub(Wec3 what)
	{
		Wec3 ret = new Wec3(x - what.x, y - what.y, z - what.z);
		return ret;
	}

	public Wec3 add(Wec3 what)
	{
		Wec3 ret = new Wec3(x + what.x, y + what.y, z + what.z);
		return ret;
	}

	public Wec3 add(ForgeDirection what)
	{
		Wec3 ret = new Wec3(x + what.offsetX, y + what.offsetY, z + what.offsetZ);
		return ret;
	}

	public Wec3 mult(double what)
	{
		Wec3 ret = new Wec3(x * what, y * what, z * what);
		return ret;
	}

	public Wec3 div(double what)
	{
		Wec3 ret = new Wec3(x / what, y / what, z / what);
		return ret;
	}

	public Wec3 pow(double what)
	{
		Wec3 ret = new Wec3(x, y, z).mult(Math.pow(this.length(), what));
		return ret;
	}

	public Vec3 toVec()
	{
		return Vec3.createVectorHelper(x, y, z);
	}

	public double[] toArray()
	{
		return new double[] { x, y, z };
	}

	public int[] toIntArray()
	{
		return new int[] { ix, iy, iz };
	}

	@Override
	public String toString()
	{
		return "{" + x + ";" + y + ";" + z + "}";
	}

	public Wec3 flip()
	{
		Wec3 ret = new Wec3(-x, -y, -z);
		return ret;
	}

	public void ptm(Entity e)
	{
		e.setVelocity(x, y, z);
	}

	public void ptp(Entity e)
	{
		e.setPosition(x, y, z);
	}

	public AxisAlignedBB toAABB()
	{
		return AxisAlignedBB.getBoundingBox(x, y, z, x, y, z);
	}
}
