package AAM.Common.Blocks.Mechanical;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AAM.Common.Items.ModItems;
import AAM.Common.Potions.Booster;
import AAM.Common.Potions.Ingridient;
import AAM.Common.Potions.ModPotions;
import AAM.Common.Potions.Prolonger;
import AAM.Common.Tiles.TECauldron;
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
							p.inventory.mainInventory[p.inventory.currentItem].stackSize--;
						flag = true;
					}
					// ==================================Fire==================================
					if (p.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
					{
						if (!p.capabilities.isCreativeMode)
							p.inventory.mainInventory[p.inventory.currentItem].damageItem(1, p);
						cauld.isBurning = cauld.burnTime > 0;
						flag = true;
					}
					// ==================================Water==================================
					if (p.getCurrentEquippedItem().getItem() == Items.water_bucket)
					{
						if (!p.capabilities.isCreativeMode)
							p.inventory.mainInventory[p.inventory.currentItem] = new ItemStack(Items.bucket);
						cauld.fluid.amount = 1000;
						flag = true;
					}
					// ==================================Potion===============================
					if (p.getCurrentEquippedItem().getItem() == ModItems.materials && p.getCurrentEquippedItem().getItemDamage() == phialMeta)
					{
						int id = 0;
						boolean brewed = false;
						for (int i = 0; i < ModPotions.pots.length; i++)
						{
							if (cauld.ingrs.containsAll(ModPotions.pots[i].ingridients))
							{
								if (p.inventory.mainInventory[p.inventory.currentItem].stackSize >= 3)
								{
									if (!p.capabilities.isCreativeMode)
									{
										p.inventory.mainInventory[p.inventory.currentItem].stackSize -= 3;
									}
									cauld.ingrs.removeAll(ModPotions.pots[i].ingridients);
									id = i;
									brewed = true;
									break;
								}
							}

						}
						if (brewed)
						{
							ItemStack ret = new ItemStack(ModItems.Potion, 3);
							NBTTagCompound tag = new NBTTagCompound();
							tag.setInteger("PotionID", id);
							tag.setInteger("PotionDur", ModPotions.pots[id].duration);

							for (int i = 0; i < cauld.ingrs.size(); i++)
							{
								if (cauld.ingrs.get(i) instanceof Booster)
								{
									tag.setInteger("PotionDur", tag.getInteger("PotionDur") + ((Booster) cauld.ingrs.get(i)).boost);
									cauld.ingrs.remove(i);
								}

								if (cauld.ingrs.get(i) instanceof Prolonger)
								{
									tag.setInteger("PotionAmpl", tag.getInteger("PotionAmpl") + ((Prolonger) cauld.ingrs.get(i)).time);
									cauld.ingrs.remove(i);
								}
							}
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
				else
				{
					if (!p.isSneaking())
					{
						if (w.isRemote)
						{
							p.addChatComponentMessage(new ChatComponentText("Ingidients IDs:"));
							for (int i = 0; i < cauld.ingrs.size(); i++)
								p.addChatComponentMessage(new ChatComponentText(new ItemStack(cauld.ingrs.get(i).item, 1, cauld.ingrs.get(i).meta).getDisplayName() + " : " + cauld.ingrs.get(i).id));
						}
					}
					else
					{
						if (cauld.isBurning)
						{
							cauld.isBurning = !cauld.isBurning;
						}
						else
							cauld.ingrs = new ArrayList<Ingridient>();
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
		this.top = ir.registerIcon("aam:cauldron_top");
		this.side = ir.registerIcon("aam:cauldron_side");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side == ForgeDirection.UP.ordinal() || side == ForgeDirection.DOWN.offsetX) ? this.top : this.side;
	}

}
