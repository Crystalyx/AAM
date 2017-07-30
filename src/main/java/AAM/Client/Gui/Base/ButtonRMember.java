package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
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
			if (this.id - 1 < ph.party.size())
			{
				ph.party.remove(this.id - 1);
			}
		}
		AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		return super.mousePressed(mc, x, y);
	}

	@Override
	public void drawButton(Minecraft p_146112_1_, int x, int y)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

		if (this.visible && this.id - 1 < ph.party.size())
		{
			p_146112_1_.getTextureManager().bindTexture(rem);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Tessellator tessellator = Tessellator.instance;
			int k = this.xPosition;
			int j = this.yPosition;
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV((double) k, (double) j + this.height, 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV((double) k + this.width, (double) j + this.height, 0.0D, 1D, 1.0D);
			tessellator.addVertexWithUV((double) k + this.width, (double) j, 0.0D, 1D, 0.0D);
			tessellator.addVertexWithUV((double) k, (double) j, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
			this.mouseDragged(p_146112_1_, x, y);
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
		}
	}
}
