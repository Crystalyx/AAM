package aam.common.items.alchemy;

import aam.api.interfaces.ICatalyst;
import aam.api.abstraction.MetaUpradableItem;
import net.minecraft.item.ItemStack;

public class SilverClock extends MetaUpradableItem implements ICatalyst
{
	public SilverClock()
	{
		this.setTextureName("aam:tools/clock");
		this.setUnlocalizedName("aam.alch_clock");
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
	}

	@Override
	public int getPotency(ItemStack i)
	{
		return this.getUpgradeLevel(i) + 1;
	}

	@Override
	public int getMaxLevel(ItemStack is)
	{
		return 4;
	}

	@Override
	public String getItemIcon(ItemStack is)
	{
		return "aam:tools/clock";
	}

	@Override
	public int getMinSlotCount(ItemStack is)
	{
		return 0;
	}

	@Override
	public int getMaxSlotCount(ItemStack is)
	{
		return 2;
	}

	@Override
	public int getDurability(ItemStack is)
	{
		return 0;
	}

	@Override
	public int getMaxRepairCount(ItemStack is)
	{
		return 0;
	}

}
