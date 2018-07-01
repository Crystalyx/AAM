package AAM.Common.Transmutations.Actions;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.EnergyProvider;
import AAM.Common.Transmutations.EnergyType;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActTransm extends TransAction
{
	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		List<EntityItem> lst = w.getEntitiesWithinAABB(EntityItem.class, tile.centralize().extendBoth(2.5f, 1, 2.5f));
		List<EntityItem> l = new ArrayList<EntityItem>();
		for (int i = 0; i < lst.size(); i++)
		{
			EntityItem ei = lst.get(i);
			ItemStack is = ei.getEntityItem();
			if (is != null)
			{
				if (EnergyProvider.hasEnergy(is) && (te.is == null || is.getItem() != te.is.getItem()))
				{
					if (te.energyType.equals(EnergyType.Unknown))
					{
						l.add(ei);
						te.energyType = EnergyProvider.getType(is);
					}
					if (te.energyType.equals(EnergyType.Philo))
					{
						l.add(ei);
						te.energyType = EnergyProvider.getType(is);
					}
					if (EnergyProvider.getType(is).isFriendly(te.energyType))
					{
						l.add(ei);
					}
				}
			}
		}

		for (int i = 0; i < l.size(); i++)
		{
			EntityItem ei = l.get(i);
			ItemStack is = ei.getEntityItem();
			if (is != null)
			{
				if (w.isRemote)
				{
					for (int j = 0; j < 12; j++)
					{
						Wec3 ip = new Wec3(ei);
						Wec3 vec = MiscUtils.getPosBy3DAngle(j * Math.PI / 6d, 0, 0.2);
						vec.normalize();
						vec = vec.mult(0.06);
						w.spawnParticle("smoke", ip.x, ip.y, ip.z, vec.x, vec.y, vec.z);
					}
				}
				else
				{
					if (is.hasTagCompound())
					{
						NBTTagCompound tg = is.getTagCompound();
						int currTime = tg.getInteger("TrTime");
						if (currTime >= 2 * Math.log(10 + EnergyProvider.getFullEnergy(is)))
						{
							te.energy += EnergyProvider.getFullEnergy(is);
							is.stackSize -= 1;
							tg.setInteger("TrTime", 0);
						}
						else
						{
							tg.setInteger("TrTime", currTime + 1);
						}
					}
					else
					{
						NBTTagCompound tg = new NBTTagCompound();
						tg.setInteger("TrTime", 1);
						is.setTagCompound(tg);
					}
				}
				break;
			}
		}

		if (te.is != null)
		{
			if (te.energy >= EnergyProvider.getValue(te.is) && te.energyType.isFriendly(EnergyProvider.getType(te.is)))
			{
				if (w.isRemote)
				{
					for (int i = 0; i < 12; i++)
					{
						Wec3 vec1 = MiscUtils.getPosBy3DAngle(i * Math.PI / 6d, 0, 0.4);
						Wec3 vec2 = MiscUtils.getPosBy3DAngle((i + 1) * Math.PI / 6d, 0, 0.3);
						Wec3 vec = vec2.sub(vec1);
						vec.normalize();
						vec = vec.mult(0.055);
						w.spawnParticle("flame", tile.x + vec1.x, tile.y + vec1.y, tile.z + vec1.z, vec.x, vec.y, vec.z);
					}
				}
				if (!w.isRemote)
				{
					MiscUtils.dropStack(w, tile, te.is.copy());
					te.energy -= EnergyProvider.getFullEnergy(te.is);
					te.energyType = EnergyProvider.getType(te.is);
				}
			}
		}
		return !(l.isEmpty()) || ((te.is != null && te.energy >= EnergyProvider.getValue(te.is)) && (te.energyType.isFriendly(EnergyProvider.getType(te.is)) || te.energyType.equals(EnergyType.Unknown)));
	}
}
