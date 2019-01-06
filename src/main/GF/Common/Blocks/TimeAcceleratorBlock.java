/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Blocks;

import GF.Common.Tile.TileEntityAccelerator;
import GF.Registry.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class TimeAcceleratorBlock extends BlockContainer
{
	
	public TimeAcceleratorBlock()
	{
	    super(Material.anvil);
	    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	    this.setTickRandomly(true);
	    this.setCreativeTab(Registry.gftab);
	    this.setBlockName("TimeAccelerator2.0");
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }
	
	public static IIcon animate;
	
    @SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
			return animate;		
	}
    @SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iir)
	{
		animate = iir.registerIcon("rogueWorld:space");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) 
	{
		return new TileEntityAccelerator();
	}

}
