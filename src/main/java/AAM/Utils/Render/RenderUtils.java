package AAM.Utils.Render;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
import AAM.Utils.Functions.CylFunction;
import AAM.Utils.Functions.Function;
import AAM.Utils.Functions.SphFunction;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

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

				Wec3 zz = MiscUtils.getPosBy3DAngle(yaw, pitch, r);
				Wec3 zo = MiscUtils.getPosBy3DAngle(yaw, pitch + dpitch, r);
				Wec3 oz = MiscUtils.getPosBy3DAngle(yaw + dyaw, pitch, r);
				Wec3 oo = MiscUtils.getPosBy3DAngle(yaw + dyaw, pitch + dpitch, r);

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

				Wec3 zz = MiscUtils.getPosBy3DAngle(yaw, pitch, r);
				Wec3 zo = MiscUtils.getPosBy3DAngle(yaw, pitch + dpitch, r);
				Wec3 oz = MiscUtils.getPosBy3DAngle(yaw + dyaw, pitch, r);
				Wec3 oo = MiscUtils.getPosBy3DAngle(yaw + dyaw, pitch + dpitch, r);

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
		Cube cub = new Cube(0, 0, 0, 64, 64, 64, (float) 1, 64);
		cub.render(b);
		GL11.glPopMatrix();
	}

	public static void renderBlock(RenderBlocks r, Block b, int meta, double x, double y, double z, int sx, int sy, int sz, float scale, int length)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		Cube cub = new Cube(0, 0, 0, sx, sy, sz, (float) scale, length);
		cub.render(b);
		GL11.glPopMatrix();
	}

	/**
	 * Rendering in inventory
	 * 
	 * @param r
	 *            RenderBlocks
	 * @param b
	 *            block To Render
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
	 * Renders an item held in hand as a 2D texture with thickness
	 */
	public static void renderTextureIn2D(Tessellator tess, double xmin, double ymin, double ymax, double xmax, int p_78439_5_, int p_78439_6_, double p_78439_7_)
	{
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		tess.addVertexWithUV(0.0D, 0.0D, 0.0D, (double) xmin, (double) ymax);
		tess.addVertexWithUV(1.0D, 0.0D, 0.0D, (double) xmax, (double) ymax);
		tess.addVertexWithUV(1.0D, 1.0D, 0.0D, (double) xmax, (double) ymin);
		tess.addVertexWithUV(0.0D, 1.0D, 0.0D, (double) xmin, (double) ymin);
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, -1.0F);
		tess.addVertexWithUV(0.0D, 1.0D, (double) (0.0F - p_78439_7_), (double) xmin, (double) ymin);
		tess.addVertexWithUV(1.0D, 1.0D, (double) (0.0F - p_78439_7_), (double) xmax, (double) ymin);
		tess.addVertexWithUV(1.0D, 0.0D, (double) (0.0F - p_78439_7_), (double) xmax, (double) ymax);
		tess.addVertexWithUV(0.0D, 0.0D, (double) (0.0F - p_78439_7_), (double) xmin, (double) ymax);
		tess.draw();
		double f5 = 0.5F * (xmin - xmax) / (double) p_78439_5_;
		double f6 = 0.5F * (ymax - ymin) / (double) p_78439_6_;
		tess.startDrawingQuads();
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		int k;
		double f7;
		double f8;

		for (k = 0; k < p_78439_5_; ++k)
		{
			f7 = (double) k / (double) p_78439_5_;
			f8 = xmin + (xmax - xmin) * f7 - f5;
			tess.addVertexWithUV((double) f7, 0.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymax);
			tess.addVertexWithUV((double) f7, 0.0D, 0.0D, (double) f8, (double) ymax);
			tess.addVertexWithUV((double) f7, 1.0D, 0.0D, (double) f8, (double) ymin);
			tess.addVertexWithUV((double) f7, 1.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymin);
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
			tess.addVertexWithUV((double) f9, 1.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymin);
			tess.addVertexWithUV((double) f9, 1.0D, 0.0D, (double) f8, (double) ymin);
			tess.addVertexWithUV((double) f9, 0.0D, 0.0D, (double) f8, (double) ymax);
			tess.addVertexWithUV((double) f9, 0.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymax);
		}

		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);

		for (k = 0; k < p_78439_6_; ++k)
		{
			f7 = (double) k / (double) p_78439_6_;
			f8 = ymax + (ymin - ymax) * f7 - f6;
			f9 = f7 + 1.0F / (double) p_78439_6_;
			tess.addVertexWithUV(0.0D, (double) f9, 0.0D, (double) xmin, (double) f8);
			tess.addVertexWithUV(1.0D, (double) f9, 0.0D, (double) xmax, (double) f8);
			tess.addVertexWithUV(1.0D, (double) f9, (double) (0.0F - p_78439_7_), (double) xmax, (double) f8);
			tess.addVertexWithUV(0.0D, (double) f9, (double) (0.0F - p_78439_7_), (double) xmin, (double) f8);
		}

		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);

		for (k = 0; k < p_78439_6_; ++k)
		{
			f7 = (double) k / (double) p_78439_6_;
			f8 = ymax + (ymin - ymax) * f7 - f6;
			tess.addVertexWithUV(1.0D, (double) f7, 0.0D, (double) xmax, (double) f8);
			tess.addVertexWithUV(0.0D, (double) f7, 0.0D, (double) xmin, (double) f8);
			tess.addVertexWithUV(0.0D, (double) f7, (double) (0.0F - p_78439_7_), (double) xmin, (double) f8);
			tess.addVertexWithUV(1.0D, (double) f7, (double) (0.0F - p_78439_7_), (double) xmax, (double) f8);
		}

		tess.draw();
	}

	/**
	 * Renders an item held in hand as a 2D texture with thickness
	 */
	public static void renderTextureIn2DwithColor(Tessellator tess, double xmin, double ymin, double ymax, double xmax, int p_78439_5_, int p_78439_6_, double p_78439_7_, Color col)
	{
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(0.0F, 0.0F, 1.0F);
		tess.addVertexWithUV(0.0D, 0.0D, 0.0D, (double) xmin, (double) ymax);
		tess.addVertexWithUV(1.0D, 0.0D, 0.0D, (double) xmax, (double) ymax);
		tess.addVertexWithUV(1.0D, 1.0D, 0.0D, (double) xmax, (double) ymin);
		tess.addVertexWithUV(0.0D, 1.0D, 0.0D, (double) xmin, (double) ymin);
		tess.draw();
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(0.0F, 0.0F, -1.0F);
		tess.addVertexWithUV(0.0D, 1.0D, (double) (0.0F - p_78439_7_), (double) xmin, (double) ymin);
		tess.addVertexWithUV(1.0D, 1.0D, (double) (0.0F - p_78439_7_), (double) xmax, (double) ymin);
		tess.addVertexWithUV(1.0D, 0.0D, (double) (0.0F - p_78439_7_), (double) xmax, (double) ymax);
		tess.addVertexWithUV(0.0D, 0.0D, (double) (0.0F - p_78439_7_), (double) xmin, (double) ymax);
		tess.draw();
		double f5 = 0.5F * (xmin - xmax) / (double) p_78439_5_;
		double f6 = 0.5F * (ymax - ymin) / (double) p_78439_6_;
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
			tess.addVertexWithUV((double) f7, 0.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymax);
			tess.addVertexWithUV((double) f7, 0.0D, 0.0D, (double) f8, (double) ymax);
			tess.addVertexWithUV((double) f7, 1.0D, 0.0D, (double) f8, (double) ymin);
			tess.addVertexWithUV((double) f7, 1.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymin);
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
			tess.addVertexWithUV((double) f9, 1.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymin);
			tess.addVertexWithUV((double) f9, 1.0D, 0.0D, (double) f8, (double) ymin);
			tess.addVertexWithUV((double) f9, 0.0D, 0.0D, (double) f8, (double) ymax);
			tess.addVertexWithUV((double) f9, 0.0D, (double) (0.0F - p_78439_7_), (double) f8, (double) ymax);
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
			tess.addVertexWithUV(0.0D, (double) f9, 0.0D, (double) xmin, (double) f8);
			tess.addVertexWithUV(1.0D, (double) f9, 0.0D, (double) xmax, (double) f8);
			tess.addVertexWithUV(1.0D, (double) f9, (double) (0.0F - p_78439_7_), (double) xmax, (double) f8);
			tess.addVertexWithUV(0.0D, (double) f9, (double) (0.0F - p_78439_7_), (double) xmin, (double) f8);
		}
		tess.draw();
		tess.startDrawingQuads();
		tess.setColorRGBA(col.red, col.green, col.blue, 255);
		tess.setNormal(0.0F, -1.0F, 0.0F);

		for (k = 0; k < p_78439_6_; ++k)
		{
			f7 = (double) k / (double) p_78439_6_;
			f8 = ymax + (ymin - ymax) * f7 - f6;
			tess.addVertexWithUV(1.0D, (double) f7, 0.0D, (double) xmax, (double) f8);
			tess.addVertexWithUV(0.0D, (double) f7, 0.0D, (double) xmin, (double) f8);
			tess.addVertexWithUV(0.0D, (double) f7, (double) (0.0F - p_78439_7_), (double) xmin, (double) f8);
			tess.addVertexWithUV(1.0D, (double) f7, (double) (0.0F - p_78439_7_), (double) xmax, (double) f8);
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
}
