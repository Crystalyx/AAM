package aam.common.blocks.plants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ModLog extends BlockRotatedPillar
{
	public IIcon side;
	public IIcon top;
	public String texture;

	public ModLog(Material mat, String texture)
	{
		super(mat);
		this.texture = texture;
		this.setHardness(2.0F);
		this.setStepSound(soundTypeWood);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		side = ir.registerIcon("aam:" + texture + "_log_side");
		top = ir.registerIcon("aam:" + texture + "_log_top");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess w, int x, int y, int z, int s)
	{
		return this.getIcon(s, w.getBlockMetadata(x, y, z));
	}

	@Override
	protected IIcon getSideIcon(int meta)
	{
		return side;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int meta)
	{
		return top;
	}

	/**
	 * Determines if this block can prevent leaves connected to it from
	 * decaying.
	 *
	 * @param world
	 *            The current world
	 * @param x
	 *            X Position
	 * @param y
	 *            Y Position
	 * @param z
	 *            Z Position
	 * @return true if the presence this block can prevent leaves from decaying.
	 */
	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

}
