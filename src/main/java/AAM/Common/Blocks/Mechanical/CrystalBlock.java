package AAM.Common.Blocks.Mechanical;

import AAM.Common.Blocks.ModBlocks;
import AAM.Common.Tiles.TECrystal;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class CrystalBlock extends BlockContainer
{

	public CrystalBlock()
	{
		super(Material.rock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setLightLevel(2F);
		this.setBlockName("aamcrystal");
		this.setBlockBounds(1 / 8F, 0, 1 / 8F, 1 - 1 / 8F, 1, 1 - 1 / 8F);
	}

	@Override
	public int getRenderType()
	{
		return 134;
	}
	
	public static IIcon[] icons = new IIcon[2];

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		icons[0] = ir.registerIcon("aam:pillar");
		icons[1] = ir.registerIcon("aam:pillar_top");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == ForgeDirection.UP.ordinal() || side == ForgeDirection.DOWN.ordinal())
		{
			return icons[1];
		}
		return icons[0];
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

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TECrystal();
	}
	
	@Override
	public void onBlockAdded(World w, int x, int y, int z)
	{
		w.setBlock(x, y+1, z, ModBlocks.PHBlock);
	}

}
