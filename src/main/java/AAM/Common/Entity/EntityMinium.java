package AAM.Common.Entity;

import java.util.List;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Items.ModItems;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.VectorWorld;
import AAM.Utils.Wec3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
		if (this.inBlood == -1)
		{
			this.setDead();
		}
		super.onUpdate();
		VectorWorld pw = new VectorWorld(this.worldObj);
		Wec3 wp = new Wec3(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
		if (pw.getBlock(wp) == ModBlocks.BloodBlock && pw.getBlockMetadata(wp) == 0)
		{
			if (this.inBlood >= 80)
			{
				int count = this.getEntityItem().stackSize;

				List<EntityMinium> l = this.worldObj.getEntitiesWithinAABB(EntityMinium.class, wp.extend(1.5f));
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

				MiscUtils.dropStack(this.worldObj, wp, is);
				pw.setBlock(wp, Blocks.air);
				this.setDead();
			}
			else
			{
				this.inBlood += 1;
			}
		}
		else
		{
			this.inBlood = 0;
		}
	}

}
