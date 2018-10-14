package aam.client.gui.base;

import org.lwjgl.opengl.GL11;

import aam.utils.Graph;

public class GuiSlot extends GuiOBJ
{
	public GuiSlot(int x, int y, int size, int type)
	{
		this.x = x;
		this.y = y;
		sizex = size;
		sizey = size;
		this.type = type;
	}

	public GuiSlot(int x, int y, int type)
	{
		this(x, y, 18, type);
	}

	@Override
	public void render(int k, int l)
	{
		if (!hidden)
		{
			GL11.glPushMatrix();
			GL11.glColor3d(1, 1, 1);
			this.bindTexture();
			GL11.glTranslated(k + x + 1, l + y + 1, 0);

			Graph.drawSizedSqr(gui, 256, 256, 34, 34, sizex, sizey, 1, 1, 1, 1);

			GL11.glPopMatrix();
			if (type >= 0)
			{
				SlotType typ = ObjTypes.slots.get(type);
				typ.setGui(gui);
				typ.render(k, l, 10 + x + sizex / 2 - typ.sizex / 2, 10 + y + sizey / 2 - typ.sizey / 2);
			}
		}
	}
}
