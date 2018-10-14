package aam.common.transmutations.actions.item;

import aam.common.items.ModItems;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.InventoryUtils;
import aam.utils.MiscUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActAirGem extends TransAction
{

	public ActAirGem(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		if (te.completeTimer % 15 == 1)
		{
			if (w.rand.nextBoolean() && w.rand.nextBoolean())
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);

				ph.consumeSoul((int) (10 * potency / (size + 1)));
				MiscUtils.retractEntitiesFrom(w, tile, (float) (4 + 4 * potency), 0.25f);
			}
			else
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				ph.consumeSoul((int) (100 * potency / (size + 1)));
				ItemStack gem = new ItemStack(ModItems.AirGem, (int) potency, 3 - (size + 1));
				InventoryUtils.dropStackToPlayer(w, tile.x, tile.y, tile.z, gem, p);
				return false;
			}
		}
		return te.completeTimer < 100;
	}
}
