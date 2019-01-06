package aam.client.gui.base;

import aam.utils.Graph;
import org.lwjgl.opengl.GL11;

public class GuiBackground extends GuiOBJ
{
	public GuiBackground(int sizex, int sizey)
	{
		this.sizex = sizex;
		this.sizey = sizey;
	}

	@Override
	public void render(int k, int l)
	{
		x = 112 - sizex / 2;
		y = 82 - sizey / 2;
		GL11.glPushMatrix();
		l += 32;

		this.bindTexture();
		GL11.glTranslated(k + x, l + y, 0);

		Graph.drawSizedSqr(gui, 256, 256, 36, 36, sizex, sizey, 220, 220, 12, 12);

		GL11.glPopMatrix();
	}
}
