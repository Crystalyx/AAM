package AAM.Common.Blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * 
 * @author user Used to create cut type of plank
 */
public class ModPlank2 extends Block
{
	protected IIcon[] icons;
	protected String[] textures;
	protected String base;

	protected ModPlank2(String base, String[] textures)
	{
		super(Material.wood);
		this.textures = textures;
		this.base = base;
	}

	public void addStorage(String texture)
	{
		expandArray();
		this.textures[this.textures.length - 1] = texture;
	}

	public void expandArray()
	{
		String[] ret = new String[this.textures.length + 1];
		for (int i = 0; i < this.textures.length; i++)
		{
			ret[i] = this.textures[i];
		}
		this.textures = ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icons = new IIcon[textures.length];
		for (int j = 0; j < icons.length; j++)
		{
			this.icons[j] = ir.registerIcon("aam:" + base + textures[j]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.icons[meta];
	}

	@Override
	public void getSubBlocks(Item i, CreativeTabs tab, List l)
	{
		for (int j = 0; j < icons.length; j++)
		{
			l.add(new ItemStack(i, 1, j));
		}
	}
}
