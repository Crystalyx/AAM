package aam.common.items.weapon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ModificationCatalyst extends Item
{
	public ModificationCatalyst()
	{
		this.setHasSubtypes(true);
		this.setUnlocalizedName("aam.catalyst");
	}

	public IIcon[] icons = new IIcon[11];

	@Override
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < icons.length; i++)
		{
			icons[i] = ir.registerIcon("aam:catalysts/cat_" + i);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
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
