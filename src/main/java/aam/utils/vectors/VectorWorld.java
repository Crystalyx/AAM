package aam.utils.vectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class VectorWorld
{
	// TODO
	public World w;
	public Random rand;
	public boolean isRemote;

	public VectorWorld(World w)
	{
		this.w = w;
		rand = w.rand;
		isRemote = w.isRemote;
	}

	// ===================Translations======================
	public Wec3 origin = new Wec3(0, 0, 0);

	public Wec3 basX = new Wec3(1, 0, 0);
	public Wec3 basY = new Wec3(0, 1, 0);
	public Wec3 basZ = new Wec3(0, 0, 1);

	public void translate(Wec3 pos)
	{
		origin = origin.add(getRealWec3(pos));
	}

	public Wec3 getRealWec3(Wec3 v)
	{
		return basX.mult(v.x).add(basY.mult(v.y).add(basZ.mult(v.z)));
	}

	public void applyRotation(WorldRotation pos)
	{
		Wec3 rx = pos.rotX;
		Wec3 ry = pos.rotY;
		Wec3 rz = pos.rotZ;

		basX = new Wec3(basX.x * rx.x + basX.y * ry.x + basX.z * rz.x, basX.x * rx.y + basX.y * ry.y + basX.z * rz.y, basX.x * rx.z + basX.y * ry.z + basX.z * rz.z);
		basY = new Wec3(basY.x * rx.x + basY.y * ry.x + basY.z * rz.x, basY.x * rx.y + basY.y * ry.y + basY.z * rz.y, basY.x * rx.z + basY.y * ry.z + basY.z * rz.z);
		basZ = new Wec3(basZ.x * rx.x + basZ.y * ry.x + basZ.z * rz.x, basZ.x * rx.y + basZ.y * ry.y + basZ.z * rz.y, basZ.x * rx.z + basZ.y * ry.z + basZ.z * rz.z);

	}

	public void alignYAxisToDirection(ForgeDirection dir)
	{
		if (dir.offsetX != 0)
		{
			WorldRotation wr1 = new WorldRotation(ForgeDirection.NORTH, Math.PI / 2 * dir.offsetX);
			this.applyRotation(wr1);
		}
		if (dir.offsetZ != 0)
		{
			WorldRotation wr1 = new WorldRotation(ForgeDirection.EAST, Math.PI / 2 * dir.offsetZ);
			this.applyRotation(wr1);
		}

		if (dir.offsetY < 0)
		{
			WorldRotation wr1 = new WorldRotation(ForgeDirection.NORTH, Math.PI);
			this.applyRotation(wr1);
		}
	}

	public void clearRotation()
	{
		basX = new Wec3(1, 0, 0);
		basY = new Wec3(0, 1, 0);
		basZ = new Wec3(0, 0, 1);
	}

	// ====================World=Methods==========================

	/**
	 * Will get all entities within the specified AABB excluding the one passed
	 * into it. Args: entityToExclude, aabb
	 */
	public List getEntitiesWithinAABBExcludingEntity(Entity e, AxisAlignedBB aabb)
	{
		Wec3 S = origin.add(getRealWec3(new Wec3(aabb.minX, aabb.minY, aabb.minZ)));
		Wec3 T = origin.add(getRealWec3(new Wec3(aabb.maxX, aabb.maxY, aabb.maxZ)));

		return w.getEntitiesWithinAABBExcludingEntity(e, S.extendTo(T), (IEntitySelector) null);
	}

	public List getEntitiesWithinAABBExcludingEntity(Entity e, AxisAlignedBB aabb, IEntitySelector sel)
	{
		Wec3 S = origin.add(getRealWec3(new Wec3(aabb.minX, aabb.minY, aabb.minZ)));
		Wec3 T = origin.add(getRealWec3(new Wec3(aabb.maxX, aabb.maxY, aabb.maxZ)));

		return w.getEntitiesWithinAABBExcludingEntity(e, S.extendTo(T), sel);
	}

	/**
	 * checks to see if a given block is both water and is cold enough to freeze
	 */
	public boolean isBlockFreezable(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));

		return w.isBlockFreezable((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	/**
	 * checks to see if a given block is both water and has at least one
	 * immediately adjacent non-water block
	 */
	public boolean isBlockFreezableNaturally(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));

		return w.isBlockFreezableNaturally((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	/**
	 * checks to see if a given block is both water, and cold enough to freeze -
	 * if the par4 boolean is set, this will only return true if there is a
	 * non-water block immediately adjacent to the specified block
	 */
	public boolean canBlockFreeze(Wec3 pos, boolean p_72834_4_)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));

		return w.canBlockFreeze((int) Wp.x, (int) Wp.y, (int) Wp.z, p_72834_4_);
	}

	public boolean canBlockFreezeBody(Wec3 pos, boolean liquid)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.canBlockFreeze((int) Wp.x, (int) Wp.y, (int) Wp.z, liquid);
	}

	/**
	 * Creates an explosion. Args: entity, x, y, z, strength
	 */
	public Explosion createExplosion(Entity e, Wec3 pos, float strength, boolean griefing)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));

		return this.newExplosion(e, Wp, strength, false, griefing);
	}

	/**
	 * returns a new explosion. Does initiation (at time of writing Explosion is
	 * not finished)
	 */
	public Explosion newExplosion(Entity e, Wec3 pos, float strength, boolean p_72885_9_, boolean p_72885_10_)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));

		return w.newExplosion(e, (int) Wp.x, (int) Wp.y, (int) Wp.z, strength, p_72885_9_, p_72885_10_);
	}

	/**
	 * Spawns a particle. Args particleName, x, y, z, velX, velY, velZ
	 */
	public void spawnParticle(String name, Wec3 pos, Wec3 vel)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		Wec3 Vp = origin.add(getRealWec3(vel));
		w.spawnParticle(name, (int) Wp.x, (int) Wp.y, (int) Wp.z, (int) Vp.x, (int) Vp.y, (int) Vp.z);
	}

	/**
	 * Checks whether its daytime by seeing if the light subtracted from the
	 * skylight is less than 4
	 */
	public boolean isDaytime()
	{
		return w.provider.isDaytime();
	}

	/**
	 * Returns how bright the block is shown as which is the block's light value
	 * looked up in a lookup table (light values aren't linear for brightness).
	 * Args: x, y, z
	 */
	public float getLightBrightness(Wec3 pos)
	{
		return w.provider.lightBrightnessTable[this.getBlockLightValue(pos)];
	}

	/**
	 * Gets the light value of a block location
	 */
	public int getBlockLightValue(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.getBlockLightValue((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	/**
	 * Does the same as getBlockLightValue_do but without checking if its not a
	 * normal block
	 */
	public int getFullBlockLightValue(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.getFullBlockLightValue((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	/**
	 * Sets a block to 0 and notifies relevant systems with the block change
	 * Args: x, y, z
	 */
	public boolean setBlockToAir(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return this.setBlock(Wp, Blocks.air, 0, 3);
	}

	/**
	 * Sets the block ID and metadata at a given location. Args: X, Y, Z, new
	 * block ID, new metadata, flags. Flag 1 will cause a block update. Flag 2
	 * will send the change to clients (you almost always want this). Flag 4
	 * prevents the block from being re-rendered, if this is a client world.
	 * Flags can be added together.
	 */
	public boolean setBlock(Wec3 pos, Block b, int meta, int flag)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.setBlock((int) Wp.x, (int) Wp.y, (int) Wp.z, b, meta, flag);
	}

	/**
	 * Returns true if the block at the specified coordinates is empty
	 */
	public boolean isAirBlock(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		Block block = this.getBlock(Wp);
		return block.isAir(w, (int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	/**
	 * Returns whether a block exists at world coordinates x, y, z
	 */
	public boolean blockExists(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.blockExists((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	public void setBlock(Wec3 pos, Block b)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		w.setBlock((int) Wp.x, (int) Wp.y, (int) Wp.z, b);
	}

	public Block getBlock(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.getBlock((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	public TileEntity getTile(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.getTileEntity((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	public int getBlockMetadata(Wec3 pos)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));
		return w.getBlockMetadata((int) Wp.x, (int) Wp.y, (int) Wp.z);
	}

	public void setBlock(int x, int y, int z, Block b)
	{
		Wec3 Wp = origin.add(getRealWec3(new Wec3(x, y, z)));
		w.setBlock((int) Wp.x, (int) Wp.y, (int) Wp.z, b);
	}

	public void setBlockMetadataWithNotify(int x, int y, int z, int meta, int flag)
	{
		Wec3 Wp = origin.add(getRealWec3(new Wec3(x, y, z)));
		w.setBlockMetadataWithNotify((int) Wp.x, (int) Wp.y, (int) Wp.z, meta, flag);
	}

	public List getEntitiesWithinAABB(Class ent, Wec3 s, Wec3 t)
	{
		Wec3 S = origin.add(getRealWec3(s));
		Wec3 T = origin.add(getRealWec3(t));

		return w.getEntitiesWithinAABB(ent, S.extendTo(T));
	}

	public List getEntitiesWithinAABB(Class ent, AxisAlignedBB aabb)
	{
		Wec3 S = origin.add(getRealWec3(new Wec3(aabb.minX, aabb.minY, aabb.minZ)));
		Wec3 T = origin.add(getRealWec3(new Wec3(aabb.maxX, aabb.maxY, aabb.maxZ)));

		return w.getEntitiesWithinAABB(ent, S.extendTo(T));
	}

	public ArrayList<ItemStack> getBlockDrops(Wec3 pos, int metadata, int fortune)
	{
		Wec3 Wp = origin.add(getRealWec3(pos));

		return this.getBlock(Wp).getDrops(w, Wp.ix, Wp.iy, Wp.iz, metadata, fortune);
	}

}
