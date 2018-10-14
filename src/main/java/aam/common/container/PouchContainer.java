package aam.common.container;

import aam.api.PouchInventory;
import net.minecraft.entity.player.InventoryPlayer;

public class PouchContainer extends ContainerBase
{
	public PouchContainer(InventoryPlayer p, PouchInventory tile)
	{
		super(p, tile);

		this.addBackground(200, 200);

		int size = tile.getSizeInventory();

		for (int i = 0; i < size; i++)
		{
			int x = Math.floorMod(i, 9);
			int y = Math.floorDiv(i, 9);

			addSlot(i, tile, 32 + x * 18, 40 + y * 18);
		}

		this.addPlayerSlots();
	}

}
