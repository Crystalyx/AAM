package aam.common.recipes;

import aam.api.AnvilRecipe;
import aam.common.items.AnvilHammer;
import aam.common.items.ModItems;
import aam.common.items.alchemy.PhilosophersStone;
import aam.common.tiles.TEModificationAnvil;
import aam.common.transmutations.EnergyProvider;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PhiloMerge extends AnvilRecipe
{

	@Override
	public boolean matches(TEModificationAnvil ic, World w)
	{
		ItemStack upg = ic.getStackInSlot(4);
		boolean b = upg != null;
		b = b && ic.getStackInSlot(2) != null;
		b = b && ic.getStackInSlot(1) != null;
		b = b && ic.getStackInSlot(0) != null;
		if (!b)
		{
			return false;
		}
		b = b && ic.getStackInSlot(0).getItem() == ModItems.PhilosophersStone;
		b = b && ic.getStackInSlot(4).getItem() == ModItems.PhilosophersStone;
		b = b && ic.getStackInSlot(1).getItem() == ModItems.BloodBucket;
		b = b && ic.getStackInSlot(2).getItem() == ModItems.AnvilHammer;
		b = b && ic.getStackInSlot(3) == null;
		return b;
	}

	@Override
	public ItemStack getCraftingResult(TEModificationAnvil ic)
	{
		return null;
	}

	@Override
	public void onCrafted(TEModificationAnvil ic)
	{
		double b = EnergyProvider.getFullEnergy(ic.getStackInSlot(4));
		double bexcess = ((PhilosophersStone) ModItems.PhilosophersStone).chargeBypass(ic.getStackInSlot(0), b);
		ItemStack aIs = ic.getStackInSlot(0);
		ic.setInventorySlotContents(3, aIs);
		((PhilosophersStone) ModItems.PhilosophersStone).dischargeBypass(ic.getStackInSlot(4), b - bexcess);
		ic.decrStackSize(0, 1);
		if (bexcess <= 1)
		{
			ic.decrStackSize(4, 1);
		}
		ic.setInventorySlotContents(1, new ItemStack(Items.bucket));
		int metadata = ic.getStackInSlot(2).getItemDamage() + Math.max(0, 10 - ((AnvilHammer) ModItems.AnvilHammer).getUpgradeLevel(ic.getStackInSlot(2)));
		if (metadata >= 450)
		{
			ic.setInventorySlotContents(2, null);
		}
	}

}
