package aam.common.items.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class SwordDye extends Item
{

	public SwordDye()
	{
		this.setHasSubtypes(true);
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int i)
	{
		return icon[i];
	}

	public static IIcon[] icon = new IIcon[9];
	public static String[] names = new String[] { "red", "green", "blue", "cyan", "purple", "yellow", "void", "philo", "space" };

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		String wp = "aam:tools/shield_";
		for (int i = 0; i < 9; i++)
		{
			icon[i] = ir.registerIcon(wp + names[i]);
		}
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		String name = names[i.getItemDamage()];
		return "aam.swdye." + name;

	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item is, CreativeTabs tab, List l)
	{
		for (int i = 0; i < icon.length; i++)
		{
			l.add(new ItemStack(is, 1, i));
		}
	}

}
