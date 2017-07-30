package AAM.Client.Gui;

import org.lwjgl.opengl.GL11;

import AAM.Client.Gui.Base.Button;
import AAM.Client.Gui.Base.ButtonRMember;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiAddMember extends GuiScreen
{
	private int x;
	private int y;

	public static final ResourceLocation padding = new ResourceLocation("aam", "textures/hud/_back.png");
	public static final ResourceLocation partyPadding = new ResourceLocation("aam", "textures/hud/party_back.png");

	public static final ResourceLocation off = new ResourceLocation("aam", "textures/hud/member_offline.png");
	public static final ResourceLocation on = new ResourceLocation("aam", "textures/hud/member_online.png");
	public static final ResourceLocation nul = new ResourceLocation("aam", "textures/hud/member_no.png");
	public static final ResourceLocation life = new ResourceLocation("aam", "textures/hud/member_life.png");
	public static final ResourceLocation soul = new ResourceLocation("aam", "textures/hud/member_soul.png");

	@Override
	public void drawScreen(int ix, int iy, float fl)
	{
		int w = 256;
		int h = 200;
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glColor4d(0.5, 0.5, 0.5, 0.25);
		Minecraft.getMinecraft().getTextureManager().bindTexture(padding);
		this.drawTexturedModalRect(this.x - w / 2, this.y - h / 2, 0, 0, w, h);
		GL11.glPopMatrix();
		this.renderParty(this.x + w / 2, this.y - h / 2);
		super.drawScreen(x, y, fl);
		if (cd > 0)
		{
			if (this.message != "")
			{
				FontRenderer f = mc.fontRenderer;
				f.drawString(this.message, this.x - this.message.length() * 2, this.y - h / 4, 16777215);
			}
			cd += this.messagelast - mc.theWorld.getWorldTime();
			this.messagelast = mc.theWorld.getWorldTime();
		}
		else
		{
			this.message = "";
			this.cd = 0;
			this.messagelast = 0;
		}
		this.textBoxFilter.drawTextBox();
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
		this.textBoxFilter.updateCursorCounter();
		this.textBoxFilter.setMaxStringLength(128);
		this.textBoxFilter.setText(ph.addMember);
	}

	private GuiTextField textBoxFilter;

	public int cd = 0;
	public long messagelast = 0;
	public String message = "";

	// 55275053-cfff-4307-bdc3-aecec93caa38
	@Override
	public void initGui()
	{
		super.initGui();
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		this.x = sr.getScaledWidth() / 2;
		this.y = sr.getScaledHeight() / 2;
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
		this.textBoxFilter = new GuiTextField(this.fontRendererObj, this.x - 64, this.y - 20, 128, 15);
		this.textBoxFilter.setMaxStringLength(128);
		this.textBoxFilter.setText(ph.addMember);

		Button gb = new Button(0, this.x - 35, this.y + 4, 70, 20, "Invite Player", this)
		{
			@Override
			public boolean mousePressed(Minecraft mc, int x, int y)
			{
				if (super.mousePressed(mc, x, y))
				{
					PlayerDataHandler ph = PlayerDataHandler.get(mc.thePlayer);
					if (mc.theWorld.getPlayerEntityByName(ph.addMember) != null)
						ph.party.add(ph.addMember);
					else
					{
						((GuiAddMember) this.gui).message = EnumChatFormatting.DARK_RED + "There is no player with so nickname or he is offline";
						((GuiAddMember) this.gui).cd = 100;
						((GuiAddMember) this.gui).messagelast = mc.theWorld.getWorldTime();
					}
					ph.addMember = "";
				}
				AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
				return super.mousePressed(mc, x, y);
			}
		};
		this.buttonList.add(gb);
		for (int i = 0; i < 6; i++)
		{
			ButtonRMember brm = new ButtonRMember(i + 1, this.x + 109, this.y + 2 + 32 * i - 94, 10, 10);
			this.buttonList.add(brm);
		}
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	public void renderParty(double d, double y)
	{
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		FontRenderer f = Minecraft.getMinecraft().fontRenderer;

		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		GL11.glPushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(partyPadding);
		GL11.glDepthMask(true);
		GL11.glTranslated(d, y, 0);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		Tessellator tessellator = Tessellator.instance;

		int ps = 72;
		int s = 64;

		int k = ps / 2 - 24;
		int l = -8;

		for (int i = 0; i < Math.min(6, ph.party.size()); i++)
		{
			String nick = "";

			boolean flag = false;
			if (ph.party.size() - 1 >= i)
			{
				if (ph.party.get(i) != null)
				{
					nick = ph.party.get(i);
					f.drawString(nick, k - nick.length() * 2 + 20, l + s * (i + 1) - 1, 0xFFFFFF);

					if (p.worldObj.getPlayerEntityByName(ph.party.get(i)) != null)
					{
						Minecraft.getMinecraft().getTextureManager().bindTexture(partyPadding);
						GL11.glColor4d(1.0, 1.0, 1.0, 0.5);

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV((double) 0, (double) l + 72.0 + s * i, 0.0D, 0.0D, 1.0D);
						tessellator.addVertexWithUV((double) ps, (double) l + 72.0 + s * i, 0.0D, 0.5D, 1.0D);
						tessellator.addVertexWithUV((double) ps, (double) l + 8.0 + s * i, 0.0D, 0.5D, 0.0D);
						tessellator.addVertexWithUV((double) 0, (double) l + 8.0 + s * i, 0.0D, 0.0D, 0.0D);
						tessellator.draw();

						GL11.glColor4d(1.0, 1.0, 1.0, 1.0);

						// ===============Soul=================
						Minecraft.getMinecraft().getTextureManager().bindTexture(soul);

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV((double) k, (double) l + 60.0 + i * s, 0.0D, 0.0D, 1.0D);
						tessellator.addVertexWithUV((double) k + 8, (double) l + 60.0 + i * s, 0.0D, 0.5D, 1.0D);
						tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s, 0.0D, 0.5D, 0.0D);
						tessellator.addVertexWithUV((double) k, (double) l + 12 + i * s, 0.0D, 0.0D, 0.0D);
						tessellator.draw();
						EntityPlayer ep = p.worldObj.getPlayerEntityByName(ph.party.get(i));
						PlayerDataHandler eph = PlayerDataHandler.get(ep);
						double length = 1 - ((double) eph.getCurrentSoul() / (double) eph.getMaxSoul());

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV((double) k, (double) l + 60.0 + i * s, 0.0D, 0.5D, 1.0D - length);
						tessellator.addVertexWithUV((double) k + 8, (double) l + 60.0 + i * s, 0.0D, 1D, 1.0D - length);
						tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s + length * 48 + 3 / 128, 0.0D, 1D, 0.0D);
						tessellator.addVertexWithUV((double) k, (double) l + 12 + i * s + length * 48 + 3 / 128, 0.0D, 0.5D, 0.0D);
						tessellator.draw();

						// ===============Life=================
						Minecraft.getMinecraft().getTextureManager().bindTexture(life);
						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV((double) k - 8, (double) l + 60.0 + i * s, 0.0D, 0.0D, 1.0D);
						tessellator.addVertexWithUV((double) k, (double) l + 60.0 + i * s, 0.0D, 0.5D, 1.0D);
						tessellator.addVertexWithUV((double) k, (double) l + 12 + i * s, 0.0D, 0.5D, 0.0D);
						tessellator.addVertexWithUV((double) k - 8, (double) l + 12 + i * s, 0.0D, 0.0D, 0.0D);
						tessellator.draw();

						length = 1 - (ep.getHealth() / ep.getMaxHealth());

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV((double) k - 8, (double) l + 60.0 + i * s, 0.0D, 0.5D, 1.0D - length);
						tessellator.addVertexWithUV((double) k, (double) l + 60.0 + i * s, 0.0D, 1D, 1.0D - length);
						tessellator.addVertexWithUV((double) k, (double) l + 12 + i * s + length * 48 + 3 / 128, 0.0D, 1D, 0.0D);
						tessellator.addVertexWithUV((double) k - 8, (double) l + 12 + i * s + length * 48 + 3 / 128, 0.0D, 0.5D, 0.0D);
						tessellator.draw();

						flag = true;

						Minecraft.getMinecraft().getTextureManager().bindTexture(on);
					}
					else
						Minecraft.getMinecraft().getTextureManager().bindTexture(off);
				}
				else
					Minecraft.getMinecraft().getTextureManager().bindTexture(off);
			}
			else
				Minecraft.getMinecraft().getTextureManager().bindTexture(nul);

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV((double) k + 8, (double) l + 60.0 + i * s, 0.0D, 0, 1);
			tessellator.addVertexWithUV((double) 64.0D + k - 8, (double) l + 60.0 + i * s, 0.0D, 1, 1);
			tessellator.addVertexWithUV((double) 64.0D + k - 8, (double) l + 12 + i * s, 0.0D, 1, 0);
			tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s, 0.0D, 0, 0);
			tessellator.draw();
			
			if (flag)
			{
				EntityClientPlayerMP ecpmp = (EntityClientPlayerMP) Minecraft.getMinecraft().theWorld.getPlayerEntityByName(nick);
				ResourceLocation skin = Minecraft.getMinecraft().thePlayer.getLocationSkin();
				Minecraft.getMinecraft().getTextureManager().bindTexture(skin);
				
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV((double) k + 11, (double) l + 57.0 + i * s, 0.0D, 1 / 8D, 2 / 4D);
				tessellator.addVertexWithUV((double) 61.0D + k - 8, (double) l + 57.0 + i * s, 0.0D, 2 / 8D, 2 / 4D);
				tessellator.addVertexWithUV((double) 61.0D + k - 8, (double) l + 15 + i * s, 0.0D, 2 / 8D, 1 / 4D);
				tessellator.addVertexWithUV((double) k + 11, (double) l + 15 + i * s, 0.0D, 1 / 8D, 1 / 4D);
				tessellator.draw();
			}
		}

		GL11.glPopMatrix();

	}

	@Override
	protected void keyTyped(char par1, int par2)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
		if (this.textBoxFilter.isFocused())
		{
			this.textBoxFilter.textboxKeyTyped(par1, par2);

			String srch = this.textBoxFilter.getText();

			if (!ph.addMember.equals(srch))
			{
				ph.addMember = srch;
			}
		}

		if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode() && !this.textBoxFilter.isFocused())
		{
			this.mc.thePlayer.closeScreen();
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
		super.mouseClicked(x, y, mouseButton);

		int minX = textBoxFilter.xPosition;
		int minY = textBoxFilter.yPosition;
		int maxX = minX + textBoxFilter.width;
		int maxY = minY + textBoxFilter.height;

		if (mouseButton == 1 && x >= minX && x <= maxX && y <= maxY)
		{
			ph.addMember = "";
			this.textBoxFilter.setText("");
		}

		this.textBoxFilter.mouseClicked(x, y, mouseButton);
	}
}
