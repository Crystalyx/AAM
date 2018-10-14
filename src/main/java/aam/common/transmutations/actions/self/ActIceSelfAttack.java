package aam.common.transmutations.actions.self;

import aam.common.potions.ModPotions;
import aam.common.soul.IceDamageSource;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActIceSelfAttack extends TransAction
{

	public ActIceSelfAttack(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		ph.consumeSoul((int) (10 * potency / (size + 1)));
		p.addPotionEffect(new PotionEffect(ModPotions.cold.id, 40 + 40 * size, 1 + w.rand.nextInt(6)));
		p.attackEntityFrom(new IceDamageSource(), 4);
	}
}
