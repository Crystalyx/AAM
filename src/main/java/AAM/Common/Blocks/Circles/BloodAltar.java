package AAM.Common.Blocks.Circles;

import java.util.Random;

import AAM.Common.Items.ModItems;
import AAM.Common.Tiles.TEBloodAltar;
import AAM.Utils.MiscUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BloodAltar extends TransCircle
{
	public BloodAltar()
	{
		this.blacklist.add(Items.bucket);
		this.setBlockBounds(0, 0, 0, 1, 1, 1);
		this.setLightLevel(1F);
		this.setHardness(4.0f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
	{
		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}

	@Override
	public int getRenderType()
	{
		return 137;
	}

	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float fx, float fy, float fz, int meta)
	{
		return side;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TEBloodAltar();
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() == Items.bucket)
			{
				TEBloodAltar te = (TEBloodAltar) w.getTileEntity(x, y, z);
				if (te.blood.amount >= 1000)
				{
					te.blood.amount -= 1000;
					MiscUtils.decrPlayerStack(p, 1);
					MiscUtils.addItemStack(p, new ItemStack(ModItems.BloodBucket));
					return true;
				}
			}
		}
		return super.onBlockActivated(w, x, y, z, p, side, px, py, pz);
	}

	public IIcon top;
	public IIcon side;

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.top = ir.registerIcon("aam:altar_top");
		this.side = ir.registerIcon("aam:altar_side");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? this.top : this.side;
	}
}
