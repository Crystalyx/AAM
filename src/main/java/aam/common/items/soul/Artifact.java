package aam.common.items.soul;

import aam.common.soul.Soul;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class Artifact extends Item
{
	public Artifact()
	{
		this.setHasSubtypes(true);
	}

	/**
	 * returns a list of items with the same ID, but different repairItemMeta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		for (int k = 0; k < Soul.values().length; k++)
		{
			l.add(new ItemStack(i, 1, k));
		}
	}

	public static IIcon[] icons = new IIcon[Soul.values().length];
	public static String[] ways = new String[Soul.values().length];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < icons.length; i++)
		{
			icons[i] = ir.registerIcon("aam:soulsword/component_" + i);
			ways[i] = "soulsword/component_" + i;
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
		return "aam.artifact" + is.getItemDamage();
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		try
		{
			return icons[meta];
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
