package aam.common.items.alchemy;

import java.util.List;

import aam.common.potions.ModPotions;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class AlchemicalBucket extends Item
{
	public AlchemicalBucket()
	{
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("aam.alchemical_bucket");
	}

	public IIcon empty;
	public IIcon full;

	@Override
	public void registerIcons(IIconRegister ir)
	{
		empty = ir.registerIcon("aam:tools/bucketpot_empty");
		full = ir.registerIcon("aam:tools/bucketpot_full");
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return meta == 0 ? empty : full;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean hand)
	{
		super.addInformation(is, p, l, hand);
		if (is.hasTagCompound())
		{
			NBTTagCompound tag = is.getTagCompound();
			int[] pot = tag.getIntArray("Potion");
			if (pot.length > 0)
			{
				l.add("Potion: " + ModPotions.pots[pot[0]]);
				l.add("Power: " + ModPotions.pots[pot[1]]);
				l.add("Duration: " + ModPotions.pots[pot[2]]);
			}
		}
	}

}
