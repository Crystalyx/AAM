package AAM.Common.Blocks.Building;

import java.util.List;

import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SandyGlass extends BlockSand
{

	public SandyGlass()
	{
		super();
		this.setHardness(1f);
		this.setResistance(5.0f);
	}

	public static IIcon icon;

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		icon = ir.registerIcon("aam:sandy_glass");
	}

	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return icon;
	}

	@Override
	public void getSubBlocks(Item i, CreativeTabs p_149666_2_, List l)
	{
		l.add(new ItemStack(i));
	}

}
