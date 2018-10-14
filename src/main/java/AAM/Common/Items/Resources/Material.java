package aam.common.items.resources;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Material extends Item
{
	public Material()
	{
		this.setHasSubtypes(true);
	}

	public String[] textures = new String[0];

	public void addMaterial(String texture)
	{
		expandArray();
		textures[textures.length - 1] = texture;
	}

	public void expandArray()
	{
		String[] ret = new String[textures.length + 1];
		for (int i = 0; i < textures.length; i++)
		{
			ret[i] = textures[i];
		}
		textures = ret;
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		for (int k = 0; k < textures.length; k++)
		{
			l.add(new ItemStack(i, 1, k));
		}
	}

	public IIcon[] icons;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icons = new IIcon[textures.length];
		for (int k = 0; k < textures.length; k++)
		{
			icons[k] = ir.registerIcon("aam:" + textures[k]);
		}
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return this.getUnlocalizedName() + is.getItemDamage();
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}
}
