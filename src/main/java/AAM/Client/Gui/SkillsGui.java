package AAM.Client.Gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import AAM.Common.Skills.ModSkills;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.SkillPackage;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.WorldPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;

public class SkillsGui extends GuiScreen
{
	public SkillsGui()
	{
	}

	private int x;
	private int y;

	@Override
	protected void actionPerformed(GuiButton but)
	{
		super.actionPerformed(but);
	}

	@Override
	public void drawScreen(int x, int y, float z)
	{
//		super.drawScreen(x, y, z);
//
//		ResourceLocation text = new ResourceLocation("aam:textures/misc/beam.png");
//		Minecraft.getMinecraft().getTextureManager().bindTexture(text);
//		Tessellator t = Tessellator.instance;
//		GL11.glTranslated(this.x, this.y, 0);
//
//		int mx = Mouse.getX() - this.x * 2;
//		mx /= 2;
//		int my = -Mouse.getY() + this.y * 2;
//		my /= 2;
//
//		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
//
//		double r = 100;
//		t.startDrawing(GL11.GL_QUAD_STRIP);
//		int ln = ph.skills.size();
//		double l = 360 / ln;
//		double n = 30;
//		double h = l / n;// part angly
//		double angle = Math.toDegrees(Math.atan2(my, mx)) + 180 + l;
//
//		for (int j = 0; j < ln; j++)
//		{
//			GL11.glPushMatrix();
//			GL11.glColor4f(40 / 255f, 160 / 255f, 255 / 255f, 0.5f);
//			GL11.glTranslated(this.x, this.y, 0);
//
//			GL11.glRotated(90, 0, 0, 1);
//
//			for (int i = 0; i < n; i++)
//			{
//
//				t.addVertex(0, 0, 0);
//				t.addVertex(0, 0, 0);
//				t.addVertex(Math.cos(Math.toRadians(i * h + j * l * 2)) * r, Math.sin(Math.toRadians(i * h + j * l * 2)) * r, 0);
//				t.addVertex(Math.cos(Math.toRadians((i + 1) * h + j * l * 2)) * r, Math.sin(Math.toRadians((i + 1) * h + j * l * 2)) * r, 0);
//			}
//			GL11.glPopMatrix();
//		}
//		t.draw();
//
//		t.startDrawing(GL11.GL_QUAD_STRIP);
//		GL11.glRotated(l + 90, 0, 0, 1);
//
//		// GL11.glTranslated(this.x, this.y, 0);
//
//		GL11.glColor4f(80 / 255f, 80 / 255f, 255 / 255f, 0.5f);
//		for (int j = 0; j < ln; j++)
//		{
//			GL11.glPushMatrix();
//
//			GL11.glTranslated(this.x, this.y, 0);
//			for (int i = 0; i < n; i++)
//			{
//				t.addVertex(0, 0, 0);
//				t.addVertex(0, 0, 0);
//				t.addVertex(Math.cos(Math.toRadians(i * h + (j + 1) * l * 2)) * r, Math.sin(Math.toRadians(i * h + (j + 1) * l * 2)) * r, 0);
//				t.addVertex(Math.cos(Math.toRadians((i + 1) * h + (j + 1) * l * 2)) * r, Math.sin(Math.toRadians((i + 1) * h + (j + 1) * l * 2)) * r, 0);
//			}
//			GL11.glPopMatrix();
//		}
//		t.draw();
//		GL11.glPushMatrix();
//
//		for (int j = 0; j < ln; j++)
//		{
//			WorldPos pp = new WorldPos(Math.cos(Math.toRadians(0.5 * h + (j + 1) * l)) * r, Math.sin(Math.toRadians(0.5 * h + (j + 1) * l)) * r, 0);
//			this.mc.fontRenderer.drawString(ModSkills.skills.get(j).name, (int) pp.x, (int) pp.y, 0xFFFFFF);
//		}
//
//		double a = (angle - (angle % l)) / l;
//		t.startDrawing(GL11.GL_QUAD_STRIP);
//
//		GL11.glPushMatrix();
//		GL11.glColor4f(1, 0, 0, 0.5f);
//
//		GL11.glTranslated(this.x, this.y, 0);
//		for (int i = 0; i < n; i++)
//		{
//			t.addVertex(0, 0, 0);
//			t.addVertex(0, 0, 0);
//			t.addVertex(Math.cos(Math.toRadians(a * l + i * h)) * r, Math.sin(Math.toRadians(a * l + i * h)) * r, 0);
//			t.addVertex(Math.cos(Math.toRadians(a * l + i * h + h)) * r, Math.sin(Math.toRadians(a * l + i * h + h)) * r, 0);
//		}
//		GL11.glPopMatrix();
//
//		t.draw();
//
//		GL11.glPopMatrix();
	}

	@Override
	public void initGui()
	{
		super.initGui();
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		this.x = sr.getScaledWidth() / 2;
		this.y = sr.getScaledHeight() / 2;
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton)
	{
//		int mx = Mouse.getX() - this.x * 2;
//		mx /= 2;
//		int my = -Mouse.getY() + this.y * 2;
//		my /= 2;
//		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
//
//		int ln = ph.skills.size();
//		double l = 360 / ln;
//		double angle = Math.toDegrees(Math.atan2(my, mx)) + 180 + l;
//
//		int a = (int) ((angle - (angle % l)) / l) - 1;// skill index
//		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(a + "" + ModSkills.skills.get(a).name));
//		SkillPackage sp = new SkillPackage(Minecraft.getMinecraft().thePlayer, a);
//		AlchemicalDispatcher.sendToServer(sp);
//		Minecraft.getMinecraft().displayGuiScreen(null);
	}

}
