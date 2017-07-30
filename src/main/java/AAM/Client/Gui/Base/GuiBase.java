package AAM.Client.Gui.Base;

import AAM.Common.Container.ContainerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiBase extends GuiContainer
{
	public ContainerBase cont;
	public int k;
	public int l;

	public GuiBase(ContainerBase cont)
	{
		super(cont);
		this.cont = cont;

		this.xSize = this.cont.xSize;
		this.ySize = this.cont.ySize;
	}

	public FontRenderer getFontRenderer()
	{
		return this.fontRendererObj;
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		for (GuiOBJ obj : this.cont.objs)
		{
			obj.setGui(this);
			obj.render(k, l);
		}
		for (Object o : this.buttonList)
		{
			GuiButton b = (GuiButton) o;
			b.drawButton(Minecraft.getMinecraft(), k + b.xPosition, l + b.yPosition);
		}
	}

}
