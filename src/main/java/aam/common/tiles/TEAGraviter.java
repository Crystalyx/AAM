package aam.common.tiles;

import aam.utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * @author Lord_Crystalyx
 */
public class TEAGraviter extends TileEntity
{
	public TEAGraviter()
	{
		super();
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void updateEntity()
	{

		double rx = worldObj.rand.nextDouble() * 10 * MathUtils.boolToNum();
		double ry = worldObj.rand.nextDouble() * 10 * MathUtils.boolToNum();
		double rz = worldObj.rand.nextDouble() * 10 * MathUtils.boolToNum();

		double radius = 40;
		List<Entity> ents = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord - radius, yCoord - radius, zCoord - radius, xCoord + radius, yCoord + radius, zCoord + radius));
		for (Entity entl : ents)
		{
			if (!(entl instanceof EntityPlayer))
			{

			}
			else
			{
				EntityPlayer p = (EntityPlayer) entl;
				if (p.getCurrentArmor(3) != null)
				{

				}
			}
		}
	}
}
