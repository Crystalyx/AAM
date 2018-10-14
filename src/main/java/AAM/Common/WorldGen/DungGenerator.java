package aam.common.worldgen;

import java.util.Random;

import aam.utils.Structure;
import aam.utils.Structures;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class DungGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random r, int x, int z, World w, IChunkProvider gen, IChunkProvider p)
	{
		DungeonBase base = new DungeonBase(x * 16, 65, z * 16, Structures.elemCenter, new Structure[] { Structures.elemPathNZ, Structures.elemPathPZ, Structures.elemPathNX, Structures.elemPathPX }, 5, 5);

		if (x % 11 == 0 && z % 11 == 0)
		{
			for (int i = -5; i <= 5; i++)
			{
				for (int j = -5; j <= 5; j++)
				{
					DungeonUnit unit = new DungeonUnit(base, i, j);
					unit.able = base.getMap(i, j);
					base.units[i + 5][j + 5] = unit;
					unit.print(w);
				}
			}
		}
	}

}
