package aam.common.container;

import aam.common.tiles.TEMechanistsTable;
import net.minecraft.entity.player.InventoryPlayer;

public class MechanistsTableContainer extends ContainerBase
{
	public TEMechanistsTable tile;

	public MechanistsTableContainer(InventoryPlayer p, TEMechanistsTable tile)
	{
		super(p, tile);
		this.tile = tile;

		this.addBackground(200, 220);

		this.addSlot(0, tile, 50, 60);
		this.addSlot(1, tile, 50, 30);
		this.addSlot(2, tile, 76, 45);
		this.addSlot(3, tile, 76, 75);
		this.addSlot(4, tile, 50, 90);
		this.addSlot(5, tile, 24, 75);
		this.addSlot(6, tile, 24, 45);

		this.addSlot(9, 4, tile, 180, 60);

		this.addPlayerSlots(17, 4);
	}

}
