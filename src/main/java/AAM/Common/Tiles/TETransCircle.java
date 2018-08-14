package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

import AAM.API.Interface.ICatalyst;
import AAM.Common.Transmutations.Circle;
import AAM.Common.Transmutations.EnergyType;
import AAM.Common.Transmutations.Extension;
import AAM.Common.Transmutations.ModCircles;
import AAM.Common.Transmutations.Transmutation;
import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TETransCircle extends TileEntity implements IInventory
{
	public List<Circle> circle = new ArrayList<Circle>();
	private String name = "circle";
	public boolean isLink = false;
	int ticktime = 0;

	public EntityPlayer alchemist;
	public Transmutation transm;
	public ItemStack is;

	public double energy = 0;
	public EnergyType energyType = EnergyType.Unknown;

	public double potency = 1;
	public Color prepCol = new Color(60, 40, 255);
	public Color actCol = new Color(160, 140, 255);

	public enum State
	{
		idle, active, complete;
	}

	public void extend()
	{
		for (Extension ex : ModCircles.extensions)
		{
			ex.work(this.worldObj, new Wec3(this));
		}
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

	public void clearEnergy()
	{
		this.energy = 0;
		this.energyType = EnergyType.Unknown;
	}

	public void check()
	{
		double scale = getMinScale(this.circle);
		for (Transmutation tr : ModCircles.circles)
		{
			if (MiscUtils.containsOnly(ModCircles.getCodeStr(this.circle), modify(tr.parts, scale)))
			{
				int maxLevel = 0;
				if (this.alchemist != null)
				{
					for (int i = 0; i < this.alchemist.inventory.getSizeInventory(); i++)
					{
						if (this.alchemist.inventory.getStackInSlot(i) != null)
						{
							if (this.alchemist.inventory.getStackInSlot(i).getItem() instanceof ICatalyst)
							{
								if (maxLevel < ((ICatalyst) this.alchemist.inventory.getStackInSlot(i).getItem()).getPotency(this.alchemist.inventory.getStackInSlot(i)))
								{
									maxLevel = ((ICatalyst) this.alchemist.inventory.getStackInSlot(i).getItem()).getPotency(this.alchemist.inventory.getStackInSlot(i));
								}
							}
						}
					}
				}
				this.transm = tr;
				this.potency = scale + maxLevel;
				this.prepCol = tr.prepCol;
				this.actCol = tr.actCol;
				return;
			}
			else
				this.transm = null;
		}
	}

	public void transmute()
	{
		check();
		if (this.transm != null)
			this.transm.action.act(this.worldObj, new Wec3(this), this, this.alchemist, this.potency, ForgeDirection.getOrientation(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)));
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
				if (i > 0)
					i -= 1;
			}
		}

		if (this.transm != null)
		{
			if (this.state.equals(State.active))
			{
				if (this.completeTimer < this.transm.prepTime)
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
				boolean ticks = this.transm.action.actTick(this.worldObj, new Wec3(this), this, this.alchemist, this.ticktime, this.potency,
						ForgeDirection.getOrientation(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)));
				if (this.completeTimer > this.transm.prepTime + this.transm.actTime && !ticks)
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

		if (this.energy <= 0)
		{
			this.energy = 0;
			this.energyType = EnergyType.Unknown;
		}
	}

	public State state = State.idle;
	public int completeTimer = 0;
	public double esize = -1;
	public boolean extended = false;

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
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		MiscUtils.readInventory(this, tag);

		NBTTagList circles = tag.getTagList("Circles", 10);

		this.energy = tag.getDouble("TrEnergy");
		this.extended = tag.getBoolean("Ext-d");
		if (this.extended)
			this.esize = tag.getDouble("ExtSize");

		int size = tag.getInteger("CircleSize");
		List<String> codes = new ArrayList<String>();
		for (int i = 0; i < size; i++)
		{
			codes.add(tag.getString("Part" + i));
		}

		this.circle = ModCircles.getCircles(codes);

		this.state = State.values()[tag.getInteger("State")];

		if (tag.hasKey("CustomName"))
		{
			this.name = tag.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		MiscUtils.saveInventory(this, tag);

		tag.setDouble("TrEnergy", this.energy);

		tag.setBoolean("Ext-d", this.extended);
		if (this.extended)
			tag.setDouble("ExtSize", this.esize);

		List<String> codes = ModCircles.getCodeStr(this.circle);
		for (int i = 0; i < this.circle.size(); i++)
		{
			tag.setInteger("CircleSize", codes.size());
			tag.setString("Part" + i, codes.get(i));
		}

		tag.setInteger("State", this.state.ordinal());

		if (!this.name.equals(""))
		{
			tag.setString("CustomName", this.name);
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return this.INFINITE_EXTENT_AABB;
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return is;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack i)
	{
		is = i;
	}

	@Override
	public String getInventoryName()
	{
		return "tetranscircle";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return false;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
	}
}
