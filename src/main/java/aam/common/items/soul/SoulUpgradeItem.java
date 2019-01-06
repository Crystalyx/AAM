package aam.common.items.soul;

import aam.common.soul.SoulUpgrade;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class SoulUpgradeItem extends Item
{
	public SoulUpgradeItem()
	{
		this.setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		for (int j = 0; j < SoulUpgrade.values().length; j++)
		{
			l.add(new ItemStack(i, 1, j));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		return this.getUnlocalizedName() + "_" + i.getItemDamage();
	}

	public IIcon[] icons = new IIcon[SoulUpgrade.values().length];

	@Override
	public void registerIcons(IIconRegister ir)
	{
		for (int j = 0; j < SoulUpgrade.values().length; j++)
		{
			icons[j] = ir.registerIcon(SoulUpgrade.values()[j].texture);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}

}
