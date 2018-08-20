package AAM.Common.Tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TEBarrel extends TileEntity
{
	public int burnTime = 0;
	public int[] potion = new int[0];
	public int volume = 0;

	public static int maxBurnTime = 6000;

	@Override
	public void updateEntity()
	{
		if (this.potion.length > 0 && this.burnTime < maxBurnTime && this.volume <= 0)
		{
			this.burnTime += 1;
		}
		if (this.burnTime >= maxBurnTime)
		{
			this.volume = 9;
			this.burnTime = 0;
		}
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
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.burnTime = tag.getInteger("BurnTime");
		this.potion = tag.getIntArray("Potion");
		this.volume = tag.getInteger("Volume");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("BurnTime", this.burnTime);
		tag.setIntArray("Potion", this.potion);
		tag.setInteger("Volume", this.volume);

	}
}
