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

	public ModificationAnvil()
	{
		super(Material.iron);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
		this.setHardness(2.0F);
		this.setBlockTextureName("aam:null");
	}

	@Override
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
					MiscUtils.dropInventory(w, x, y, z, tile);
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TEModificationAnvil();
	}

	@Override
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
							ItemStack item = p.inventory.decrStackSize(p.inventory.currentItem, 1);
							for (int i = 0; i < tile.getSizeInventory(); i++)
							{
								if (tile.getStackInSlot(i) == null)
								{
									tile.setInventorySlotContents(i, item);
									break;
								}
							}
						}
						else
						{
							if (p.getCurrentEquippedItem().getItem() == ModItems.RiteBook)
							{
								if (!tile.isCrafting)
								{
									tile.startCrafting();
								}
							}
						}
					}
				}
				else
				{
					for (int i = 6; i >= 0; i--)
					{
						if (tile.getStackInSlot(i) != null)
						{
							MiscUtils.addItemStack(p, tile.decrStackSize(i, 1));
							tile.setInventorySlotContents(i, null);
							break;
						}
					}
				}
			}
		return true;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 136;
	}

	@Override
	public boolean isNormalCube()
	{
		return false;
	}
}
