package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Graph;

public class GuiSlot extends GuiOBJ
{
	public GuiSlot(int x, int y, int size, int type)
	{
		this.x = x;
		this.y = y;
		this.sizex = size;
		this.sizey = size;
		this.type = type;
	}

	public GuiSlot(int x, int y, int type)
	{
		this(x, y, 18, type);
	}

	@Override
	public void render(int k, int l)
	{
		if (!this.hidden)
		{
			GL11.glPushMatrix();
			GL11.glColor3d(1, 1, 1);
			this.bindTexture();
			GL11.glTranslated(k + this.x + 1, l + this.y + 1, 0);

			Graph.drawSizedSqr(this.gui, 256, 256, 34, 34, this.sizex, this.sizey, 1, 1, 1, 1);

			GL11.glPopMatrix();
			if (this.type >= 0)
			{
				SlotType typ = ObjTypes.slots.get(this.type);
				typ.setGui(this.gui);
				typ.render(k, l, 10 + this.x + this.sizex / 2 - typ.sizex / 2, 10 + this.y + this.sizey / 2 - typ.sizey / 2);
			}
		}
	}
}
