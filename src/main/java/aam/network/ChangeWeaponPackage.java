package aam.network;

import DummyCore.Utils.DummyPacketIMSG_Tile;
import DummyCore.Utils.DummyTilePacketHandler;
import aam.common.tiles.TEMechanistsTable;
import aam.network.packages.AlchemicalPackage;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import java.io.IOException;

public class ChangeWeaponPackage extends AlchemicalPackage
{

	public int x = 0, y = 0, z = 0;

	public ChangeWeaponPackage()
	{
	}

	public ChangeWeaponPackage(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
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
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException
	{
		NBTTagCompound data = new NBTTagCompound();
		data.setIntArray("Wec3", new int[] { x, y, z });
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
			te.switchType();

			NBTTagCompound tag = new NBTTagCompound();
			te.writeToNBT(tag);
			DummyPacketIMSG_Tile packet = new DummyPacketIMSG_Tile(tag);
			DummyTilePacketHandler.sendToAll(packet);
		}
	}

}
