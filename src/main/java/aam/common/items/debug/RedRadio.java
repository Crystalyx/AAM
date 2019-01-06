package aam.common.items.debug;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class RedRadio extends Item
{
	public RedRadio()
	{
		this.setTextureName("aam:red_radio");
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		if (p.isSneaking())
		{
			if (i.hasTagCompound())
			{
				i.getTagCompound().setInteger("x", x);
				i.getTagCompound().setInteger("y", y);
				i.getTagCompound().setInteger("z", z);
			}
			else
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("x", x);
				tag.setInteger("y", y);
				tag.setInteger("z", z);
				i.setTagCompound(tag);
			}
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

				if (w.getBlock(tx, ty, tz) != null)
				{
					w.getBlock(tx, ty, tz).onBlockActivated(w, tx, ty, tz, p, 0, 0, 0, 0);
				}
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
