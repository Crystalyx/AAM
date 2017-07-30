package AAM.Common.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Structurer extends Item
{
	public Structurer()
	{
		this.setTextureName("aam:structurer");
	}

	@Override
	public boolean hasEffect(ItemStack i)
	{
		return i.getItemDamage() == 1;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int i3, float f0, float f1, float f2)
	{
		{
			if (is != null)
			{
				switch (is.getItemDamage())
				{
				case (0):
				{
					if (is.getTagCompound() != null)
					{
						is.getTagCompound().setInteger("BlockXS", x + 1);
						is.getTagCompound().setInteger("BlockYS", y + 1);
						is.getTagCompound().setInteger("BlockZS", z + 1);
						is.getTagCompound().setInteger("Dim", p.dimension);
					} else
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger("BlockXS", x + 1);
						tag.setInteger("BlockYS", y + 1);
						tag.setInteger("BlockZS", z + 1);
						tag.setInteger("Dim", p.dimension);
						is.setTagCompound(tag);
					}
					is.setItemDamage(1);
				}
				case (1):
				{
					if (is.getTagCompound() != null)
					{
						is.getTagCompound().setInteger("BlockXE", x);
						is.getTagCompound().setInteger("BlockYE", y);
						is.getTagCompound().setInteger("BlockZE", z);
					}
					is.setItemDamage(0);
				}
				}
			}
		}
		return false;
	}
}
