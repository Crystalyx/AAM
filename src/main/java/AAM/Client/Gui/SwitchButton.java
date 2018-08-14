package AAM.Client.Gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import AAM.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class SwitchButton extends GuiButton
{
	boolean up;

	public SwitchButton(int id, int x, int y, int w, int h, String text, boolean up)
	{
		super(id, x, y, w, h, text);
		this.up = up;
		this.visible = true;
	}

	public static final ResourceLocation classSelector = new ResourceLocation("aam", "textures/hud/selectorClass.png");

	@Override
	public void drawButton(Minecraft mc, int x, int y)
	{
		// super.drawButton(mc, x, y);
		if (this.visible)
		{
			GL11.glPushMatrix();
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(classSelector);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			int mx = Mouse.getX() / 2;
			int my = sr.getScaledHeight() - Mouse.getY() / 2;
			this.field_146123_n = mx >= this.xPosition && my >= this.yPosition && mx < this.xPosition + this.width && my < this.yPosition + this.height;
			int k = MiscUtils.boolToNum(field_146123_n);// this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glTranslated(this.xPosition, this.yPosition, 0);
			GL11.glScaled(0.25, 0.25, 1);
			this.drawTexturedModalRect(0, 0, 32 * 4 * (k), MiscUtils.boolToNum(!this.up) * 32 * 4, this.width * 4, this.height * 4);
			this.mouseDragged(mc, x, y);
			int l = 14737632;

			if (packedFGColour != 0)
			{
				l = packedFGColour;
			}
			else
				if (!this.enabled)
				{
					l = 10526880;
				}
				else
					if (this.field_146123_n)
					{
						l = 16777120;
					}
			GL11.glPopMatrix();
		}

	}

}
