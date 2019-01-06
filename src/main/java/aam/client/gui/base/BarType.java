package aam.client.gui.base;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class BarType extends ObjType
{
	public BarType(int id, int px, int py, int bpx, int bpy, int sizex, int sizey)
	{
		super(Obj.Bar, id, px, py, sizex, sizey);
		this.bpx = bpx;
		this.bpy = bpy;
	}

	public int bpx;
	public int bpy;

	public void render(int k, int l, int x, int y, GuiBar bar)
	{
		// TODO
		int val = bar.lastValue;
		int mval = bar.lastMaxValue;

		double part = (double) val / (double) mval;

		int length = 10;

		GL11.glPushMatrix();
		GL11.glTranslated(k + x, l + y, 0);
		int xk = k + x;
		int yl = l + y;

		Minecraft.getMinecraft().getTextureManager().bindTexture(GuiOBJ.baseTexture);

		GL11.glScaled(0.65, 0.65, 0);
		gui.drawTexturedModalRect(0, 0, px, py, sizex, sizey);

		gui.drawTexturedModalRect(0, sizey - length, px, py + sizey - length, sizex, length);

		GL11.glPopMatrix();
	}

}
