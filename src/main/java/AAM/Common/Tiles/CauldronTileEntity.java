package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Potions.Colorer;
import AAM.Common.Potions.Ingridient;
import AAM.Common.Potions.Ingridients;
import AAM.Utils.Color;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class CauldronTileEntity extends TileEntity
{
	@Override
	public void updateEntity()
	{
		if (this.isBurning)
		{
			this.burnTime--;
			if (this.burnTime <= 0)
				this.isBurning = false;
			if (this.fluid.amount > 0)
				if (this.worldObj.rand.nextInt(7) == 1)
					this.fluid.amount--;
		}

		if (this.isBurning)
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(0.0625F + this.xCoord, 0.15625F + this.yCoord, 0.0625F + this.zCoord, 0.9375F + this.xCoord, 0.8875F + this.yCoord, 0.9375F + this.zCoord);
			List<EntityItem> entities = this.worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
			if (!entities.isEmpty())
			{
				EntityItem item = entities.get(0);
				ItemStack is = item.getEntityItem();
				boolean flag = false;
				if (is.getTagCompound() != null)
				{
					if (is.getTagCompound().hasKey("Time"))
					{
						if (is.getTagCompound().getInteger("Time") > 200)
						{
							if (this.addIngridient(is))
							{
								if (is.stackSize > 1)
								{
									is.stackSize--;
									is.getTagCompound().setInteger("Time", 1);
								}
								else
									item.setDead();
							}
						}
						else
						{
							is.getTagCompound().setInteger("Time", is.getTagCompound().getInteger("Time") + 1);
						}
					}
					else
					{
						is.getTagCompound().setInteger("Time", 1);
					}
				}
				else
				{
					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("Time", 1);
					is.setTagCompound(tag);
				}
			}
		}
		Color col = new Color(0, 123, 255);
		Color withClrr = new Color(256, 256, 256);
		for (int i = this.ingrs.size() - 1; i >= 0; i--)
		{
			col = col.add(this.ingrs.get(i).color);
			if (this.ingrs.get(i) instanceof Colorer)
			{
				withClrr = withClrr.add(this.ingrs.get(i).color);
			}
		}
		if (withClrr.red == 256)
		{
			this.color = col;
		}
		else
		{
			this.color = withClrr;
		}

		if (this.isBurning)
		{
			double px = this.worldObj.rand.nextDouble() + this.xCoord;
			double pz = this.worldObj.rand.nextDouble() + this.zCoord;
			double py = this.worldObj.rand.nextDouble() * 0.15625F + this.yCoord;

			this.worldObj.spawnParticle("flame", px, py, pz, 0, 0, 0);

			px = this.worldObj.rand.nextDouble() + this.xCoord;
			pz = this.worldObj.rand.nextDouble() + this.zCoord;
			py = this.worldObj.rand.nextDouble() * 0.15625F + this.yCoord;

			this.worldObj.spawnParticle("flame", px, py, pz, 0, 0, 0);
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

	public int addFuel(ItemStack is)
	{
		if (getItemBurnTime(is) > 0)
		{
			this.burnTime += getItemBurnTime(is);
		}
		return getItemBurnTime(is);
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the
	 * furnace burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack is)
	{
		if (is == null)
		{
			return 0;
		}
		else
		{
			Item item = is.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
			{
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.wooden_slab)
				{
					return 150;
				}

				if (block.getMaterial() == Material.wood)
				{
					return 300;
				}

				if (block == Blocks.coal_block)
				{
					return 16000;
				}
			}

			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item == Items.stick)
				return 100;
			if (item == Items.coal)
				return 1600;
			if (item == Items.lava_bucket)
				return 20000;
			if (item == Item.getItemFromBlock(Blocks.sapling))
				return 100;
			if (item == Items.blaze_rod)
				return 2400;
			return GameRegistry.getFuelValue(is);
		}
	}

	public boolean addIngridient(ItemStack is)
	{
		if (Ingridients.getId(is) >= 0)
		{
			Ingridient id = Ingridients.ings.get(Ingridients.getId(is));
			this.ingrs.add(id);
			this.lastid += 1;
			return true;
		}
		return false;
	}

	public boolean isBurning = false;
	public int burnTime = 0;
	public FluidStack fluid = new FluidStack(FluidRegistry.WATER, 0);
	public Color color = new Color(0, 123, 254);
	public List<Ingridient> ingrs = new ArrayList<Ingridient>();
	public int lastid = 0;

	public void readFromNBT(NBTTagCompound tag)
	{
		System.out.println("RFNBT");
		super.readFromNBT(tag);
		this.isBurning = tag.getBoolean("Burning");
		this.burnTime = tag.getInteger("BurnTime");
		this.fluid = new FluidStack(FluidRegistry.getFluid(tag.getString("Fluid")), tag.getInteger("FluidAmount"));
		this.color = new Color(tag.getIntArray("Color"));
		int size = tag.getInteger("IngCount");
		for (int i = 0; i < size; i++)
			this.ingrs.add(Ingridients.ings.get(tag.getInteger("Ing_" + i)));
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		System.out.println("WTNBT");
		super.writeToNBT(tag);
		tag.setBoolean("Burning", this.isBurning);
		tag.setInteger("BurnTime", this.burnTime);
		tag.setString("Fluid", this.fluid.getFluid().getName());
		tag.setInteger("FluidAmount", this.fluid.amount);
		tag.setIntArray("Color", new int[] { this.color.red, this.color.green, this.color.blue });
		tag.setInteger("IngCount", this.ingrs.size());
		for (int i = 0; i < this.ingrs.size(); i++)
			tag.setInteger("Ing_" + i, this.ingrs.get(i).id);
	}

}
