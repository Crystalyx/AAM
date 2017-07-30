package AAM.Common.Container;

import AAM.Client.Gui.Base.BarSlag;
import AAM.Client.Gui.Base.GuiBar;
import AAM.Client.Gui.Base.GuiPicture;
import AAM.Common.Tiles.TileSoulAltar;
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
				return (int) (((TileSoulAltar) this.gui.cont.tile).value / ((TileSoulAltar) this.gui.cont.tile).maxValue * this.sizey);
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
		this.add(new BarSlag(150, 28, 20, 82, 1, 97, 9, 255).setHidden(true));

		GuiPicture gp = new GuiPicture(116, 8, 32, 32, 4, new Color());
		// gp.setHidden(true);
		gp.scale = 0.35f;
		this.add(gp);

		GuiPicture gp1 = new GuiPicture(220, 18, 32, 32, 5, new Color());
		// gp.setHidden(true);
		gp1.scale = 0.6f;
		this.add(gp1);

		this.addTooltip(220, 18, 236, 34, "Text");

		this.addPlayerSlots();
	}

}
