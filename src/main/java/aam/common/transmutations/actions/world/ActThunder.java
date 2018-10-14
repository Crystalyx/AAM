package aam.common.transmutations.actions.world;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActThunder extends TransAction
{

	public ActThunder(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		int i = (300 - 100 * size + w.rand.nextInt(600)) * 20;
		w.getWorldInfo().setRainTime(i);
		w.getWorldInfo().setThunderTime(i);
		w.getWorldInfo().setRaining(true);
		w.getWorldInfo().setThundering(true);

	}
}
