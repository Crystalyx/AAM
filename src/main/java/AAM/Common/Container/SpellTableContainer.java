package aam.common.container;

import aam.common.tiles.TESpellTable;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.player.InventoryPlayer;

public class SpellTableContainer extends ContainerBase
{
	public TESpellTable tile;
	public InventoryPlayer p;

	public SpellTableContainer(InventoryPlayer p, TESpellTable tile)
	{
		super(p, tile);
		this.tile = tile;
		this.p = p;

		this.addBackground(234, 234);

		PlayerDataHandler ph = PlayerDataHandler.get(p.player);
		this.addText("Soul", 194, 94, 48, 10, false);

		this.addSlot(0, 0, tile, 60, 64);
		this.addSlot(1, 1, tile, 131, 64);

		this.addPlayerSlots();
	}

}
