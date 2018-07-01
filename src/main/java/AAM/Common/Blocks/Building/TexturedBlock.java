package AAM.Common.Blocks.Building;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TexturedBlock extends Block
{
	public TexturedBlock(Material mat, String name, String texturename, float hardness, float resist)
	{
		super(mat);
		this.setBlockName(name);
		this.setBlockTextureName(texturename);
		this.setHardness(hardness);
		this.setResistance(resist);
	}
}
