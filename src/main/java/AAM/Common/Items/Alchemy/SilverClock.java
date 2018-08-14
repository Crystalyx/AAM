package AAM.Common.Items.Alchemy;

import AAM.API.Abstract.MetaUpradableItem;
import AAM.API.Interface.ICatalyst;
import net.minecraft.item.ItemStack;

public class SilverClock extends MetaUpradableItem implements ICatalyst
{
	public SilverClock()
	{
		this.setTextureName("aam:tools/clock");
		this.setUnlocalizedName("aam_alch_clock");
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

}
