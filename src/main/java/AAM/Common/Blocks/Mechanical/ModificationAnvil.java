/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Blocks.Mechanical;

import AAM.Common.Items.ModItems;
import AAM.Common.Tiles.TEModificationAnvil;
import AAM.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class ModificationAnvil extends BlockContainer
{

	public ModificationAnvil(Material mat)
	{
		super(mat);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
		this.setHardness(2.0F);
		this.setBlockTextureName("aam:null");
	}

	public void breakBlock(World w, int x, int y, int z, Block b, int meta)
	{
		EntityItem entity;
		if (w.getTileEntity(x, y, z) instanceof TEModificationAnvil)
		{
			TEModificationAnvil tile = (TEModificationAnvil) w.getTileEntity(x, y, z);
			for (int i = 0; i < 7; i++)
			{
				if (tile.inventory[i] != null)
				{
					entity = new EntityItem(w, x, y, z, tile.inventory[i]);
					entity.motionX = w.rand.nextDouble() / 2;
					entity.motionY = w.rand.nextDouble() / 2;
					entity.motionZ = w.rand.nextDouble() / 2;
					w.spawnEntityInWorld(entity);
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TEModificationAnvil();
	}

	public boolean isCursed = false;

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int p_149727_6_, float px, float py, float pz)
	{
		if (w.getTileEntity(x, y, z) != null)
			if (w.getTileEntity(x, y, z) instanceof TEModificationAnvil)
			{
				TEModificationAnvil tile = (TEModificationAnvil) w.getTileEntity(x, y, z);
				if (!p.isSneaking())
				{
					if (p.getCurrentEquippedItem() != null)
					{
						if (p.getCurrentEquippedItem().getItem() != ModItems.RiteBook)
						{
							ItemStack item = p.getCurrentEquippedItem().copy();
							item.stackSize = 1;
							for (int i = 0; i < 7; i++)
							{
								if (tile.getStackInSlot(i) == null)
								{
									tile.setInventorySlotContents(i, item);
									p.inventory.decrStackSize(p.inventory.currentItem, 1);
									break;
								}
							}
						}
						else
						{
							if (p.getCurrentEquippedItem().getItem() == ModItems.RiteBook)
							{
								// if (!w.isRemote)
								{
									if (!tile.isCrafting)
									{
										tile.startCrafting();
									}
								}
							}
						}
					}
				}
				else
				{
					if (p.isSneaking())
					{
						for (int i = 6; i >= 0; i--)
						{
							if (tile.getStackInSlot(i) != null)
							{
								MiscUtils.addItemStack(p, tile.getStackInSlot(i));
								tile.setInventorySlotContents(i, null);
								break;
							}
						}
					}
				}
				if (p.getCurrentEquippedItem() != null)
				{
					if (p.getCurrentEquippedItem().getItem() != ModItems.RiteBook)
					{
						this.isCursed = true;
					}
				}

			}
		return true;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public int getRenderType()
	{
		return 136;
	}

	public boolean isNormalCube()
	{
		return false;
	}
}
