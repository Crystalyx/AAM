package aam.common.transmutations.actions.world;

import aam.common.blocks.building.ModBlocks;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActLight extends TransAction
{
	public ActLight(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		int n = (int) (20 + 2 * potency);

		for (int i = -n; i <= n; i++)
		{
			for (int j = -n; j <= n; j++)
			{
				for (int k = -n; k <= n; k++)
				{
					if (w.getBlockLightValue((int) tile.x + i, (int) tile.y + j, (int) tile.z + k) < 9 && w.isAirBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k))
					{
						w.setBlock((int) tile.x + i, (int) tile.y + j, (int) tile.z + k, ModBlocks.LightBlock);
					}
				}
			}
		}
	}
}
