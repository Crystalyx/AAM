package AAM.Client.Renderer.Entity;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Elemental;
import AAM.Client.Model.ElementalGuard;
import AAM.Common.Entity.Elemental.State;
import AAM.Utils.Color;
import AAM.Utils.Render.Cube;
import AAM.Utils.Render.RenderUtils;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
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
		super.doRender(e, x, y, z, f1, f2);
		// if (e instanceof AAM.Common.Entity.ElementalGuard)
		// {
		// AAM.Common.Entity.ElementalGuard b =
		// (AAM.Common.Entity.ElementalGuard) e;
		// GL11.glPushMatrix();
		// GL11.glTranslated(x, y, z);
		// GL11.glScaled(0.15, 0.15, 0.15);
		// GL11.glRotated(180, 1, 0, 0);
		// GL11.glRotated(e.rotationYaw, 0, 1, 0);
		//
		// RenderUtils.bindTexture("aam", "textures/misc/model/elemguard.png");
		//
		// Elemental model = new Elemental();
		// model.render(e, 0, 0, 0, 1, 1, 1);
		//
		// GL11.glScaled(1, 1.5, 1);
		// GL11.glTranslated(0, 12, 0);
		// GL11.glRotated(-e.rotationYaw, 0, 1, 0);
		// GL11.glPopMatrix();
		// }
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return new ResourceLocation("aam", "textures/models/elemguard.png");
	}

}
