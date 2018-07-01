package AAM.Common.Container;

import AAM.Client.Gui.Base.GuiBar;
import AAM.Common.Tiles.TESoulAltar;
import AAM.Utils.Color;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

public class SoulAltarContainer extends ContainerBase
{

	public SoulAltarContainer(InventoryPlayer p, IInventory tile)
	{
		super(p, tile);

		this.addBackground(234, 234);
		this.addText("Soul", 194, 94, 68, 10, false);
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
				return (int) (((TESoulAltar) this.gui.cont.tile).value / ((TESoulAltar) this.gui.cont.tile).maxValue * this.sizey);
			}
		};
		this.add(soul);

		this.addSlot(0, 4, tile, 100, 10);

		this.addHiddenSlot(1, tile, 70, 70, true);
		this.addHiddenSlot(2, tile, 100, 80, true);
		this.addHiddenSlot(3, tile, 130, 70, true);

		this.addHiddenSlot(4, tile, 10, 20, true);
		this.addHiddenSlot(5, tile, 10, 45, true);
		this.addHiddenSlot(6, tile, 10, 70, true);

		this.addHiddenSlot(7, tile, 190, 20, true);
		this.addHiddenSlot(8, tile, 190, 45, true);
		this.addHiddenSlot(9, tile, 190, 70, true);

		this.addHiddenText("Owner:", 50, -8, 140, 12, true, true);
		this.addHiddenSlot(10, tile, 130, 30, true);

		this.addPlayerSlots();
	}

}
