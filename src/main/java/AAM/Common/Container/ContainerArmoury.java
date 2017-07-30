package AAM.Common.Container;

import AAM.Client.Gui.Base.GuiBar;
import AAM.Common.Items.SoulSword;
import AAM.Common.Tiles.TileArmoury;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ContainerArmoury extends ContainerBase
{
	public ContainerArmoury(InventoryPlayer p, IInventory tile)
	{
		super(p, tile);
		PlayerDataHandler ph = PlayerDataHandler.get(p.player);
		this.addBackground(234, 234);
		this.addSlot(27, 4, tile, 121, 0);
		this.addSlot(28, 4, tile, 11, 50);
		GuiBar b = new GuiBar(29, 28, 8, 60, 0, SoulSword.getcPhColor(ph))
		{
			@Override
			public int getLength()
			{
				this.lastMaxValue = 100;
				this.lastValue = ((TileArmoury) this.gui.cont.tile).cons;
				return (int) (((double) ((TileArmoury) this.gui.cont.tile).cons) / ((double) 100) * this.sizey);
			}
		};
		this.add(b);
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlot(i * 9 + j, tile, 41 + j * 20, 30 + i * 20);
			}
		}

		this.addText("Swords:", -120, -10, 50, 50, false);
		for (int i = 0; i < ph.swords.size(); i++)
		{
			this.addText(ph.swords.get(i).getItemStackDisplayName(new ItemStack(ph.swords.get(i))), -120, 10 * i, 50, 50, false);
		}
		this.addPlayerSlots();
	}

}
