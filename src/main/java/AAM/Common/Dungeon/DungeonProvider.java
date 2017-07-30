package AAM.Common.Dungeon;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class DungeonProvider extends WorldProvider
{
	@Override
	public String getDimensionName()
	{
		return "aamdung";
	}

	@Override
	public String getSaveFolder()
	{
		return "DIM-Dungeon";
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Descending To The Dungeon";
	}

	@Override
	public String getDepartMessage()
	{
		return "Ascending from Dungeon";
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(0, 64, 0);
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	@Override
	public boolean canDoLightning(Chunk chunk)
	{
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return false;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new DungeonChunkProvider(this.worldObj, this.worldObj.getSeed());
	}

}
