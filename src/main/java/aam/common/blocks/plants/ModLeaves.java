package aam.common.blocks.plants;

import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModLeaves extends BlockLeaves
{
	public IIcon icons;
	public IIcon icons_opaque;
	public String texture;

	public ModLeaves(String texture)
	{
		super();
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);
		this.texture = texture;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		icons = ir.registerIcon("aam:" + texture + "_leaves");
		icons_opaque = ir.registerIcon("aam:" + texture + "_leaves_opaque");
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		if (Minecraft.isFancyGraphicsEnabled())
		{
			return icons;
		}
		else
		{
			return icons_opaque;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess w, int x, int y, int z, int s)
	{
		return this.getIcon(s, w.getBlockMetadata(x, y, z));
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (Minecraft.isFancyGraphicsEnabled())
		{
			return icons;
		}
		else
		{
			return icons_opaque;
		}
	}

	@Override
	public void getSubBlocks(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
	}

	@Override
	public String[] func_150125_e()
	{
		return new String[] { "aam.leaves" };
	}

	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_)
	{
		int id = -1;
		for (int i = 0; i < ModBlocks.ModLeaves.length; i++)
		{
			if (ModBlocks.ModLeaves[i] == this)
			{
				id = i;
				break;
			}
		}
		if (id >= 0)
		{
			return Item.getItemFromBlock(ModBlocks.ModSaplings[id]);
		}
		else
		{
			return Item.getItemFromBlock(this);
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> al = super.getDrops(world, x, y, z, metadata, fortune);
		int id = -1;
		for (int i = 0; i < ModBlocks.ModLeaves.length; i++)
		{
			if (ModBlocks.ModLeaves[i] == this)
			{
				id = i;
				break;
			}
		}
		float rnd = world.rand.nextFloat();
		if (id == 0 && rnd >= 0.85)
		{
			al.add(new ItemStack(ModItems.Berry, (int) (1 + fortune * rnd), 4));
		}
		return al;
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and
	 * wood.
	 */
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Returns the color this block should be rendered. Used by leaves.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int col)
	{
		return 0xffffff;
	}

	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied
	 * against the blocks color. Note only called when first determining what to
	 * render.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		return 0xffffff;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if
	 * the adjacent block is at the given coordinates. Args: blockAccess, x, y,
	 * z, side
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return true;
	}

}
