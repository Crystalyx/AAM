package AAM.Common.Items;

import AAM.Common.Blocks.Plants.BerryBush;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BushSproutItemBlock extends ItemBlock
{
	public Item b;

	public BushSproutItemBlock(Block b)
	{
		super(b);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int i)
	{
		return i;
	}

	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		return i.getItem().getUnlocalizedName() + BerryBush.names[Math.floorDiv(i.getItemDamage(), 4)];
	}

	IIcon[] icons = new IIcon[4];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icons[0] = ir.registerIcon("aam:seeds_blackberry");
		icons[1] = ir.registerIcon("aam:seeds_blueberry");
		icons[2] = ir.registerIcon("aam:seeds_mortis");
		icons[3] = ir.registerIcon("aam:seeds_raspberry");
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		int beta = Math.floorDiv(meta, 4);
		return this.icons[beta];
	}

}
