package AAM.Common.Blocks.Mechanical;

import AAM.Common.Dungeon.DungeonTeleporter;
import AAM.Common.Tiles.TeleporterTileEntity;
import AAM.Core.AAMCore;
import AAM.Utils.Logger;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;

public class BlockTeleporter extends BlockContainer
{
	public BlockTeleporter()
	{
		super(Material.rock);
		this.setHardness(1.0F);
		this.setBlockTextureName("aam:clearblock");
		this.setBlockBounds(0, 0, 0, 1, 0.5f, 1);
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		Logger.info(p.dimension);
		if (!w.isRemote)
		{
			EntityPlayerMP player = (EntityPlayerMP) p;
			MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();

			if (p.dimension == AAMCore.dungdimid)
			{
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new DungeonTeleporter(mServer.worldServerForDimension(AAMCore.dungdimid)));
			}
			else
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, AAMCore.dungdimid, new DungeonTeleporter(mServer.worldServerForDimension(AAMCore.dungdimid)));
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TeleporterTileEntity();
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
