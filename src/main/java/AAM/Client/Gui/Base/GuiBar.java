package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Color;
import AAM.Utils.Graph;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;

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
		this.lastValue = value;
		this.lastMaxValue = maxValue;
	}

	public int getLength()
	{
		return (int) (((double) this.lastValue) / ((double) this.lastMaxValue) * this.sizey);
	}

	@Override
	public void render(int k, int l)
	{
		if (!this.hidden)
		{
			GL11.glPushMatrix();
			bindTexture();
			GL11.glTranslated(k + this.x, l + this.y, 0);
			Graph.drawSizedSqr(this.gui, 256, 256, 52, 24, this.sizex, this.sizey, 204, 0, 2, 2);

			GL11.glPopMatrix();

			GL11.glPushMatrix();
			int length = this.getLength();

			GL11.glColor3b((byte) (this.c.red / 2), (byte) (this.c.green / 2), (byte) (this.c.blue / 2));
			// FULL
			{
				int a = 0;
				while (a < Math.floorDiv(this.sizex, 2) - 1)
				{
					int b = 0;
					while (b < Math.floorDiv(length, 2) - Math.floorMod(length + 1, 2))
					{
						this.gui.drawTexturedModalRect(k + this.x + a * 2 + 2, l + this.y + b * 2 + 2 - Math.floorMod(length, 2) + this.sizey - length, Math.floorMod(a * 2, 32) + 74, Math.floorMod(b * 2, 32) + 2, 2, 2);
						b++;
					}
					a++;
				}
			}

			this.gui.drawString(this.gui.getFontRenderer(), this.lastValue + "/" + this.lastMaxValue, this.x + k - this.sizex / 2, this.y + l - 16, 16777215);

			GL11.glPopMatrix();
		}
	}
}
