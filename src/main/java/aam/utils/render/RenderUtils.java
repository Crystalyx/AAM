package aam.utils.render;

import aam.utils.Color;
import aam.utils.MathUtils;
import aam.utils.MiscUtils;
import aam.utils.functions.CylFunction;
import aam.utils.functions.Function;
import aam.utils.functions.SphFunction;
import aam.utils.vectors.Wec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.Random;

public class RenderUtils
{

	public static void renderFunction(String rl, SphFunction f, double x, double y, double z, double px, double pz, double dx, double dy)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		MiscUtils.bindTexture(rl);
		Tessellator t = Tessellator.instance;
		t.startDrawing(GL11.GL_QUADS);
		double n = Math.PI * 2;
		double dyaw = 0.1;
		double dpitch = 0.1;

		for (double yaw = 0; yaw < n; yaw += dyaw)
		{
			for (double pitch = -n; pitch <= n; pitch += dpitch)
			{
				double r = f.count2(yaw, pitch);

				Wec3 zz = MathUtils.getPosBy3DAngle(yaw, pitch, r);
				Wec3 zo = MathUtils.getPosBy3DAngle(yaw, pitch + dpitch, r);
				Wec3 oz = MathUtils.getPosBy3DAngle(yaw + dyaw, pitch, r);
				Wec3 oo = MathUtils.getPosBy3DAngle(yaw + dyaw, pitch + dpitch, r);

				t.addVertexWithUV(zz.x, zz.y, zz.z, dx * yaw, dy * pitch);
				t.addVertexWithUV(oz.x, oz.y, oz.z, dx * (yaw + 1), dy * pitch);
				t.addVertexWithUV(oo.x, oo.y, oo.z, dx * (yaw + 1), dy * (pitch + 1));
				t.addVertexWithUV(zo.x, zo.y, zo.z, dx * yaw, dy * (pitch + 1));

				t.addVertexWithUV(zz.x, zz.y, zz.z, dx * yaw, dy * (pitch + 1));
				t.addVertexWithUV(oz.x, oz.y, oz.z, dx * (yaw + 1), dy * (pitch + 1));
				t.addVertexWithUV(oo.x, oo.y, oo.z, dx * (yaw + 1), dy * pitch);
				t.addVertexWithUV(zo.x, zo.y, zo.z, dx * yaw, dy * pitch);
			}
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderFunction(String rl, CylFunction f, double x, double y, double z, double px, double pz, double dx, double dy, double height)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		MiscUtils.bindTexture(rl);
		Tessellator t = Tessellator.instance;
		t.startDrawing(GL11.GL_QUADS);
		double n = Math.PI * 2;
		double da = 0.1;
		double dh = 0.1;
		dy /= height;

		for (double ang = 0; ang < n; ang += da)
		{
			for (double hei = 0; hei <= height; hei += da)
			{
				Wec3 zz = new Wec3(Math.cos(ang) * f.count2(ang, hei), hei, Math.sin(ang) * f.count2(ang, hei));
				Wec3 zo = new Wec3(Math.cos(ang) * f.count2(ang, hei + dh), hei + dh, Math.sin(ang) * f.count2(ang, hei + dh));
				Wec3 oz = new Wec3(Math.cos(ang + da) * f.count2(ang + da, hei), hei, Math.sin(ang + da) * f.count2(ang + da, hei));
				Wec3 oo = new Wec3(Math.cos(ang + da) * f.count2(ang + da, hei + dh), hei + dh, Math.sin(ang + da) * f.count2(ang + da, hei + dh));

				int ia = (int) (ang / da);
				int ih = (int) (hei / dh);

				t.addVertexWithUV(zz.x, zz.y, zz.z, dx * ia, dy * ih);
				t.addVertexWithUV(oz.x, oz.y, oz.z, dx * (ia + 1), dy * ih);
				t.addVertexWithUV(oo.x, oo.y, oo.z, dx * (ia + 1), dy * (ih + 1));
				t.addVertexWithUV(zo.x, zo.y, zo.z, dx * ia, dy * (ih + 1));

				t.addVertexWithUV(zo.x, zo.y, zo.z, dx * ia, dy * (ih + 1));
				t.addVertexWithUV(oo.x, oo.y, oo.z, dx * (ia + 1), dy * (ih + 1));
				t.addVertexWithUV(oz.x, oz.y, oz.z, dx * (ia + 1), dy * ih);
				t.addVertexWithUV(zz.x, zz.y, zz.z, dx * ia, dy * ih);
			}
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderFunction(ResourceLocation rl, SphFunction f, double x, double y, double z, double px, double pz, double dx, double dy)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		MiscUtils.bindTexture(rl);
		Tessellator t = Tessellator.instance;
		t.startDrawing(GL11.GL_QUADS);
		double n = Math.PI * 2;
		double dyaw = 0.1;
		double dpitch = 0.1;

		for (double yaw = 0; yaw < n; yaw += dyaw)
		{
			for (double pitch = -n; pitch <= n; pitch += dpitch)
			{
				double r = f.count2(yaw, pitch);

				Wec3 zz = MathUtils.getPosBy3DAngle(yaw, pitch, r);
				Wec3 zo = MathUtils.getPosBy3DAngle(yaw, pitch + dpitch, r);
				Wec3 oz = MathUtils.getPosBy3DAngle(yaw + dyaw, pitch, r);
				Wec3 oo = MathUtils.getPosBy3DAngle(yaw + dyaw, pitch + dpitch, r);

				t.addVertexWithUV(zz.x, zz.y, zz.z, dx * yaw, dy * pitch);
				t.addVertexWithUV(oz.x, oz.y, oz.z, dx * (yaw + 1), dy * pitch);
				t.addVertexWithUV(oo.x, oo.y, oo.z, dx * (yaw + 1), dy * (pitch + 1));
				t.addVertexWithUV(zo.x, zo.y, zo.z, dx * yaw, dy * (pitch + 1));
			}
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderFunction(ResourceLocation rl, CylFunction f, double x, double y, double z, double px, double pz, double dx, double dy, double height)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		MiscUtils.bindTexture(rl);
		Tessellator t = Tessellator.instance;
		t.startDrawing(GL11.GL_QUADS);
		double n = Math.PI * 2;
		double da = 0.1;
		double dh = 0.1;
		dy /= height;

		for (double ang = 0; ang < n; ang += da)
		{
			for (double hei = 0; hei <= height; hei += da)
			{
				Wec3 zz = new Wec3(Math.cos(ang) * f.count2(ang, hei), hei, Math.sin(ang) * f.count2(ang, hei));
				Wec3 zo = new Wec3(Math.cos(ang) * f.count2(ang, hei + dh), hei + dh, Math.sin(ang) * f.count2(ang, hei + dh));
				Wec3 oz = new Wec3(Math.cos(ang + da) * f.count2(ang + da, hei), hei, Math.sin(ang + da) * f.count2(ang + da, hei));
				Wec3 oo = new Wec3(Math.cos(ang + da) * f.count2(ang + da, hei + dh), hei + dh, Math.sin(ang + da) * f.count2(ang + da, hei + dh));

				t.addVertexWithUV(zz.x, zz.y, zz.z, dx * ang, dy * hei);
				t.addVertexWithUV(oz.x, oz.y, oz.z, dx * (ang + 1), dy * hei);
				t.addVertexWithUV(oo.x, oo.y, oo.z, dx * (ang + 1), dy * (hei + 1));
				t.addVertexWithUV(zo.x, zo.y, zo.z, dx * ang, dy * (hei + 1));
			}
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderFunction(ResourceLocation rl, Function<Double> f, double x, double y, double z, double px, double pz, double dx, double dy)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		MiscUtils.bindTexture(rl);
		Tessellator t = Tessellator.instance;
		t.startDrawing(GL11.GL_QUADS);
		int n = 1;
		double dc = 0.1;

		for (double ii = 0; ii < n; ii += dc)
		{
			for (double ij = 0; ij < n; ij += dc)
			{
				double i = ii / n;
				double j = ij / n;
				double a = i + dc;
				double b = j + dc;

				t.addVertexWithUV(i, f.count2(i + px, b + pz), b, dx * ii, dy * ij);
				t.addVertexWithUV(a, f.count2(a + px, b + pz), b, dx * (ii + 1), dy * ij);
				t.addVertexWithUV(a, f.count2(a + px, j + pz), j, dx * (ii + 1), dy * (ij + 1));
				t.addVertexWithUV(i, f.count2(i + px, j + pz), j, dx * ii, dy * (ij + 1));
			}
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderSphere(ResourceLocation rl, double x, double y, double z, double r)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		MiscUtils.bindTexture(rl);
		Tessellator t = Tessellator.instance;
		t.startDrawing(GL11.GL_QUADS);
		int n = 50;
		for (int i = 0; i < n; i++)
		{
			double a = 2 * Math.PI / n;
			for (int j = -n; j <= n; j++)
			{
				t.addVertexWithUV(r * Math.sin(a * i) * Math.cos(a * j), r * Math.sin(a * j), r * Math.cos(a * i) * Math.cos(a * j), 0, 1);
				t.addVertexWithUV(r * Math.sin(a * (i + 1)) * Math.cos(a * j), r * Math.sin(a * j), r * Math.cos(a * (i + 1)) * Math.cos(a * j), 1, 1);
				t.addVertexWithUV(r * Math.sin(a * (i + 1)) * Math.cos(a * (j + 1)), r * Math.sin(a * (j + 1)), r * Math.cos(a * (i + 1)) * Math.cos(a * (j + 1)), 1, 0);
				t.addVertexWithUV(r * Math.sin(a * i) * Math.cos(a * (j + 1)), r * Math.sin(a * (j + 1)), r * Math.cos(a * i) * Math.cos(a * (j + 1)), 0, 0);
			}
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderSphere(String rl, double x, double y, double z, double r)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		MiscUtils.bindTexture(rl);
		Tessellator t = Tessellator.instance;
		t.startDrawing(GL11.GL_QUADS);
		int n = 50;
		for (int i = 0; i < n; i++)
		{
			double a = 2 * Math.PI / n;
			for (int j = -n; j <= n; j++)
			{
				t.addVertexWithUV(r * Math.sin(a * i) * Math.cos(a * j), r * Math.sin(a * j), r * Math.cos(a * i) * Math.cos(a * j), 0, 1);
				t.addVertexWithUV(r * Math.sin(a * (i + 1)) * Math.cos(a * j), r * Math.sin(a * j), r * Math.cos(a * (i + 1)) * Math.cos(a * j), 1, 1);
				t.addVertexWithUV(r * Math.sin(a * (i + 1)) * Math.cos(a * (j + 1)), r * Math.sin(a * (j + 1)), r * Math.cos(a * (i + 1)) * Math.cos(a * (j + 1)), 1, 0);
				t.addVertexWithUV(r * Math.sin(a * i) * Math.cos(a * (j + 1)), r * Math.sin(a * (j + 1)), r * Math.cos(a * i) * Math.cos(a * (j + 1)), 0, 0);
			}
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderBlock(RenderBlocks r, Block b, int meta, double x, double y, double z)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		ResourceLocation txt = Minecraft.getMinecraft().getTextureManager().getResourceLocation(new ItemStack(b, 1, meta).getItemSpriteNumber());
		Cube cub = new Cube(0, 0, 0, 64, 64, 64, 1, 64);
		cub.render(b);
		GL11.glPopMatrix();
	}

	public static void renderBlock(RenderBlocks r, Block b, int meta, double x, double y, double z, int sx, int sy, int sz, float scale, int length)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		Cube cub = new Cube(0, 0, 0, sx, sy, sz, scale, length);
		cub.render(b);
		GL11.glPopMatrix();
	}

	/**
	 * Rendering in inventory
	 * 
	 * @param r
	 *            RenderBlocks
	 * @param b
	 *            block To render
	 */
	public static void renderBlock(RenderBlocks r, Block b)
	{
		GL11.glPushMatrix();

		drawSides(r, b, getIconArray(b, 0), true);

		GL11.glPopMatrix();
	}

	private static IIcon[] getIconArray(Block b, int meta)
	{
		IIcon[] ret = new IIcon[6];

		for (int l = 0; l < 6; l++)
		{
			ret[l] = b.getIcon(l, meta);
		}

		return ret;
	}

	public static void drawSides(RenderBlocks renderblocks, Block block, IIcon[] icons, boolean solidtop)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icons[0]);
		tessellator.draw();
		if (solidtop)
		{
			GL11.glDisable(GL11.GL_ALPHA_TEST);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icons[1]);
		tessellator.draw();
		if (solidtop)
		{
			GL11.glEnable(GL11.GL_ALPHA_TEST);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icons[2]);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icons[3]);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icons[4]);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icons[5]);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	public static void drawSide(RenderBlocks renderblocks, Block block, IIcon icons, boolean solidtop)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		if (solidtop)
		{
			GL11.glDisable(GL11.GL_ALPHA_TEST);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		if (solidtop)
		{
			GL11.glEnable(GL11.GL_ALPHA_TEST);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	static IIcon[] getIconArray(IIcon b)
	{
		IIcon[] ret = new IIcon[6];

		for (int l = 0; l < 6; l++)
		{
			ret[l] = b;
		}

		return ret;
	}

	/**
	 * Renders an item held in hand as a 2D textureName with thickness
	 */
	public static void renderTextureIn2D(Tessellator tess, double xmin, double ymin, double ymax, double xmax, int p_78439_5_, int p_78439_6_, double p_78439_7_)
	{
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		tess.addVertexWithUV(0.0D, 0.0D, 0.0D, xmin, ymax);
		tess.addVertexWithUV(1.0D, 0.0D, 0.0D, xmax, ymax);
		tess.addVertexWithUV(1.0D, 1.0D, 0.0D, xmax, ymin);
		tess.addVertexWithUV(0.0D, 1.0D, 0.0D, xmin, ymin);
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, -1.0F);
		tess.addVertexWithUV(0.0D, 1.0D, 0.0F - p_78439_7_, xmin, ymin);
		tess.addVertexWithUV(1.0D, 1.0D, 0.0F - p_78439_7_, xmax, ymin);
		tess.addVertexWithUV(1.0D, 0.0D, 0.0F - p_78439_7_, xmax, ymax);
		tess.addVertexWithUV(0.0D, 0.0D, 0.0F - p_78439_7_, xmin, ymax);
		tess.draw();
		double f5 = 0.5F * (xmin - xmax) / p_78439_5_;
		double f6 = 0.5F * (ymax - ymin) / p_78439_6_;
		tess.startDrawingQuads();
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		int k;
		double f7;
		double f8;

		for (k = 0; k < p_78439_5_; ++k)
		{
			f7 = (double) k / (double) p_78439_5_;
			f8 = xmin + (xmax - xmin) * f7 - f5;
			tess.addVertexWithUV(f7, 0.0D, 0.0F - p_78439_7_, f8, ymax);
			tess.addVertexWithUV(f7, 0.0D, 0.0D, f8, ymax);
			tess.addVertexWithUV(f7, 1.0D, 0.0D, f8, ymin);
			tess.addVertexWithUV(f7, 1.0D, 0.0F - p_78439_7_, f8, ymin);
		}

		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(1.0F, 0.0F, 0.0F);
		double f9;

		for (k = 0; k < p_78439_5_; ++k)
		{
			f7 = (double) k / (double) p_78439_5_;
			f8 = xmin + (xmax - xmin) * f7 - f5;
			f9 = f7 + 1.0F / (double) p_78439_5_;
			tess.addVertexWithUV(f9, 1.0D, 0.0F - p_78439_7_, f8, ymin);
			tess.addVertexWithUV(f9, 1.0D, 0.0D, f8, ymin);
			tess.addVertexWithUV(f9, 0.0D, 0.0D, f8, ymax);
			tess.addVertexWithUV(f9, 0.0D, 0.0F - p_78439_7_, f8, ymax);
		}

		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);

		for (k = 0; k < p_78439_6_; ++k)
		{
			f7 = (double) k / (double) p_78439_6_;
			f8 = ymax + (ymin - ymax) * f7 - f6;
			f9 = f7 + 1.0F / (double) p_78439_6_;
			tess.addVertexWithUV(0.0D, f9, 0.0D, xmin, f8);
			tess.addVertexWithUV(1.0D, f9, 0.0D, xmax, f8);
			tess.addVertexWithUV(1.0D, f9, 0.0F - p_78439_7_, xmax, f8);
			tess.addVertexWithUV(0.0D, f9, 0.0F - p_78439_7_, xmin, f8);
		}

		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);

		for (k = 0; k < p_78439_6_; ++k)
		{
			f7 = (double) k / (double) p_78439_6_;
			f8 = ymax + (ymin - ymax) * f7 - f6;
			tess.addVertexWithUV(1.0D, f7, 0.0D, xmax, f8);
			tess.addVertexWithUV(0.0D, f7, 0.0D, xmin, f8);
			tess.addVertexWithUV(0.0D, f7, 0.0F - p_78439_7_, xmin, f8);
			tess.addVertexWithUV(1.0D, f7, 0.0F - p_78439_7_, xmax, f8);
		}

		tess.draw();
	}

