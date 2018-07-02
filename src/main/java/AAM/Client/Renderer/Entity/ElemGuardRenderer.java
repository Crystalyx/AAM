package AAM.Client.Renderer.Entity;

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
		super.doRender(e, x, y, z, f1, f2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return new ResourceLocation("aam", "textures/models/elemguard.png");
	}

}
