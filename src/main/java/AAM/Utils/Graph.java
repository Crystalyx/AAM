package aam.utils;

import org.lwjgl.opengl.GL11;

import aam.utils.vectors.AABB2;
import aam.utils.vectors.Matrix2;
import aam.utils.vectors.Vec2;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;

public class Graph
{
	public static Tessellator t = Tessellator.instance;

	public static void colorize(Color c)
	{
		if (c != null)
		{
			GL11.glColor4d(c.red / 255d, c.green / 255d, c.blue / 255d, c.alpha / 255d);
		}
	}

	public static void clearColor()
	{
		GL11.glColor4d(1, 1, 1, 1);
	}

	public static void renderAABB(AABB2 aabb, double z)
	{
		t.startDrawing(GL11.GL_QUADS);
		double ds = 3;

		t.addVertexWithUV(aabb.ox - ds, aabb.oy, z, 0, 0);
		t.addVertexWithUV(aabb.ox + ds, aabb.oy, z, 1, 0);
		t.addVertexWithUV(aabb.ox + ds, aabb.ty, z, 1, 1);
		t.addVertexWithUV(aabb.ox - ds, aabb.ty, z, 0, 1);

		t.addVertexWithUV(aabb.tx - ds, aabb.oy, z, 0, 0);
		t.addVertexWithUV(aabb.tx + ds, aabb.oy, z, 1, 0);
		t.addVertexWithUV(aabb.tx + ds, aabb.ty, z, 1, 1);
		t.addVertexWithUV(aabb.tx - ds, aabb.ty, z, 0, 1);

		t.addVertexWithUV(aabb.ox, aabb.oy - ds, z, 0, 0);
		t.addVertexWithUV(aabb.tx, aabb.oy - ds, z, 1, 0);
		t.addVertexWithUV(aabb.tx, aabb.oy + ds, z, 1, 1);
		t.addVertexWithUV(aabb.ox, aabb.oy + ds, z, 0, 1);

		t.addVertexWithUV(aabb.ox, aabb.ty - ds, z, 0, 0);
		t.addVertexWithUV(aabb.tx, aabb.ty - ds, z, 1, 0);
		t.addVertexWithUV(aabb.tx, aabb.ty + ds, z, 1, 1);
		t.addVertexWithUV(aabb.ox, aabb.ty + ds, z, 0, 1);
		t.draw();
	}

	public static void renderSqr(AABB2 aabb, double z)
	{
		t.startDrawing(GL11.GL_QUADS);

		t.addVertexWithUV(aabb.tx, aabb.ty, z, 1, 1);
		t.addVertexWithUV(aabb.tx, aabb.oy, z, 1, 0);
		t.addVertexWithUV(aabb.ox, aabb.oy, z, 0, 0);
		t.addVertexWithUV(aabb.ox, aabb.ty, z, 0, 1);

		t.draw();
	}

	public static void renderCircumference(Vec2 v, double r)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(v.x, v.y, 0);
		int n = (int) (3 * r);
		double da = 2 * Math.PI / n;
		t.startDrawing(GL11.GL_QUADS);

