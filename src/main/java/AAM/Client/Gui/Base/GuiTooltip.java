package AAM.Client.Gui.Base;

public class GuiTooltip extends GuiOBJ
{
	public boolean background = true;
	public String[] text;
	public int sx, sy, tx, ty;

	public GuiTooltip(int sx, int sy, int tx, int ty, String... text)
	{
		this.x = sx;
		this.y = sy;
		this.sizex = tx - sx;
		this.sizey = ty - sy;
		this.text = text;
	}

	public GuiTooltip(int sx, int sy, int tx, int ty, boolean back, String... text)
	{
		this.x = sx;
		this.y = sy;
		this.tx = tx;
		this.ty = ty;
		this.text = text;
		int max = 0;
		for (int i = 0; i < text.length; i++)
		{
			if (text.length > max)
			{
				max = text.length;
			}
		}
		this.sizey = (int) (max * Math.PI / 2);
		this.sizex = text.length * 10 + 2;
		this.background = back;
	}

	@Override
	public void render(int k, int l)
	{
		/**int mx = Mouse.getX();
		mx /= 2;
		int my = 703 - Mouse.getY();
		my /= 2;
//		Logger.info("X: " + mx + "; Y: " + my);
		this.gui.drawTexturedModalRect(mx, my, 208, 58, 10, 10);

		if (!this.hidden && MiscUtils.isInLimit(mx, x*2, tx*2) && MiscUtils.isInLimit(my, y, ty*2))
		{
			GL11.glPushMatrix();
			GL11.glTranslated(this.x+k, this.y+l, 0);
			this.bindTexture();

			if (this.background)
			{
				// CORNER -,-
				this.gui.drawTexturedModalRect(0, 0, 204, 54, 1, 1);

				// CORNER +,-
				this.gui.drawTexturedModalRect(this.sizex, 0, 254, 54, 1, 1);

				// CORNER +,+
				this.gui.drawTexturedModalRect(this.sizex, this.sizey, 254, 77, 1, 1);

				// CORNER -,+
				this.gui.drawTexturedModalRect(0, this.sizey, 204, 77, 1, 1);

				int i = 0;
				// UPPER AND LOWER
				while (i < Math.floorDiv(this.sizex, 2) - 1)
				{
					this.gui.drawTexturedModalRect(i * 2 + 2, 0, 204 + Math.floorMod(i * 2, 48) + 1, 54, 1, 1);
					this.gui.drawTexturedModalRect(i * 2 + 2, this.sizey, 204 + Math.floorMod(i * 2, 48) + 1, 77, 1, 1);
					i++;
				}

				i = 0;
				// LEFT AND RIGHT
				while (i < Math.floorDiv(this.sizey, 2) - 1)
				{
					this.gui.drawTexturedModalRect(0, i * 2 + 2, 204, Math.floorMod(i * 2, 20) + 1 + 54, 1, 1);
					this.gui.drawTexturedModalRect(this.sizex, i * 2 + 2, 254, Math.floorMod(i * 2, 20) + 1 + 54, 1, 1);
					i++;
				}

				int a = 0;
				while (a < Math.floorDiv(this.sizex, 2) - 1)
				{
					int b = 0;
					while (b < Math.floorDiv(this.sizey, 2) - 1)
					{
						this.gui.drawTexturedModalRect(a * 2 + 2, b * 2 + 2, Math.floorMod(a * 2, 32) + 1, Math.floorMod(b * 2, 32) + 1 + 54, 1, 1);
						b++;
					}
					a++;
				}
			}
			for (int i = 0; i < this.text.length; i++)
				this.gui.drawString(this.gui.getFontRenderer(), this.text[i], 3, 3 + 10 * i, 16777215);
			GL11.glPopMatrix();
		}*/
	}
}
