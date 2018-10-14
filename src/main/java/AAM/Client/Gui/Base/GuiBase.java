package aam.client.gui.base;

import org.lwjgl.opengl.GL11;

import aam.common.container.ContainerBase;
import aam.utils.Graph;
import aam.utils.MiscUtils;
import aam.utils.vectors.AABB2;
import aam.utils.vectors.Vec2;
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

		xSize = this.cont.xSize;
		ySize = this.cont.ySize;
	}

	public FontRenderer getFontRenderer()
	{
		return fontRendererObj;
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int mx, int my)
	{
		int k = +(width - xSize) / 2;
		int l = (height - ySize) / 2;

		for (GuiOBJ obj : cont.objs)
		{
			obj.setGui(this);
			obj.render(k, l);
		}
		for (Object o : buttonList)
		{
			GuiButton b = (GuiButton) o;
			b.drawButton(Minecraft.getMinecraft(), mx, my);
		}
		GL11.glPushMatrix();
		MiscUtils.bindTexture("aam:textures/misc/barrier.png");
		AABB2 ab = new Vec2(mx, my).extendBoth(10);
		Graph.renderAABB(ab, zLevel);
		GL11.glPopMatrix();
	}

}
