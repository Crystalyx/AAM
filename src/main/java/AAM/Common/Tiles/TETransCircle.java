package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Transmutations.Circle;
import AAM.Common.Transmutations.ModCircles;
import AAM.Common.Transmutations.Transmutation;
import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
import AAM.Utils.WorldPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TETransCircle extends TileEntity // implements IInventory
{
	// private ItemStack[] inv = new ItemStack[100];
	public List<Circle> circle = new ArrayList<Circle>();
	private String name = "circle";
	public boolean isLink = false;
	int ticktime = 0;

	public EntityPlayer alchemist;
	public Transmutation last;
	public double potency = 1;
	public Color prepCol = new Color(60, 40, 255);
	public Color actCol = new Color(160, 140, 255);

	public enum State
	{
		idle, active, complete;
	}

	public List<String> modify(List<String> l, double mod)
	{
		List<String> lst = new ArrayList<String>();
		for (String c : l)
		{
			String num = c.substring(2, c.length() - 1);
			double i = Double.parseDouble(num);
			lst.add(c.substring(0, 2) + (int) (i * mod) + c.substring(c.length() - 1));
		}

		return lst;
	}

	public double getMinScale(List<Circle> l)
	{
		double min = 100;

		for (int i = 0; i < l.size(); i++)
		{
			double s = l.get(i).scale;
			if (s < min)
				min = s;
		}

		return min;
	}

	public void check()
	{
		double scale = getMinScale(this.circle);
		for (Transmutation tr : ModCircles.circles)
		{
			if (MiscUtils.containsOnly(ModCircles.getCodeStr(this.circle), modify(tr.parts, scale)))
			{
				this.last = tr;
				this.potency = scale;
				this.prepCol = tr.prepCol;
				this.actCol = tr.actCol;
				return;
			}
			else
				this.last = null;
		}
	}

	public void transmute()
	{
		check();
		if (this.last != null)
			this.last.action.act(this.worldObj, new WorldPos(this), this, this.alchemist, this.potency, ForgeDirection.getOrientation(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)));
		else
			this.state = State.idle;
	}

	@Override
	public void updateEntity()
	{
		check();

		for (int i = 0; i < circle.size(); i++)
		{
			Circle cl = circle.get(i);
			int n = 0;
			for (Circle c : circle)
			{
				if (cl.rev == c.rev && cl.pt == c.pt)
				{
					n += 1;
				}
			}
			if (n >= 2)
			{
				circle.remove(cl);
			}
		}

		if (this.last != null)
		{
			if (this.state.equals(State.active))
			{
				if (this.completeTimer < this.last.prepTime)
				{
					++this.completeTimer;
				}
				else
				{
					this.completeTimer++;
					this.state = State.complete;
					this.transmute();
				}
			}
			if (this.state.equals(State.complete))
			{
				boolean ticks = this.last.action.actTick(this.worldObj, new WorldPos(this), this, this.alchemist, this.ticktime, this.potency,
						ForgeDirection.getOrientation(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)));
				if (this.completeTimer > this.last.prepTime + this.last.actTime && !ticks)
				{
					this.completeTimer = 0;
					this.state = State.idle;
					this.ticktime = 0;
				}
				else
				{
					this.completeTimer++;
					if (ticks)
					{
						this.ticktime += 1;
					}
				}
			}
		}
		else
			this.state = State.idle;
	}

	public State state = State.idle;
	public int completeTimer = 0;

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
		// NBTTagList nbttaglist = tag.getTagList("Items", 10);
		// this.inv = new ItemStack[this.getSizeInventory()];

		// for (int i = 0; i < nbttaglist.tagCount(); ++i)
		// {
		// NBTTagCompound nbttagcompound1 = (NBTTagCompound)
		// nbttaglist.getCompoundTagAt(i);
		// int j = nbttagcompound1.getByte("Slot") & 255;
		//
		// if (j >= 0 && j < this.inv.length)
		// {
		// this.inv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		// }
		// }

		NBTTagList circles = tag.getTagList("Circles", 10);

		for (int i = 0; i < circles.tagCount(); i++)
		{
			NBTTagCompound part = circles.getCompoundTagAt(i);
			int id = part.getInteger("Part");
			boolean rev = part.getBoolean("Reversed");
			double scale = part.getDouble("Scale");

			this.circle.add(new Circle(ModCircles.parts.get(id), scale, rev));
		}

		this.state = State.values()[tag.getInteger("State")];

		if (tag.hasKey("CustomName"))
		{
			this.name = tag.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		// NBTTagList items = new NBTTagList();

		// for (int i = 0; i < this.inv.length; ++i)
		// {
		// if (this.inv[i] != null)
		// {
		// NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		// nbttagcompound1.setByte("Slot", (byte) i);
		// this.inv[i].writeToNBT(nbttagcompound1);
		// nbttaglist.appendTag(nbttagcompound1);
		// }
		// }

		// tag.setTag("Items", items);

		NBTTagList circles = new NBTTagList();

		for (int i = 0; i < this.circle.size(); i++)
		{
			NBTTagCompound part = new NBTTagCompound();
			part.setInteger("Part", ModCircles.parts.indexOf(this.circle.get(i).pt));
			part.setBoolean("Reversed", this.circle.get(i).rev);
			part.setDouble("Scale", this.circle.get(i).scale);

			circles.appendTag(part);
		}

		tag.setInteger("State", this.state.ordinal());

		tag.setTag("Circles", circles);

		if (this.name != "")
		{
			tag.setString("CustomName", this.name);
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return this.INFINITE_EXTENT_AABB;
	}
}
