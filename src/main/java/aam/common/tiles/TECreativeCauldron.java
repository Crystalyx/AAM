package aam.common.tiles;

import aam.common.potions.Colorer;
import aam.common.potions.IngridientItem;
import aam.common.potions.Ingridients;
import aam.utils.Color;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class TECreativeCauldron extends TileEntity
{
	@Override
	public void updateEntity()
	{
		fluid.amount = 1000;
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(0.0625F + xCoord, 0.15625F + yCoord, 0.0625F + zCoord, 0.9375F + xCoord, 0.8875F + yCoord, 0.9375F + zCoord);
		List<EntityItem> entities = worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
		if (!entities.isEmpty())
		{
			EntityItem item = entities.get(0);

			ItemStack is = item.getEntityItem();
			boolean flag = false;
			if (is.getTagCompound() != null)
			{
				if (this.addIngridient(is))
				{
					if (is.stackSize > 1)
					{
						is.stackSize--;
					}
					else
					{
						item.setDead();
					}
				}
			}
			else
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Time", 1);
				is.setTagCompound(tag);
			}
		}

		Color col = new Color(0, 136, 255);
		Color withClrr = new Color(256, 256, 256);
		for (int i = ingrs.size() - 1; i >= 0; i--)
		{
			col = col.add(ingrs.get(i).ing.color);
			if (ingrs.get(i).ing instanceof Colorer)
			{
				withClrr = ingrs.get(i).ing.color;
			}
		}
		if (withClrr.equals(Color.White))
		{
			color = col;
		}
		else
		{
			color = withClrr;
		}

		double px = worldObj.rand.nextDouble() + xCoord;
		double pz = worldObj.rand.nextDouble() + zCoord;
		double py = worldObj.rand.nextDouble() * 0.15625F + yCoord;

		worldObj.spawnParticle("flame", px, py, pz, 0, 0, 0);

		px = worldObj.rand.nextDouble() + xCoord;
		pz = worldObj.rand.nextDouble() + zCoord;
		py = worldObj.rand.nextDouble() * 0.15625F + yCoord;

		worldObj.spawnParticle("flame", px, py, pz, 0, 0, 0);
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

	public int addFuel(ItemStack is)
	{
		if (getItemBurnTime(is) > 0)
		{
			burnTime += getItemBurnTime(is);
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
			{
				return 200;
			}
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
			{
				return 200;
			}
			if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
			{
				return 200;
			}
			if (item == Items.stick)
			{
				return 100;
			}
			if (item == Items.coal)
			{
				return 1600;
			}
			if (item == Items.lava_bucket)
			{
				return 20000;
			}
			if (item == Item.getItemFromBlock(Blocks.sapling))
			{
				return 100;
			}
			if (item == Items.blaze_rod)
			{
				return 2400;
			}
			return GameRegistry.getFuelValue(is);
		}
	}

	public boolean addIngridient(ItemStack is)
	{
		if (Ingridients.getId(is) >= 0)
		{
			IngridientItem id = Ingridients.getPair(is);
			ingrs.add(id);
			lastid += 1;
			return true;
		}
		return false;
	}

	public boolean isBurning = false;
	public int burnTime = 0;
	public FluidStack fluid = new FluidStack(FluidRegistry.WATER, 1000);
	public Color color = new Color(0, 136, 254);
	public List<IngridientItem> ingrs = new ArrayList<>();
	public int lastid = 0;

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		color = new Color(tag.getIntArray("Color"));
		int size = tag.getInteger("IngCount");
		for (int i = 0; i < size; i++)
		{
			ingrs.add(new IngridientItem(Ingridients.ings.get(tag.getInteger("Ing_" + i)), tag.getInteger("IngType_" + i)));
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setIntArray("Color", new int[] { color.red, color.green, color.blue });
		tag.setInteger("IngCount", ingrs.size());
		for (int i = 0; i < ingrs.size(); i++)
		{
			tag.setInteger("Ing_" + i, ingrs.get(i).ing.id);
			tag.setInteger("IngType_" + i, ingrs.get(i).type);
		}
	}

}
