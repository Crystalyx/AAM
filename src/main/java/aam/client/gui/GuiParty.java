package aam.client.gui;

import aam.client.gui.base.ButtonRMember;
import aam.common.soul.SoulWeaponType;
import aam.common.soul.Trait;
import aam.common.soul.WarriorType;
import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.PlayerSyncMessage;
import aam.utils.Logger;
import aam.utils.MathUtils;
import aam.utils.MiscUtils;
import aam.utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
		this.drawTexturedModalRect(x - w / 2, y - h / 2, 0, 0, w, h);
		GL11.glPopMatrix();

		this.renderParty(x + w / 2, y - h / 2);
		super.drawScreen(x, y, fl);
		if (cd > 0)
		{
			if (!message.equals(""))
			{
				FontRenderer f = mc.fontRenderer;
				f.drawString(message, x - message.length() * 2, y - h / 4, 16777215);
			}
			cd += messagelast - mc.theWorld.getWorldTime();
			messagelast = mc.theWorld.getWorldTime();
		}
		else
		{
			message = "";
			cd = 0;
			messagelast = 0;
		}
		textBoxFilter.drawTextBox();
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
		textBoxFilter.updateCursorCounter();
		textBoxFilter.setMaxStringLength(128);
		ph.addMember = textBoxFilter.getText();
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
		x = sr.getScaledWidth() / 2;
		y = sr.getScaledHeight() / 2;
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
		textBoxFilter = new GuiTextField(fontRendererObj, x - 64, y - 20, 128, 15);
		textBoxFilter.setMaxStringLength(128);
		textBoxFilter.setText(ph.addMember);

		GuiButton gb = new GuiButton(0, x - 35, y + 4, 70, 20, "Invite Player");

		SwitchButton classUp = new SwitchButton(7, x - w / 2 - ps / 2, y - h / 2 - 8, 32, 32, "", true);

		SwitchButton classDown = new SwitchButton(8, x - w / 2 - ps / 2, y - h / 2 + 24, 32, 32, "", false);
		buttonList.add(gb);
		buttonList.add(classUp);
		buttonList.add(classDown);

		SwitchButton swordUp = new SwitchButton(9, x - w / 2 - ps / 2, y - h / 2 + 56, 32, 32, "", true);
		SwitchButton swordDown = new SwitchButton(10, x - w / 2 - ps / 2, y - h / 2 + 88, 32, 32, "", false);

		buttonList.add(swordUp);
		buttonList.add(swordDown);

		for (int i = 0; i < 6; i++)
		{
			ButtonRMember brm = new ButtonRMember(i + 1, x + 109, y - 92 + 32 * i, 10, 10);
			buttonList.add(brm);
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
				textBoxFilter.setText("");
			}
			else
			{
				message = EnumChatFormatting.DARK_RED + "There is no player with such nickname or he is offline";
				cd = 100;
				messagelast = mc.theWorld.getWorldTime();
			}
			ph.addMember = "";
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 7)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MathUtils.cycle(ph.warrior.ordinal() + 1, 0, WarriorType.values().length - 1);
			ph.warrior = WarriorType.values()[id];
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 8)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MathUtils.cycle(ph.warrior.ordinal() - 1, 0, WarriorType.values().length - 1);
			ph.warrior = WarriorType.values()[id];
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 9)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MathUtils.cycle(ph.sword.ordinal() + 1, 0, SoulWeaponType.values().length - 1);
			ph.sword = SoulWeaponType.values()[id];
			AlchemicalDispatcher.sendToServer(new PlayerSyncMessage(mc.thePlayer));
		}
		if (b.id == 10)
		{
			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
			int id = MathUtils.cycle(ph.sword.ordinal() - 1, 0, SoulWeaponType.values().length - 1);
			ph.sword = SoulWeaponType.values()[id];
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
		GL11.glTranslated(dPad - 4, dPad + 88, 0);
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

						// ===============soul=================
						Minecraft.getMinecraft().getTextureManager().bindTexture(soul);

						tessellator.startDrawingQuads();
						tessellator.addVertexWithUV(k, l + 60.0 + i * s, 0.0D, 0.0D, 1.0D);
						tessellator.addVertexWithUV((double) k + 8, l + 60.0 + i * s, 0.0D, 0.5D, 1.0D);
						tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s, 0.0D, 0.5D, 0.0D);
						tessellator.addVertexWithUV(k, (double) l + 12 + i * s, 0.0D, 0.0D, 0.0D);
						tessellator.draw();

						EntityPlayer ep = p.worldObj.getPlayerEntityByName(ph.party.get(i));
						PlayerDataHandler eph = PlayerDataHandler.get(ep);
						double length = 1 - eph.getCurrentSoul() / (double) eph.getTrait(Trait.Soul);

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

						length = 1 - ep.getHealth() / ep.getMaxHealth();

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
					{
						Minecraft.getMinecraft().getTextureManager().bindTexture(off);
					}
				}
				else
				{
					Minecraft.getMinecraft().getTextureManager().bindTexture(off);
				}
			}
			else
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(nul);
			}

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
		if (textBoxFilter.isFocused())
		{
			textBoxFilter.textboxKeyTyped(par1, par2);

			String srch = textBoxFilter.getText();

			if (!ph.addMember.equals(srch))
			{
				ph.addMember = srch;
			}
		}

		if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.getKeyCode() && !textBoxFilter.isFocused())
		{
			mc.thePlayer.closeScreen();
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
			textBoxFilter.setText("");
		}

		textBoxFilter.mouseClicked(x, y, mouseButton);
	}
}
