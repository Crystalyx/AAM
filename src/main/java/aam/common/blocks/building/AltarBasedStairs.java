/**
 * This Class Created By Lord_Crystalyx.
 */
package aam.common.blocks.building;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * @author Lord_Crystalyx
 */
public class AltarBasedStairs extends BlockStairs
{

	public AltarBasedStairs()
	{
		super(ModBlocks.altar_base, 0);
		this.setBlockTextureName("aam:altar_base");
		this.setHardness(2.0F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setLightOpacity(0);
	}
}
