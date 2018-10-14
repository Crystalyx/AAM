package aam.utils.forge;

import net.minecraft.item.ItemStack;

public interface IItemHandler
{
	/**
	 * Returns the number of slots available
	 *
	 * @return The number of slots available
	 **/
	int getSlots();

	/**
	 * Returns the ItemStack in a given slot.
	 *
	 * The result's stack size may be greater than the itemstacks max size.
	 *
	 * If the result is empty, then the slot is empty.
	 *
	 * <p/>
	 * IMPORTANT: This ItemStack MUST NOT be modified. This method is not for
	 * altering an inventories contents. Any implementers who are able to detect
	 * modification through this method should throw an exception.
	 * <p/>
	 * SERIOUSLY: DO NOT MODIFY THE RETURNED ITEMSTACK
	 *
	 * @param slot
	 *            Slot to query
	 * @return ItemStack in given slot. Empty Itemstack if the slot is empty.
	 **/
	ItemStack getStackInSlot(int slot);

	/**
	 * Inserts an ItemStack into the given slot and return the remainder. The
	 * ItemStack should not be modified in this function! Note: This behaviour
	 * is subtly different from IFluidHandlers.fill()
	 *
	 * @param slot
	 *            Slot to insert into.
	 * @param stack
	 *            ItemStack to insert. This must not be modified by the item
	 *            handler.
	 * @param simulate
	 *            If true, the insertion is only simulated
	 * @return The remaining ItemStack that was not inserted (if the entire
	 *         stack is accepted, then return an empty ItemStack). May be the
	 *         same as the input ItemStack if unchanged, otherwise a new
	 *         ItemStack. The returned ItemStack can be safely modified after.
	 **/
	ItemStack insertItem(int slot, ItemStack stack, boolean simulate);

	/**
	 * Extracts an ItemStack from the given slot. The returned value must be
	 * empty if nothing is extracted, otherwise it's stack size must less than
	 * than amount and {@link ItemStack#getMaxStackSize()}.
	 *
	 * @param slot
	 *            Slot to extract from.
	 * @param amount
	 *            Amount to extract (may be greater than the current stacks max
	 *            limit)
	 * @param simulate
	 *            If true, the extraction is only simulated
	 * @return ItemStack extracted from the slot, must be empty if nothing can
	 *         be extracted. The returned ItemStack can be safely modified
	 *         after, so item handlers should return a new or copied stack.
	 **/
	ItemStack extractItem(int slot, int amount, boolean simulate);

	/**
	 * Retrieves the maximum stack size allowed to exist in the given slot.
	 *
	 * @param slot
	 *            Slot to query.
	 * @return The maximum stack size allowed in the slot.
	 */
	int getSlotLimit(int slot);
}
