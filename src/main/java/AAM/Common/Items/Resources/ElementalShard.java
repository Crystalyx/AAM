/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Items.Resources;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * @author Lord_Crystalyx
 */
public class ElementalShard extends Item
{
	public ElementalShard()
	{
		this.setHasSubtypes(true);
		this.setUnlocalizedName("aam.elementalshard");
	}

	public void getSubItems(Item i, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(i, 1, 0));
		list.add(new ItemStack(i, 1, 1));
		list.add(new ItemStack(i, 1, 2));
		list.add(new ItemStack(i, 1, 3));
		list.add(new ItemStack(i, 1, 4));
		list.add(new ItemStack(i, 1, 5));
	}

	public IIcon[] icons = new IIcon[6];
	public static String[] names = new String[]
	{ "Fire", "Earth", "Water", "Power", "Magic", "Sky" };

	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < 6; i++)
		{
			icons[i] = ir.registerIcon("aam:elemental_crystal" + i);
		}
	}

	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	public String getUnlocalizedName(ItemStack stack)
	{
		return names[stack.getItemDamage()] + " Fragment";
	}
}
