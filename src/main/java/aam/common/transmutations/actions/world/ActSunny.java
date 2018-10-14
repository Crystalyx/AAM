package aam.common.transmutations.actions.world;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActSunny extends TransAction
{

	public ActSunny(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		w.getWorldInfo().setRainTime((int) (potency * 6000 - 2000 * size));
		w.getWorldInfo().setRaining(false);
		w.getWorldInfo().setThundering(false);
	}
}
