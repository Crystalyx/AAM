package aam.common.transmutations.actions.self;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.MiscUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActAirSelfAttack extends TransAction
{

	public ActAirSelfAttack(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		Wec3 pw = new Wec3(te);
		pw.centralize();
		ph.consumeSoul((int) (10 * potency / (size + 1)));
		MiscUtils.retractEntitiesFrom(w, tile, (float) (4 + 4 * potency), (float) potency);
	}
}
