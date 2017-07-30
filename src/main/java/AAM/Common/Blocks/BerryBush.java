package AAM.Common.Blocks;

import java.util.List;
import java.util.Random;

import AAM.Common.Items.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BerryBush extends Block implements IGrowable
{

	public static String[] names = new String[] { "blackberry", "blueberry", "mortis", "raspberry" };

	public BerryBush(Material mat)
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
	public int damageDropped(int meta)
	{
		return Math.floorDiv(meta, 2) * 2;
	}

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
			ItemStack berry = new ItemStack(ModItems.Berry, w.rand.nextInt(3) + 1, meta);

			EntityItem e = new EntityItem(w, x, y, z, berry);
			e.setVelocity(w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8);
			w.spawnEntityInWorld(e);
		}
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
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
		berries[4] = ir.registerIcon("aam:bushblock");
		for (int i = 0; i < 4; i++)
			berries[i] = ir.registerIcon("aam:" + BerryBush.names[i]);
	}

	public int getRenderType()
	{
		return 125;
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
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		int meta = Math.floorDiv(w.getBlockMetadata(x, y, z), 2);
		int beta = Math.floorMod(w.getBlockMetadata(x, y, z), 2);

		if (beta == 1)
		{
			if (p.getCurrentEquippedItem() == null)
			{
				w.setBlockMetadataWithNotify(x, y, z, meta * 2, 2);
				ItemStack berry = new ItemStack(ModItems.Berry, w.rand.nextInt(4), meta);
				if (!p.inventory.addItemStackToInventory(berry))
				{
					EntityItem e = new EntityItem(w, x, y, z, berry);

					e.setVelocity(w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8);
					w.spawnEntityInWorld(e);
				}
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World w, int x, int y, int z, Random r)
	{
		if (Math.floorMod(w.getBlockMetadata(x, y, z), 2) == 0)
		{
			if (!w.isRemote)
			{
				if (w.getBlockLightValue(x, y + 1, z) >= 9 && r.nextInt(3) == 0)
				{
					grow(w, r, x, y, z);
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
		if (Math.floorMod(w.getBlockMetadata(x, y, z), 2) == 0)
		{
			if (!w.isRemote)
			{
				grow(w, r, x, y, z);
			}
		}
	}

	public void grow(World w, Random r, int x, int y, int z)
	{
		w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z) + 1, 2);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return this.blockIcon;
	}

}
