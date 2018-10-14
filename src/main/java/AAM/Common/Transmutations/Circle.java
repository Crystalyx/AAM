package aam.common.transmutations;

import aam.common.tiles.TETransCircle;
import aam.common.tiles.TETransCircle.State;
import aam.utils.Color;
import aam.utils.MathUtils;
import aam.utils.MiscUtils;
import net.minecraft.client.renderer.Tessellator;

public class Circle
{
	public Circle(CirclePart pt, double scale, boolean rev)
	{
		this.pt = pt;
		this.scale = scale;
		this.rev = rev;
	}

	@Override
	public boolean equals(Object a)
	{
		if (a instanceof Circle)
		{
			Circle b = (Circle) a;
			return pt == b.pt && scale == b.scale && rev == b.rev;
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return ModCircles.parts.indexOf(pt) * 10000 + (int) MathUtils.round(scale * 10, 0) + MathUtils.boolToNum(rev, 1, 0);
	}

	public CirclePart pt;
	public double scale;
	public boolean rev;

	public Color col = new Color(208, 244, 244);
	public int maxTime = 40;

	public void render(TETransCircle te, double scale, int time)
	{
		Tessellator t = Tessellator.instance;

		t.startDrawingQuads();
		if (te.transm != null)
		{
			if (te.state.equals(State.active))
			{
				Color c = te.transm.prepCol;
				double tm = (time + 1) / (float) maxTime;
				t.setColorRGBA((int) (c.red * tm + col.red * (1 - tm)), (int) (c.green * tm + col.green * (1 - tm)), (int) (c.blue * tm + col.blue * (1 - tm)), c.alpha);
			}
			if (te.state.equals(State.complete))
			{
				Color c = te.transm.actCol;
				t.setColorRGBA(Math.min(c.red, 255), Math.min(c.green, 255), Math.min(c.blue, 255), 255);
			}
		}
		if (rev)
		{
			MiscUtils.bindTexture(pt.blockRev);

		}
		else
		{
			MiscUtils.bindTexture(pt.block);
		}

		t.addVertexWithUV(-0.5 * this.scale, 0, -0.5 * this.scale, 0.0, 0.0);
		t.addVertexWithUV(-0.5 * this.scale, 0, 0.5 * this.scale, 0.0, 1.0);
		t.addVertexWithUV(0.5 * this.scale, 0, 0.5 * this.scale, 1.0, 1.0);
		t.addVertexWithUV(0.5 * this.scale, 0, -0.5 * this.scale, 1.0, 0.0);

		t.addVertexWithUV(0.5 * this.scale, 0, -0.5 * this.scale, 1.0, 0.0);
		t.addVertexWithUV(0.5 * this.scale, 0, 0.5 * this.scale, 1.0, 1.0);
		t.addVertexWithUV(-0.5 * this.scale, 0, 0.5 * this.scale, 0.0, 1.0);
		t.addVertexWithUV(-0.5 * this.scale, 0, -0.5 * this.scale, 0.0, 0.0);

		t.draw();
	}

	@Override
	public String toString()
	{
		return "Name:" + pt.name + "; Size:" + scale + "; Reversed:" + rev;
	}
}
