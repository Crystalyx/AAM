package aam.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

public class ContainerTeleporter extends ContainerBase
{
	public ContainerTeleporter(InventoryPlayer p, IInventory tile)
	{
		super(p, tile);
		this.addBackground(234, 234);
		this.addPlayerSlots();
	}

}
