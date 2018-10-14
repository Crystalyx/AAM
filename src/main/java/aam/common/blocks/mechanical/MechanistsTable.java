package aam.common.blocks.mechanical;

import aam.common.tiles.TEMechanistsTable;
import aam.core.AAMCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MechanistsTable extends BlockContainer
{

	public MechanistsTable()
	{
		super(Material.wood);
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		p.openGui(AAMCore.instance, 4, w, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TEMechanistsTable();
	}

	public static IIcon topIcon;
	public static IIcon sideIcon;
	public static IIcon botIcon;

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		topIcon = ir.registerIcon("aam:table_top");
		sideIcon = ir.registerIcon("aam:table_side");
		botIcon = ir.registerIcon("aam:trees/darkwood/_plank_cut");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == ForgeDirection.UP.ordinal())
		{
			return topIcon;
		}
		if (side == ForgeDirection.DOWN.ordinal())
		{
			return botIcon;
		}
		return sideIcon;
	}

	@Override
	public int getRenderType()
	{
		return 140;
	}

	@Override
	public boolean isNormalCube()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

}
