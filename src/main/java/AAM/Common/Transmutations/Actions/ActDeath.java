package AAM.Common.Transmutations.Actions;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.Wec3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActDeath extends TransAction
{

	public ActDeath()
	{
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		int n = (int) potency / 2;

		for (int i = -n; i <= n; i++)
		{
			for (int j = -n; j <= n; j++)
			{
				for (int k = -n; k <= n; k++)
				{
					Block b = w.getBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k);
					if ((b instanceof IGrowable && b != Blocks.grass) || b instanceof BlockBush)
					{
						w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, Blocks.air);
						// TODO
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
