package aam.common.blocks.mechanical;

import aam.common.dungeon.DungeonTeleporter;
import aam.common.tiles.TETeleporter;
import aam.core.AAMCore;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTeleporter extends BlockContainer
{
	public BlockTeleporter()
	{
		super(Material.rock);
		this.setHardness(4.0F);
		this.setBlockTextureName("aam:clear_block");
		this.setBlockBounds(0, 0, 0, 1, 0.5f, 1);
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		if (!w.isRemote)
		{
			EntityPlayerMP player = (EntityPlayerMP) p;
			MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();

			if (p.dimension == AAMCore.dungdimid)
			{
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new DungeonTeleporter(mServer.worldServerForDimension(AAMCore.dungdimid)));
			}
			else
			{
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, AAMCore.dungdimid, new DungeonTeleporter(mServer.worldServerForDimension(AAMCore.dungdimid)));
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TETeleporter();
	}

	@Override
	public int getRenderType()
	{
		return 135;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isNormalCube()
	{
		return false;
	}

}
