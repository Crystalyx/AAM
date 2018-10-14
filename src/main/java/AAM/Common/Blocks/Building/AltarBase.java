/**
 * This Class Created By Lord_Crystalyx.
 */
package aam.common.blocks.building;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class AltarBase extends Block
{

	public AltarBase(Material mat)
	{
		super(mat);
		this.setBlockTextureName("aam:altar_base");
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		return false;
	}
}
