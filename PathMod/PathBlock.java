package PathMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PathBlock extends Block
{
	public PathBlock()
	{
		super(Material.grass);
		// this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0, 0, 0, 1, 0.875f, 1);
	}

	public IIcon[] icons = new IIcon[3];

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		icons[0] = ir.registerIcon("pathmod:grass_path_top");
		icons[1] = ir.registerIcon("pathmod:gravel_path_top");
		icons[2] = ir.registerIcon("pathmod:sand_path_top");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return icons[meta];
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	{
		if (!w.getBlock(x, y + 1, z).isAir(w, x, y, z))
		{
			this.setBlockBounds(0, 0, 0, 1, 1, 1);
		}
		else
			this.setBlockBounds(0, 0, 0, 1, 0.875f, 1);

	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
	{
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox((double) x, (double) y, (double) z, (double) (x + 1), (double) ((float) (y + 1) - f), (double) (z + 1));
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
