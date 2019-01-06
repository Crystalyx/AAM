package aam.common.items.alchemy;

import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.Circle;
import aam.common.transmutations.CirclePart;
import aam.common.transmutations.ModCircles;
import aam.utils.InventoryUtils;
import aam.utils.MathUtils;
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

import java.util.List;

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

	public static IIcon[] icon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icon = new IIcon[ModCircles.count * 2 + 1];
		icon[0] = ir.registerIcon("aam:tools/chalk/paper_blank");
		int j = 0;
		for (CirclePart pt : ModCircles.parts)
		{
			icon[2 * j + 1] = ir.registerIcon("aam:" + pt.item.getResourcePath());
			icon[2 * j + 2] = ir.registerIcon("aam:" + pt.itemRev.getResourcePath());
			j += 1;
		}
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		if (pass == 0)
		{
			return icon[0];
		}
		return icon[meta * 2 + 1];
	}

	@Override
	public IIcon getIcon(ItemStack is, int pass, EntityPlayer p, ItemStack use, int time)
	{
		if (pass == 0)
		{
			return icon[0];
		}
		return icon[2 * is.getItemDamage() + 1 + MathUtils.boolToNum(p.isSneaking())];
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		return "aam.chalk." + ModCircles.parts.get(i.getItemDamage()).name;

	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item is, CreativeTabs tab, List l)
	{
		for (int i = 0; i < ModCircles.count; i++)
		{
			l.add(new ItemStack(is, 1, i));
		}
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int px, int py, int pz, int side, float fx, float fy, float fz)
	{
		int x = px + ForgeDirection.getOrientation(side).offsetX;
		int y = py + ForgeDirection.getOrientation(side).offsetY;
		int z = pz + ForgeDirection.getOrientation(side).offsetZ;
		if (y < 256)
		{
			if (InventoryUtils.contains(p.inventory, ModItems.ItemChalk))
			{
				InventoryUtils.getStack(p.inventory, ModItems.ItemChalk).damageItem(1, p);
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