	/**
	 * Renders an item held in hand as a 2D textureName with thickness
	 */
	public static void renderTextureIn2DwithColor(Tessellator tess, double xmin, double ymin, double ymax, double xmax, int p_78439_5_, int p_78439_6_, double p_78439_7_, Color col)
	{
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(0.0F, 0.0F, 1.0F);
		tess.addVertexWithUV(0.0D, 0.0D, 0.0D, xmin, ymax);
		tess.addVertexWithUV(1.0D, 0.0D, 0.0D, xmax, ymax);
		tess.addVertexWithUV(1.0D, 1.0D, 0.0D, xmax, ymin);
		tess.addVertexWithUV(0.0D, 1.0D, 0.0D, xmin, ymin);
		tess.draw();
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(0.0F, 0.0F, -1.0F);
		tess.addVertexWithUV(0.0D, 1.0D, 0.0F - p_78439_7_, xmin, ymin);
		tess.addVertexWithUV(1.0D, 1.0D, 0.0F - p_78439_7_, xmax, ymin);
		tess.addVertexWithUV(1.0D, 0.0D, 0.0F - p_78439_7_, xmax, ymax);
		tess.addVertexWithUV(0.0D, 0.0D, 0.0F - p_78439_7_, xmin, ymax);
		tess.draw();
		double f5 = 0.5F * (xmin - xmax) / p_78439_5_;
		double f6 = 0.5F * (ymax - ymin) / p_78439_6_;
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		int k;
		double f7;
		double f8;

		for (k = 0; k < p_78439_5_; ++k)
		{
			f7 = (double) k / (double) p_78439_5_;
			f8 = xmin + (xmax - xmin) * f7 - f5;
			tess.addVertexWithUV(f7, 0.0D, 0.0F - p_78439_7_, f8, ymax);
			tess.addVertexWithUV(f7, 0.0D, 0.0D, f8, ymax);
			tess.addVertexWithUV(f7, 1.0D, 0.0D, f8, ymin);
			tess.addVertexWithUV(f7, 1.0D, 0.0F - p_78439_7_, f8, ymin);
		}
		tess.draw();
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(1.0F, 0.0F, 0.0F);
		double f9;

		for (k = 0; k < p_78439_5_; ++k)
		{
			f7 = (double) k / (double) p_78439_5_;
			f8 = xmin + (xmax - xmin) * f7 - f5;
			f9 = f7 + 1.0F / (double) p_78439_5_;
			tess.addVertexWithUV(f9, 1.0D, 0.0F - p_78439_7_, f8, ymin);
			tess.addVertexWithUV(f9, 1.0D, 0.0D, f8, ymin);
			tess.addVertexWithUV(f9, 0.0D, 0.0D, f8, ymax);
			tess.addVertexWithUV(f9, 0.0D, 0.0F - p_78439_7_, f8, ymax);
		}
		tess.draw();
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(0.0F, 1.0F, 0.0F);

		for (k = 0; k < p_78439_6_; ++k)
		{
			f7 = (double) k / (double) p_78439_6_;
			f8 = ymax + (ymin - ymax) * f7 - f6;
			f9 = f7 + 1.0F / (double) p_78439_6_;
			tess.addVertexWithUV(0.0D, f9, 0.0D, xmin, f8);
			tess.addVertexWithUV(1.0D, f9, 0.0D, xmax, f8);
			tess.addVertexWithUV(1.0D, f9, 0.0F - p_78439_7_, xmax, f8);
			tess.addVertexWithUV(0.0D, f9, 0.0F - p_78439_7_, xmin, f8);
		}
		tess.draw();
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(0.0F, -1.0F, 0.0F);

		for (k = 0; k < p_78439_6_; ++k)
		{
			f7 = (double) k / (double) p_78439_6_;
			f8 = ymax + (ymin - ymax) * f7 - f6;
			tess.addVertexWithUV(1.0D, f7, 0.0D, xmax, f8);
			tess.addVertexWithUV(0.0D, f7, 0.0D, xmin, f8);
			tess.addVertexWithUV(0.0D, f7, 0.0F - p_78439_7_, xmin, f8);
			tess.addVertexWithUV(1.0D, f7, 0.0F - p_78439_7_, xmax, f8);
		}
		tess.draw();
	}

