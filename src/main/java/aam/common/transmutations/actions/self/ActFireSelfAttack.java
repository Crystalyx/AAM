package aam.common.transmutations.actions.self;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActFireSelfAttack extends TransAction
{

	public ActFireSelfAttack(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		ph.consumeSoul((int) (10 * potency / (size + 1)));
		p.setFire(w.rand.nextInt((int) (10 * potency / (size + 1))));
		p.attackEntityFrom(DamageSource.inFire, 1);
	}
}
