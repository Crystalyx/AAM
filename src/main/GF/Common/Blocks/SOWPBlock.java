/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Blocks;

import GF.Common.Item.SandOreBucket;
import GF.Common.Tile.SOWPTile;
import GF.Registry.Registry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SOWPBlock extends BlockContainer
{

	public SOWPBlock()
	{
		super(Material.wood);
		this.setCreativeTab(Registry.gftab);
		this.setBlockName("GFSOWP");
		this.setBlockTextureName("goldflushing:null");
	}

	public boolean isNormalCube()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new SOWPTile();
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() instanceof SandOreBucket)
			{
				int i = Registry.getType(p.getCurrentEquippedItem());
				if (p.getCurrentEquippedItem().getItemDamage() < 7)
				{
					if (p.getCurrentEquippedItem().stackSize > 1)
					{
						p.getCurrentEquippedItem().stackSize -= 1;
						p.inventory.addItemStackToInventory(new ItemStack(p.getCurrentEquippedItem().getItem(), 1, p.getCurrentEquippedItem().getItemDamage() + 1));
					} else
						p.getCurrentEquippedItem().setItemDamage(p.getCurrentEquippedItem().getItemDamage() + 1);
				} else
					p.getCurrentEquippedItem().stackSize -= 1;
				if (!w.isRemote)
				{
					EntityItem it = new EntityItem(w, x, y, z, new ItemStack(Registry.dusts[i]));
					w.spawnEntityInWorld(it);
					it.onCollideWithPlayer(p);
				}
			}
		}
		return true;
	}
	
	//This will tell minecraft not to render any side of our cube.
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	   return false;
	}

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack i)
	{
		 int dir = MathHelper.floor_double((double)((p.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		 w.setBlockMetadataWithNotify(x, y, z, dir, 0);
	}
}
