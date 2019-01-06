package aam.common.worldgen;

import aam.common.blocks.building.ModBlocks;
import aam.utils.MiscUtils;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class PlantWorldGen implements IWorldGenerator
{
	@Override
	public void generate(Random r, int chunkX, int chunkZ, World w, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int x = chunkX * 16 + r.nextInt(16);
		int z = chunkZ * 16 + r.nextInt(16);

		int generatorId = r.nextInt(3);
		if (generatorId == 0)
		{
			int y = MiscUtils.gethighestBlock(w, x, z);
			if (w.getBlock(x, y, z) == Blocks.grass)
			{
				w.setBlock(x, y + 1, z, ModBlocks.BerryBush, r.nextInt(8), 2);
			}
		}
		if (generatorId == 1)
		{
			int y = MiscUtils.gethighestBlock(w, x, z);
			int stage = 0;
			for (int i = y - 1; i > 0; i--)
			{
				if (stage == 0)
				{
					if (w.isAirBlock(x, i, z))
					{
						stage = 1;
					}
				}
				if (stage == 1)
				{
					if (!w.isAirBlock(x, i, z))
					{
						stage = 0;
						y = i;
						break;
					}
				}
			}
			if (World.doesBlockHaveSolidTopSurface(w, x, y, z))
			{
				w.setBlock(x, y + 1, z, ModBlocks.ShroomPlant);
			}
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
