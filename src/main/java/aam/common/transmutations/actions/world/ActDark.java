package aam.common.transmutations.actions.world;

import aam.common.blocks.building.ModBlocks;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActDark extends TransAction
{
	public ActDark(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		int n = (int) (5 + Math.log(potency + 5 - 2 * size) / Math.log(1.4)) * 2;

		for (int i = -n; i <= n; i++)
		{
			for (int j = -n; j <= n; j++)
			{
				for (int k = -n; k <= n; k++)
				{
					if (w.getBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k) == ModBlocks.LightBlock)
					{
						w.setBlockToAir((int) tile.x + i, (int) tile.y + j, (int) tile.z + k);
					}
				}
			}
		}
	}
}
