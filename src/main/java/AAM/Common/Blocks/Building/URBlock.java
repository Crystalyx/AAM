package aam.common.blocks.building;

import java.util.Random;

import aam.utils.InventoryUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class URBlock extends Block
{
	public URBlock()
	{
		super(Material.rock);
	}

	@Override
	public float getBlockHardness(World w, int x, int y, int z)
	{
		return w.getBlock(x, y - 1, z).getBlockHardness(w, x, y - 1, z);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	{
		Block b = w.getBlock(x, y - 1, z);
		double lx = b.getBlockBoundsMinX();
		double ly = b.getBlockBoundsMinY();
		double lz = b.getBlockBoundsMinZ();
		double mx = b.getBlockBoundsMaxX();
		double my = b.getBlockBoundsMaxY();
		double mz = b.getBlockBoundsMaxZ();
		this.setBlockBounds((float) lx, (float) ly, (float) lz, (float) mx, (float) my, (float) mz);
	}

	@Override
	public void breakBlock(World w, int x, int y, int z, Block p_149749_5_, int p_149749_6_)
	{
		super.breakBlock(w, x, y, z, p_149749_5_, p_149749_6_);
		InventoryUtils.dropStack(w, x, y, z, new ItemStack(w.getBlock(x, y - 1, z), 1, w.getBlockMetadata(x, y - 1, z)));
		w.setBlockToAir(x, y - 1, z);
	}

	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b)
	{
		if (w.isAirBlock(x, y - 1, z))
		{
			w.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		return w.getBlock(x, y - 1, z).onBlockActivated(w, x, y - 1, z, p, side, px, py, pz);
	}

	@Override
	public int getRenderType()
	{
		return 130;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition mop, World w, int x, int y, int z, EntityPlayer p)
	{
		return new ItemStack(w.getBlock(x, y - 1, z), 1, w.getBlockMetadata(x, y - 1, z));
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
