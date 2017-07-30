package AAM.Common.Items;

import java.util.List;

import AAM.Common.Blocks.ModBlocks;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.Circle;
import AAM.Common.Transmutations.ModCircles;
import AAM.Utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ChalkPattern extends Item
{

	public ChalkPattern()
	{
		super();
		this.setHasSubtypes(true);
	}

	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	public static IIcon[] icon = new IIcon[ModCircles.parts.size() * 2 + 1];

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icon[0] = ir.registerIcon("aam:tools/chalk/paper_blank");
		for (int j = 0; j < ModCircles.parts.size(); j++)
		{
			icon[2 * j + 1] = ir.registerIcon("aam:" + ModCircles.parts.get(j).item.getResourcePath());
			icon[2 * j + 2] = ir.registerIcon("aam:" + ModCircles.parts.get(j).itemRev.getResourcePath());
		}
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		if (pass == 0)
			return icon[0];
		return icon[meta * 2 + 1];
	}

	@Override
	public IIcon getIcon(ItemStack is, int pass, EntityPlayer p, ItemStack use, int time)
	{
		if (pass == 0)
			return icon[0];
		return icon[2 * is.getItemDamage() + 1 + MiscUtils.boolToNum(p.isSneaking())];
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	public String getUnlocalizedName(ItemStack i)
	{
		return "aam.chalk." + ModCircles.parts.get(i.getItemDamage()).name;

	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item is, CreativeTabs tab, List l)
	{
		for (int i = 0; i < ModCircles.parts.size(); i++)
		{
			l.add(new ItemStack(is, 1, i));
		}
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int px, int py, int pz, int side, float fx, float fy, float fz)
	{
		int x = px + ForgeDirection.getOrientation(side).offsetX;
		int y = py + ForgeDirection.getOrientation(side).offsetY;
		int z = pz + ForgeDirection.getOrientation(side).offsetZ;
		if (y < 256)
		{

			if (MiscUtils.contains(p.inventory, ModItems.ItemChalk))
			{
				MiscUtils.getStack(p.inventory, ModItems.ItemChalk).damageItem(1, p);
				;
				if (w.getBlock(px, py, pz) != ModBlocks.TransCircle && w.getBlock(px, py, pz) != ModBlocks.MechanicalBase)
				{
					if (w.getBlock(x, y, z) == Blocks.air && w.getBlock(x, y, z) != ModBlocks.TransCircle && w.getBlock(x, y, z) != ModBlocks.MechanicalBase)
					{
						w.setBlock(x, y, z, ModBlocks.TransCircle, side, 2);

						TETransCircle te = (TETransCircle) w.getTileEntity(x, y, z);

						te.circle.add(new Circle(ModCircles.parts.get(is.getItemDamage()), 1.0, p.isSneaking()));
					}
				}
				else
				{
					w.getBlock(px, py, pz).onBlockActivated(w, px, py, pz, p, side, fx, fy, fz);
				}
			}
		}
		return true;
	}
}
