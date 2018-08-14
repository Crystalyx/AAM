/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Blocks.Mechanical;

import AAM.Common.Tiles.TEModificationAnvil;
import AAM.Core.AAMCore;
import AAM.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Lord_Crystalyx
 */
public class ModificationAnvil extends BlockContainer
{

	public ModificationAnvil()
	{
		super(Material.iron);
		this.setHardness(2.0F);
	}

	@Override
	public void breakBlock(World w, int x, int y, int z, Block b, int meta)
	{
		EntityItem entity;
		if (w.getTileEntity(x, y, z) instanceof TEModificationAnvil)
		{
			TEModificationAnvil tile = (TEModificationAnvil) w.getTileEntity(x, y, z);
			MiscUtils.dropInventory(w, x, y, z, tile);
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
		p.openGui(AAMCore.instance, 2, w, x, y, z);
		return true;
	}

	public IIcon side;
	public IIcon top;

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.side = ir.registerIcon("aam:anvil_base");
		this.top = ir.registerIcon("aam:anvil_top");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side == ForgeDirection.UP.ordinal() ? this.top : this.side;
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
