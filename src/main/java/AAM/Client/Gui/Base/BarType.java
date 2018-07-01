package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

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

		double part = ((double) val) / ((double) mval);

		int length = 10;

		GL11.glPushMatrix();
		GL11.glTranslated(k + x, l + y, 0);
		int xk = k + x;
		int yl = l + y;

		Minecraft.getMinecraft().getTextureManager().bindTexture(bar.baseTexture);

		GL11.glScaled(0.65, 0.65, 0);
		this.gui.drawTexturedModalRect(0, 0, this.px, this.py, this.sizex, this.sizey);

		this.gui.drawTexturedModalRect(0, this.sizey - length, this.px, this.py + this.sizey - length, this.sizex, length);

		GL11.glPopMatrix();
	}

}
