package AAM.Common.Transmutations.Actions;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Tiles.TEBloodAltar;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.EnergyType;
import AAM.Common.Transmutations.ModCircles;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActBlood extends TransAction
{
	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		if (w.getWorldTime() % 2 == 1)
			if (!PlayerDataHandler.get(p).consumeSoul(1))
				return false;
		if ((te.energyType.equals(EnergyType.Unknown) || te.energyType.equals(EnergyType.Blood)))
		{
			List<EntityLivingBase> lst = w.getEntitiesWithinAABB(EntityLivingBase.class, tile.centralize().extendBoth(15f, 20f, 15f));
			List<EntityLivingBase> l = new ArrayList<EntityLivingBase>();
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
				return false;

			return true;
		}
		return false;
	}
}
