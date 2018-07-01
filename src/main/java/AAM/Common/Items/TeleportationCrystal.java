package AAM.Common.Items;

import java.util.List;

import AAM.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TeleportationCrystal extends Item
{
	public TeleportationCrystal()
	{
		this.setTextureName("aam:altersteel_scale");
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		if (i.hasTagCompound())
		{
			if (!i.getTagCompound().hasKey("x"))
			{
				i.getTagCompound().setInteger("x", x);
				i.getTagCompound().setInteger("y", y);
				i.getTagCompound().setInteger("z", z);
			}
			else
			{
				int tx = i.getTagCompound().getInteger("x");
				int ty = i.getTagCompound().getInteger("y");
				int tz = i.getTagCompound().getInteger("z");

				p.fallDistance = 0F;
				p.setVelocity(0, 0, 0);
				p.setPosition(tx + 0.5, ty + 2.79, tz + 0.5);
				MiscUtils.decrPlayerStack(p, 1);
			}
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("x", x);
			tag.setInteger("y", y);
			tag.setInteger("z", z);
			i.setTagCompound(tag);
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (i.hasTagCompound())
		{
			if (i.getTagCompound().hasKey("x"))
			{
				int tx = i.getTagCompound().getInteger("x");
				int ty = i.getTagCompound().getInteger("y");
				int tz = i.getTagCompound().getInteger("z");

				p.fallDistance = 0F;
				p.setVelocity(0, 0, 0);
				p.setPosition(tx, ty + 3, tz);
				MiscUtils.decrPlayerStack(p, 1);
			}
		}
		return i;
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean hand)
	{
		if (i.hasTagCompound())
		{
			if (i.getTagCompound().hasKey("x"))
			{
				l.add("Bound to:");
				l.add("x: " + i.getTagCompound().getInteger("x"));
				l.add("y: " + i.getTagCompound().getInteger("y"));
				l.add("z: " + i.getTagCompound().getInteger("z"));
			}
		}
	}
}
