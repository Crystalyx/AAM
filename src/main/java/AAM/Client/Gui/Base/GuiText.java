package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

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
		this.background = back;
	}

	public void updText(String text)
	{
		this.text = text;
	}

	public String text = "";

	@Override
	public void render(int k, int l)
	{
		if (!this.hidden)
		{
			GL11.glPushMatrix();

			this.bindTexture();

			if (this.background)
			{
				// CORNER -,-
				this.gui.drawTexturedModalRect(k + this.x, l + this.y, 204, 0, 2, 2);

				// CORNER +,-
				this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y, 254, 0, 2, 2);

				// CORNER +,+
				this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y + this.sizey, 254, 22, 2, 2);

				// CORNER -,+
				this.gui.drawTexturedModalRect(k + this.x, l + this.y + this.sizey, 204, 22, 2, 2);

				int i = 0;
				// UPPER AND LOWER
				while (i < Math.floorDiv(this.sizex, 2) - 1)
				{
					this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y, 204 + Math.floorMod(i * 2, 48) + 2, 0, 2, 2);
					this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y + this.sizey, 204 + Math.floorMod(i * 2, 48) + 2, 22, 2, 2);
					i++;
				}

				i = 0;
				// LEFT AND RIGHT
				while (i < Math.floorDiv(this.sizey, 2) - 1)
				{
					this.gui.drawTexturedModalRect(k + this.x, l + this.y + i * 2 + 2, 204, Math.floorMod(i * 2, 20) + 2, 2, 2);
					this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y + i * 2 + 2, 254, Math.floorMod(i * 2, 20) + 2, 2, 2);
					i++;
				}

				int a = 0;
				while (a < Math.floorDiv(this.sizex, 2) - 1)
				{
					int b = 0;
					while (b < Math.floorDiv(this.sizey, 2) - 1)
					{
						this.gui.drawTexturedModalRect(k + this.x + a * 2 + 2, l + this.y + b * 2 + 2, Math.floorMod(a * 2, 32) + 2, Math.floorMod(b * 2, 32) + 2, 2, 2);
						b++;
					}
					a++;
				}
			}
			this.gui.drawString(this.gui.getFontRenderer(), this.text, k + this.x + 3, l + this.y + 3, 16777215);
			GL11.glPopMatrix();
		}
	}
}
