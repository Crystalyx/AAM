package aam.common.blocks.mechanical;

import aam.common.items.ModItems;
import aam.common.potions.*;
import aam.common.tiles.TECauldron;
import aam.utils.Logger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cauldron extends BlockContainer
{

	public Cauldron(Material mat)
	{
		super(mat);
		this.setHardness(2.0F);
		this.setBlockBounds(0.0525F, 0.0F, 0.0525F, 0.9475F, 0.8875F, 0.9475F);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(this);
	}

	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add
	 * boxes to the list if they intersect the mask.) Parameters: World, X, Y,
	 * Z, mask, list, colliding entity
	 */
	@Override
	public void addCollisionBoxesToList(World w, int x, int y, int z, AxisAlignedBB aabb, List l, Entity e)
	{
		this.setBlockBounds(0.0525F, 0.00F, 0.0525F, 0.9475F, 0.8875F, 0.115F);
		super.addCollisionBoxesToList(w, x, y, z, aabb, l, e);
		this.setBlockBounds(0.0525F, 0.00F, 0.0525F, 0.115F, 0.8875F, 0.9475F);
		super.addCollisionBoxesToList(w, x, y, z, aabb, l, e);
		this.setBlockBounds(0.0525F, 0.00F, 0.885F, 0.9475F, 0.8875F, 0.9475F);
		super.addCollisionBoxesToList(w, x, y, z, aabb, l, e);
		this.setBlockBounds(0.885F, 0.00F, 0.0525F, 0.9475F, 0.8875F, 0.9475F);
		super.addCollisionBoxesToList(w, x, y, z, aabb, l, e);
		this.setBlockBounds(0.0525F, 0.00F, 0.0525F, 0.9475F, 0.208F, 0.9475F);
		super.addCollisionBoxesToList(w, x, y, z, aabb, l, e);
		this.setBlockBounds(0.0525F, 0.0F, 0.0525F, 0.9475F, 0.8875F, 0.9475F);

	}

	// @Override
	// public int getLightValue(IBlockAccess world, int x, int y, int z)
	// {
	// return super.getLightValue(world, x, y, z);
	// }

	public void addCollisionBoxToList(World w, int x, int y, int z, AxisAlignedBB aabb, List l, Entity e)
	{
		AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(0.0525F, 0.0F, 0.0525F, 0.9475F, 0.8875F, 0.9475F);

		if (aabb != null && aabb.intersectsWith(axisalignedbb1))
		{
			l.add(aabb);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TECauldron();
	}

	public static final int phialMeta = 3;

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		boolean flag = false;
		if (w.getTileEntity(x, y, z) != null)
		{
			TileEntity tile = w.getTileEntity(x, y, z);
			if (tile instanceof TECauldron)
			{
				TECauldron cauld = (TECauldron) tile;
				if (p.getCurrentEquippedItem() != null)
				{
					// ==================================Fuel==================================
					if (cauld.addFuel(p.getCurrentEquippedItem()) > 0)
					{
						if (!p.capabilities.isCreativeMode)
						{
							p.inventory.mainInventory[p.inventory.currentItem].stackSize--;
						}
						flag = true;
					}
					// ==================================Fire==================================
					if (p.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
					{
						if (!p.capabilities.isCreativeMode)
						{
							p.inventory.mainInventory[p.inventory.currentItem].damageItem(1, p);
						}
						cauld.isBurning = cauld.burnTime > 0;
						flag = true;
					}
					if (p.getCurrentEquippedItem().getItem() == ModItems.RiteBook)
					{
						cauld.burnTime = 1000000;
						cauld.fluid.amount = 1000;
						cauld.isBurning = cauld.burnTime > 0;
						flag = true;
					}
					// ==================================Water==================================
					if (p.getCurrentEquippedItem().getItem() == Items.water_bucket)
					{
						if (!p.capabilities.isCreativeMode)
						{
							p.inventory.mainInventory[p.inventory.currentItem] = new ItemStack(Items.bucket);
						}
						cauld.fluid.amount = 1000;
						flag = true;
					}
					if (p.getCurrentEquippedItem().getItem() == ModItems.AlchemicalBucket && p.getCurrentEquippedItem().getItemDamage() == 0)
					{
						if (Ingridients.getIngredientBase(cauld.ingrs).contains(Ingridients.berryAssorty))
						{
							if (cauld.potion.length > 0 && cauld.fluid.amount > 200)
							{
								ItemStack is = p.getCurrentEquippedItem();
								NBTTagCompound tag = is.getTagCompound();
								if (tag == null)
								{
									tag = new NBTTagCompound();
									is.setTagCompound(tag);
								}
								tag.setIntArray("Potion", cauld.potion);
								is.setItemDamage(1);
								cauld.ingrs = new ArrayList<>();
								cauld.fluid.amount -= 200;
								cauld.potion = new int[0];
							}
						}
					}
					// ==================================Potion===============================
					if (p.getCurrentEquippedItem().getItem() == ModItems.materials && p.getCurrentEquippedItem().getItemDamage() == phialMeta)
					{
						if (cauld.potion != null && cauld.potion.length > 0 && cauld.potion[0] != -1)
						{
							cauld.fluid.amount -= 300;
							ItemStack ret = new ItemStack(ModItems.Potion, 3);
							NBTTagCompound tag = new NBTTagCompound();
							tag.setInteger("PotionID", cauld.potion[0]);
							tag.setInteger("PotionAmpl", cauld.potion[1]);
							tag.setInteger("PotionDur", cauld.potion[2]);
							ret.setTagCompound(tag);
							if (!p.inventory.addItemStackToInventory(ret))
							{
								EntityItem e = new EntityItem(w, x, y, z, ret);

								e.setVelocity(w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8);
								w.spawnEntityInWorld(e);
							}
						}
						else
							if (cauld.isBurning && cauld.fluid.amount >= 300)
							{
								int id = 0;
								boolean brewed = false;
								int dur = ModPotions.pots[id].duration;
								int power = 1;
								for (int i = 0; i < ModPotions.pots.length; i++)
								{
									if (Ingridients.getIngredientBase(cauld.ingrs).containsAll(ModPotions.pots[i].ingridients))
									{
										if (p.inventory.mainInventory[p.inventory.currentItem].stackSize >= 3)
										{
											if (!p.capabilities.isCreativeMode)
											{
												p.inventory.mainInventory[p.inventory.currentItem].stackSize -= 3;
											}
											for (int j = 0; j < cauld.ingrs.size(); j++)
											{
												power += cauld.ingrs.get(j).type;
											}
											Ingridients.removeBaseList(cauld.ingrs, ModPotions.pots[i].ingridients);

											id = i;
											brewed = true;
											break;
										}
									}

								}
								if (brewed)
								{
									cauld.fluid.amount -= 300;
									ItemStack ret = new ItemStack(ModItems.Potion, 3);
									NBTTagCompound tag = new NBTTagCompound();
									tag.setInteger("PotionID", id);
									for (int i = 0; i < cauld.ingrs.size(); i++)
									{
										if (cauld.ingrs.get(i).ing instanceof Booster)
										{
											power += ((Booster) cauld.ingrs.get(i).ing).boost;
											cauld.ingrs.remove(i);
										}

										if (cauld.ingrs.get(i).ing instanceof Prolonger)
										{
											dur += ((Prolonger) cauld.ingrs.get(i).ing).time;
											cauld.ingrs.remove(i);
										}
									}
									tag.setInteger("PotionAmpl", power);
									tag.setInteger("PotionDur", dur);

									ret.setTagCompound(tag);

									if (!p.inventory.addItemStackToInventory(ret))
									{
										EntityItem e = new EntityItem(w, x, y, z, ret);

										e.setVelocity(w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8, w.rand.nextDouble() * 0.8);
										w.spawnEntityInWorld(e);
									}
								}
							}
					}
				}
				else
				{
					if (!p.isSneaking())
					{
						if (w.isRemote)
						{
							p.addChatComponentMessage(new ChatComponentText("Ingidients IDs:"));
							for (int i = 0; i < cauld.ingrs.size(); i++)
							{
								IngridientItem ingi = cauld.ingrs.get(i);
								ItemStack is = new ItemStack(ingi.ing.items.get(ingi.type).key, 1, ingi.ing.items.get(ingi.type).value);
								Logger.chat(p, is.getDisplayName() + " : " + ingi.ing.id + " : " + ingi.type);
							}
							if (cauld.potion.length > 0)
							{
								Logger.mchat(p, cauld.potion[0], cauld.potion[1], cauld.potion[2]);
							}
						}
					}
					else
					{
						if (cauld.isBurning)
						{
							cauld.isBurning = !cauld.isBurning;
						}
						else
						{
							cauld.ingrs = new ArrayList<>();
						}
					}
				}
			}
		}

		return flag;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isNormalCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 126;
	}

	public IIcon top;
	public IIcon side;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		top = ir.registerIcon("aam:cauldron_top");
		side = ir.registerIcon("aam:cauldron_side");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side == ForgeDirection.UP.ordinal() || side == ForgeDirection.DOWN.offsetX ? top : this.side;
	}

}
