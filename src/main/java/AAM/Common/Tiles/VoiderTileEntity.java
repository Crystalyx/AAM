package AAM.Common.Tiles;

import java.util.List;

import AAM.Utils.PlayerDataHandler;
import AAM.Utils.WorldPos;
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
public class VoiderTileEntity extends TileEntity
{
	public VoiderTileEntity()
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
			// RogueWorldCore.proxy.spawnParticle("energyFX", this.xCoord +
			// 0.5F,
			// this.yCoord - 0.5F, this.zCoord + 0.5F, this.xCoord + rx,
			// this.yCoord
			// + ry, this.zCoord + rz);

			WorldPos pp = new WorldPos(this).centralize().add(ForgeDirection.UP);
			AxisAlignedBB aabb = pp.getAABB(25f);

			List<Entity> es = this.worldObj.getEntitiesWithinAABB(Entity.class, aabb);
			for (Entity e : es)
			{
				if (!(e instanceof EntityPlayer && PlayerDataHandler.get((EntityPlayer) e).playerIsSeeker))
				{
					WorldPos ep = new WorldPos(e);
					WorldPos vec = pp.subtruct(ep);
//					vec = vec.power(1).modify(1);
					vec.ptm(e);
				}
			}
			// entl.setVelocity(this.xCoord-entl.posX+0.5,
			// this.yCoord-entl.posY+0.5, this.zCoord-entl.posZ+0.5);
		}
	}
}