	public static void renderVisiblePlane(Wec3 s, Wec3 e)
	{
		Tessellator t = Tessellator.instance;
		// yz
		if (s.x == e.x)
		{
			t.startDrawingQuads();
			t.setNormal(1.0F, 0, 0);
			t.addVertexWithUV(0, s.y, s.z, 0, 0);
			t.addVertexWithUV(0, e.y, s.z, 0, 1);
			t.addVertexWithUV(0, e.y, e.z, 1, 1);
			t.addVertexWithUV(0, s.y, e.z, 1, 0);
			t.draw();
			t.startDrawingQuads();
			t.setNormal(-1.0F, 0, 0);
			t.addVertexWithUV(0, s.y, s.z, 0, 1);
			t.addVertexWithUV(0, e.y, s.z, 0, 0);
			t.addVertexWithUV(0, e.y, e.z, 1, 0);
			t.addVertexWithUV(0, s.y, e.z, 1, 1);
			t.draw();
		}
		// xz
		if (s.y == e.y)
		{
			t.startDrawingQuads();
			t.setNormal(1.0F, 0, 0);
			t.addVertexWithUV(s.x, 0, s.z, 0, 0);
			t.addVertexWithUV(e.x, 0, s.z, 0, 1);
			t.addVertexWithUV(e.x, 0, e.z, 1, 1);
			t.addVertexWithUV(s.x, 0, e.z, 1, 0);
			t.draw();
			t.startDrawingQuads();
			t.setNormal(-1.0F, 0, 0);
			t.addVertexWithUV(s.x, 0, s.z, 0, 1);
			t.addVertexWithUV(e.x, 0, s.z, 0, 0);
			t.addVertexWithUV(e.x, 0, e.z, 1, 0);
			t.addVertexWithUV(s.x, 0, e.z, 1, 1);
			t.draw();
		}
		// xy
		if (s.z == e.z)
		{
			t.startDrawingQuads();
			t.setNormal(1.0F, 0, 0);
			t.addVertexWithUV(s.x, s.y, 0, 0, 0);
			t.addVertexWithUV(s.x, e.y, 0, 0, 1);
			t.addVertexWithUV(e.x, e.y, 0, 1, 1);
			t.addVertexWithUV(e.x, s.y, 0, 1, 0);
			t.draw();
			t.startDrawingQuads();
			t.setNormal(-1.0F, 0, 0);
			t.addVertexWithUV(s.x, s.y, 0, 0, 1);
			t.addVertexWithUV(s.x, e.y, 0, 0, 0);
			t.addVertexWithUV(e.x, e.y, 0, 1, 0);
			t.addVertexWithUV(e.x, s.y, 0, 1, 1);
			t.draw();
		}
	}

