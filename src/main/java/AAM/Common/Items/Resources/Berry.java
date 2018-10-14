package aam.common.items.resources;

import java.util.List;

import aam.common.blocks.plants.BerryBush;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Berry extends ItemFood
{
	public Berry()
	{
		super(3, false);
		this.setHasSubtypes(true);
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
		l.add(new ItemStack(i, 1, 1));
		l.add(new ItemStack(i, 1, 2));
		l.add(new ItemStack(i, 1, 3));
		l.add(new ItemStack(i, 1, 4));

	}

	public IIcon[] icons = new IIcon[5];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icons[0] = ir.registerIcon("aam:ingredients/blackberry");
		icons[1] = ir.registerIcon("aam:ingredients/blueberry");
		icons[2] = ir.registerIcon("aam:ingredients/mortisberry");
		icons[3] = ir.registerIcon("aam:ingredients/raspberry");
		icons[4] = ir.registerIcon("aam:ingredients/wormwoodberry");
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return "aam.berry" + BerryBush.names[is.getItemDamage()];
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
