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
import net.minecraft.world.IBlockAccess;

public class Storage extends Block
{
	public Storage(Material m)
	{
		super(m);
		this.setHardness(1.0F);
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

	public String[] textures = new String[0];

	public IIcon[] icons;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		icons = new IIcon[textures.length];
		for (int k = 0; k < this.textures.length; k++)
			this.icons[k] = ir.registerIcon("aam:" + textures[k]);
	}

	@Override
	public void getSubBlocks(Item i, CreativeTabs tab, List l)
	{
		for (int k = 0; k < this.textures.length; k++)
			l.add(new ItemStack(i, 1, k));

	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return this.icons[meta];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess w, int x, int y, int z, int s)
	{
		return this.getIcon(s, w.getBlockMetadata(x, y, z));
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.icons[meta];
	}
}
