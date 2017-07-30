package AAM.Common.Dungeon;

import java.util.List;
import java.util.Random;

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

public class DungeonChunkProvider implements IChunkProvider
{
	public static Random r;
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
		this.r = new Random(seed);
		this.sound1 = new NoiseGeneratorOctaves(this.r, 16);
		this.sound2 = new NoiseGeneratorOctaves(this.r, 16);
		this.sound3 = new NoiseGeneratorOctaves(this.r, 8);
		this.sound4 = new NoiseGeneratorPerlin(this.r, 4);

		this.noiseGen1 = new NoiseGeneratorOctaves(this.r, 10);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.r, 16);

		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.r, 8);

	}

	@Override
	public boolean chunkExists(int x, int z)
	{
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		this.r.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		Block[] ablock = new Block[65536];
		Byte[] abyte = new Byte[65536];

		// Generators work here
		this.generator.func_151539_a(this, this.w, x, z, ablock);
		this.cavegen.func_151539_a(this, this.w, x, z, ablock);
		// Generators stopped working

		Chunk chunk = new Chunk(this.w, ablock, x, z);
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
		BiomeGenBase biome = this.w.getBiomeGenForCoords(x, z);
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
	public void recreateStructures(int x, int y)
	{
		// TODO
	}

	@Override
	public void saveExtraData()
	{

	}

}
