package aam.common.entity;

import java.util.List;

import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import aam.utils.InventoryUtils;
import aam.utils.vectors.VectorWorld;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMinium extends EntityItem
{

	public EntityMinium(World w, Wec3 wp, ItemStack is)
	{
		super(w, wp.x, wp.y, wp.z, is);
	}

	public int inBlood = 0;

	@Override
	public void onUpdate()
	{
		if (inBlood == -1)
		{
			this.setDead();
		}
		super.onUpdate();
		VectorWorld pw = new VectorWorld(worldObj);
		Wec3 wp = new Wec3(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
		if (pw.getBlock(wp) == ModBlocks.BloodBlock && pw.getBlockMetadata(wp) == 0)
		{
			if (inBlood >= 80)
			{
				int count = this.getEntityItem().stackSize;

				List<EntityMinium> l = worldObj.getEntitiesWithinAABB(EntityMinium.class, wp.extend(1.5f));
				l.remove(this);
				for (EntityMinium em : l)
				{
					count += em.getEntityItem().stackSize;
					em.inBlood = -1;
				}

				ItemStack is = new ItemStack(ModItems.MiniumStone);
				NBTTagCompound tg = new NBTTagCompound();
				tg.setInteger("TrLast", count * 4);
				is.setTagCompound(tg);

				InventoryUtils.dropStack(worldObj, wp, is);
				pw.setBlock(wp, Blocks.air);
				this.setDead();
			}
			else
			{
				inBlood += 1;
			}
		}
		else
		{
			inBlood = 0;
		}
	}

}
