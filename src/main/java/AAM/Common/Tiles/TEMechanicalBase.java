package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Transmutations.Circle;
import AAM.Common.Transmutations.ModCircles;
import AAM.Utils.Wec3;
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
	public List<Circle> circle = new ArrayList<Circle>();

	public void createCircleAbove()
	{
		if (!this.circle.isEmpty())
		{
			Wec3 cl = new Wec3(this).add(ForgeDirection.UP);
			this.worldObj.setBlock((int) cl.x, (int) cl.y, (int) cl.z, ModBlocks.TransCircle, 1, 2);
			TETransCircle tc = (TETransCircle) this.worldObj.getTileEntity((int) cl.x, (int) cl.y, (int) cl.z);
			tc.circle = this.circle;
		}
	}

	@Override
	public void updateEntity()
	{
		if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
		{
			if (this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
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
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}

	/**
	 * Reads a tile entity from NBT.
	 */
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

			this.circle.add(new Circle(ModCircles.parts.get(id), scale, rev));
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList circles = new NBTTagList();

		for (int i = 0; i < this.circle.size(); i++)
		{
			NBTTagCompound part = new NBTTagCompound();
			part.setInteger("Part", ModCircles.parts.indexOf(this.circle.get(i).pt));
			part.setBoolean("Reversed", this.circle.get(i).rev);
			part.setDouble("Scale", this.circle.get(i).scale);

			circles.appendTag(part);
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return this.INFINITE_EXTENT_AABB;
	}
}
