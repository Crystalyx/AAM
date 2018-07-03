package AAM.Common.Event;

import org.lwjgl.opengl.GL11;

import AAM.Common.Items.ModItems;
import AAM.Common.Items.Artifacts.CrystalBow;
import AAM.Common.Items.Soul.Artifact;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Render.RenderUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class ClientRenderHelper
{
	public static ResourceLocation back = new ResourceLocation("aam", "textures/hud/party_back.png");
	public static ResourceLocation bar_mana = new ResourceLocation("aam", "textures/hud/bar_mana.png");
	public static ResourceLocation bar_soul = new ResourceLocation("aam", "textures/hud/bar_soul.png");
	public static ResourceLocation bar_mana_long = new ResourceLocation("aam", "textures/hud/bar_mana.png");
	public static ResourceLocation off = new ResourceLocation("aam", "textures/hud/member_offline.png");
	public static ResourceLocation on = new ResourceLocation("aam", "textures/hud/member_online.png");
	public static ResourceLocation nul = new ResourceLocation("aam", "textures/hud/member_no.png");
	public static ResourceLocation life = new ResourceLocation("aam", "textures/hud/member_life.png");
	public static ResourceLocation soul = new ResourceLocation("aam", "textures/hud/member_soul.png");

	@SubscribeEvent
	public void onClientRenderTick(RenderGameOverlayEvent.Pre event)
	{
		if (event.type != ElementType.ALL)
		{
			EntityPlayer p = Minecraft.getMinecraft().thePlayer;
			PlayerDataHandler ph = PlayerDataHandler.get(p);
			FontRenderer f = Minecraft.getMinecraft().fontRenderer;
			if (event.type == ElementType.CROSSHAIRS)
			{
				Minecraft mc = Minecraft.getMinecraft();
				ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

				int ps = 72;
				int s = 64;
				int k = ps / 2 - 24;
				int l = 20;

				int w = sr.getScaledWidth() * 2;
				int h = sr.getScaledHeight();

				GL11.glPushMatrix();
				Minecraft.getMinecraft().getTextureManager().bindTexture(back);
				GL11.glDepthMask(true);
				GL11.glScaled(0.5D, 0.5D, 0.5D);
				Tessellator tessellator = Tessellator.instance;
				GL11.glTranslated(w - ps, 0, 0);

				for (int i = 0; i < Math.min(6, ph.party.size()); i++)
				{
					String nick = "";
					boolean flag = false;

					if (ph.party.size() - 1 >= i)
					{
						if (ph.party.get(i) != null)
						{
							Minecraft.getMinecraft().getTextureManager().bindTexture(back);
							GL11.glColor4d(1.0, 1.0, 1.0, 0.5);

							tessellator.startDrawingQuads();
							tessellator.addVertexWithUV(0, l + 72.0 + s * i, 0.0D, 0.0D, 1.0D);
							tessellator.addVertexWithUV(ps, l + 72.0 + s * i, 0.0D, 0.5D, 1.0D);
							tessellator.addVertexWithUV(ps, l + 8.0 + s * i, 0.0D, 0.5D, 0.0D);
							tessellator.addVertexWithUV(0, l + 8.0 + s * i, 0.0D, 0.0D, 0.0D);
							tessellator.draw();

							GL11.glColor4d(1.0, 1.0, 1.0, 1.0);

							nick = ph.party.get(i);
							f.drawString(nick, k - nick.length() * 2 + 20, l + s * (i + 1) - 1, 0xFFFFFF);

							if (p.worldObj.getPlayerEntityByName(ph.party.get(i)) != null)
							{
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
								double length = 1 - ((double) eph.getCurrentSoul() / (double) eph.getMaxSoul());

								tessellator.startDrawingQuads();
								tessellator.addVertexWithUV(k, l + 60.0 + i * s, 0.0D, 0.5D, 1.0D - length);
								tessellator.addVertexWithUV((double) k + 8, l + 60.0 + i * s, 0.0D, 1D, 1.0D - length);
								tessellator.addVertexWithUV((double) k + 8, (double) l + 12 + i * s + length * 48 + 3 / 128, 0.0D, 1D, 0.0D);
								tessellator.addVertexWithUV(k, (double) l + 12 + i * s + length * 48 + 3 / 128, 0.0D, 0.5D, 0.0D);
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
								tessellator.addVertexWithUV(k, (double) l + 12 + i * s + length * 48 + 3 / 128, 0.0D, 1D, 0.0D);
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
			if (event.type == ElementType.CROSSHAIRS)
			{
				Minecraft mc = Minecraft.getMinecraft();
				int k = 5;
				int l = 5;

				GL11.glPushMatrix();
				Minecraft.getMinecraft().getTextureManager().bindTexture(bar_mana);
				GL11.glDepthMask(true);
				GL11.glScaled(0.5D, 0.5D, 0.5D);
				GL11.glColor4d(1.0, 1.0, 1.0, 0.5);
				Tessellator t = Tessellator.instance;

				GL11.glColor4d(1.0, 1.0, 1.0, 1.0);

				t.startDrawingQuads();
				t.addVertexWithUV(k, l + 128, 0.0D, 0.0D, 1.0D);
				t.addVertexWithUV(32.0D + k, l + 128, 0.0D, 0.5D, 1.0D);
				t.addVertexWithUV(32.0D + k, l, 0.0D, 0.5D, 0.0D);
				t.addVertexWithUV(k, l, 0.0D, 0.0D, 0.0D);
				t.draw();

				double lgt = 1 - (ph.getCurrentSoul() / ((double) ph.getMaxSoul()));

				double mr = 118 / 128d;
				double ls = 10 / 128d;
				double v = 108 / 128d;
				double lt = 108;

				t.startDrawingQuads();
				t.addVertexWithUV(k, l + 118, 0, 0.5D, mr);
				t.addVertexWithUV(32.0D + k, l + 118, 0.0D, 1D, mr);
				t.addVertexWithUV(32.0D + k, l + 10 + lgt * lt, 0.0D, 1D, ls + lgt * v);
				t.addVertexWithUV(k, l + 10 + lgt * lt, 0.0D, 0.5D, ls + lgt * v);
				t.draw();
				double dm = ph.getFullMeleeDamage(false);
				String dam = dm + "";
				dam = dam.substring(0, dam.indexOf(".") + 2);

				f.drawString("Soul: " + ph.getCurrentSoul() + "/" + ph.getMaxSoul(), k + 32, l + 5, new Color(255, 255, 255).hex);
				f.drawString("Level: " + ph.getSoulLevel(), k + 32, l + 15, new Color(255, 255, 255).hex);
				f.drawString("Expirience: " + (ph.soulxp) + "/" + (100 * ph.soulLevel), k + 32, l + 25, new Color(255, 255, 255).hex);
				f.drawString("Damage: " + dam, k + 32, l + 35, new Color(255, 255, 255).hex);

				MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
				if (mop != null)
				{
					if (mop.typeOfHit == MovingObjectType.BLOCK)
					{
						Block block = p.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
						f.drawString("Block: " + block.getLocalizedName(), k + 32, l + 55, new Color(255, 255, 255).hex);
						f.drawString("Meta: " + p.worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ), k + 32, l + 65, new Color(255, 255, 255).hex);
						f.drawString("Has TileEntity: " + (p.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) != null), k + 32, l + 75, new Color(255, 255, 255).hex);
						if (p.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) != null)
						{
							f.drawString("TileEntity: " + p.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ).getClass().getName(), k + 32, l + 85, new Color(255, 255, 255).hex);
						}
					}
					if (mop.typeOfHit == MovingObjectType.ENTITY)
					{
						f.drawString("Entity: " + mop.entityHit.getCommandSenderName(), k + 32, l + 55, new Color(255, 255, 255).hex);
						if (mop.entityHit instanceof EntityLivingBase)
						{
							EntityLivingBase e = (EntityLivingBase) mop.entityHit;

							f.drawString("Health: " + e.getHealth() + "/" + e.getMaxHealth(), k + 32, l + 65, new Color(255, 255, 255).hex);
							float specdmg = ph.getFullMeleeDamageAgainst(e, false);
							f.drawString("Specific Damage: " + specdmg, k + 32, l + 85, new Color(255, 255, 255).hex);
						}
					}
				}
				GL11.glPopMatrix();
			}
		}
	}

	@SubscribeEvent
	public void drawScreen(DrawScreenEvent e)
	{
		if (e.gui instanceof GuiContainer)
		{
			GuiContainer gc = (GuiContainer) e.gui;
			if (gc.isCtrlKeyDown())
			{
				GL11.glPushMatrix();
				GL11.glScaled(0.5, 0.5, 0.5);
				RenderHelper.disableStandardItemLighting();
				ScaledResolution sr = new ScaledResolution(gc.mc, gc.mc.displayWidth, gc.mc.displayHeight);
				int sx = 3 * (sr.getScaledWidth()) / 4;
				int sy = sr.getScaledHeight() / 2;
				GL11.glTranslated(sx, sy, 0);

				for (int i = 0; i < gc.inventorySlots.getInventory().size(); i++)
				{

					Slot s = gc.inventorySlots.getSlot(i);
					int x = s.xDisplayPosition * 2;
					int y = s.yDisplayPosition * 2;
					gc.mc.fontRenderer.drawString(s.getSlotIndex() + "", x, y, new Color(255, 255, 255).hex);
				}
				GL11.glTranslated(sx, sy, 0);
				RenderHelper.enableStandardItemLighting();
				GL11.glPopMatrix();

			}
		}
	}

	@SubscribeEvent
	public void onOverlay(DrawBlockHighlightEvent event)
	{

	}

	@SubscribeEvent
	public void modelrender(EntityViewRenderEvent e)
	{
		if (Minecraft.getMinecraft().renderViewEntity instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) Minecraft.getMinecraft().renderViewEntity;

		}
	}

	@SubscribeEvent
	public void render(RenderPlayerEvent.Pre e)
	{
		if (e.entityPlayer.worldObj.isRemote)
		{
			EntityPlayer p = e.entityPlayer;
			PlayerDataHandler ph = PlayerDataHandler.get(p);

			int dt = MiscUtils.boolToNum(ph.arbitur) * 4;

			if (ph.getPermission() > 0)
			{
				int meta = ph.sword.ordinal();
				ph.soulTag.setString("Owner", p.getGameProfile().getName());
				ItemStack sword = new ItemStack(ModItems.SoulSword, 1, meta);
				sword.setTagCompound(PlayerDataHandler.get(p).soulTag);

				String way = "aam:textures/items/" + SoulSword.ways[0];
				String art = "aam:textures/items/soulsword/component_nil";
				String bow = "aam:textures/items/" + CrystalBow.ways[0];

				way = "aam:textures/items/" + SoulSword.ways[sword.getItemDamage() + dt];
				if (ph.art)
					art = "aam:textures/items/" + Artifact.ways[ph.stype.ordinal()];
				GL11.glPushMatrix();

				GL11.glTranslated(0.5, 0, 0);
				int n = 6;
				double ang = 360 / n;
				for (int i = 0; i < n; i++)
				{
					GL11.glPushMatrix();

					GL11.glTranslated(-1 / 2d, -1 / 2d, 0);

					GL11.glRotated(p.rotationYawHead, 0, -1, 0);
					GL11.glRotated(ang * i, 0, 0, 1);
					if (p.isBlocking() && ph.bow)
					{
						GL11.glRotated(80, 1, 1, 0);
						int r = 1;
						GL11.glTranslated(r, -r, r);
					}
					GL11.glTranslated(-1 / 2d, -1 / 2d, 0);

					GL11.glTranslated(-1, 1, 0);

					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way + ".png"));
					Tessellator tess = Tessellator.instance;
					RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, 0.05F);

					GL11.glScaled(0.25, 0.25, 1.0);
					GL11.glTranslated(2.5, 0.5, 0);

					if (ph.bow)
					{
						GL11.glTranslated(0, 0, 0.01);
						Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
						RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, 0.05F);

						GL11.glTranslated(0, 0, -0.02);
						Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
						RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, 0.05F);

						GL11.glTranslated(0, 0, 0.01);
					}

					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(art + ".png"));
					RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, 0.05F);

					GL11.glPopMatrix();
				}
				GL11.glPopMatrix();
			}
		}
	}
}
