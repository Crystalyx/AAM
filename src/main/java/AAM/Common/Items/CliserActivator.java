package AAM.Common.Items;

import AAM.Common.Blocks.ModBlocks;
import AAM.Utils.Logger;
import AAM.Utils.Structures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CliserActivator extends Item
{
	public CliserActivator()
	{
		this.setTextureName("aam:cliser_activator");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		// ResourceLocation rl = new
		// ResourceLocation("aam:structures/elemental/bossroom.str");

		return super.onItemRightClick(i, w, p);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (w.getBlock(x, y, z) == ModBlocks.TransCircle)
		{
			int meta = w.getBlockMetadata(x, y, z);
			if (meta == 0)
			{
				w.setBlockMetadataWithNotify(x, y, z, 1, 2);
			}
			else
			{
				w.setBlockMetadataWithNotify(x, y, z, 0, 2);
			}
		}
		return true;
	}
}
