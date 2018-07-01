package AAM.Client.Renderer.Entity;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.ElementalGuard;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ElemGuardRenderer extends RenderLiving
{
	public ElemGuardRenderer()
	{
		super(new ElementalGuard(), 0);
	}

	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		GL11.glPushMatrix();
		double k = 0.5;
		GL11.glTranslated(-k * e.getLookVec().xCoord, -k * e.getLookVec().yCoord, -k * e.getLookVec().zCoord);
		super.doRender(e, x, y, z, f1, f2);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return new ResourceLocation("aam", "textures/models/elemguard.png");
	}

}
