package aam.common.transmutations;

import aam.common.tiles.TETransCircle;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TransAction
{
	public int size = 0;

	public TransAction(int size)
	{
		this.size = size;
	}

	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{

	}

	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		return false;
	}

	public boolean renderTick(World w, Wec3 tile, TETransCircle te, int time, double potency, ForgeDirection dir)
	{
		return false;
	}
}
