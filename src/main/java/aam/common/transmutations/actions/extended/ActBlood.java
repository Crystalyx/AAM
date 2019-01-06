package aam.common.transmutations.actions.extended;

import aam.common.tiles.TEBloodAltar;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.EnergyType;
import aam.common.transmutations.ModCircles;
import aam.common.transmutations.TransAction;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class ActBlood extends TransAction
{
	public ActBlood(int size)
	{
		super(size);
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		if (w.getWorldTime() > 100)
		{
			if (w.getWorldTime() % 2 == 1)
			{
				if (!PlayerDataHandler.get(p).consumeSoul(1))
				{
					return false;
				}
			}
			if (te.energyType.equals(EnergyType.Unknown) || te.energyType.equals(EnergyType.Blood))
			{
				List<EntityLivingBase> lst = w.getEntitiesWithinAABB(EntityLivingBase.class, tile.centralize().extendBoth(15f, 20f, 15f));
				List<EntityLivingBase> l = new ArrayList<>();
				for (int i = 0; i < lst.size(); i++)
				{
					EntityLivingBase ei = lst.get(i);
					if (ei != te.alchemist)
					{
						l.add(ei);
					}
				}
				l.remove(te.alchemist);
				for (int i = 0; i < l.size(); i++)
				{
					EntityLivingBase ei = l.get(i);
					if (te instanceof TEBloodAltar)
					{
						((TEBloodAltar) te).blood.amount += ModCircles.K * 2;
						ei.attackEntityFrom(DamageSource.outOfWorld.setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);

						if (ei.getHealth() <= 1.1)
						{
							((TEBloodAltar) te).blood.amount += ModCircles.K * 2;
							ei.attackEntityFrom(DamageSource.outOfWorld.setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);

						}
					}
					else
					{
						return false;
					}
				}

				if (l.isEmpty())
				{
					return false;
				}

				return true;
			}
			return false;
		}
		return true;
	}
}
