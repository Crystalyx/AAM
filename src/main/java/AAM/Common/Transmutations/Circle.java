package AAM.Common.Transmutations;

import org.lwjgl.opengl.GL11;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Tiles.TETransCircle.State;
import AAM.Utils.Color;
import AAM.Utils.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
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
			return (this.pt == b.pt) && (this.scale == b.scale) && (this.rev == b.rev);
		}
		return false;
	}

	public CirclePart pt;
	public double scale;
	public boolean rev;

	public Color col = new Color(208, 244, 244);
	public int maxTime = 40;

	public void render(double x, double y, double z, TETransCircle te, double scale, int time)
	{
		Tessellator t = Tessellator.instance;

		t.startDrawingQuads();
		// Logger.info(te.last.prepCol);
		if (te.state.equals(State.active))
		{
			Color c = te.last.prepCol;
			double tm = ((time + 1) / (float) this.maxTime);
			t.setColorRGBA((int) (c.red * tm + col.red * (1 - tm)), (int) (c.green * tm + col.green * (1 - tm)), (int) (c.blue * tm + col.blue * (1 - tm)), c.alpha);
		}
		if (te.state.equals(State.complete))
		{
			Color c = te.last.actCol;
			t.setColorRGBA(Math.min(c.red, 255), Math.min(c.green, 255), Math.min(c.blue, 255), 255);
		}

		if (rev)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(this.pt.blockRev);

		}
		else
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(this.pt.block);
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
		return "Name:" + this.pt.name + "; Size:" + this.scale + "; Reversed:" + this.rev;
	}
}
