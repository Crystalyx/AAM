package aam.common.dungeon;

import aam.common.blocks.building.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;

public class DungWorldGenerator extends MapGenBase
{
	/**
	 * Generation Function
	 */
	@Override
	public void func_151539_a(IChunkProvider prov, World w, int x, int z, Block[] ablock)
	{
		super.func_151539_a(prov, w, x, z, ablock);
		for (int i = 0; i < ablock.length; i++)
		{
			ablock[i] = ModBlocks.Bricks;
		}
	}
}
