package AAM.Common.Blocks.Mechanical;

import AAM.API.IStructureCore;
import AAM.Common.Tiles.TESoulAltar;
import AAM.Core.AAMCore;
import AAM.Utils.Structure;
import AAM.Utils.Structures;
import AAM.Utils.Wec3;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SoulAltar extends BlockContainer
{
	public SoulAltar()
	{
		super(Material.iron);
		this.setHardness(2.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TESoulAltar();
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		p.openGui(AAMCore.instance, 1, w, x, y, z);
		return true;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isNormalCube()
	{
		return false;
	}

	public int getRenderType()
	{
		return 131;
	}
}
