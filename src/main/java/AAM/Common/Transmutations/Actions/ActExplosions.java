package AAM.Common.Transmutations.Actions;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActExplosions extends TransAction
{

	public ActExplosions()
	{
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		int n = (int) (12 + (potency + 1) * (Math.log(potency + 1) / Math.log(1.4)));
		int t = Math.floorDiv(time, n);
		if (t >= n * 2 || time > 400)
		{
			return false;
		}
		else
		{
			Wec3 exp = tile.add(Wec3.random(n, n, n));
			if (!w.isRemote)
				w.createExplosion(null, exp.x, exp.y, exp.z, Math.max(t, 1), true);
		}
		return true;
	}

}
