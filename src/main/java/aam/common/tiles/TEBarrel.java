package aam.common.tiles;

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
		if (potion.length > 0 && burnTime < maxBurnTime && volume <= 0)
		{
			burnTime += 1;
		}
		if (burnTime >= maxBurnTime)
		{
			volume = 9;
			burnTime = 0;
		}
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
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		burnTime = tag.getInteger("BurnTime");
		potion = tag.getIntArray("Potion");
		volume = tag.getInteger("Volume");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("BurnTime", burnTime);
		tag.setIntArray("Potion", potion);
		tag.setInteger("Volume", volume);

	}
}
