
package AAM.Common.Items.Debug;

import java.lang.reflect.Field;

import AAM.Common.Blocks.Circles.TransCircle;
import AAM.Common.Event.SoulEvent;
import AAM.Common.Tiles.TETransCircle;
import AAM.Utils.Logger;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
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
		// PlayerDataHandler.get(p).clearProperties(false);
		ItemStack bs = p.inventory.getStackInSlot(Math.min(p.inventory.currentItem + 1, 8));
		if (bs != null)
		{
			Block b = null;
			int meta = 0;
			if (bs.getItem() instanceof ItemBlock)
			{
				b = ((ItemBlock) bs.getItem()).field_150939_a;
				meta = bs.getItemDamage();
			}
			if (bs.getItem() instanceof ItemBucket)
			{
				try
				{
					Class clazz = ItemBucket.class;
					Field f = clazz.getDeclaredField("isFull");
					f.setAccessible(true);
					b = (Block) f.get((bs.getItem()));
					meta = bs.getItemDamage();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			if (!w.isRemote)
			{
				MovingObjectPosition mop = SoulEvent.getMouseOverExtended(80);
				if (mop.typeOfHit.equals(MovingObjectType.BLOCK))
				{
					if (b != null)
					{
						int r = is.getItemDamage();
						for (int i = -r; i <= r; i++)
						{
							for (int j = -r; j <= r; j++)
							{
								for (int k = -r; k <= r; k++)
								{
									w.setBlock(mop.blockX + i, mop.blockY + j, mop.blockZ + k, b, meta, 2);
								}
							}
						}
					}
				}
				else
				{
					if (p.isSneaking())
					{
						is.setItemDamage(Math.max(is.getItemDamage() - 1, 0));
					}
					else
					{
						is.setItemDamage(Math.min(is.getItemDamage() + 1, 16));
					}
				}
			}
		}
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
