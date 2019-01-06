package aam.client.gui.base;

import aam.utils.Color;
import org.lwjgl.opengl.GL11;

public class GuiPicture extends GuiOBJ
{
	public GuiPicture(int x, int y, int sizex, int sizey, int type, Color c)
	{
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.type = type;
	}

	public float rotation = 0;
	public float scale = 1;

	@Override
	public void render(int k, int l)
	{
		if (!hidden)
		{
			GL11.glPushMatrix();
			this.setCustomTexture("aam", "textures/misc/gui/pictures.png");
			GL11.glTranslated(x + k, y + l, 0);
			GL11.glRotated(rotation, 0, 0, -1);
			GL11.glScaled(scale, scale, scale);
			this.bindTexture();

			int m = Math.floorMod(type, 8);
			int d = Math.floorDiv(type, 8);

			gui.drawTexturedModalRect((int) (-sizex / Math.PI * 2), (int) (-sizey / Math.PI * 2), m * 32, d * 32, sizex, sizey);
			this.disableCustomTexture();
			GL11.glPopMatrix();
		}
	}
}
