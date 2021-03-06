package aam.common.tiles;

import aam.common.blocks.building.PillarBlock;
import aam.utils.vectors.Wec3;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TECrystal extends TileEntity
{
	// TODO
	public int soulValue = 0;
	public int packetSizeB = 6;
	public int packetSize = 6;

	@Override
	public void updateEntity()
	{
		if (this.checkStructure())
		{
			if (worldObj.getWorldTime() % 40 == 0)
			{
				soulValue += 1;
			}
		}
	}

	public boolean checkStructure()
	{
		Wec3 cp = new Wec3(this);
		boolean ret = true;
		for (int i = 0; i < 4; i++)
		{
			cp = cp.add(ForgeDirection.DOWN);
			ret = ret && cp.getBlock(worldObj) instanceof PillarBlock;
		}

		return ret;
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
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		soulValue = tag.getInteger("SoulValue");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setInteger("SoulValue", soulValue);
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}
}
