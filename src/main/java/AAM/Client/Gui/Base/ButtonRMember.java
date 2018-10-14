package aam.client.gui.base;

import org.lwjgl.opengl.GL11;

import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.PlayerSyncMessage;
import aam.utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class ButtonRMember extends GuiButton
{
	public static final ResourceLocation rem = new ResourceLocation("aam", "textures/hud/member_remove.png");

	public ButtonRMember(int id, int x, int y, int sx, int sy)
	{
		super(id, x, y, sx, sy, "");
	}

	@Override
	public boolean mousePressed(Minecraft mc, int x, int y)
	{
		if (super.mousePressed(mc, x, y))
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			if (id - 1 < ph.party.size())
			{
				ph.party.remove(id - 1);
			}
		}
		AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		return super.mousePressed(mc, x, y);
	}

	@Override
	public void drawButton(Minecraft p_146112_1_, int x, int y)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

		if (visible && id - 1 < ph.party.size())
		{
			p_146112_1_.getTextureManager().bindTexture(rem);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Tessellator tessellator = Tessellator.instance;
			int k = xPosition;
			int j = yPosition;
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(k, (double) j + height, 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV((double) k + width, (double) j + height, 0.0D, 1D, 1.0D);
			tessellator.addVertexWithUV((double) k + width, j, 0.0D, 1D, 0.0D);
			tessellator.addVertexWithUV(k, j, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
			this.mouseDragged(p_146112_1_, x, y);
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
		}
	}
}
