/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Tile;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class TileEntityAccelerator extends TileEntity
{
	int syncTick;
	@Override
	public void updateEntity()
	{
		updateInPedestal(worldObj, xCoord, yCoord, zCoord);
//		if(syncTick == 0)
//		{
//			if(!this.worldObj.isRemote)
//				MiscUtils.sendPacketToAllAround(worldObj, getDescriptionPacket(), xCoord, yCoord, zCoord, this.worldObj.provider.dimensionId, 16);
//			syncTick = 10;
//		}else
//			--this.syncTick;
	}
	
	public void updateInPedestal(World world, int x, int y, int z)
	{		
		//if (!world.isRemote)
		{
			AxisAlignedBB bBox = AxisAlignedBB.getBoundingBox(x-4, y-4, z-4, x+4, y+4, z+4);
			speedUpTileEntities(world, 240, bBox);
			speedUpRandomTicks(world, 240, bBox);

		}
	}
	
	private void speedUpTileEntities(World world, int bonusTicks, AxisAlignedBB bBox)
	{
		if (bBox == null || bonusTicks == 0) // Sanity check the box for chunk unload weirdness
		{
			return;
		}
		Iterator<TileEntity> iter = getTileEntitiesWithinAABB(world, bBox).iterator();
		while (iter.hasNext())
		{
			TileEntity tile = iter.next();
			for (int i = 0; i < bonusTicks; i++)
			{
				if (tile instanceof TileEntityAccelerator)
				{
					
				}
				else
				{
					tile.updateEntity();
				}
			}
		}
	}
	
	public static List<TileEntity> getTileEntitiesWithinAABB(World world, AxisAlignedBB bBox)
	{
		List<TileEntity> list = Lists.newArrayList();

		for (int i = (int) bBox.minX; i <= bBox.maxX; i++)
			for (int j = (int) bBox.minY; j <= bBox.maxY; j++)
				for (int k = (int) bBox.minZ; k <= bBox.maxZ; k++)
				{
					TileEntity tile = world.getTileEntity(i, j, k);
					if (tile != null)
					{
						list.add(tile);
					}
				}

		return list;
	}

	private void speedUpRandomTicks(World world, int bonusTicks, AxisAlignedBB bBox)
	{
		if (bBox == null || bonusTicks == 0) // Sanity check the box for chunk unload weirdness
		{
			return;
		}
		for (int x = (int) bBox.minX; x <= bBox.maxX; x++)
		{
			for (int y = (int) bBox.minY; y <= bBox.maxY; y++)
			{
				for (int z = (int) bBox.minZ; z <= bBox.maxZ; z++)
				{
					Block block = world.getBlock(x, y, z);

					if (block.getTickRandomly()
							&& !(block instanceof BlockLiquid) // Don't speed vanilla non-source blocks - dupe issues
							&& !(block instanceof BlockFluidBase) // Don't speed Forge fluids - just in case of dupes as well
						)
					{
						for (int i = 0; i < bonusTicks; i++)
						{
							block.updateTick(world, x, y, z, world.rand);
						}
					}
				}
			}

		}
	}
}
