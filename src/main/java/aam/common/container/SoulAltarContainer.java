package aam.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

public class SoulAltarContainer extends ContainerBase
{

	public SoulAltarContainer(InventoryPlayer p, IInventory tile)
	{
		super(p, tile);

		this.addBackground(200, 200);
		this.addText("soul", 194, 94, 68, 10, false);

		this.addSlot(0, 4, tile, 100, 26);

		this.addHiddenSlot(1, tile, 100, 96, 18, 7, false);
		this.addHiddenSlot(2, tile, 100, 72, 18, 5, false);
		this.addHiddenSlot(3, tile, 100, 49, 18, 6, false);

		this.addHiddenSlot(4, tile, 28, 36, 18, 8, false);
		this.addHiddenSlot(5, tile, 28, 61, 18, 8, false);
		this.addHiddenSlot(6, tile, 28, 86, 18, 8, false);

		this.addHiddenSlot(7, tile, 180, 36, 18, 8, false);
		this.addHiddenSlot(8, tile, 180, 61, 18, 8, false);
		this.addHiddenSlot(9, tile, 180, 86, 18, 8, false);

		this.addHiddenText("Owner:", 50, 12, 140, 12, true, false);
		this.addHiddenSlot(10, tile, 76, 72, false);
		this.addHiddenSlot(11, tile, 124, 72, false);

		this.addPlayerSlots();
	}

}
