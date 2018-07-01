package AAM.Common.Transmutations.Actions;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Items.ModItems;
import AAM.Common.Items.Alchemy.PhilosophersStone;
import AAM.Common.Tiles.TEBloodAltar;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.EnergyType;
import AAM.Common.Transmutations.ModCircles;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActPhilo extends TransAction
{
	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		if ((te.energyType.equals(EnergyType.Unknown) || te.energyType.equals(EnergyType.Philo)) && te.is == null)
		{
			List<EntityLivingBase> lst = w.getEntitiesWithinAABB(EntityLivingBase.class, tile.centralize().extendBoth(25f, 30f, 25f));
			List<EntityLivingBase> l = new ArrayList<EntityLivingBase>();
			for (int i = 0; i < lst.size(); i++)
			{
				EntityLivingBase ei = lst.get(i);
				if (ei instanceof EntityVillager || (ei instanceof EntityPlayer && ei != te.alchemist))
				{
					l.add(ei);
				}
			}

			for (int i = 0; i < l.size(); i++)
			{
				EntityLivingBase ei = l.get(i);
				if (te instanceof TEBloodAltar)
				{
					((TEBloodAltar) te).blood.amount += ModCircles.K * 10;
					ei.attackEntityFrom(DamageSource.outOfWorld.setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);

					if (ei.getHealth() <= 1.1)
					{
						((TEBloodAltar) te).blood.amount += ModCircles.K * 10;
						ei.attackEntityFrom(DamageSource.outOfWorld.setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);

					}
				}
				else
				{
					te.energyType = EnergyType.Philo;
					int k = 2;
					if (ei instanceof EntityPlayer)
					{
						k = 5;
					}
					if (ei.getHealth() <= 1.1)
					{
						te.energy += ModCircles.K * k * 2;
						l.remove(ei);
						ei.attackEntityFrom(DamageSource.outOfWorld.setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);
					}
					else
					{
						te.energy += k * k;
						ei.attackEntityFrom(DamageSource.outOfWorld.setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 1);
					}
				}
			}

			if (l.isEmpty() && te.energy > 0)
			{
				ItemStack isk = new ItemStack(ModItems.PhilosophersStone);
				((PhilosophersStone) isk.getItem()).chargeBypass(isk, te.energy);
				te.clearEnergy();
				te.is = isk;
			}
			if (l.isEmpty())
				return false;

			return true;
		}
		return false;
	}
}
