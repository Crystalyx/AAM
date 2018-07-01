package AAM.Common.Blocks.Plants;

import java.util.List;
import java.util.Random;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Items.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BushSprout extends Block implements IGrowable
{
	public BushSprout(Material mat)
	{
		super(mat);
		this.setBlockTextureName("aam:bush");
		this.setHardness(0.5F);
		this.setTickRandomly(true);
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and
	 * wood.
	 */
	@Override
	public int damageDropped(int meta)
	{
		return Math.floorDiv(meta, 4);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return ModItems.Berry;
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
		l.add(new ItemStack(i, 1, 4));
		l.add(new ItemStack(i, 1, 8));
		l.add(new ItemStack(i, 1, 12));
	}

	public static IIcon[] berries = new IIcon[5];

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		berries[4] = ir.registerIcon("aam:bushblock");
		for (int i = 0; i < 4; i++)
			berries[i] = ir.registerIcon("aam:" + BerryBush.names[i]);
	}

	@Override
	public int getRenderType()
	{
		return 138;
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

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	{
		int i = Math.floorMod(w.getBlockMetadata(x, y, z), 4) * 2;
		int m = i == 0 ? 5 : 0;
		this.setBlockBounds(0.1F + 0.05F * (5 - i), 0.F, 0.1F + 0.05F * (5 - i), 0.9F - 0.05F * (5 - i), 0.9F - 0.05F * (5 + m - i), 0.9F - 0.05F * (5 - i));
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World w, int x, int y, int z, Random r)
	{
		if (Math.floorMod(w.getBlockMetadata(x, y, z), 4) <= 3)
		{
			if (!w.isRemote)
			{
				if (w.getBlockLightValue(x, y + 1, z) >= 9 && r.nextInt(3) == 0)
				{
					if (Math.floorMod(w.getBlockMetadata(x, y, z), 4) < 3)
					{
						grow(w, r, x, y, z);
					}
					if (Math.floorMod(w.getBlockMetadata(x, y, z), 4) == 3)
					{
						w.setBlock(x, y, z, ModBlocks.BerryBush, Math.floorDiv(w.getBlockMetadata(x, y, z), 4) * 2, 2);
					}
				}
			}
		}

	}

	@Override
	public boolean func_149851_a(World w, int x, int y, int z, boolean bool)
	{
		return true;
	}

	@Override
	public boolean func_149852_a(World w, Random r, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void func_149853_b(World w, Random r, int x, int y, int z)
	{
		if (Math.floorMod(w.getBlockMetadata(x, y, z), 4) < 3)
		{
			if (!w.isRemote)
			{
				grow(w, r, x, y, z);
			}
		}
		if (Math.floorMod(w.getBlockMetadata(x, y, z), 4) == 3)
		{
			w.setBlock(x, y, z, ModBlocks.BerryBush, Math.floorDiv(w.getBlockMetadata(x, y, z), 4) * 2, 2);
		}
	}

	public void grow(World w, Random r, int x, int y, int z)
	{
		w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z) + 1, 2);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}

}
