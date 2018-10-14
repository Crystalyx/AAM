package aam.common.transmutations.actions.entity;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActShield extends TransAction
{

	public ActShield(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		p.setAbsorptionAmount((float) (10f + (5 - size) * potency));
	}

}
