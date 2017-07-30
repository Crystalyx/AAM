package AAM.Common.Blocks.Building;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author Lord_Crystalyx
 */
public class Marker extends Block
{
	public Marker()
	{
		super(Material.cloth);
		this.setBlockTextureName("rogueWorld:marker");
		this.setBlockBounds(0.314F, 0.314F, 0.314F, 0.686F, 0.686F, 0.686F);
		this.setBlockName("rw.marker");
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

}
