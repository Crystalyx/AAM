package aam.client.gui;

import aam.client.gui.base.GuiOBJ;
import aam.common.transmutations.Circle;
import aam.utils.MathUtils;
import aam.utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class CircleRemoveButton extends GuiButton
{
	public Circle circle;

	public CircleRemoveButton(int id, int px, int py, Circle cir)
	{
		super(id, px, py, 32, 32, "");
		circle = cir;
	}

	@Override
	public void drawButton(Minecraft mc, int x, int y)
	{
		if (visible)
		{
			// super.drawButton(mc, x, y);
			GL11.glPushMatrix();
			FontRenderer fontrenderer = mc.fontRenderer;

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			int mx = Mouse.getX() / 2;
			int my = sr.getScaledHeight() - Mouse.getY() / 2;
			field_146123_n = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			// GL11.glTranslated(this.xPosition, this.yPosition, 0);
			Tessellator t = Tessellator.instance;

			MiscUtils.bindTexture(GuiOBJ.baseTexture);
			int ty = field_146123_n ? 32 : 0;
			GL11.glTranslated(xPosition, yPosition, zLevel);
			this.drawTexturedModalRect(0, 0, 72, ty, 32, 32);

			if (!circle.rev)
			{
				MiscUtils.bindTexture("aam:textures/items/" + circle.pt.item.getResourcePath() + ".png");
			}
			else
			{
				MiscUtils.bindTexture("aam:textures/items/" + circle.pt.itemRev.getResourcePath() + ".png");
			}

			GL11.glPushMatrix();
			GL11.glScaled(0.125, 0.125, 0.125);
			this.drawTexturedModalRect(0, 0, 0, 0, 256, 256);
			GL11.glPopMatrix();

			FontRenderer fr = mc.fontRenderer;
			fr.drawString(MathUtils.roundStr(circle.scale, 0), 24, 22, 0);

			GL11.glColor4d(1, 1, 1, 1);

			this.mouseDragged(mc, x, y);
			int l = 14737632;

			if (packedFGColour != 0)
			{
				l = packedFGColour;
			}
			else
				if (!enabled)
				{
					l = 10526880;
				}
				else
					if (field_146123_n)
					{
						l = 16777120;
					}
			GL11.glPopMatrix();
		}
	}

}
