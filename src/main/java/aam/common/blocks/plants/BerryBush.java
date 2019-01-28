package aam.common.blocks.plants;

import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import aam.utils.InventoryUtils;
import aam.utils.MathUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BerryBush extends Block implements IGrowable
{

	public static String[] names = new String[]
	{ "blackberry", "blueberry", "mortis", "raspberry", "wormwoodberry" };

	public BerryBush(Material mat)
	{
		super(mat);
		this.setBlockTextureName("aam:bush");
		this.setHardness(0.5F);
		this.setTickRandomly(true);
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	@Override
	public int damageDropped(int meta)
	{
		return Math.floorDiv(meta, 2) * 2;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public void breakBlock(World w, int x, int y, int z, Block b, int hz)
	{
		super.breakBlock(w, x, y, z, b, hz);
		int meta = Math.floorDiv(w.getBlockMetadata(x, y, z), 2);
		int beta = Math.floorMod(w.getBlockMetadata(x, y, z), 2);

		if (beta == 1)
		{
			ItemStack berry = new ItemStack(ModItems.Berry, w.rand.nextInt(4) + 1, meta);
			InventoryUtils.dropStack(w, x, y, z, berry);
		}
	}

	/**
	 * returns a list of blocks with the same ID, but different repairItemMeta (eg: wood
	 * returns 4 blocks)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
		l.add(new ItemStack(i, 1, 1));
		l.add(new ItemStack(i, 1, 2));
		l.add(new ItemStack(i, 1, 3));
		l.add(new ItemStack(i, 1, 4));
		l.add(new ItemStack(i, 1, 5));
		l.add(new ItemStack(i, 1, 6));
		l.add(new ItemStack(i, 1, 7));
	}

	public static IIcon[] berries = new IIcon[5];

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		berries[4] = ir.registerIcon("aam:bush_block");
		for (int i = 0; i < 4; i++)
		{
			berries[i] = ir.registerIcon("aam:" + BerryBush.names[i]);
		}
	}

	@Override
	public int getRenderType()
	{
		return 125;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
	 * not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		int meta = Math.floorDiv(w.getBlockMetadata(x, y, z), 2);
		int beta = Math.floorMod(w.getBlockMetadata(x, y, z), 2);

		if (beta == 1)
		{
			if (p.getCurrentEquippedItem() == null)
			{
				w.setBlockMetadataWithNotify(x, y, z, meta * 2, 2);
				ItemStack berry = new ItemStack(ModItems.Berry, (int) (Math.floorMod(w.getWorldTime(), 4) + 1), meta);
				InventoryUtils.dropStackToPlayer(w, x, y, z, berry, p);
				return true;
			} else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World w, int x, int y, int z, Random r)
	{
		func_149853_b(w, r, x, y, z);
	}

	@Override
	public boolean canBlockStay(World w, int x, int y, int z)
	{
		return w.getBlock(x, y - 1, z) == Blocks.grass;
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
		if (Math.floorMod(w.getBlockMetadata(x, y, z), 2) == 0)
		{
			if (!w.isRemote)
			{
				grow(w, r, x, y, z);
			}
		}
		if (MathUtils.randWPercent(r, 20))// TODO
		{
			byte b0 = 4;
			int l = 5;
			int i1;
			int j1;
			int k1;

			for (i1 = x - b0; i1 <= x + b0; ++i1)
			{
				for (j1 = z - b0; j1 <= z + b0; ++j1)
				{
					for (k1 = y - 1; k1 <= y + 1; ++k1)
					{
						if (w.getBlock(i1, k1, j1) == this)
						{
							--l;

							if (l <= 0)
							{
								return;
							}
						}
					}
				}
			}

			int a = x, b = y, c = z;

			i1 = a + r.nextInt(3) - 1;
			j1 = b + r.nextInt(2) - r.nextInt(2);
			k1 = c + r.nextInt(3) - 1;

			for (int l1 = 0; l1 < 4; ++l1)
			{
				if (w.isAirBlock(i1, j1, k1) && this.canBlockStay(w, i1, j1, k1))
				{
					a = i1;
					b = j1;
					c = k1;
				}

				i1 = a + r.nextInt(3) - 1;
				j1 = b + r.nextInt(2) - r.nextInt(2);
				k1 = c + r.nextInt(3) - 1;
			}

			if (w.isAirBlock(i1, j1, k1) && this.canBlockStay(w, i1, j1, k1))
			{
				w.setBlock(i1, j1, k1, ModBlocks.BushSprout, Math.floorDiv(w.getBlockMetadata(x, y, z), 2) * 4, 2);
			}
		}
	}

	public void grow(World w, Random r, int x, int y, int z)
	{
		w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z) + 1, 2);
	}

	/**
	 * Gets the block's textureName. Args: side, repairItemMeta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return blockIcon;
	}

}
