package AAM.Common.Blocks.Mechanical;

import AAM.Common.Tiles.TESpellTable;
import AAM.Core.AAMCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class SpellTable extends BlockContainer
{
	public SpellTable(Material mat)
	{
		super(mat);
		this.setHardness(1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TESpellTable();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack p_149689_6_)
	{
		int l = MathHelper.floor_double((double) ((-p.rotationYaw - 45) * 4.0F / 360.0F)) & 3;

		w.setBlockMetadataWithNotify(x, y, z, ForgeDirection.getOrientation(l).getOpposite().flag, 2);
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
