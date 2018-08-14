package AAM.Common.Transmutations.Actions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import AAM.Common.Items.ModItems;
import AAM.Common.Items.Alchemy.PhilosophersStone;
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
		// Stream of the items that have Energy Value
		Stream<EntityItem> l = lst.stream().filter(e -> EnergyProvider.hasEnergy(e.getEntityItem())).filter(e -> te.energyType.equals(EnergyType.Unknown) || EnergyProvider.getType(e.getEntityItem()).isFriendly(te.energyType))
				.filter(e -> te.is == null || (e.getEntityItem().getItem() != te.is.getItem() || e.getEntityItem().getItemDamage() != te.is.getItemDamage()));
		Optional<EntityItem> opei = l.findFirst();
		if (opei.isPresent())
		{
			EntityItem ei = opei.get();
			ItemStack is = ei.getEntityItem();
			if (!is.hasTagCompound())
			{
				NBTTagCompound tg = new NBTTagCompound();
				is.setTagCompound(tg);
			}
			NBTTagCompound tg = is.getTagCompound();
			int trTime = tg.getInteger("TrTime");
			if (!w.isRemote)
			{
				if (trTime >= 40 / potency)
				{
					te.energy += EnergyProvider.getFullEnergy(is);
					if (te.energyType.equals(EnergyType.Unknown))
					{
						te.energyType = EnergyProvider.getType(is);
					}
					is.stackSize -= 1;
					tg.setInteger("TrTime", 0);
				}
				else
				{
					trTime += 1;
					tg.setInteger("TrTime", trTime);
				}
			}

			if (w.isRemote && w.getWorldTime() % 8 == 1)
			{
				for (int i = 0; i < 12; i++)
				{
					Wec3 vec1 = MiscUtils.getPosBy3DAngle(i * Math.PI / 6d, 0, 0.4);
					Wec3 vec2 = MiscUtils.getPosBy3DAngle((i + 1) * Math.PI / 6d, 0, 0.3);
					Wec3 vec = vec2.sub(vec1);
					vec.normalize();
					vec = vec.mult(0.0055);
					w.spawnParticle("smoke", ei.posX + vec1.x, ei.posY + vec1.y, ei.posZ + vec1.z, vec.x, vec.y, vec.z);
				}
			}
		}
		// if (!w.isRemote)
		// Logger.mchat(p, te.energy);
		if (te.is != null && te.energy >= EnergyProvider.getFullEnergy(te.is))
		{
			if (w.isRemote && w.getWorldTime() % 8 == 1)
			{
				for (int i = 0; i < 12; i++)
				{
					Wec3 vec1 = MiscUtils.getPosBy3DAngle(i * Math.PI / 6d, 0, 0.4);
					Wec3 vec2 = MiscUtils.getPosBy3DAngle((i + 1) * Math.PI / 6d, 0, 0.3);
					Wec3 vec = vec2.sub(vec1);
					vec.normalize();
					vec = vec.mult(0.0055);
					w.spawnParticle("flame", tile.x + vec1.x, tile.y + vec1.y, tile.z + vec1.z, vec.x, vec.y, vec.z);
				}
			}
			if (te.is.getItem() != ModItems.PhilosophersStone)
			{
				if (!w.isRemote)
				{
					MiscUtils.dropStack(w, new Wec3(te).centralize(), te.is.copy());
					te.energy -= EnergyProvider.getFullEnergy(te.is);
					te.energyType = EnergyProvider.getType(te.is);
				}
			}
			else
			{
				if (!w.isRemote)
				{
					PhilosophersStone phI = (PhilosophersStone) te.is.getItem();
					te.energy = phI.chargeBypass(te.is, te.energy);
				}
			}

		}

		return opei.isPresent() || (te.is != null && te.energy >= EnergyProvider.getFullEnergy(te.is) && te.energy > 0);
	}
}
