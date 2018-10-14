package aam.common.blocks.mechanical;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Expander extends Block
{

	public Expander(Material mat)
	{
		super(mat);
		this.setHardness(20.0F);
		this.setBlockTextureName("aam:expander");
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int siden, float px, float py, float pz)
	{
		int sx = -7;
		int ex = 8;
		int sz = -7;
		int ez = 8;
		int sy = -7;
		int ey = 8;

		ForgeDirection side = ForgeDirection.getOrientation(siden);

		if (side.equals(ForgeDirection.DOWN))
		{
			sy = 0;
			ey = 2;

		}

		if (side.equals(ForgeDirection.UP))
		{
			sy = -1;
			ey = 1;
		}

		if (side.equals(ForgeDirection.NORTH))
		{
			sz = 0;
			ez = 2;
		}

		if (side.equals(ForgeDirection.SOUTH))
		{
			sz = -1;
			ez = 1;
		}

		if (side.equals(ForgeDirection.WEST))
		{
			sx = 0;
			ex = 2;
		}

		if (side.equals(ForgeDirection.EAST))
		{
			sx = -1;
			ex = 1;
		}

		for (int i = sx; i < ex; i++)
		{
			for (int j = sz; j < ez; j++)
			{
				for (int k = sy; k < ey; k++)
				{
					w.setBlock(x + i, y + k, z + j, Blocks.grass, 2, 2);
				}
			}
		}
		return true;
	}

}
