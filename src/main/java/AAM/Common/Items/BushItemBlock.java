package AAM.Common.Items;

import AAM.Common.Blocks.BerryBush;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BushItemBlock extends ItemBlock
{
	public Item b;

	public BushItemBlock(Block b)
	{
		super(b);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int i)
	{
		return i;
	}

	public String getUnlocalizedName(ItemStack i)
	{
		return i.getItem().getUnlocalizedName() + BerryBush.names[Math.floorDiv(i.getItemDamage(), 2)];
	}

	IIcon[] icons = new IIcon[5];

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icons[0] = ir.registerIcon("aam:blackberrybush");
		icons[1] = ir.registerIcon("aam:blueberrybush");
		icons[2] = ir.registerIcon("aam:mortisbush");
		icons[3] = ir.registerIcon("aam:raspberrybush");
		icons[4] = ir.registerIcon("aam:bush");

	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		int beta = Math.floorMod(meta, 2);
		if (beta == 1)
			return this.icons[Math.floorDiv(meta, 2)];
		else
			return this.icons[4];
	}

}
