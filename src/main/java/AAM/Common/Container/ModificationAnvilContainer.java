package AAM.Common.Container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

public class ModificationAnvilContainer extends ContainerBase
{

	public ModificationAnvilContainer(InventoryPlayer p, IInventory tile)
	{
		super(p, tile);

		this.addBackground(234, 234);

		this.addSlot(0, tile, 30, 56, 18, 4);// sword
		this.addSlot(1, tile, 100, 90, 18, 10);// catalyst
		this.addSlot(2, tile, 100, 23, 18, 9);// hammer
		this.addSlot(3, tile, 170, 56, 18);// result

		this.addPlayerSlots();
	}

}
