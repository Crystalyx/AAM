package aam.common.blocks.circles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CircleBase extends Block
{
	public CircleBase()
	{
		super(Material.rock);
		this.setBlockTextureName("aam:circles/base");
		this.setHardness(2.0f);
		this.setResistance(5.0f);
		this.setStepSound(soundTypeWood);
	}

}
