package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

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
			GL11.glColor3b((byte) 127, (byte) 127, (byte) 127);
			this.bindTexture();

			// CORNER -,-
			this.gui.drawTexturedModalRect(k + this.x+1, l + this.y+1, 1, 1, 1, 1);

			// CORNER +,-
			this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y+1, 34, 0, 1, 1);

			// CORNER +,+
			this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y + this.sizey, 34, 34, 1, 1);

			// CORNER -,+
			this.gui.drawTexturedModalRect(k + this.x+1, l + this.y + this.sizey, 0, 34, 1, 1);

			int i = 0;
			// UPPER AND LOWER
			while (i < Math.floorDiv(this.sizex, 2) - 1)
			{
				this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y+1, Math.floorMod(i * 2, 32) + 2, 0, 2, 1);
				this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y + this.sizey, Math.floorMod(i * 2, 32) + 2, 34, 2, 1);
				i++;
			}

			i = 0;
			// LEFT AND RIGHT
			while (i < Math.floorDiv(this.sizey, 2) - 1)
			{
				this.gui.drawTexturedModalRect(k + this.x+1, l + this.y + i * 2 + 2, 0, Math.floorMod(i * 2, 32) + 2, 1, 2);
				this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y + i * 2 + 2, 34, Math.floorMod(i * 2, 32) + 2, 1, 2);
				i++;
			}

			int a = 0;
			while (a < Math.floorDiv(this.sizex, 2) - 1)
			{
				int b = 0;
				while (b < Math.floorDiv(this.sizey, 2) - 1)
				{
					this.gui.drawTexturedModalRect(k + this.x + a * 2 + 2, l + this.y + b * 2 + 2, Math.floorMod(a * 2, 32) + 2, Math.floorMod(b * 2, 32) + 2, 2, 2);
					b++;
				}
				a++;
			}
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
