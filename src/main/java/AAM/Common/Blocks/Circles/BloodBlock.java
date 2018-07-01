package AAM.Common.Blocks.Circles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BloodBlock extends BlockFluidClassic
{

	public BloodBlock(Fluid blood)
	{
		super(blood, Material.water);
	}

	protected IIcon still;
	protected IIcon flowing;

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		still = ir.registerIcon("aam:blood_still");
		flowing = ir.registerIcon("aam:blood_flow");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? still : flowing;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		return super.displaceIfPossible(world, x, y, z);
	}

}
