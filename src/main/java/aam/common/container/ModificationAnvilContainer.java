package aam.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

public class ModificationAnvilContainer extends ContainerBase
{

	public ModificationAnvilContainer(InventoryPlayer p, IInventory tile)
	{
		super(p, tile);

		this.addBackground(200, 200);

		this.addSlot(0, tile, 48, 56, 18, 4);// swordType
		this.addSlot(1, tile, 104, 90, 18, 10);// catalyst
		this.addSlot(2, tile, 104, 23, 18, 9);// hammer
		this.addSlot(3, tile, 170, 56, 18);// result
		this.addSlot(4, tile, 28, 56, 18);// item

		this.addPlayerSlots();
	}

}
