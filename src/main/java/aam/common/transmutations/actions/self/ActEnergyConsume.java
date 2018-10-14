package aam.common.transmutations.actions.self;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActEnergyConsume extends TransAction
{

	public ActEnergyConsume(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);

		ph.consumeSoul((int) (3 * potency / (size + 1)));
	}
}
