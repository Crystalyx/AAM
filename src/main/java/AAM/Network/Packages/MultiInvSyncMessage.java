package AAM.Network.Packages;

import java.io.IOException;

import AAM.Common.Tiles.MultiInventory;
import AAM.Utils.WorldPos;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class MultiInvSyncMessage extends AlchemicalPackage
{
	// Previously, we've been writing each field in our properties one at a
	// time,
	// but that is really annoying, and we've already done it in the save and
	// load
	// NBT methods anyway, so here's a slick way to efficiently send all of your
	// extended data, and no matter how much you add or remove, you'll never
	// have
	// to change the packet / synchronization of your data.

	// this will store our ExtendedPlayer data, allowing us to easily read and
	// write
	private NBTTagCompound data;

	// The basic, no-argument constructor MUST be included to use the new
	// automated handling
	public MultiInvSyncMessage()
	{
	}

	// We need to initialize our data, so provide a suitable constructor:
	public MultiInvSyncMessage(MultiInventory te)
	{
		// create a new tag compound
		data = new NBTTagCompound();
		data.setInteger("Line", te.line);
		data.setIntArray("InvPos", new int[]
		{ te.xCoord, te.yCoord, te.zCoord });

		// and save our player's data into it
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
		// now we can just load the NBTTagCompound data directly; one and done,
		// folks
		if (side.equals(Side.SERVER))
		{
			int[] cd = data.getIntArray("InvPos");
			if (cd.length == 3)
			{
				TileEntity te = new WorldPos(cd[0], cd[1], cd[2]).getTileEntity(player.worldObj);
				if (te instanceof MultiInventory)
				{
					((MultiInventory) te).line = data.getInteger("Line");
				}
			}
		}
	}

}
