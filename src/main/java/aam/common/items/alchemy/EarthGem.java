package aam.common.items.alchemy;

import aam.utils.InventoryUtils;
import aam.utils.MiscUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class EarthGem extends Item
{
	public EarthGem()
	{
		this.setHasSubtypes(true);
		this.setUnlocalizedName("aam.earth_gem");
	}

	public static IIcon[] icon = new IIcon[3];
	public static String[] names = new String[] { "small", "medium", "big" };

	@Override
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < 3; i++)
		{
			icon[i] = ir.registerIcon("aam:earth_gem_" + names[i]);
		}
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		Wec3 pw = new Wec3(x, y, z);
		pw.centralize();
		MiscUtils.attractEntitiesTo(w, pw, 4 + 4 * i.getItemDamage(), 0.5f, p);

		InventoryUtils.decrPlayerStack(p, 1);
		return true;
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icon[meta];
	}

	@Override
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		for (int j = 0; j < 3; j++)
		{
			l.add(new ItemStack(i, 1, j));
		}
	}
}
