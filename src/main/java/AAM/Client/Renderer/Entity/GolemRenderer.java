package AAM.Client.Renderer.Entity;

import AAM.Client.Model.Golem;
import AAM.Common.Entity.Golem.GolemBoss;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class GolemRenderer extends RenderLiving
{
	public GolemRenderer()
	{
		super(new Golem(), 0);
	}

	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		super.doRender(e, x, y, z, f1, f2);
		GolemBoss b = (GolemBoss) e;
		BossStatus.setBossStatus(b, true);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		return new ResourceLocation("aam", "textures/models/golem_boss.png");
	}
}
