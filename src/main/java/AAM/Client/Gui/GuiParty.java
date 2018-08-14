package AAM.Client.Gui;

import org.lwjgl.opengl.GL11;

import AAM.Client.Gui.Base.ButtonRMember;
import AAM.Common.Soul.Trait;
import AAM.Common.Soul.WarriorType;
import AAM.Common.Soul.WeaponType;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiParty extends GuiScreen
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

	public static final ResourceLocation classPadding = new ResourceLocation("aam", "textures/hud/class.png");
	public static final ResourceLocation classPaddingGold = new ResourceLocation("aam", "textures/hud/class_gold.png");
	public static final ResourceLocation classSelector = new ResourceLocation("aam", "textures/hud/selectorClass.png");

	int w = 256;
	int h = 200;

	@Override
	public void drawScreen(int ix, int iy, float fl)
	{

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
			if (!this.message.equals(""))
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
		ph.addMember = this.textBoxFilter.getText();
	}

	private GuiTextField textBoxFilter;

	public int cd = 0;
	public long messagelast = 0;
	public String message = "";

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

		GuiButton gb = new GuiButton(0, this.x - 35, this.y + 4, 70, 20, "Invite Player");

		SwitchButton classUp = new SwitchButton(7, this.x - w / 2 - ps / 2, this.y - h / 2 - 8, 32, 32, "", true);

		SwitchButton classDown = new SwitchButton(8, this.x - w / 2 - ps / 2, this.y - h / 2 + 12, 32, 32, "", false);
		this.buttonList.add(gb);
		this.buttonList.add(classUp);
		this.buttonList.add(classDown);

		SwitchButton swordUp = new SwitchButton(9, this.x - w / 2 - ps / 2, this.y - h / 2 + 28, 32, 32, "", true);
		SwitchButton swordDown = new SwitchButton(10, this.x - w / 2 - ps / 2, this.y - h / 2 + 12 + 36, 32, 32, "", false);

		this.buttonList.add(swordUp);
		this.buttonList.add(swordDown);

		for (int i = 0; i < 6; i++)
		{
			ButtonRMember brm = new ButtonRMember(i + 1, this.x + 109, this.y - 92 + 32 * i, 10, 10);
			this.buttonList.add(brm);
		}
	}

	@Override
	public void actionPerformed(GuiButton b)
	{
		Logger.info(b.id);
		if (b.id == 0)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(mc.thePlayer);
			if (mc.theWorld.getPlayerEntityByName(ph.addMember) != null)
			{
				ph.party.add(ph.addMember);
				this.textBoxFilter.setText("");
			}
			else
			{
				this.message = EnumChatFormatting.DARK_RED + "There is no player with such nickname or he is offline";
				this.cd = 100;
				this.messagelast = mc.theWorld.getWorldTime();
			}
			ph.addMember = "";
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 7)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MiscUtils.cycle(ph.warrior.ordinal() + 1, 0, WarriorType.values().length - 1);
			ph.warrior = WarriorType.values()[id];
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 8)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MiscUtils.cycle(ph.warrior.ordinal() - 1, 0, WarriorType.values().length - 1);
			ph.warrior = WarriorType.values()[id];
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 9)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MiscUtils.cycle(ph.sword.ordinal() + 1, 0, WeaponType.values().length - 1);
			ph.sword = WeaponType.values()[id];
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 10)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MiscUtils.cycle(ph.sword.ordinal() - 1, 0, WeaponType.values().length - 1);
			ph.sword = WeaponType.values()[id];
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	int t = w * 2;

	int ps = 64;
	int s = 64;

	int k = ps / 2 - 24;
	int l = -8;
	int dPad = 8;

	public void renderParty(double d, double y)
	{
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		FontRenderer f = Minecraft.getMinecraft().fontRenderer;

		Minecraft mc = Minecraft.getMinecraft();

		GL11.glPushMatrix();
		GL11.glDepthMask(true);
		GL11.glTranslated(d, y, 0);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		Tessellator tessellator = Tessellator.instance;

		// Class
		GL11.glPushMatrix();
		GL11.glTranslated(dPad - 4, dPad + 4, 0);
		GL11.glColor4d(1, 1, 1, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ph.warrior.icon);

		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 - t, s + l, 0.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(ps - t, s + l, 0.0D, 1D, 1.0D);
		tessellator.addVertexWithUV(ps - t, l, 0.0D, 1D, 0.0D);
		tessellator.addVertexWithUV(0 - t, l, 0.0D, 0.0D, 0.0D);
		tessellator.draw();

		Minecraft.getMinecraft().getTextureManager().bindTexture(classPadding);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 - t - dPad, s + l + dPad, 0.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(ps - t + dPad, s + l + dPad, 0.0D, 1D, 1.0D);
		tessellator.addVertexWithUV(ps - t + dPad, l - dPad, 0.0D, 1D, 0.0D);
		tessellator.addVertexWithUV(0 - t - dPad, l - dPad, 0.0D, 0.0D, 0.0D);
		tessellator.draw();

		GL11.glPushMatrix();
		GL11.glTranslated(-t - dPad * 2, 3 * l, 0);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(ps - t + dPad * 2, s / 2 + 3 * l / 2, 0);
		GL11.glScaled(2, 2, 2);
		f.drawString(ph.warrior.toString(), 0, 0, 0xFFFFFF);
		GL11.glPopMatrix();

		GL11.glPopMatrix();

		// Weapon
		GL11.glPushMatrix();
		GL11.glTranslated(dPad - 4, dPad + 76, 0);
		GL11.glColor4d(1, 1, 1, 1);
		MiscUtils.bindTexture("aam:textures/items/" + ph.sword.texture + ".png");

		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 - t, s + l, 0.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(ps - t, s + l, 0.0D, 1D, 1.0D);
		tessellator.addVertexWithUV(ps - t, l, 0.0D, 1D, 0.0D);
		tessellator.addVertexWithUV(0 - t, l, 0.0D, 0.0D, 0.0D);
		tessellator.draw();

		Minecraft.getMinecraft().getTextureManager().bindTexture(classPadding);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 - t - dPad, s + l + dPad, 0.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(ps - t + dPad, s + l + dPad, 0.0D, 1D, 1.0D);
		tessellator.addVertexWithUV(ps - t + dPad, l - dPad, 0.0D, 1D, 0.0D);
		tessellator.addVertexWithUV(0 - t - dPad, l - dPad, 0.0D, 0.0D, 0.0D);
		tessellator.draw();

		GL11.glPushMatrix();
		GL11.glTranslated(-t - dPad * 2, 3 * l, 0);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(ps - t + dPad * 2, s / 2 + 3 * l / 2, 0);
		GL11.glScaled(2, 2, 2);
		f.drawString(ph.sword.toString(), 0, 0, 0xFFFFFF);
		GL11.glPopMatrix();

		GL11.glPopMatrix();

		// Party
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
						tessellator.addVertexWithUV(0, l + 72.0 + s * i, 0.0D, 0.0D, 1.0D);
						tessellator.addVertexWithUV(ps, l + 72.0 + s * i, 0.0D, 0.5D, 1.0D);
						tessellator.addVertexWithUV(ps, l + 8.0 + s * i, 0.0D, 0.5D, 0.0D);
						tessellator.addVertexWithUV(0, l + 8.0 + s * i, 0.0D, 0.0D, 0.0D);
						tessellator.draw();

						GL11.glColor4d(1.0, 1.0, 1.0, 1.0);

						// ===============Soul=================
						Minecraft.getMinecraft().getTextureManager().bindTexture(soul);

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV(k, l + 60.0 + i * s, 0.0D, 0.0D, 1.0D);
						tessellator.addVertexWithUV((double) k + 8, l + 60.0 + i * s, 0.0D, 0.5D, 1.0D);
						tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s, 0.0D, 0.5D, 0.0D);
						tessellator.addVertexWithUV(k, (double) l + 12 + i * s, 0.0D, 0.0D, 0.0D);
						tessellator.draw();

						EntityPlayer ep = p.worldObj.getPlayerEntityByName(ph.party.get(i));
						PlayerDataHandler eph = PlayerDataHandler.get(ep);
						double length = 1 - ((double) eph.getCurrentSoul() / (double) eph.getTrait(Trait.Soul));

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV(k, l + 60.0 + i * s, 0.0D, 0.5D, 1.0D - length);
						tessellator.addVertexWithUV((double) k + 8, l + 60.0 + i * s, 0.0D, 1D, 1.0D - length);
						tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s + length * 48, 0.0D, 1D, 0.0D);
						tessellator.addVertexWithUV(k, (double) l + 12 + i * s + length * 48, 0.0D, 0.5D, 0.0D);
						tessellator.draw();

						// ===============Life=================
						Minecraft.getMinecraft().getTextureManager().bindTexture(life);
						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV((double) k - 8, l + 60.0 + i * s, 0.0D, 0.0D, 1.0D);
						tessellator.addVertexWithUV(k, l + 60.0 + i * s, 0.0D, 0.5D, 1.0D);
						tessellator.addVertexWithUV(k, (double) l + 12 + i * s, 0.0D, 0.5D, 0.0D);
						tessellator.addVertexWithUV((double) k - 8, (double) l + 12 + i * s, 0.0D, 0.0D, 0.0D);
						tessellator.draw();

						length = 1 - (ep.getHealth() / ep.getMaxHealth());

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV((double) k - 8, l + 60.0 + i * s, 0.0D, 0.5D, 1.0D - length);
						tessellator.addVertexWithUV(k, l + 60.0 + i * s, 0.0D, 1D, 1.0D - length);
						tessellator.addVertexWithUV(k, (double) l + 12 + i * s + length * 48, 0.0D, 1D, 0.0D);
						tessellator.addVertexWithUV((double) k - 8, (double) l + 12 + i * s + length * 48, 0.0D, 0.5D, 0.0D);
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
			tessellator.addVertexWithUV((double) k + 8, l + 60.0 + i * s, 0.0D, 0, 1);
			tessellator.addVertexWithUV(64.0D + k - 8, l + 60.0 + i * s, 0.0D, 1, 1);
			tessellator.addVertexWithUV(64.0D + k - 8, (double) l + 12 + i * s, 0.0D, 1, 0);
			tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s, 0.0D, 0, 0);
			tessellator.draw();

			if (flag)
			{
				EntityClientPlayerMP ecpmp = (EntityClientPlayerMP) Minecraft.getMinecraft().theWorld.getPlayerEntityByName(nick);
				ResourceLocation skin = Minecraft.getMinecraft().thePlayer.getLocationSkin();
				Minecraft.getMinecraft().getTextureManager().bindTexture(skin);

				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV((double) k + 11, l + 57.0 + i * s, 0.0D, 1 / 8D, 2 / 4D);
				tessellator.addVertexWithUV(61.0D + k - 8, l + 57.0 + i * s, 0.0D, 2 / 8D, 2 / 4D);
				tessellator.addVertexWithUV(61.0D + k - 8, (double) l + 15 + i * s, 0.0D, 2 / 8D, 1 / 4D);
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
