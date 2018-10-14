package aam.common.transmutations.actions.world;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.MiscUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActGrowth extends TransAction
{

	public ActGrowth(int size)
	{
		super(size);
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		int n = (int) potency * (3 - size);

		int maxTime = (2 * n + 1) * (2 * n + 1);

		for (int i = 0; i <= n * n + n; i++)
		{
			// if (MathUtils.randWPercent(w.rand, 10))
			int id = w.rand.nextInt(maxTime);
			int x = Math.floorDiv(id, (2 * n + 1)) - n;
			int z = Math.floorMod(id, (2 * n + 1)) - n;
			int y = MiscUtils.getLowerHighBlock(w, tile.add(new Wec3(x, 0, z)), tile.iy + n - 1);

			Block b = w.getBlock((int) tile.x + x, (int) tile.y + y, (int) tile.z + z);
			if (b instanceof IGrowable)
			{
				b.updateTick(w, (int) tile.x + x, (int) tile.y + y, (int) tile.z + z, w.rand);
			}
			if ((b instanceof BlockDirt || b == Blocks.dirt) && w.canBlockSeeTheSky((int) tile.x + x, (int) tile.y + y, (int) tile.z + z))
			{
				w.setBlock((int) tile.x + x, (int) tile.y + y, (int) tile.z + z, Blocks.grass, 2, 0);
			}
		}

		if (te.completeTimer > 400)
		{
			return false;
		}
		return true;
	}

}
