package aam.common.blocks.circles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import aam.common.tiles.TETransCircle;
import aam.common.tiles.TETransCircle.State;
import aam.common.transmutations.CircleUtils;
import aam.common.transmutations.EnergyProvider;
import aam.core.AAMCore;
import aam.utils.InventoryUtils;
import aam.utils.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TransCircle extends BlockContainer
{

	public TransCircle()
	{
		super(Material.circuits);
		this.setBlockTextureName("aam:clear_block");
		this.setResistance(100000000F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.08F, 1.0F);
		this.setLightLevel(1F);
	}

	public List<Item> blacklist = new ArrayList<>();

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	{
		if (w.getBlock(x, y, z) == ModBlocks.TransCircle)
		{
			int meta = w.getBlockMetadata(x, y, z);
			if (meta == 1)
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.08F, 1.0F);
			}
			if (meta == 0)
			{
				this.setBlockBounds(0.0F, 1.0F - 0.08F, 0.0F, 1.0F, 1, 1.0F);
			}
			if (meta == 3)
			{
				this.setBlockBounds(0.0F, 0, 0.0F, 1.0F, 1, 0.08F);
			}
			if (meta == 2)
			{
				this.setBlockBounds(0.0F, 0, 1.0F - 0.08F, 1.0F, 1, 1.0F);
			}
			if (meta == 5)
			{
				this.setBlockBounds(0.0F, 0, 0.0F, 0.08F, 1, 1.0F);
			}
			if (meta == 4)
			{
				this.setBlockBounds(1.0F - 0.08F, 0, 0.0f, 1.0F, 1, 1.0F);
			}
		}
	}

	@Override
	public void breakBlock(World w, int x, int y, int z, Block b, int meta)
	{
		super.breakBlock(w, x, y, z, b, meta);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return null;
	}

	@Override
	public float getExplosionResistance(Entity exp)
	{
		return Float.MAX_VALUE / 4;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TETransCircle();
	}

	public static final int range = 32;

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		TETransCircle te = (TETransCircle) w.getTileEntity(x, y, z);
		te.check();
		te.alchemist = p;
		// outputCircle(te.circle);
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() == ModItems.ItemChalk)
			{
				AAMCore.proxy.openCircleGui(x, y, z);
				return true;
			}
			if (p.getCurrentEquippedItem().getItem() == ModItems.AlchPaper && p.getCurrentEquippedItem().getItemDamage() == 0)
			{
				CircleUtils.handleAlchPaperTranscribing(p, p.getCurrentEquippedItem(), te);
				return true;
			}
			if (p.getCurrentEquippedItem().getItem() == ModItems.RiteBook)
			{
				Logger.mchat(p, te.energy, te.energyType);
				return true;
			}
			if (InventoryUtils.contains(p.inventory, ModItems.ItemChalk))
			{
				InventoryUtils.getStack(p.inventory, ModItems.ItemChalk).damageItem(1, p);

				if (p.getCurrentEquippedItem().getItem() == ModItems.AlchPaper)
				{
					CircleUtils.handleAlchPaperPlacing(p, p.getCurrentEquippedItem(), te);
				}
			}
			if (p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() == ModItems.LinkObol)
			{
				if (!te.isLink)
				{
					te.isLink = true;
					InventoryUtils.decrPlayerStack(p, 1);
				}
				else
				{
					te.isLink = false;
					InventoryUtils.addItemStack(p, new ItemStack(ModItems.LinkObol));
				}
				return true;
			}
			if (te.is == null)
			{
				if (p.getCurrentEquippedItem() != null && EnergyProvider.hasValue(p.getCurrentEquippedItem()) && !blacklist.contains(p.getCurrentEquippedItem().getItem()))
				{
					te.is = p.inventory.decrStackSize(p.inventory.currentItem, 1);
				}
				return true;
			}
		}
		else
		{
			if (!p.isSneaking())
			{
				if (te.state.equals(State.idle))
				{
					te.state = State.active;
					if (te.isLink)
					{
						CircleUtils.handleObolBehaviour(w, p, x, y, z, te);
					}
				}
				if (te.state.equals(State.complete))
				{
					te.state = State.idle;
					te.completeTimer = 0;
				}
			}
			else
			{
				if (te.is != null)
				{
					EntityItem ei = new EntityItem(w, x, y + 0.5, z, te.is);
					ei.onCollideWithPlayer(p);
					te.is = null;
				}
				else
				{
					CircleUtils.clearEnergy(te);
				}
			}
		}
		return true;
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

}
