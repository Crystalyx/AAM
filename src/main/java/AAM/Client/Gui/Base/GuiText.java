package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Graph;

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
			GL11.glTranslated(k + this.x, l + this.y, 0);

			if (this.background)
			{
				Graph.drawSizedSqr(this.gui, 256, 256, 52, 24, this.sizex, this.sizey, 204, 0, 2, 2);
			}
			this.gui.drawString(this.gui.getFontRenderer(), this.text, 3, 3, 16777215);
			GL11.glPopMatrix();
		}
	}
}
