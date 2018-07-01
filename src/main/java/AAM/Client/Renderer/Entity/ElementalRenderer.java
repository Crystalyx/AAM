package AAM.Client.Renderer.Entity;

import AAM.Client.Model.Elemental;
import AAM.Common.Entity.Elemental.ElementalBoss;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class ElementalRenderer extends RenderLiving
{
	public ElementalRenderer()
	{
		super(new Elemental(), 0);
	}

	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		super.doRender(e, x, y, z, f1, f2);
		ElementalBoss b = (ElementalBoss) e;
		BossStatus.setBossStatus(b, true);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		return new ResourceLocation("aam", "textures/models/elemental_boss.png");
	}
}
