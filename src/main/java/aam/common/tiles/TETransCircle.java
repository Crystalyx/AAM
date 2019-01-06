package aam.common.tiles;

import aam.api.interfaces.ICatalyst;
import aam.common.transmutations.*;
import aam.utils.Color;
import aam.utils.InventoryUtils;
import aam.utils.vectors.Wec3;
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

import java.util.ArrayList;
import java.util.List;

public class TETransCircle extends TileEntity implements IInventory
{
	public List<Circle> circle = new ArrayList<>();
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

	public void check()
	{
		double scale = CircleUtils.getMinScale(circle);
		if (!ModCircles.reloading)
		{
			for (Transmutation tr : ModCircles.circles)
			{
				if (InventoryUtils.containsOnly(ModCircles.getCodeStr(circle), CircleUtils.modify(tr.parts, scale)))
				{
					int maxLevel = 0;
					if (alchemist != null)
					{
						for (int i = 0; i < alchemist.inventory.getSizeInventory(); i++)
						{
							if (alchemist.inventory.getStackInSlot(i) != null)
							{
								if (alchemist.inventory.getStackInSlot(i).getItem() instanceof ICatalyst)
								{
									if (maxLevel < ((ICatalyst) alchemist.inventory.getStackInSlot(i).getItem()).getPotency(alchemist.inventory.getStackInSlot(i)))
									{
										maxLevel = ((ICatalyst) alchemist.inventory.getStackInSlot(i).getItem()).getPotency(alchemist.inventory.getStackInSlot(i));
									}
								}
							}
						}
					}
					transm = tr;
					potency = scale + maxLevel;
					prepCol = tr.prepCol;
					actCol = tr.actCol;
					return;
				}
				else
				{
					transm = null;
				}
			}
		}
	}

	public void transmute()
	{
		check();
		if (transm != null)
		{
			transm.action.act(worldObj, new Wec3(this), this, alchemist, potency, ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)));
		}
		else
		{
			state = State.idle;
		}
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
				{
					i -= 1;
				}
			}
		}

		if (transm != null)
		{
			if (state.equals(State.active))
			{
				if (completeTimer < transm.prepTime)
				{
					++completeTimer;
				}
				else
				{
					completeTimer++;
					state = State.complete;
					this.transmute();
				}
			}
			if (state.equals(State.complete))
			{
				boolean ticks = transm.action.actTick(worldObj, new Wec3(this), this, alchemist, ticktime, potency, ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)));
				if (completeTimer > transm.prepTime + transm.actTime && !ticks)
				{
					completeTimer = 0;
					state = State.idle;
					ticktime = 0;
				}
				else
				{
					completeTimer++;
					if (ticks)
					{
						ticktime += 1;
					}
				}
			}
		}
		else
		{
			state = State.idle;
		}

		if (energy <= 0)
		{
			CircleUtils.clearEnergy(this);
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

		InventoryUtils.readInventory(this, tag);

		NBTTagList circles = tag.getTagList("circles", 10);

		energy = tag.getDouble("TrEnergy");
		extended = tag.getBoolean("Ext-d");
		if (extended)
		{
			esize = tag.getDouble("ExtSize");
		}

		int size = tag.getInteger("CircleSize");
		List<String> codes = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			codes.add(tag.getString("Part" + i));
		}

		circle = ModCircles.getCircles(codes);

		state = State.values()[tag.getInteger("State")];

		if (tag.hasKey("CustomName"))
		{
			name = tag.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		InventoryUtils.saveInventory(this, tag);

		tag.setDouble("TrEnergy", energy);

		tag.setBoolean("Ext-d", extended);
		if (extended)
		{
			tag.setDouble("ExtSize", esize);
		}

		List<String> codes = ModCircles.getCodeStr(circle);
		for (int i = 0; i < circle.size(); i++)
		{
			tag.setInteger("CircleSize", codes.size());
			tag.setString("Part" + i, codes.get(i));
		}

		tag.setInteger("State", state.ordinal());

		if (!name.equals(""))
		{
			tag.setString("CustomName", name);
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return new Wec3(this).extendBoth((float) CircleUtils.getMaxScale(circle) + 1);
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
