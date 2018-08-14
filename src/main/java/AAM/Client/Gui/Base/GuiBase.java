package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Common.Container.ContainerBase;
import AAM.Utils.AABB2;
import AAM.Utils.Graph;
import AAM.Utils.MiscUtils;
import AAM.Utils.Vec2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiBase extends GuiContainer
{
	public ContainerBase cont;

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
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int mx, int my)
	{
		int k = +(this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		for (GuiOBJ obj : this.cont.objs)
		{
			obj.setGui(this);
			obj.render(k, l);
		}
		for (Object o : this.buttonList)
		{
			GuiButton b = (GuiButton) o;
			b.drawButton(Minecraft.getMinecraft(), mx, my);
		}
		GL11.glPushMatrix();
		MiscUtils.bindTexture("aam:textures/misc/barrier.png");
		AABB2 ab = new Vec2(mx, my).extendBoth(10);
		Graph.renderAABB(ab, this.zLevel);
		GL11.glPopMatrix();
	}

}
