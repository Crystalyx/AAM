package aam.common.tiles;

import java.util.ArrayList;
import java.util.List;

import aam.common.blocks.building.ModBlocks;
import aam.common.transmutations.Circle;
import aam.common.transmutations.ModCircles;
import aam.utils.vectors.Wec3;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TEMechanicalBase extends TileEntity
{
	public List<Circle> circle = new ArrayList<>();

	public void createCircleAbove()
	{
		if (!circle.isEmpty())
		{
			Wec3 cl = new Wec3(this).add(ForgeDirection.UP);
			worldObj.setBlock((int) cl.x, (int) cl.y, (int) cl.z, ModBlocks.TransCircle, 1, 2);
			TETransCircle tc = (TETransCircle) worldObj.getTileEntity((int) cl.x, (int) cl.y, (int) cl.z);
			tc.circle = circle;
		}
	}

	@Override
	public void updateEntity()
	{
		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
			if (worldObj.isAirBlock(xCoord, yCoord + 1, zCoord))
			{
				this.createCircleAbove();
			}
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
		readFromNBT(pkt.func_148857_g());
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList circles = tag.getTagList("Circles", 10);

		for (int i = 0; i < circles.tagCount(); i++)
		{
			NBTTagCompound part = circles.getCompoundTagAt(i);
			int id = part.getInteger("Part");
			boolean rev = part.getBoolean("Reversed");
			double scale = part.getDouble("Scale");

			circle.add(new Circle(ModCircles.parts.get(id), scale, rev));
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList circles = new NBTTagList();

		for (int i = 0; i < circle.size(); i++)
		{
			NBTTagCompound part = new NBTTagCompound();
			part.setInteger("Part", ModCircles.parts.indexOf(circle.get(i).pt));
			part.setBoolean("Reversed", circle.get(i).rev);
			part.setDouble("Scale", circle.get(i).scale);

			circles.appendTag(part);
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return INFINITE_EXTENT_AABB;
	}
}
