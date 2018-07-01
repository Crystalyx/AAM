package AAM.Common.Items;

import java.util.List;

import AAM.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LinkObol extends Item
{
	public LinkObol()
	{
		this.setTextureName("aam:linkobol");
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		return i;
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean hand)
	{
	}
}
