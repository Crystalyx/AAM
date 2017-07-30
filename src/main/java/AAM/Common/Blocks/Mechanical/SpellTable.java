package AAM.Common.Blocks.Mechanical;

import AAM.Common.Tiles.TileSpellTable;
import AAM.Core.AAMCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SpellTable extends BlockContainer
{
	public SpellTable(Material mat)
	{
		super(mat);
		this.setHardness(1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileSpellTable();
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack p_149689_6_)
	{
		int l = MathHelper.floor_double((double) ((-p.rotationYaw - 45) * 4.0F / 360.0F)) & 3;

		if (l == 0)
		{
			w.setBlockMetadataWithNotify(x, y, z, 1, 2);
		}

		if (l == 1)
		{
			w.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}

		if (l == 2)
		{
			w.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3)
		{
			w.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean isNormalCube()
	{
		return false;
	}

	public int getRenderType()
	{
		return 128;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		p.openGui(AAMCore.instance, 0, w, x, y, z);
		return true;
	}

}
