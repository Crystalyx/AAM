package AAM.Client.Renderer.Entity;

import org.lwjgl.opengl.GL11;

import AAM.Common.Entity.EntityBloodball;
import AAM.Utils.MiscUtils;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class BloodballRenderer extends Render
{

	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		if (e instanceof EntityBloodball)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			Tessellator t = Tessellator.instance;

			float f4 = 1.0F;
			float f5 = 0.5F;
			float f6 = 0.25F;
			MiscUtils.bindTexture("aam:textures/items/bloodball.png");
			t.startDrawingQuads();
			t.setNormal(0.0F, 1.0F, 0.0F);
			t.addVertexWithUV(-0.5, -0.25, 0.0D, 0, 1);
			t.addVertexWithUV(0.5, -0.25, 0.0D, 1, 1);
			t.addVertexWithUV(0.5, 0.75, 0.0D, 1, 0);
			t.addVertexWithUV(-0.5, 0.75, 0.0D, 0, 0);
			t.draw();
			GL11.glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return null;
	}

}
