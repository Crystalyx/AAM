package AAM.Common.Transmutations.Actions;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActRainy extends TransAction
{

	public ActRainy()
	{
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		int i = (300 + w.rand.nextInt(600)) * 20;
		w.getWorldInfo().setRainTime(i);
		w.getWorldInfo().setThunderTime(i);
		w.getWorldInfo().setRaining(true);
	}
}
