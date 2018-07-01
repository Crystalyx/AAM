package AAM.Common.Container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ContainerInspectionTerm extends Container
{
	public ContainerInspectionTerm(InventoryPlayer p, IInventory tile)
	{
		// this.addBackground(234, 254);

		// this.addSlot(tile.getMultiSizeInventory(), tile, 100, 10);

		// for (int i = 0; i < tile.getMultiSizeInventory(); i++)
		// {
		// int n = Math.floorDiv(i, 9);
		// int m = i % 9;
		// this.addSlot(i, tile, 28 + m * 18, 32 + n * 18);
		// }
		//
		// this.addPlayerSlots(0, 30);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
	{
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

}
