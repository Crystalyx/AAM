package aam.common.dungeon;

import aam.common.blocks.building.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class ElementalBiome extends BiomeGenBase
{

	public ElementalBiome(int id)
	{
		super(id);
		enableRain = false;
		enableSnow = false;
		biomeName = "aamdungeon";
		topBlock = Blocks.bedrock;
		fillerBlock = ModBlocks.Bricks;

		spawnableCaveCreatureList.clear();
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();

		this.setHeight(new Height(250, 6));
	}

	@Override
	public void decorate(World w, Random r, int x, int z)
	{
		super.decorate(w, r, x, z);
	}
}
