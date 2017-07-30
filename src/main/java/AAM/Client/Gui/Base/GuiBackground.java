package AAM.Client.Gui.Base;

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
		this.x = 112 - this.sizex / 2;
		this.y = 72 - this.sizey / 2;
		GL11.glPushMatrix();
		l += 32;

		this.bindTexture();

		// CORNER -,-
		this.gui.drawTexturedModalRect(k + this.x, l + this.y, 220, 220, 12, 12);

		// CORNER +,-
		this.gui.drawTexturedModalRect(k + this.x + Math.floorDiv(this.sizex, 12) * 12, l + this.y, 244, 220, 12, 12);

		// CORNER +,+
		this.gui.drawTexturedModalRect(k + this.x + Math.floorDiv(this.sizex, 12) * 12, l + this.y + Math.floorDiv(this.sizey, 12) * 12, 244, 244, 12, 12);

		// CORNER -,+
		this.gui.drawTexturedModalRect(k + this.x, l + this.y + Math.floorDiv(this.sizey, 12) * 12, 220, 244, 12, 12);

		int i = 0;
		// UPPER AND LOWER
		while (i < Math.floorDiv(this.sizex, 12) - 1)
		{
			this.gui.drawTexturedModalRect(k + this.x + i * 12 + 12, l + this.y, 220 + Math.floorMod(i * 12, 12) + 12, 220, 12, 12);
			this.gui.drawTexturedModalRect(k + this.x + i * 12 + 12, l + this.y + Math.floorDiv(this.sizey, 12) * 12, 220 + Math.floorMod(i * 12, 12) + 12, 244, 12, 12);
			i++;
		}

		i = 0;
		// LEFT AND RIGHT
		while (i < Math.floorDiv(this.sizey, 12) - 1)
		{
			this.gui.drawTexturedModalRect(k + this.x, l + this.y + i * 12 + 12, 220, 220 + Math.floorMod(i * 12, 12) + 12, 12, 12);
			this.gui.drawTexturedModalRect(k + this.x + Math.floorDiv(this.sizex, 12) * 12, l + this.y + i * 12 + 12, 244, 220 + Math.floorMod(i * 12, 12) + 12, 12, 12);
			i++;
		}

		int a = 0;
		while (a < Math.floorDiv(this.sizex, 12) - 1)
		{
			int b = 0;
			while (b < Math.floorDiv(this.sizey, 12) - 1)
			{
				this.gui.drawTexturedModalRect(k + this.x + a * 12 + 12, l + this.y + b * 12 + 12, 220 + Math.floorMod(a * 12, 12) + 12, 220 + Math.floorMod(b * 12, 12) + 12, 12,
						12);
				b++;
			}
			a++;
		}

		GL11.glPopMatrix();
	}
}
