package aam.common.transmutations.actions.waves;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.MathUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActMelt extends TransAction
{
	public ActMelt(int size)
	{
		super(size);
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		int n = (int) potency * 6;

		for (int i = -n; i <= n; i++)
		{
			for (int j = -n; j <= n; j++)
			{
				for (int k = -n; k <= n; k++)
				{
					if (MathUtils.randWPercent(w.rand, 10))
					{
						Block b = w.getBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k);
						if (b == Blocks.packed_ice)
						{
							w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, Blocks.water);
						}
						if (b == Blocks.ice)
						{
							w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, Blocks.flowing_water);
						}
						if (b == Blocks.obsidian)
						{
							w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, Blocks.lava);
						}
						if (b == Blocks.cobblestone)
						{
							w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, Blocks.flowing_lava);
						}
						if (b == Blocks.snow_layer)
						{
							w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, Blocks.air);
						}
					}
				}
			}
		}

		if (te.completeTimer > 400)

		{
			return false;
		}
		return true;
	}
}
