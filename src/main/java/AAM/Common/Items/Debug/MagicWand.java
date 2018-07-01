
package AAM.Common.Items.Debug;

import AAM.Common.Blocks.Circles.TransCircle;
import AAM.Common.Tiles.TETransCircle;
import AAM.Utils.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagicWand extends Item
{
	public MagicWand()
	{
		this.setTextureName("aam:tools/nullfier");
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		return is;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float hx, float hy, float hz)
	{

		if (w.getTileEntity(x, y, z) != null)
		{
			if (w.getTileEntity(x, y, z) instanceof TETransCircle)
			{
				TETransCircle te = (TETransCircle) w.getTileEntity(x, y, z);
				Logger.info(TransCircle.outputCircle(te.circle));
			}
		}
		return true;
	}
}
