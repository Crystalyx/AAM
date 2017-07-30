package AAM.Common.Blocks.Mechanical;

import AAM.Client.Renderer.Tile.ArmouryRenderer;
import AAM.Common.Tiles.TileArmoury;
import AAM.Core.AAMCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Armoury extends BlockContainer
{
	public Armoury()
	{
		super(Material.iron);
		this.setHardness(2.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TileArmoury();
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		p.openGui(AAMCore.instance, 2, w, x, y, z);
		return true;
	}

	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack is)
	{
		super.onBlockPlacedBy(w, x, y, z, p, is);
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
		return 129;
	}

}
