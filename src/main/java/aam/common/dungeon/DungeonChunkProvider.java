package aam.common.dungeon;

import aam.common.worldgen.DungGenerator;
import aam.utils.MathUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import java.util.List;
import java.util.Random;

public class DungeonChunkProvider implements IChunkProvider
{
	public Random r;
	public NoiseGeneratorOctaves sound1;
	public NoiseGeneratorOctaves sound2;
	public NoiseGeneratorOctaves sound3;
	public NoiseGeneratorPerlin sound4;
	/**
	 * Used in generating terrain
	 */
	public NoiseGeneratorOctaves noiseGen1;
	/**
	 * Used in generating terrain
	 */
	public NoiseGeneratorOctaves noiseGen2;
	public NoiseGeneratorOctaves mobSpawnerNoise;

	public World w;

	public DungWorldGenerator generator = new DungWorldGenerator();
	public MapGenCaves cavegen = new MapGenCaves();

	public DungeonChunkProvider(World w, long seed)
	{
		this.w = w;
		r = new Random(seed);
		sound1 = new NoiseGeneratorOctaves(r, 16);
		sound2 = new NoiseGeneratorOctaves(r, 16);
		sound3 = new NoiseGeneratorOctaves(r, 8);
		sound4 = new NoiseGeneratorPerlin(r, 4);

		noiseGen1 = new NoiseGeneratorOctaves(r, 10);
		noiseGen2 = new NoiseGeneratorOctaves(r, 16);

		mobSpawnerNoise = new NoiseGeneratorOctaves(r, 8);

	}

	@Override
	public boolean chunkExists(int x, int z)
	{
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		r.setSeed(x * 341873128712L + z * 132897987541L);
		Block[] ablock = new Block[65536];

		// Generators work here
		generator.func_151539_a(this, w, x, z, ablock);
		cavegen.func_151539_a(this, w, x, z, ablock);
		// Generators stopped working

		Chunk chunk = new Chunk(w, ablock, x, z);
		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	public Chunk loadChunk(int x, int z)
	{
		return this.provideChunk(x, z);
	}

	@Override
	public void populate(IChunkProvider p_73153_1_, int x, int z)
	{

	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	{
		return true;
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return "RandomLevelSource";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType type, int x, int y, int z)
	{
		BiomeGenBase biome = w.getBiomeGenForCoords(x, z);
		return biome.getSpawnableList(type);
	}

	@Override
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(int x, int z)
	{
		DungGenerator dg = new DungGenerator();
		dg.generate(MathUtils.r, x, z, w, this, this);
	}

	@Override
	public void saveExtraData()
	{

	}

}
