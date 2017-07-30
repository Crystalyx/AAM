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
public class MetaItem extends Item
{
	protected IIcon[] icons;
	protected String[] textures;
	protected String[] names;

	public MetaItem(String[] text, String[] name)
	{
		textures = text;
		names = name;
		icons = new IIcon[text.length];
		this.setUnlocalizedName("aam.metaitem");
	}

	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}

	@Override
	public void registerIcons(IIconRegister ir)
	{
		for (int j = 0; j < this.textures.length; j++)
		{
			icons[j] = ir.registerIcon("aam:" + this.textures[j]);
		}
	}

	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		for (int j = 0; j < this.textures.length; j++)
		{
			l.add(new ItemStack(i, 1, j));
		}
	}

	public String getUnlocalizedName(ItemStack i)
	{
		return this.names[i.getItemDamage()];
	}

}
