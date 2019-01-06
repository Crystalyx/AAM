package aam.network;

import DummyCore.Utils.DummyPacketIMSG_Tile;
import DummyCore.Utils.DummyTilePacketHandler;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.Circle;
import aam.common.transmutations.CircleUtils;
import aam.common.transmutations.ModCircles;
import aam.network.packages.AlchemicalPackage;
import aam.utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.io.IOException;
import java.util.List;

public class CircleChangePackage extends AlchemicalPackage
{
	public String dcircle = "";
	public int x = 0, y = 0, z = 0;
	public int add = -1;

	public CircleChangePackage()
	{
	}

	public CircleChangePackage(String circle, int x, int y, int z, int add)
	{
		dcircle = circle;
		this.x = x;
		this.y = y;
		this.z = z;
		this.add = add;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException
	{
		NBTTagCompound data = buffer.readNBTTagCompoundFromBuffer();
		dcircle = data.getString("Dcircle");
		int[] wec3 = data.getIntArray("Wec3");
		if (wec3.length == 0)
		{
			wec3 = new int[] { 0, 0, 0 };
		}
		x = wec3[0];
		y = wec3[1];
		z = wec3[2];

		add = data.getInteger("Add");
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException
	{
		NBTTagCompound data = new NBTTagCompound();
		data.setString("Dcircle", dcircle);
		data.setIntArray("Wec3", new int[] { x, y, z });

		data.setInteger("Add", add);
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer p, Side side)
	{
		World w = p.worldObj;

		TileEntity teRaw = w.getTileEntity(x, y, z);

		if (teRaw instanceof TETransCircle)
		{
			TETransCircle te = (TETransCircle) teRaw;
			List<String> dCirlePartCodes = MiscUtils.unpact(dcircle);
			List<Circle> dCirlePart = ModCircles.getCircles(dCirlePartCodes);
			if (add == 0)
			{
				for (Circle pr : dCirlePart)
				{
					CircleUtils.tryToAddCircle(te, pr);
				}
			}
			if (add == 1)
			{
				for (Circle c : dCirlePart)
				{
					te.circle.remove(c);
				}
			}
			NBTTagCompound tag = new NBTTagCompound();
			te.writeToNBT(tag);
			DummyPacketIMSG_Tile packet = new DummyPacketIMSG_Tile(tag);
			DummyTilePacketHandler.sendToAll(packet);
		}
	}
}
