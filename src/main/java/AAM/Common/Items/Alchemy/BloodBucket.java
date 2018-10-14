package aam.common.items.alchemy;

import aam.common.blocks.building.ModBlocks;
import aam.utils.vectors.Wec3;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BloodBucket extends ItemBucket
{

	public BloodBucket()
	{
		super(ModBlocks.BloodBlock);
		this.setTextureName("aam:tools/bucket_blood");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;

		if (mop.typeOfHit.equals(MovingObjectType.BLOCK))
		{
			Wec3 v = new Wec3(mop);
			v = v.add(ForgeDirection.getOrientation(mop.sideHit));

			w.setBlock(v.ix, v.iy, v.iz, ModBlocks.BloodBlock, 0, 3);

			if (!p.capabilities.isCreativeMode)
			{
				return new ItemStack(Items.bucket);
			}
		}
		return is;
	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
	}

}
