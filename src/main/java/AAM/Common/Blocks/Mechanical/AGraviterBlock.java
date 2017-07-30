package AAM.Common.Blocks.Mechanical;

import AAM.Common.Tiles.AGraviterTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class AGraviterBlock extends BlockContainer
{

	public AGraviterBlock()
	{
		super(Material.rock);
		this.setBlockTextureName("aam:null");
		this.setBlockName("aam.agrav");
		this.setResistance(10.0F);
		this.setHardness(10.0F);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
	{
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new AGraviterTileEntity();
	}

}
