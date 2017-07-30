package AAM.Client.Renderer.Entity;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Elemental;
import AAM.Common.Entity.Elemental.State;
import AAM.Utils.Color;
import AAM.Utils.Render.Cube;
import AAM.Utils.Render.RenderUtils;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class ElementalRenderer extends RenderLiving
{
	public ElementalRenderer()
	{
		super(new Elemental(), 0);
	}

	public static String[] textures = new String[] { "water", "fire", "air", "earth", "chaos", "order", "Null" };

	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		super.doRender(e, x, y, z, f1, f2);
		if (e instanceof AAM.Common.Entity.Elemental)
		{
			AAM.Common.Entity.Elemental b = (AAM.Common.Entity.Elemental) e;
			BossStatus.setBossStatus(b, true);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		if (e instanceof AAM.Common.Entity.Elemental)
		{
			AAM.Common.Entity.Elemental b = (AAM.Common.Entity.Elemental) e;
			return new ResourceLocation("aam", "textures/models/elemental_boss.png");
		}
		return null;
	}
}
