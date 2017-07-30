package AAM.Client.Gui;

import AAM.Client.Gui.Base.ButtonOBJ;
import AAM.Client.Gui.Base.GuiBase;
import AAM.Common.Container.ContainerBase;
import AAM.Common.Tiles.MultiInventory;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.MultiInvSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiArmoury extends GuiBase
{
	public GuiArmoury(ContainerBase cont)
	{
		super(cont);
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui()
	{
		super.initGui();
		this.k = (this.width - this.xSize) / 2;
		this.l = (this.height - this.ySize) / 2;

		this.buttonList.add(new GuiButton(1, this.k + 125, this.l + 100, 14, 14, "<"));
		this.buttonList.add(new GuiButton(2, this.k + 193, this.l + 100, 14, 14, ">"));

		ButtonOBJ up = new ButtonOBJ(0, this.k + 125, this.l + 100)
		{
			@Override
			public boolean mousePressed(Minecraft mc, int x, int y)
			{
				if (super.mousePressed(mc, x, y))
				{
					MultiInventory inv = (MultiInventory) this.gui.cont.tile;

					if (inv.line > 0)
					{
						inv.line -= 1;
					}
					MultiInvSyncMessage mism = new MultiInvSyncMessage(inv);
					AlchemicalDispatcher.sendToServer(mism);
				}
				return super.mousePressed(mc, x, y);
			}
		};
		ButtonOBJ down = new ButtonOBJ(1, this.k + 193, this.l + 100)
		{
			@Override
			public boolean mousePressed(Minecraft mc, int x, int y)
			{
				if (super.mousePressed(mc, x, y))
				{
					MultiInventory inv = (MultiInventory) this.gui.cont.tile;
					if (inv.getStackInSlot(inv.line * 9 + 3) != null)
					{
						inv.line += 1;
					}
					MultiInvSyncMessage mism = new MultiInvSyncMessage(inv);
					AlchemicalDispatcher.sendToServer(mism);
				}
				return super.mousePressed(mc, x, y);
			}
		};
		up.setGui(this);
		down.setGui(this);
		this.buttonList.add(up);
		this.buttonList.add(down);
	}

}
