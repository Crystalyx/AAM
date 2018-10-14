package aam.client.gui.base;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import aam.utils.Graph;
import aam.utils.MathUtils;

public class GuiTooltip extends GuiOBJ
{
	// TODO
	public boolean background = true;
	public String[] text;
	public int sx, sy, tx, ty;

	public GuiTooltip(int sx, int sy, int tx, int ty, String... text)
	{
		x = sx;
		y = sy;
		sizex = tx - sx;
		sizey = ty - sy;
		this.text = text;
	}

	public GuiTooltip(int sx, int sy, int tx, int ty, boolean back, String... text)
	{
		x = sx;
		y = sy;
		this.tx = tx;
		this.ty = ty;
		this.text = text;
		int max = 0;
		for (String element : text)
		{
			if (text.length > max)
			{
				max = text.length;
			}
		}
		sizey = (int) (max * Math.PI / 2);
		sizex = text.length * 10 + 2;
		background = back;
	}

	@Override
	public void render(int k, int l)
	{

		int mx = Mouse.getX();
		mx /= 2;
		int my = 703 - Mouse.getY();
		my /= 2;
		// Logger.info("X: " + mx + "; Y: " + my);
		gui.drawTexturedModalRect(mx, my, 208, 58, 10, 10);

		if (!hidden && MathUtils.isInLimit(mx, x * 2, tx * 2) && MathUtils.isInLimit(my, y, ty * 2))
		{
			GL11.glPushMatrix();
			GL11.glTranslated(x + k, y + l, 0);
			this.bindTexture();

			if (background)
			{
				Graph.drawSizedSqr(gui, 256, 256, 36, 36, sizex, sizey, 220, 184, 10, 10);
			}
			for (int i = 0; i < text.length; i++)
			{
				gui.drawString(gui.getFontRenderer(), text[i], 8, 8 + 10 * i, 16777215);
			}
			GL11.glPopMatrix();
		}

	}
}
