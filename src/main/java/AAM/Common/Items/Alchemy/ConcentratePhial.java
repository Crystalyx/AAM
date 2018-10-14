package aam.common.items.alchemy;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ConcentratePhial extends Item
{
	public ConcentratePhial()
	{
		this.setHasSubtypes(true);
		this.setUnlocalizedName("aam.concentrate_phial");
	}

	public IIcon[] icons = new IIcon[3];

	@Override
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < 3; i++)
		{
			icons[i] = ir.registerIcon("aam:potions/" + AlchemicalConcentrateItem.sizes[i] + "_concentrate");
		}
	}

	@Override
	public IIcon getIconFromDamage(int i)
	{
		return icons[i];
	}

	@Override
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		for (int j = 0; j < icons.length; j++)
		{
			l.add(new ItemStack(i, 1, j));
		}
	}
}
