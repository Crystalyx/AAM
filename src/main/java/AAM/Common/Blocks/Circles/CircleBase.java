package AAM.Common.Blocks.Circles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CircleBase extends Block
{
	public CircleBase()
	{
		super(Material.rock);
		this.setBlockTextureName("aam:circles/base");
		this.setBlockName("aamcircleBase");
	}

}
