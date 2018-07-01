package AAM.Common.Transmutations.Actions;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.BlockState;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActExtinguish extends TransAction
{

	public ActExtinguish()
	{
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		int n = 4 + (int) potency / 2;
		if (Math.floorDiv(time, n) >= n + 4)
		{
			return false;
		}
		else
		{
			for (int i = -Math.floorDiv(time, n); i <= Math.floorDiv(time, n); i++)
			{
				for (int j = -Math.floorDiv(time, n); j <= Math.floorDiv(time, n); j++)
				{
					for (int k = -Math.floorDiv(time, n); k <= Math.floorDiv(time, n); k++)
					{
						if (w.getBlock((int) tile.x + i, (int) tile.y + k, (int) tile.z + j) == Blocks.fire)
						{
							BlockState bs = new BlockState(Blocks.air, (int) (i + tile.x), (int) tile.y + k, (int) (j + tile.z));
							bs.print(w);
						}
					}
				}
			}

		}
		return true;
	}

}
