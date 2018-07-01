package AAM.Common.Dungeon;

import java.util.Random;

import AAM.Common.Blocks.Building.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ElementalBiome extends BiomeGenBase
{

	public ElementalBiome(int id)
	{
		super(id);
		this.enableRain = false;
		this.enableSnow = false;
		this.biomeName = "aamdungeon";
		this.topBlock = Blocks.bedrock;
		this.fillerBlock = ModBlocks.Bricks;

		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		this.setHeight(new Height(250, 6));
	}

	@Override
	public void decorate(World w, Random r, int x, int z)
	{
		super.decorate(w, r, x, z);
	}
}
