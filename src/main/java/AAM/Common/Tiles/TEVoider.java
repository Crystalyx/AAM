package AAM.Common.Tiles;

import java.util.List;

import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Lord_Crystalyx
 */
public class TEVoider extends TileEntity
{
	public TEVoider()
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
		if (!this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
		{
			double rx = this.worldObj.rand.nextDouble() * 10;
			double ry = this.worldObj.rand.nextDouble() * 10;
			double rz = this.worldObj.rand.nextDouble() * 10;
			if (this.worldObj.rand.nextBoolean())
				rx *= -1;
			if (this.worldObj.rand.nextBoolean())
				ry *= -1;
			if (this.worldObj.rand.nextBoolean())
				rz *= -1;

			Wec3 pp = new Wec3(this).centralize().add(ForgeDirection.UP);
			AxisAlignedBB aabb = pp.extend(25f);

			List<Entity> es = this.worldObj.getEntitiesWithinAABB(Entity.class, aabb);
			for (Entity e : es)
			{
				Wec3 ep = new Wec3(e);
				Wec3 vec = pp.sub(ep);
				vec.ptm(e);
			}
		}
	}
}
