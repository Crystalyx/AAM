package AAM.Client.Gui.Base;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import AAM.Utils.Graph;
import AAM.Utils.MiscUtils;

public class GuiTooltip extends GuiOBJ
{
	// TODO
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

		int mx = Mouse.getX();
		mx /= 2;
		int my = 703 - Mouse.getY();
		my /= 2;
		// Logger.info("X: " + mx + "; Y: " + my);
		this.gui.drawTexturedModalRect(mx, my, 208, 58, 10, 10);

		if (!this.hidden && MiscUtils.isInLimit(mx, x * 2, tx * 2) && MiscUtils.isInLimit(my, y, ty * 2))
		{
			GL11.glPushMatrix();
			GL11.glTranslated(this.x + k, this.y + l, 0);
			this.bindTexture();

			if (this.background)
			{
				Graph.drawSizedSqr(this.gui, 256, 256, 36, 36, this.sizex, this.sizey, 220, 184, 10, 10);
			}
			for (int i = 0; i < text.length; i++)
			{
				this.gui.drawString(this.gui.getFontRenderer(), this.text[i], 8, 8 + 10 * i, 16777215);
			}
			GL11.glPopMatrix();
		}

	}
}
