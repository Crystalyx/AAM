package aam.client.gui.base;

import org.lwjgl.opengl.GL11;

import aam.utils.Graph;

public class GuiText extends GuiOBJ
{
	public boolean background = true;

	public GuiText(String text, int x, int y, int sizex, int sizey)
	{
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.text = text;
	}

	public GuiText(String text, int x, int y, int sizex, int sizey, boolean back)
	{
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.text = text;
		background = back;
	}

	public void updText(String text)
	{
		this.text = text;
	}

	public String text = "";

	@Override
	public void render(int k, int l)
	{
		if (!hidden)
		{
			GL11.glPushMatrix();

			this.bindTexture();
			GL11.glTranslated(k + x, l + y, 0);

			if (background)
			{
				Graph.drawSizedSqr(gui, 256, 256, 52, 24, sizex, sizey, 204, 0, 2, 2);
			}
			gui.drawString(gui.getFontRenderer(), text, 3, 3, 16777215);
			GL11.glPopMatrix();
		}
	}
}
