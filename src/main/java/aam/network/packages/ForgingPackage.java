package aam.network.packages;

import java.io.IOException;

import DummyCore.Utils.DummyPacketIMSG_Tile;
import DummyCore.Utils.DummyTilePacketHandler;
import aam.common.tiles.TEMechanistsTable;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

public class ForgingPackage extends AlchemicalPackage
{

	public int x = 0, y = 0, z = 0;
	public String name = "";

	public ForgingPackage()
	{
	}

	public ForgingPackage(int x, int y, int z, String name)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = name;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException
	{
		NBTTagCompound data = buffer.readNBTTagCompoundFromBuffer();
		int[] wec3 = data.getIntArray("Wec3");
		if (wec3.length == 0)
		{
			wec3 = new int[] { 0, 0, 0 };
		}
		x = wec3[0];
		y = wec3[1];
		z = wec3[2];
		this.name = data.getString("Name");
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException
	{
		NBTTagCompound data = new NBTTagCompound();
		data.setIntArray("Wec3", new int[] { x, y, z });
		data.setString("Name", this.name);
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer p, Side side)
	{
		World w = p.worldObj;

		TEMechanistsTable teRaw = (TEMechanistsTable) w.getTileEntity(x, y, z);

		if (teRaw instanceof TEMechanistsTable)
		{
			TEMechanistsTable te = teRaw;
			te.forge(this.name);

			NBTTagCompound tag = new NBTTagCompound();
			te.writeToNBT(tag);
			DummyPacketIMSG_Tile packet = new DummyPacketIMSG_Tile(tag);
			DummyTilePacketHandler.sendToAll(packet);
		}
	}

}
