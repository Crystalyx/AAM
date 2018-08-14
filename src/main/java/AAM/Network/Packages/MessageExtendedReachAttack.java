package AAM.Network.Packages;

import java.io.IOException;

import AAM.API.Interface.IExtendedReach;
import AAM.Utils.Logger;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

public class MessageExtendedReachAttack extends AlchemicalPackage
{
	private int entityId;

	public MessageExtendedReachAttack()
	{
	}

	public MessageExtendedReachAttack(int entityId)
	{
		this.entityId = entityId;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException
	{
		this.entityId = buffer.readVarIntFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException
	{
		buffer.writeVarIntToBuffer(this.entityId);
	}

	@Override
	public void process(EntityPlayer player, Side side)
	{
		if (side.equals(Side.SERVER))
		{
			// Know it will be on the server so make it thread-safe
			final EntityPlayerMP p = (EntityPlayerMP) player;
			Entity target = p.worldObj.getEntityByID(this.entityId);

			if (target != null)
			{
				// Need to ensure that hackers can't cause trick kills,
				// so double check weapon type and reach
				if (p.getCurrentEquippedItem() == null)
				{
					return;
				}
				if (p.getCurrentEquippedItem().getItem() instanceof IExtendedReach)
				{
					IExtendedReach theExtendedReachWeapon = (IExtendedReach) p.getCurrentEquippedItem().getItem();
					double distanceSq = p.getDistanceSqToEntity(target);
					double reachSq = theExtendedReachWeapon.getReachValue(p, p.getCurrentEquippedItem()) * theExtendedReachWeapon.getReachValue(p, p.getCurrentEquippedItem());
					if (reachSq >= distanceSq)
					{
						p.attackTargetEntityWithCurrentItem(target);
					}
				}
			}
			else
				Logger.warn("Entity ID Broken");
		}
	}

}
