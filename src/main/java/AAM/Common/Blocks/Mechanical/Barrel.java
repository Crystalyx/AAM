package aam.common.blocks.mechanical;

import aam.common.items.ModItems;
import aam.common.items.alchemy.AlchemicalConcentrateItem;
import aam.common.potions.ModPotions;
import aam.common.tiles.TEBarrel;
import aam.utils.InventoryUtils;
import aam.utils.Logger;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Barrel extends BlockContainer
{
	public Barrel()
	{
		super(Material.wood);
		this.setBlockTextureName("planks_oak");
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		TileEntity teRaw = w.getTileEntity(x, y, z);
		if (teRaw instanceof TEBarrel)
		{
			TEBarrel te = (TEBarrel) teRaw;
			if (p.getCurrentEquippedItem() != null)
			{
				ItemStack is = p.getCurrentEquippedItem();
				if (is.getItem() == ModItems.AlchemicalBucket && p.getCurrentEquippedItem().getItemDamage() == 1)
				{
					if (is.hasTagCompound())
					{
						te.potion = is.getTagCompound().getIntArray("Potion");
						is.setTagCompound(null);
						is.setItemDamage(0);
					}
				}
				if (is.getItem() == ModItems.RiteBook)
				{
					te.burnTime += 3000;
				}
				if (te.potion.length > 0)
				{
					if (ModPotions.getConcentrateID(te.potion[0]) != -1)
					{
						if (is.getItem() == ModItems.ConcentratePhial)
						{
							int i = is.getItemDamage();
							if (i < 3)
							{
								int[] volumes = new int[] { 1, 3, 9 };

								if (te.volume >= volumes[i])
								{
									Item item = i == 0 ? ModItems.smallConcentrate : i == 1 ? ModItems.mediumConcentrate : ModItems.bigConcentrate;

									ItemStack concentrateIs = new ItemStack(item, 1, ModPotions.getConcentrateID(te.potion[0]));
									NBTTagCompound tag = new NBTTagCompound();
									tag.setInteger("potionLevel", te.potion[1]);
									tag.setInteger("Fluid", AlchemicalConcentrateItem.Volumes[i] + 1);
									concentrateIs.setTagCompound(tag);
									InventoryUtils.dropStackToPlayer(w, x, y, z, concentrateIs, p);
									InventoryUtils.decrPlayerStack(p, 1);
									te.volume -= volumes[i];
								}
							}
							if (te.volume == 0 && te.burnTime == 0)
							{
								te.potion = new int[0];
							}
						}
					}
				}
			}
			else
			{
				if (te.potion.length > 0)
				{
					Logger.chat(p, "");
					if (te.volume <= 0)
					{
						Logger.chat(p, "BurnTime: " + te.burnTime);
					}
					else
					{
						Logger.chat(p, "Volume: " + te.volume);
					}
					Logger.chat(p, "Potion: " + ModPotions.pots[te.potion[0]].name);
					Logger.chat(p, "Power: " + (te.potion[1] + 1));
				}
				else
				{
					Logger.chat(p, "");
					Logger.chat(p, "Empty");
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

	@Override
	public int getRenderType()
	{
		return 139;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TEBarrel();
	}

}
