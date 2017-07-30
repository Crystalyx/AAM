package PathMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PathSpade extends ItemSpade
{
	public PathSpade()
	{
		super(ToolMaterial.IRON);
		this.setTextureName("pathmod:iron_shovel");
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		if (w.getBlock(x, y, z) == PathMod.path && w.getBlockMetadata(x, y, z) == 0)
		{
			w.setBlock(x, y, z, Blocks.dirt);
		}
		else
			if (w.getBlock(x, y, z) == Blocks.dirt || w.getBlock(x, y, z) == Blocks.grass)
			{
				w.setBlock(x, y, z, PathMod.path,0,2);
			}

		if (w.getBlock(x, y, z) == PathMod.path && w.getBlockMetadata(x, y, z) == 1)
		{
			w.setBlock(x, y, z, Blocks.gravel);
		}
		else
			if (w.getBlock(x, y, z) == Blocks.gravel)
			{
				w.setBlock(x, y, z, PathMod.path,1,2);
			}

		if (w.getBlock(x, y, z) == PathMod.path && w.getBlockMetadata(x, y, z) == 2)
		{
			w.setBlock(x, y, z, Blocks.sand);
		}
		else
			if (w.getBlock(x, y, z) == Blocks.sand)
			{
				w.setBlock(x, y, z, PathMod.path,2,2);
			}
		return super.onItemUse(i, p, w, x, y, z, side, fx, fy, fz);
	}

}
