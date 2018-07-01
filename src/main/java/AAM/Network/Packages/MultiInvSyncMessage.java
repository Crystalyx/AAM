package AAM.Network.Packages;

import java.io.IOException;

import AAM.Common.Tiles.MultiInventory;
import AAM.Utils.Wec3;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class MultiInvSyncMessage extends AlchemicalPackage
{
	private NBTTagCompound data;

	public MultiInvSyncMessage()
	{
	}

	public MultiInvSyncMessage(MultiInventory te)
	{
		data = new NBTTagCompound();
		data.setInteger("Line", te.line);
		data.setIntArray("InvPos", new int[] { te.xCoord, te.yCoord, te.zCoord });

	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException
	{
		data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException
	{
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer player, Side side)
	{
		if (side.equals(Side.SERVER))
		{
			int[] cd = data.getIntArray("InvPos");
			if (cd.length == 3)
			{
				TileEntity te = new Wec3(cd[0], cd[1], cd[2]).getTileEntity(player.worldObj);
				if (te instanceof MultiInventory)
				{
					((MultiInventory) te).line = data.getInteger("Line");
				}
			}
		}
	}

}
