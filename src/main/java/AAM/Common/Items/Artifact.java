package AAM.Common.Items;

import java.util.List;

import AAM.Utils.Soul;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Artifact extends Item
{
	public Artifact()
	{
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
		for (int k = 0; k < Soul.values().length; k++)
			l.add(new ItemStack(i, 1, k));
	}

	public static IIcon[] icons = new IIcon[Soul.values().length];
	public static String[] ways = new String[Soul.values().length];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icons[0] = ir.registerIcon("aam:component_4");// light
		icons[1] = ir.registerIcon("aam:component_6");// normal
		icons[2] = ir.registerIcon("aam:component_3");// blood
		icons[3] = ir.registerIcon("aam:component_7");// lunar
		icons[4] = ir.registerIcon("aam:component_2");// plant
		icons[5] = ir.registerIcon("aam:component_5");// water
		icons[6] = ir.registerIcon("aam:component_1");// first

		icons[Soul.values().length - 1] = ir.registerIcon("aam:component_8");// seventh

		ways[0] = "component_4";// light
		ways[1] = "component_6"; // normal
		ways[2] =  "component_3"; // blood
		
		ways[3] =  "component_7"; // lunar
		ways[4] =  "component_2"; // plant
		ways[5] =  "component_5"; // water
		
		ways[6] =  "component_1"; // first
		
		ways[Soul.values().length - 1] =  "component_8"; // seventh
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return "aam_artifact" + is.getItemDamage();
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}
}
