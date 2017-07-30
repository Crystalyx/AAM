package AAM.Common.WorldGen;

import java.util.Random;

import AAM.Common.Blocks.ModBlocks;
import AAM.Utils.MiscUtils;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class PlantWorldGen implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);

		int generatorId = random.nextInt(3);
		if (generatorId == 0)
		{
			int y = MiscUtils.gethighestBlock(w, x, z);
			if (w.getBlock(x, y, z) == Blocks.grass)
			{
				w.setBlock(x, y + 1, z, ModBlocks.BerryBush, w.rand.nextInt(8), 2);
			}
		}
		if (generatorId == 1)
		{
			int y = MiscUtils.gethighestBlock(w, x, z);
			int stage = 0;
			for (int i = y - 1; i > 0; i--)
			{
				if (stage == 0)
					if (w.isAirBlock(x, i, z))
					{
						stage = 1;
					}
				if (stage == 1)
					if (!w.isAirBlock(x, i, z))
					{
						stage = 0;
						y = i;
						break;
					}
			}
			if (w.doesBlockHaveSolidTopSurface(w, x, y, z))
				w.setBlock(x, y + 1, z, ModBlocks.ShroomPlant);
		}

		if (generatorId == 2)
		{
			int y = MiscUtils.gethighestBlock(w, x, z);
			if (w.getBlock(x, y, z) == Blocks.grass)
			{
				w.setBlock(x, y + 1, z, ModBlocks.ShadowveilPlant);
			}
		}
	}

}
