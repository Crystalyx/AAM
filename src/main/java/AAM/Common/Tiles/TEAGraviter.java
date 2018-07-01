package AAM.Common.Tiles;

import java.util.List;

import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

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
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}

	public void updateEntity()
	{

		double rx = this.worldObj.rand.nextDouble() * 10 * MiscUtils.boolToNum();
		double ry = this.worldObj.rand.nextDouble() * 10 * MiscUtils.boolToNum();
		double rz = this.worldObj.rand.nextDouble() * 10 * MiscUtils.boolToNum();

		double radius = 40;
		List<Entity> ents = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord - radius, this.yCoord - radius, this.zCoord - radius, this.xCoord + radius, this.yCoord + radius, this.zCoord + radius));
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
