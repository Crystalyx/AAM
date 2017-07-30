package AAM.Client.Gui;

import AAM.Client.Gui.Base.GuiBar;
import AAM.Client.Gui.Base.GuiBase;
import AAM.Common.Container.SpellTableContainer;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;

public class GuiSpellTable extends GuiBase
{

	public GuiSpellTable(SpellTableContainer cont)
	{
		super(cont);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f1, int x, int y)
	{
		if (this.cont.objs.get(2) instanceof GuiBar)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			((GuiBar) this.cont.objs.get(2)).updBar(ph.getCurrentSoul(), ph.maxSoul);
		}
		super.drawGuiContainerBackgroundLayer(f1, x, y);
	}

}
