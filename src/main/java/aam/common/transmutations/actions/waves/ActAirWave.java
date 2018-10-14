package aam.common.transmutations.actions.waves;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.MiscUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActAirWave extends TransAction
{

	public ActAirWave(int size)
	{
		super(size);
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		Wec3 pw = new Wec3(te);
		pw.centralize();
		MiscUtils.retractEntitiesFrom(w, tile, (float) (4 + 4 * potency), 0.25f, p);
		return te.completeTimer < 400;
	}
}
