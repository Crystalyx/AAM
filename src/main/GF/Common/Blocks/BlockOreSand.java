/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GF.Common.Item.ItemGoldPan;
import GF.Registry.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreSand extends Block {

	public BlockOreSand(int type) 
	{
		super(Material.sand);
		this.fullType=type;
		this.setBlockTextureName("goldflushing:sandore"+Registry.names[type]);
		//this.setCreativeTab(Registry.gftab);
		this.setBlockName(Registry.names[type]);
	    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F*(8-this.meta), 1.0F);
        this.func_150154_b(7);
	}
	
	public int fullType;
	/**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
    	return true;
    }

	
	/**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        this.func_150154_b(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
    }

    protected void func_150154_b(int p_150154_1_)
    {
        int j = 7-(p_150154_1_ & 7);
        float f = (float)(2 * (1 + j)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        int l =7-p_149668_1_.getBlockMetadata(p_149668_2_, p_149668_3_, p_149668_4_) & 7;
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)((float)p_149668_3_ + (float)l * f), (double)p_149668_4_ + this.maxZ);
    }
    
    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return Registry.itemBs[this.fullType-1];
    }
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float px, float py, float pz)
	{
		if(p.getCurrentEquippedItem() != null)
		{
			if(p.getCurrentEquippedItem().getItem() instanceof ItemGoldPan)
			{
				if(!((ItemGoldPan)p.getCurrentEquippedItem().getItem()).isFull())
				{
					if(w.getBlockMetadata(x, y, z)<7)
					{
						w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z)+1, 2);
					}
					else 
					{
						w.setBlock(x, y, z,Blocks.air,0, 2);
					}
					p.inventory.consumeInventoryItem(Registry.goldpanfull[0]);
					p.inventory.addItemStackToInventory(new ItemStack(Registry.goldpanfull[this.fullType]));
					return true;	
				}
			}
		}
		return false;	
	}
	
	public boolean flag = false;
	private int meta=0;
	
	@Override
	public int getBlockColor()
	{
		return Registry.colors[this.fullType-1];	
	}
	@Override
	public int damageDropped(int data)
	{
		this.meta=data;
		return data;		
	}
	
	@Override
	public boolean isNormalCube()
	{
		return true;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
