/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Registry;

import aam.Utils.MiscUtils;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class SandOreGenerator implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int type = random.nextInt(9);
		if (Registry.exists[type])
		{
			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);
			int y = random.nextInt(64);

			int step = 0;
			boolean flag = true;

			while (flag && step < 5)
			{
				for (int i = -1; i < 2; i++)
				{
					for (int j = -1; j < 2; j++)
					{
						for (int k = -1; k < 2; k++)
						{
							if (MiscUtils.randWPercent(15))
								w.setBlock(x + i, y + j, z + k, Registry.sand[type], 0, 3);
						}
					}
				}
				// w.setBlock(x, y, z, Registry.sand[type], 0, 3);
				//
				// ForgeDirection direct =
				// ForgeDirection.getOrientation(random.nextInt(6));
				// x = x + direct.offsetX;
				// y = y + direct.offsetY;
				// z = z + direct.offsetZ;
				// ++step;
				// if (w.getBlock(x, y, z) == Registry.sand[type])
				// {
				// flag = false;
				// }
			}
		}
	}

	public void generate(Random r, int x, int y, int z, World w)
	{
		int type = r.nextInt(2);
		if (Registry.exists[type])
		{
			int step = 0;
			int max = 5;
			boolean count = true;

			for (int i = -1; i < 2; i++)
			{
				for (int j = -1; j < 2; j++)
				{
					for (int k = -1; k < 2; k++)
					{
						if (MiscUtils.randWPercent(15))
						{
							if ((count && step < max) || !count)
							{
								MiscUtils.replaceBlock(w, x + i, y + j, z + k, Registry.sand[type]);
								step++;
							}
							if (count && step >= max)
							{
								break;
							}
						}
					}
					if (count && step >= max)
					{
						break;
					}
				}
				if (count && step >= max)
				{
					break;
				}
			}
		}
	}
}
