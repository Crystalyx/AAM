package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Color;
import net.minecraft.util.ResourceLocation;

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
		if (!this.hidden)
		{
			GL11.glPushMatrix();
			this.setCustomTexture("aam", "textures/misc/gui/pictures.png");
			GL11.glTranslated(this.x + k, this.y + l, 0);
			GL11.glRotated(this.rotation, 0, 0, -1);
			GL11.glScaled(this.scale, this.scale, this.scale);
			this.bindTexture();

			int m = Math.floorMod(this.type, 8);
			int d = Math.floorDiv(this.type, 8);

			this.gui.drawTexturedModalRect((int) (-this.sizex / Math.PI * 2), (int) (-this.sizey / Math.PI * 2), m * 32, d * 32, this.sizex, this.sizey);
			this.disableCustomTexture();
			GL11.glPopMatrix();
		}
	}
}
