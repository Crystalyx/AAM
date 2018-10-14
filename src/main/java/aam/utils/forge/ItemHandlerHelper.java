package aam.utils.forge;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public class ItemHandlerHelper
{
	@Nonnull
	public static ItemStack insertItem(IItemHandler dest, @Nonnull ItemStack stack, boolean simulate)
	{
		if (dest == null || stack == null)
			return stack;

		for (int i = 0; i < dest.getSlots(); i++)
		{
			stack = dest.insertItem(i, stack, simulate);
			if (stack == null)
			{
				return null;
			}
		}

		return stack;
	}

	public static boolean canItemStacksStack(@Nonnull ItemStack a, @Nonnull ItemStack b)
	{
		if (a == null || !a.isItemEqual(b) || a.hasTagCompound() != b.hasTagCompound())
			return false;

		return (!a.hasTagCompound() || a.getTagCompound().equals(b.getTagCompound()));
	}

	/**
	 * A relaxed version of canItemStacksStack that stacks itemstacks with
	 * different metadata if they don't have subtypes. This usually only applies
	 * when players pick up items.
	 */
	public static boolean canItemStacksStackRelaxed(@Nonnull ItemStack a, @Nonnull ItemStack b)
	{
		if (a == null || b == null || a.getItem() != b.getItem())
			return false;

		if (!a.isStackable())
			return false;

		// Metadata value only matters when the item has subtypes
		// Vanilla stacks non-subtype items with different metadata together
		// e.g. a stick with metadata 0 and a stick with metadata 1 stack
		if (a.getHasSubtypes() && a.getItemDamage() != b.getItemDamage())
			return false;

		if (a.hasTagCompound() != b.hasTagCompound())
			return false;

		return (!a.hasTagCompound() || a.getTagCompound().equals(b.getTagCompound()));
	}

	@Nonnull
	public static ItemStack copyStackWithSize(@Nonnull ItemStack itemStack, int size)
	{
		if (size == 0)
			return null;
		ItemStack copy = itemStack.copy();
		copy.stackSize = size;
		return copy;
	}

	/**
	 * This method uses the standard vanilla algorithm to calculate a comparator
	 * output for how "full" the inventory is. This method is an adaptation of
	 * Container#calcRedstoneFromInventory(IInventory).
	 * 
	 * @param inv
	 *            The inventory handler to test.
	 * @return A redstone value in the range [0,15] representing how "full" this
	 *         inventory is.
	 */
	public static int calcRedstoneFromInventory(IItemHandler inv)
	{
		if (inv == null)
		{
			return 0;
		}
		else
		{
			int itemsFound = 0;
			float proportion = 0.0F;

			for (int j = 0; j < inv.getSlots(); ++j)
			{
				ItemStack itemstack = inv.getStackInSlot(j);

				if (itemstack != null)
				{
					proportion += (float) itemstack.stackSize / Math.min(inv.getSlotLimit(j), itemstack.getMaxStackSize());
					++itemsFound;
				}
			}

			proportion = proportion / inv.getSlots();
			return (int) (Math.floor(proportion * 14.0F) + (itemsFound > 0 ? 1 : 0));
		}
	}
}
