package PathMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.List;

public class Guitar extends Item
{
	public Guitar()
	{
		this.setTextureName("pathmod:guitar");
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		p.setItemInUse(is, Integer.MAX_VALUE);
		return super.onItemRightClick(is, w, p);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer p, int time)
	{
		double x = p.posX;
		double y = p.posY;
		double z = p.posZ;
		List<EntityAnimal> lst = p.worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.getBoundingBox(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10));
		for (EntityAnimal e : lst)
		{
			if (!e.isInLove())
			{
				try
				{
					Field love = EntityAnimal.class.getDeclaredField("love");
					love.setAccessible(true);
					love.set(e, 600);
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.block;
	}

}
