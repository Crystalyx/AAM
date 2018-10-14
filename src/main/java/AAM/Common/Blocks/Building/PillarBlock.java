package aam.common.blocks.building;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author user
 */
public class PillarBlock extends Block
{

	public PillarBlock()
	{
		super(Material.rock);
		this.setHardness(1.0F);
		this.setResistance(10.0F);
		this.setBlockBounds(1 / 8F, 0, 1 / 8F, 1 - 1 / 8F, 1, 1 - 1 / 8F);
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
	public int getRenderType()
	{
		return 133;
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
