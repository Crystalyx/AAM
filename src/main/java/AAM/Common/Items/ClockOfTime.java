package AAM.Common.Items;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import AAM.API.ItemArtifact;
import AAM.Utils.PlayerDataHandler;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class ClockOfTime extends ItemArtifact implements IBauble
{
	public ClockOfTime()
	{
		super("aam.clocktime", "aam.clocktime.descr", 1);
		this.setTextureName("aam:clock_of_time");
	}

	@Override
	public BaubleType getBaubleType(ItemStack i)
	{
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack i, EntityLivingBase e)
	{
		if (e instanceof EntityPlayer)
		{
			double x = e.posX;
			double y = e.posY;
			double z = e.posZ;

			AxisAlignedBB bBox = AxisAlignedBB.getBoundingBox(x - 4, y - 4, z - 4, x + 4, y + 4, z + 4);
			PlayerDataHandler ph = PlayerDataHandler.get((EntityPlayer) e);
			int speed = (int) (16 * Math.sqrt(ph.soulLevel));
			speedUpTileEntities(e.worldObj, speed, bBox);
			speedUpRandomTicks(e.worldObj, speed, bBox);
			speedUpEntities(e.worldObj, speed, bBox);
		}
	}

	private void speedUpTileEntities(World world, int bonusTicks, AxisAlignedBB bBox)
	{
		if (bBox == null || bonusTicks == 0) // Sanity check the box for chunk
												// unload weirdness
		{
			return;
		}
		Iterator<TileEntity> iter = getTileEntitiesWithinAABB(world, bBox).iterator();
		while (iter.hasNext())
		{
			TileEntity tile = iter.next();
			for (int i = 0; i < bonusTicks; i++)
			{
//				if (tile instanceof TileEntityAccelerator)
//				{
//
//				} else
				{
					tile.updateEntity();
				}
			}
		}
	}

	public void speedUpEntities(World w, int bonusTicks, AxisAlignedBB bBox)
	{
		if (bBox == null || bonusTicks == 0) // Sanity check the box for chunk
												// unload weirdness
		{
			return;
		}
		Iterator<EntityLiving> iter = w.getEntitiesWithinAABB(EntityLiving.class, bBox).iterator();
		while (iter.hasNext())
		{
			EntityLiving tile = iter.next();
			for (int i = 0; i < bonusTicks; i++)
			{
				if (tile instanceof EntityMob)
				{

				} else
				{
					tile.onUpdate();
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

	public void speedUpRandomTicks(World world, int bonusTicks, AxisAlignedBB bBox)
	{
		if (bBox == null || bonusTicks == 0) // Sanity check the box for chunk
												// unload weirdness
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

					if (block.getTickRandomly() && !(block instanceof BlockLiquid) && !(block instanceof BlockFluidBase))
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

	@Override
	public void onEquipped(ItemStack i, EntityLivingBase e)
	{

	}

	@Override
	public void onUnequipped(ItemStack i, EntityLivingBase e)
	{

	}

	@Override
	public boolean canEquip(ItemStack var1, EntityLivingBase var2)
	{
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack var1, EntityLivingBase var2)
	{
		return true;
	}

}
