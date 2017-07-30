package AAM.Common.Container;

import AAM.Client.Gui.Base.GuiBar;
import AAM.Common.Tiles.TileSpellTable;
import AAM.Utils.Color;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

public class SpellTableContainer extends ContainerBase
{
	public TileSpellTable tile;
	public InventoryPlayer p;

	public SpellTableContainer(InventoryPlayer p, TileSpellTable tile)
	{
		super(p, tile);
		this.tile = tile;
		this.p = p;

		this.addBackground(234, 234);

		PlayerDataHandler ph = PlayerDataHandler.get(p.player);
		this.addText("Soul", 194, 94, 48, 10, false);
		GuiBar soul = new GuiBar(199, 124, 20, 82, 1, new Color(53, 143, 255))
		{

			@Override
			public void updBar(int value, int maxValue)
			{
				super.updBar(value, maxValue);
			}

			@Override
			public int getLength()
			{
				PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
				return (int) (ph.getCurrentSoul() / (double) ph.getMaxSoul() * 82);
			}
		};
		this.add(soul);

		this.addSlot(0, 0, tile, 60, 64);
		this.addSlot(1, 1, tile, 131, 64);

		this.addPlayerSlots();
	}

}
