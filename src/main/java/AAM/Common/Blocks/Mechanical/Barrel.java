package AAM.Common.Blocks.Mechanical;

import AAM.Common.Items.ModItems;
import AAM.Common.Potions.ModPotions;
import AAM.Common.Tiles.TEBarrel;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
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
				if (te.burnTime >= 300)
				{
					if (te.potion.length > 0)
					{
						if (ModPotions.getConcentrateID(te.potion[0]) != -1)
						{
							if (is.getItem() == ModItems.ConcentratePhial)
							{
								int i = is.getItemDamage();
								if (i < 3)
								{
									Item item = i == 0 ? ModItems.smallConcentrate : (i == 1 ? ModItems.mediumConcentrate : ModItems.bigConcentrate);
									int count = 5 - 2 * i;
									if (is.stackSize >= count)
									{
										ItemStack concentrateIs = new ItemStack(item, count, ModPotions.getConcentrateID(te.potion[0]));
										NBTTagCompound tag = new NBTTagCompound();
										tag.setInteger("potionLevel", te.potion[1]);
										concentrateIs.setTagCompound(tag);
										MiscUtils.dropStackToPlayer(w, x, y, z, concentrateIs, p);
										MiscUtils.decrPlayerStack(p, count);
										te.potion = new int[0];
									}
								}
							}
						}
					}
				}
			}
			else
			{
				if (te.potion.length > 0)
					Logger.mchat(p, te.potion[0], te.potion[1], te.potion[2]);
				Logger.chat(p, te.burnTime);
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
