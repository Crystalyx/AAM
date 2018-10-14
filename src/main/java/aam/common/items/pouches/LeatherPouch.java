package aam.common.items.pouches;

import aam.api.abstraction.Pouch;
import aam.core.AAMCore;
import aam.utils.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LeatherPouch extends Pouch
{
	public LeatherPouch()
	{
		this.setUnlocalizedName("aam.pouch.leather");
		this.setTextureName("aam:pouches/leather_pouch");
	}

	@Override
	public int getSizeInventory()
	{
		return 18;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		Logger.info("item: " + (is.hasTagCompound() ? is.getTagCompound().toString() : "null"));
		p.openGui(AAMCore.instance, 3, w, (int) p.posX, (int) p.posY, (int) p.posZ);
		return is;
	}

}
