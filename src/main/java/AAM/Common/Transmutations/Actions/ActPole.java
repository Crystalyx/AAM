package AAM.Common.Transmutations.Actions;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.ModCircles;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.BlockState;
import AAM.Utils.Logger;
import AAM.Utils.VectorWorld;
import AAM.Utils.Wec3;
import AAM.Utils.WorldRotation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActPole extends TransAction
{

	public ActPole()
	{
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		int h = w.getBlockMetadata((int) tile.x, (int) tile.y - 1, (int) tile.z);
		int d = (int) potency / 2;
		int hg = (int) potency + 3;
		VectorWorld pw = new VectorWorld(w);
		pw.translate(tile.subtruct(new Wec3(dir)));

		if (dir.offsetX != 0)
		{
			WorldRotation wr1 = new WorldRotation(ForgeDirection.NORTH, Math.PI / 2 * dir.offsetX);
			pw.applyRotation(wr1);
		}
		if (dir.offsetZ != 0)
		{
			WorldRotation wr1 = new WorldRotation(ForgeDirection.EAST, Math.PI / 2 * dir.offsetZ);
			pw.applyRotation(wr1);
		}

		if (dir.offsetY < 0)
		{
			WorldRotation wr1 = new WorldRotation(ForgeDirection.NORTH, Math.PI);
			pw.applyRotation(wr1);
		}
		Logger.info(pw.basX);
		Logger.info(pw.basY);
		Logger.info(pw.basZ);

		for (int i = 0; i < hg; i++)
		{
			for (int j = -d; j <= d; j++)
			{
				for (int k = -d; k <= d; k++)
				{
					if (ModCircles.isAllowed(pw, j, 0, k))
					{
						BlockState bs = new BlockState(pw.getBlock(new Wec3(j, 0, k)), new Wec3(j, i, k), h);
						if (pw.getBlock(new Wec3(j, i, k)) == Blocks.air || pw.getBlock(new Wec3(j, i, k)) == ModBlocks.TransCircle)
							bs.print(pw);
					}
				}
			}
		}
	}

}
