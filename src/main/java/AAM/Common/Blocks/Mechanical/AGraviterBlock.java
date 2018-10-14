package aam.common.blocks.mechanical;

import aam.common.tiles.TEAGraviter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class AGraviterBlock extends BlockContainer
{

	public AGraviterBlock()
	{
		super(Material.iron);
		this.setBlockTextureName("aam:null");
		this.setBlockName("aam.agrav");
		this.setResistance(10.0F);
		this.setHardness(10.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TEAGraviter();
	}

}
