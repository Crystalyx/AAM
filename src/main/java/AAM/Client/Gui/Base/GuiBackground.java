package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Graph;

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
		this.y = 82 - this.sizey / 2;
		GL11.glPushMatrix();
		l += 32;

		this.bindTexture();
		GL11.glTranslated(k + this.x, l + this.y, 0);

		Graph.drawSizedSqr(this.gui, 256, 256, 36, 36, this.sizex, this.sizey, 220, 220, 12, 12);

		GL11.glPopMatrix();
	}
}
