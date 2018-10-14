package aam.client.gui.base;

import org.lwjgl.opengl.GL11;

import aam.utils.Color;
import aam.utils.Graph;

public class GuiBar extends GuiOBJ
{
	public GuiBar(int x, int y, int sizex, int sizey, int type)
	{
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.type = type;
	}

	public GuiBar(int x, int y, int sizex, int sizey, int type, Color c)
	{
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.type = type;
		this.c = c;
	}

	public Color c = new Color(255, 255, 255);
	public int lastValue = 0;
	public int lastMaxValue = 1;

	public void updBar(int value, int maxValue)
	{
		lastValue = value;
		lastMaxValue = maxValue;
	}

	public int getLength()
	{
		return (int) ((double) lastValue / (double) lastMaxValue * sizey);
	}

	@Override
	public void render(int k, int l)
	{
		if (!hidden)
		{
			GL11.glPushMatrix();
			bindTexture();
			GL11.glTranslated(k + x, l + y, 0);
			Graph.drawSizedSqr(gui, 256, 256, 52, 24, sizex, sizey, 204, 0, 2, 2);

			GL11.glPopMatrix();

			GL11.glPushMatrix();
			int length = this.getLength();

			GL11.glColor3b((byte) (c.red / 2), (byte) (c.green / 2), (byte) (c.blue / 2));
			// FULL
			{
				int a = 0;
				while (a < Math.floorDiv(sizex, 2) - 1)
				{
					int b = 0;
					while (b < Math.floorDiv(length, 2) - Math.floorMod(length + 1, 2))
					{
						gui.drawTexturedModalRect(k + x + a * 2 + 2, l + y + b * 2 + 2 - Math.floorMod(length, 2) + sizey - length, Math.floorMod(a * 2, 32) + 74, Math.floorMod(b * 2, 32) + 2, 2, 2);
						b++;
					}
					a++;
				}
			}

			gui.drawString(gui.getFontRenderer(), lastValue + "/" + lastMaxValue, x + k - sizex / 2, y + l - 16, 16777215);

			GL11.glPopMatrix();
		}
	}
}
