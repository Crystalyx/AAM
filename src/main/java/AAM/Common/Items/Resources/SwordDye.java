package AAM.Common.Items.Resources;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SwordDye extends Item
{

	public SwordDye()
	{
		this.setHasSubtypes(true);
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int i)
	{
		return icon[i];
	}

	public static IIcon[] icon = new IIcon[9];
	public static String[] names = new String[] { "red", "green", "blue", "cyan", "purple", "yellow", "void", "philo", "space" };

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		String wp = "aam:tools/shield-";
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
	public String getUnlocalizedName(ItemStack i)
	{
		String name = names[i.getItemDamage()];
		return "aam.swdye." + name;

	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item is, CreativeTabs tab, List l)
	{
		for (int i = 0; i < this.icon.length; i++)
		{
			l.add(new ItemStack(is, 1, i));
		}
	}

}
