package aam.common.transmutations.actions.extended;

import java.util.ArrayList;
import java.util.List;

import aam.common.items.ModItems;
import aam.common.items.alchemy.PhilosophersStone;
import aam.common.tiles.TEBloodAltar;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.CircleUtils;
import aam.common.transmutations.EnergyType;
import aam.common.transmutations.ModCircles;
import aam.common.transmutations.TransAction;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActPhilo extends TransAction
{
	public ActPhilo(int size)
	{
		super(size);
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		if (te.energyType.equals(EnergyType.Unknown) || te.energyType.equals(EnergyType.Philo))
		{
			List<EntityLivingBase> lst = w.getEntitiesWithinAABB(EntityLivingBase.class, tile.centralize().extendBoth(40f, 30f, 40f));
			List<EntityLivingBase> l = new ArrayList<>();
			for (int i = 0; i < lst.size(); i++)
			{
				EntityLivingBase ei = lst.get(i);
				if (ei instanceof EntityVillager || ei instanceof EntityPlayer && ei != te.alchemist)
				{
					l.add(ei);
				}
			}

			for (int i = 0; i < l.size(); i++)
			{
				EntityLivingBase ei = l.get(i);
				if (te instanceof TEBloodAltar)
				{
					((TEBloodAltar) te).blood.amount += ModCircles.K;
					ei.attackEntityFrom(DamageSource.causePlayerDamage(te.alchemist).setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);

					if (ei.getHealth() <= 1.1)
					{
						((TEBloodAltar) te).blood.amount += ModCircles.K;
						ei.attackEntityFrom(DamageSource.causePlayerDamage(te.alchemist).setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 2);
					}
				}
				else
				{
					te.energyType = EnergyType.Philo;
					int k = 1000;
					if (ei instanceof EntityPlayer)
					{
						k = 2500;
					}
					if (ei.getHealth() <= 1.1)
					{
						te.energy += ModCircles.K * k * 2;
						l.remove(ei);
						ei.attackEntityFrom(DamageSource.causePlayerDamage(te.alchemist).setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 2);
					}
					else
					{
						ei.attackEntityFrom(DamageSource.causePlayerDamage(te.alchemist).setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);
					}
				}
			}

			if (l.isEmpty() && te.energy > 0)
			{
				if (te.is == null)
				{
					ItemStack isk = new ItemStack(ModItems.PhilosophersStone);
					te.is = isk;
				}
				((PhilosophersStone) te.is.getItem()).chargeBypass(te.is, te.energy);
				CircleUtils.clearEnergy(te);
				return false;
			}
			if (l.isEmpty())
			{
				return false;
			}

			return true;
		}
		return false;
	}
}