	public static void bindTexture(String domain, String textur)
	{
		ResourceLocation txt = new ResourceLocation(domain, textur);
		Minecraft.getMinecraft().getTextureManager().bindTexture(txt);
	}

	public static void renderCube(Wec3 s, Wec3 e)
	{
		// y+
		renderVisiblePlane(new Wec3(s.x, e.y, s.z), e);
		// y-
		renderVisiblePlane(s, new Wec3(e.x, s.y, e.z));
		// x+
		renderVisiblePlane(new Wec3(e.x, s.y, s.z), e);
		// x-
		renderVisiblePlane(s, new Wec3(s.x, e.y, e.z));
		// z+
		renderVisiblePlane(new Wec3(s.x, s.y, e.z), e);
		// z-
		renderVisiblePlane(s, new Wec3(e.x, e.y, s.z));
	}
	/**
	 * Renders the given ItemStack in the world. Call ONLY from render methods!
	 * @version From DummyCore 2.0
	 * @param stk - ItemStack you wish to render
	 * @param posX - xCoord in the world
	 * @param posY - yCoord in the world
	 * @param posZ - zCoord in the world
	 * @param screenPosX - x position on the screen(given by render)
	 * @param screenPosY - y position on the screen(given by render)
	 * @param screenPosZ - z position on the screen(given by render)
	 * @param rotation - the X axis rotation
	 * @param rotationZ - the Z axis rotation
	 * @param colorRed - red color index(0.0F is 0% and 1.0F is 100%)
	 * @param colorGreen - green color index(0.0F is 0% and 1.0F is 100%)
	 * @param colorBlue - blue color index(0.0F is 0% and 1.0F is 100%)
	 * @param offsetX - offset by X
	 * @param offsetY - offset by Y
	 * @param offsetZ - offset by Z
	 * @param force3DRender - should be item rendered in 3d even if the fancy graphics are off?
	 */
	@SideOnly(Side.CLIENT)
	public static void renderItemStack_Full(ItemStack stk,double posX, double posY, double posZ, double screenPosX, double screenPosY, double screenPosZ, float rotation, float rotationZ, float colorRed, float colorGreen, float colorBlue, float offsetX, float offsetY, float offsetZ, boolean force3DRender)
	{
		if(stk != null)
		{
			ItemStack itemstack = stk.copy();
			itemstack.stackSize = 1; //Doing this so no weird glitches occur.
			RenderBlocks renderBlocksRi = new RenderBlocks();
			Random random = new Random();
			boolean renderWithColor = true;
			if (itemstack != null && itemstack.getItem() != null)
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(stk.getItemSpriteNumber()));
				TextureUtil.func_152777_a(false, false, 1.0F);
				random.setSeed(187L);
				GL11.glPushMatrix();
				float f2 = rotationZ;
				float f3 = rotation;
				byte b0 = 1;

				if (stk.stackSize > 1)
				{
					b0 = 2;
				}

				if (stk.stackSize > 5)
				{
					b0 = 3;
				}

				if (stk.stackSize > 20)
				{
					b0 = 4;
				}

				if (stk.stackSize > 40)
				{
					b0 = 5;
				}

				GL11.glTranslated((float)screenPosX+offsetX, (float)screenPosY+offsetY, (float)screenPosZ+offsetZ);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				float f6;
				float f7;
				int k;
				EntityItem fakeItem = new EntityItem(Minecraft.getMinecraft().theWorld, posX, posY, posZ, stk);
				GL11.glRotatef(f2, 0, 0, 1);
				if (ForgeHooksClient.renderEntityItem(fakeItem, itemstack, f2, f3, random, Minecraft.getMinecraft().renderEngine, renderBlocksRi, b0))
				{

				}
				else if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
				{
					Block block = Block.getBlockFromItem(itemstack.getItem());
					GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
					float f9 = 0.25F;
					k = block.getRenderType();

					if (k == 1 || k == 19 || k == 12 || k == 2)
					{
						f9 = 0.5F;
					}

					if (block.getRenderBlockPass() > 0)
					{
						GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
						GL11.glEnable(GL11.GL_BLEND);
						OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
					}

					GL11.glScalef(f9, f9, f9);

					for (int l = 0; l < b0; ++l)
					{
						GL11.glPushMatrix();

						if (l > 0)
						{
							f6 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
							f7 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
							float f8 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
							GL11.glTranslatef(f6, f7, f8);
						}

						renderBlocksRi.renderBlockAsItem(block, itemstack.getItemDamage(), 1.0F);
						GL11.glPopMatrix();
					}

					if (block.getRenderBlockPass() > 0)
					{
						GL11.glDisable(GL11.GL_BLEND);
					}
				}
				else
				{
					if (itemstack.getItem().requiresMultipleRenderPasses())
					{
						GL11.glScalef(0.5F, 0.5F, 0.5F);
						for (int j = 0; j < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++j)
						{
							random.setSeed(187L);
							itemstack.getItem().getIcon(itemstack, j);
							renderItemStack(stk, posX, posY, posZ, screenPosX, screenPosY, screenPosZ, rotation, colorRed, colorGreen, colorBlue, j, stk.stackSize,force3DRender);
						}
					}
					else
					{
						if (itemstack != null && itemstack.getItem() instanceof ItemCloth)
						{
							GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
							GL11.glEnable(GL11.GL_BLEND);
							OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
						}
						GL11.glScalef(0.5F, 0.5F, 0.5F);
						itemstack.getIconIndex();

						if (renderWithColor)
						{
							renderItemStack(stk, posX, posY, posZ, screenPosX, screenPosY, screenPosZ, rotation, colorRed, colorGreen, colorBlue, 0, stk.stackSize,force3DRender);
						}
						if (itemstack != null && itemstack.getItem() instanceof ItemCloth)
						{
							GL11.glDisable(GL11.GL_BLEND);
						}
					}
				}
				fakeItem = null;
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(stk.getItemSpriteNumber()));
				TextureUtil.func_147945_b();
			}
			itemstack = null;
		}
	}

	/**
	 * Sub-function to the first one. You shouldn't use this, however it is also possible.
	 * @version From DummyCore 2.0
	 * @param stk - ItemStack you wish to render
	 * @param posX - xCoord in the world
	 * @param posY - yCoord in the world
	 * @param posZ - zCoord in the world
	 * @param screenPosX - x position on the screen(given by render)
	 * @param screenPosY - y position on the screen(given by render)
	 * @param screenPosZ - z position on the screen(given by render)
	 * @param rotation - the X axis rotation
	 * @param colorRed - red color index(0.0F is 0% and 1.0F is 100%)
	 * @param colorGreen - green color index(0.0F is 0% and 1.0F is 100%)
	 * @param colorBlue - blue color index(0.0F is 0% and 1.0F is 100%)
	 * @param renderPass - the render pass of the ItemStack(is 0 for most of them, however may depend)
	 * @param itemsAmount - the amount of items in the ItemStack(or actually ItemStack.stackSize)
	 * @param force3DRender - should be item rendered in 3d even if the fancy graphics are off?
	 */
	@SideOnly(Side.CLIENT)
	public static void renderItemStack(ItemStack stk,double posX, double posY, double posZ, double screenPosX, double screenPosY, double screenPosZ, float rotation, float colorRed, float colorGreen, float colorBlue, int renderPass, int itemsAmount, boolean force3DRender)
	{
		final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
		Random random = new Random();
		IIcon iicon = stk.getItem().getIcon(stk, renderPass);

		Tessellator tessellator = Tessellator.instance;

		if (iicon == null)
		{
			TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
			ResourceLocation resourcelocation = texturemanager.getResourceLocation(stk.getItem().getSpriteNumber());
			iicon = ((TextureMap)texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
		}

		float minU = ((IIcon)iicon).getMinU();
		float maxU = ((IIcon)iicon).getMaxU();
		float minV = ((IIcon)iicon).getMinV();
		float maxV = ((IIcon)iicon).getMaxV();
		float f6 = 1.0F;
		float f7 = 0.5F;
		float f8 = 0.25F;
		float f10;

		if (Minecraft.getMinecraft().gameSettings.fancyGraphics || force3DRender)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
			float f9 = 0.0625F;
			f10 = 0.021875F;
			ItemStack itemstack = stk;
			int j = itemstack.stackSize;
			byte b0;

			if (j < 2)
			{
				b0 = 1;
			}
			else if (j < 16)
			{
				b0 = 2;
			}
			else if (j < 32)
			{
				b0 = 3;
			}
			else
			{
				b0 = 4;
			}

			GL11.glTranslatef(-f7, -f8, -((f9 + f10) * (float)b0 / 2.0F));

			for (int k = 0; k < b0; ++k)
			{
				if (k > 0)
				{
					float x = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
					float y = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
					random.nextFloat();
					GL11.glTranslatef(x, y, f9 + f10);
				}
				else
				{
					GL11.glTranslatef(0f, 0f, f9 + f10);
				}

				if (itemstack.getItemSpriteNumber() == 0)
				{
					Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				}
				else
				{
					Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
				}

				GL11.glColor4f(colorRed, colorGreen, colorBlue, 1.0F);
				ItemRenderer.renderItemIn2D(tessellator, maxU, minV, minU, maxV, ((IIcon)iicon).getIconWidth(), ((IIcon)iicon).getIconHeight(), f9);

				if (itemstack.hasEffect(renderPass))
				{
					GL11.glDepthFunc(GL11.GL_EQUAL);
					GL11.glDisable(GL11.GL_LIGHTING);
					Minecraft.getMinecraft().renderEngine.bindTexture(RES_ITEM_GLINT);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
					float f11 = 0.76F;
					GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
					GL11.glMatrixMode(GL11.GL_TEXTURE);
					GL11.glPushMatrix();
					float f12 = 0.125F;
					GL11.glScalef(f12, f12, f12);
					float f13 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
					GL11.glTranslatef(f13, 0.0F, 0.0F);
					GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
					ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glScalef(f12, f12, f12);
					f13 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
					GL11.glTranslatef(-f13, 0.0F, 0.0F);
					GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
					ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
					GL11.glPopMatrix();
					GL11.glMatrixMode(GL11.GL_MODELVIEW);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glDepthFunc(GL11.GL_LEQUAL);
				}
			}

			GL11.glPopMatrix();
		}
		else
		{
			for (int l = 0; l < itemsAmount; ++l)
			{
				GL11.glPushMatrix();

				if (l > 0)
				{
					f10 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
					float f16 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
					float f17 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
					GL11.glTranslatef(f10, f16, f17);
				}
				GL11.glColor4f(colorRed, colorGreen, colorBlue, 1.0F);
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 1.0F, 0.0F);
				tessellator.addVertexWithUV((double)(0.0F - f7), (double)(0.0F - f8), 0.0D, (double)minU, (double)maxV);
				tessellator.addVertexWithUV((double)(f6 - f7), (double)(0.0F - f8), 0.0D, (double)maxU, (double)maxV);
				tessellator.addVertexWithUV((double)(f6 - f7), (double)(1.0F - f8), 0.0D, (double)maxU, (double)minV);
				tessellator.addVertexWithUV((double)(0.0F - f7), (double)(1.0F - f8), 0.0D, (double)minU, (double)minV);
				tessellator.draw();
				GL11.glPopMatrix();
			}
		}
	}
}
