package AAM.Common.Transmutations.Actions;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActDark extends TransAction
{
	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		int n = (int) (5 + (Math.log(potency + 1) / Math.log(1.4))) * 2;

		for (int i = -n; i <= n; i++)
		{
			for (int j = -n; j <= n; j++)
			{
				for (int k = -n; k <= n; k++)
				{
					if (w.getBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k) == ModBlocks.LightBlock)
					{
						w.setBlockToAir((int) tile.x + i, (int) tile.y + j, (int) tile.z + k);
					}
				}
			}
		}
	}
}
