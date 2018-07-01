/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Blocks.Mechanical;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class GrassClearer extends Block
{

	public GrassClearer()
	{
		super(Material.wood);
		this.setHardness(0.5F);
		this.setBlockName("aam.grassclearer");
		this.setBlockTextureName("aam:grass_hater");
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float px, float py, float pz)
	{
		boolean flag = false;
		int rad = 10;
		int hei = 4;
		for (int i = -rad; i < rad + 1; i++)
		{
			for (int l = -hei; l < hei + 1; l++)
			{
				for (int j = -rad; j < rad + 1; j++)
				{
					if (w.getBlock(x + i, y + l, z + j) instanceof BlockBush || w.getBlock(x + i, y + l, z + j) == Blocks.tallgrass || w.getBlock(x + i, y + l, z + j) == Blocks.double_plant
							|| w.getBlock(x + i, y + l, z + j) == Blocks.yellow_flower || w.getBlock(x + i, y + l, z + j) == Blocks.red_flower)
					{
						flag = true;
						w.setBlockToAir(x + i, y + l, z + j);
					}
				}
			}
		}
		if (flag)
			w.setBlockToAir(x, y, z);
		return flag;
	}

}
