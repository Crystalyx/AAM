package aam.common.transmutations.actions.world;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.MiscUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class ActDeath extends TransAction
{

	public ActDeath(int size)
	{
		super(size);
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		int n = (int) (potency+1) * (6 - 2 * size);

		int maxTime = (2 * n + 1) * (2 * n + 1);

		for (int i = 0; i <= n * n + n; i++)
		{
			// if (MathUtils.randWPercent(w.rand, 10))
			int id = w.rand.nextInt(maxTime);
			int x = Math.floorDiv(i, (2 * n + 1)) - n;
			int z = Math.floorMod(i, (2 * n + 1)) - n;
			int y = MiscUtils.getLowerHighBlock(w, tile.add(new Wec3(x, tile.y+n, z)), tile.iy - n);

			Block b = w.getBlock((int) tile.x + x, (int) tile.y + y, (int) tile.z + z);
			if (b instanceof IGrowable && b != Blocks.grass || b instanceof BlockBush || b instanceof BlockLeavesBase || b instanceof IShearable)
			{
				w.setBlock((int) tile.x + x, (int) tile.y + y, (int) tile.z + z, Blocks.air);
			}
			if (b instanceof BlockGrass)
			{
				w.setBlock((int) tile.x + x, (int) tile.y + y, (int) tile.z + z, Blocks.dirt);
			}
		}

		if (te.completeTimer > 400)
		{
			return false;
		}
		return true;
	}

}
