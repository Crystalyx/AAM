package AAM.Common.Blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ModLog extends BlockRotatedPillar
{
	protected IIcon side;
	protected IIcon top;
	protected String texture;

	protected ModLog(Material p_i45425_1_, String texture)
	{
		super(p_i45425_1_);
		this.texture = texture;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.side = ir.registerIcon("aam:" + this.texture + "_log_side");
		this.top = ir.registerIcon("aam:" + this.texture + "_log_top");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess w, int x, int y, int z, int s)
	{
		return this.getIcon(s, w.getBlockMetadata(x, y, z));
	}

	@Override
	public void getSubBlocks(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
	}

	@Override
	protected IIcon getSideIcon(int meta)
	{
		return side;
	}

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
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

}
