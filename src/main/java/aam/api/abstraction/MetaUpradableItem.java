package aam.api.abstraction;

import aam.api.Interface.IUpgradableItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class MetaUpradableItem extends Item implements IUpgradableItem
{
	public IIcon itemIcon;
	public IIcon[] icons = new IIcon[4];

	@Override
	public void registerIcons(IIconRegister ir)
	{
		itemIcon = ir.registerIcon(this.getItemIcon(new ItemStack(this)));
		for (int i = 0; i < icons.length; i++)
		{
			icons[i] = ir.registerIcon("aam:spaceSimbol_" + i);
		}
	}

	@Override
	public int getColorFromItemStack(ItemStack p_82790_1_, int p_82790_2_)
	{
		return super.getColorFromItemStack(p_82790_1_, p_82790_2_);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		if (pass == 0)
		{
			return itemIcon;
		}
		else
		{
			if (this.getUpgradeLevel(stack) > 0)
			{
				return icons[Math.min(this.getUpgradeLevel(stack) - 1, 3)];
			}
			else
			{
				return itemIcon;
			}
		}
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return 2;
	}

	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public int getUpgradeLevel(ItemStack is)
	{
		return is.getItemDamage();
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is)
	{
		is.setItemDamage(is.getItemDamage() + 1);
	}

	@Override
	public void setUpgradeLevel(World w, ItemStack is, int level)
	{
		is.setItemDamage(level);
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is, int level)
	{
		is.setItemDamage(is.getItemDamage() + level);
	}

	@Override
	public boolean enableLayers(ItemStack is)
	{
		return true;
	}

	public abstract String getItemIcon(ItemStack is);

}
