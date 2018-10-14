package aam.common.transmutations.actions.waves;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.MiscUtils;
import aam.utils.vectors.BlockState;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActFireWave extends TransAction
{

	public ActFireWave(int size)
	{
		super(size);
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		int n = 3 + (int) potency / 2 - size * 2;

		if (Math.floorDiv(time, n) >= n)
		{
			return false;
		}
		else
		{
			for (int i = -Math.floorDiv(time, n); i <= Math.floorDiv(time, n); i++)
			{
				for (int j = -Math.floorDiv(time, n); j <= Math.floorDiv(time, n); j++)
				{
					int h = MiscUtils.getLowerHighBlock(w, new Wec3(i, 16, j).add(tile));
					if (w.getBlock((int) tile.x + i, h + 1, (int) tile.z + j).isAir(w, (int) tile.x + i, h + 1, (int) tile.z + j) && w.getBlock((int) tile.x + i, h, (int) tile.z + j).isNormalCube())
					{
						BlockState bs = new BlockState(Blocks.fire, (int) (i + tile.x), h + 1, (int) (j + tile.z));
						bs.print(w);
					}
				}
			}

		}
		return true;
	}

}
