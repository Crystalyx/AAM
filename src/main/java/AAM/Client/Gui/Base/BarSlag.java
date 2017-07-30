package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Color;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;

public class BarSlag extends GuiBar
{
	public BarSlag(int x, int y, int sizex, int sizey, int type, Color c)
	{
		super(x, y, sizex, sizey, type, c);
	}

	public BarSlag(int x, int y, int sizex, int sizey, int type, int r, int g, int b)
	{
		super(x, y, sizex, sizey, type, new Color(r, g, b));
	}

	public BarSlag(int x, int y, int sizex, int sizey, int type)
	{
		super(x, y, sizex, sizey, type);
	}

	@Override
	public void render(int k, int l)
	{
		if (!this.hidden)
		{
			GL11.glPushMatrix();
			bindTexture();

			// EMPTY
			{
				// CORNER -,-
				this.gui.drawTexturedModalRect(k + this.x, l + this.y, 204, 0, 2, 2);

				// CORNER +,-
				this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y, 254, 0, 2, 2);

				// CORNER +,+
				this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y + this.sizey, 254, 22, 2, 2);

				// CORNER -,+
				this.gui.drawTexturedModalRect(k + this.x, l + this.y + this.sizey, 204, 22, 2, 2);

				int i = 0;
				// UPPER AND LOWER
				while (i < Math.floorDiv(this.sizex, 2) - 1)
				{
					this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y, 204 + Math.floorMod(i * 2, 48) + 2, 0, 2, 2);
					this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y + this.sizey, 204 + Math.floorMod(i * 2, 48) + 2, 22, 2, 2);
					i++;
				}

				i = 0;
				// LEFT AND RIGHT
				while (i < Math.floorDiv(this.sizey, 2) - 1)
				{
					this.gui.drawTexturedModalRect(k + this.x, l + this.y + i * 2 + 2, 204, Math.floorMod(i * 2, 20) + 2, 2, 2);
					this.gui.drawTexturedModalRect(k + this.x + this.sizex, l + this.y + i * 2 + 2, 254, Math.floorMod(i * 2, 20) + 2, 2, 2);
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
			}

			GL11.glPopMatrix();

			GL11.glPushMatrix();
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int length = (int) (((double) ph.slag) / ((double) ph.slagMax) * this.sizey);

			GL11.glColor3b((byte) (this.c.red / 2), (byte) (this.c.green / 2), (byte) (this.c.blue / 2));
			// FULL
			{
				int a = 0;
				while (a < Math.floorDiv(this.sizex, 2) - 1)
				{
					int b = 0;
					while (b < Math.floorDiv(length, 2) - Math.floorMod(length + 1, 2))
					{
						this.gui.drawTexturedModalRect(k + this.x + a * 2 + 2, l + this.y + b * 2 + 2 - Math.floorMod(length, 2) + this.sizey - length,
								Math.floorMod(a * 2, 32) + 74, Math.floorMod(b * 2, 32) + 2, 2, 2);
						b++;
					}
					a++;
				}
			}

			this.gui.drawString(this.gui.getFontRenderer(), ph.slag + "/" + ph.slagMax, this.x + k - this.sizex / 2, this.y + l - 16, 16777215);

			GL11.glPopMatrix();
		}
	}
}