		for (int i = 0; i < n; i++)
		{
			t.addVertexWithUV(Math.cos(i * da) * r, Math.sin(i * da) * r, 0, 0, 0);
			t.addVertexWithUV(Math.cos((i + 1) * da) * r, Math.sin((i + 1) * da) * r, 0, 1, 0);
			t.addVertexWithUV(Math.cos((i + 1) * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 0, 1, 1);
			t.addVertexWithUV(Math.cos(i * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 0, 0, 1);

			t.addVertexWithUV(Math.cos(i * da) * r, Math.sin(i * da) * r, 0, 0, 1);
			t.addVertexWithUV(Math.cos((i + 1) * da) * r, Math.sin((i + 1) * da) * r, 0, 1, 1);
			t.addVertexWithUV(Math.cos((i + 1) * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 0, 1, 0);
			t.addVertexWithUV(Math.cos(i * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 0, 0, 0);
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void renderCircle(Vec2 v, double r)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(v.x, v.y, 0);
		int n = (int) (3 * r);
		double da = 2 * Math.PI / n;
		t.startDrawing(GL11.GL_QUADS);
		for (int i = 0; i < n; i++)
		{
			t.addVertexWithUV(Math.cos(i * da) * r, Math.sin(i * da) * r, 0, 0, 0);
			t.addVertexWithUV(Math.cos((i + 1) * da) * r, Math.sin((i + 1) * da) * r, 0, 1, 0);
			t.addVertexWithUV(0, 0, 0, 1, 1);
			t.addVertexWithUV(0, 0, 0, 0, 1);

			t.addVertexWithUV(Math.cos(i * da) * r, Math.sin(i * da) * r, 0, 0, 1);
			t.addVertexWithUV(Math.cos((i + 1) * da) * r, Math.sin((i + 1) * da) * r, 0, 1, 1);
			t.addVertexWithUV(0, 0, 0, 1, 0);
			t.addVertexWithUV(0, 0, 0, 0, 0);
		}

		t.draw();
		GL11.glPopMatrix();
	}

	public static void drawLine(Vec2 a, Vec2 b, double width, int type)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(a.x, a.y, 0);
		double atg = b.x - a.x;
		double btg = b.y - a.y;
		double ang = Math.toDegrees(Math.atan2(btg, atg));
		double l = Math.hypot(atg, btg);
		GL11.glRotated(ang, 0, 0, 1);

		// if (type == 0)
		t.startDrawing(GL11.GL_QUADS);
		double d = width / 2d;

		t.addVertexWithUV(0, -d, 0, 0, 0);
		t.addVertexWithUV(l, -d, 0, 1, 0);
		t.addVertexWithUV(l, d, 0, 1, 1);
		t.addVertexWithUV(0, d, 0, 0, 1);

		t.addVertexWithUV(0, d, 0, 0, 1);
		t.addVertexWithUV(l, d, 0, 1, 1);
		t.addVertexWithUV(l, -d, 0, 1, 0);
		t.addVertexWithUV(0, -d, 0, 0, 0);

		t.draw();

		GL11.glPopMatrix();
	}

	public static void drawSizedSqr(GuiScreen gui, int tsizex, int tsizey, int sizeu, int sizev, int sizex, int sizey, int startu, int startv, int borderx, int bordery)
	{
		GL11.glPushMatrix();

		// 0/0
		gui.drawTexturedModalRect(0, 0, startu, startv, borderx, bordery);
		// 1/0
		gui.drawTexturedModalRect(sizex - borderx, 0, startu + sizeu - borderx, startv, borderx, bordery);
		// 1/1
		gui.drawTexturedModalRect(sizex - borderx, sizey - bordery, startu + sizeu - borderx, startv + sizev - bordery, borderx, bordery);
		// 0/1
		gui.drawTexturedModalRect(0, sizey - bordery, startu, startv + sizev - bordery, borderx, bordery);

		int dpx = 4;

		int nu = Math.floorDiv(sizex - borderx * 2, dpx) + MathUtils.boolToNum(Math.floorMod(sizex - borderx * 2, dpx) > 0);
		int nv = Math.floorDiv(sizey - bordery * 2, dpx) + MathUtils.boolToNum(Math.floorMod(sizey - bordery * 2, dpx) > 0);

		for (int i = 0; i < nv; i++)
		{
			gui.drawTexturedModalRect(0, bordery + i * dpx, startu, startv + bordery, borderx, dpx);
			gui.drawTexturedModalRect(sizex - borderx, bordery + i * dpx, startu + sizeu - borderx, startv + bordery, borderx, dpx);
		}

		for (int i = 0; i < nu; i++)
		{
			gui.drawTexturedModalRect(borderx + i * dpx, 0, startu + borderx, startv, dpx, borderx);
			gui.drawTexturedModalRect(borderx + i * dpx, sizey - bordery, startu + borderx, startv + sizev - bordery, dpx, borderx);
		}

		for (int i = 0; i < nv; i++)
		{
			for (int j = 0; j < nu; j++)
			{
				gui.drawTexturedModalRect(borderx + dpx * j, bordery + dpx * i, startu + borderx, startv + bordery, dpx, dpx);
			}
		}

		GL11.glPopMatrix();
	}

	public static void drawLines(Vec2 a, Vec2 b, double width, int count, double interv, int type)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(a.x, a.y, 0);
		double atg = b.x - a.x;
		double btg = b.y - a.y;
		double ang = Math.toDegrees(Math.atan2(btg, atg));
		double l = Math.hypot(atg, btg);

		Vec2 cv = a.sub(b).crossProduct(Matrix2.i);
		cv.normalize();
		GL11.glTranslated(-interv * cv.x * (count - 1) / 2, -interv * cv.y * (count - 1) / 2, 0);
		for (int i = 0; i < count; i++)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(interv * cv.x * i, interv * cv.y * i, 0);
			GL11.glRotated(ang, 0, 0, 1);

			// if (type == 0)
			t.startDrawing(GL11.GL_QUADS);
			double d = width / 2;

			t.addVertexWithUV(0, -d, 0, 0, 0);
			t.addVertexWithUV(l, -d, 0, 1, 0);
			t.addVertexWithUV(l, d, 0, 1, 1);
			t.addVertexWithUV(0, d, 0, 0, 1);

			t.draw();
			GL11.glPopMatrix();
		}

		GL11.glPopMatrix();
	}
}
