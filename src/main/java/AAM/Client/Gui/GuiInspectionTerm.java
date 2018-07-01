package AAM.Client.Gui;

import AAM.Common.Container.ContainerInspectionTerm;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiInspectionTerm extends GuiContainer
{

	public GuiInspectionTerm(ContainerInspectionTerm cont)
	{
		super(cont);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f1, int x, int y)
	{
		this.drawTexturedModalRect(x, y, 0, 0, 234, 254);
	}

}
