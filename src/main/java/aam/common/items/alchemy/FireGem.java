package aam.common.items.alchemy;

import aam.utils.InventoryUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class FireGem extends Item
{
	public FireGem()
	{
		this.setHasSubtypes(true);
		this.setUnlocalizedName("aam.fire_gem");
	}

	public static IIcon[] icon = new IIcon[3];
	public static String[] names = new String[] { "small", "medium", "big" };

	@Override
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < 3; i++)
		{
			icon[i] = ir.registerIcon("aam:fire_gem_" + names[i]);
		}
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		Wec3 pw = new Wec3(x, y, z);
		pw.centralize();
		List<Entity> l = w.getEntitiesWithinAABB(Entity.class, pw.extendBoth(4 + 4 * i.getItemDamage()));
		l.remove(p);
		for (Entity el : l)
		{
			el.setFire(40 + 40 * i.getItemDamage());
		}

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
