package AAM.Client.Renderer.Entity;

import org.lwjgl.opengl.GL11;

import AAM.Common.Entity.StaffCharge;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Utils.Color;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Render.Cube;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class StaffChargeRenderer extends Render
{

	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{
		if (e instanceof StaffCharge)
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			EntityPlayer p = Minecraft.getMinecraft().thePlayer;
			GL11.glTranslated(x + Math.ulp(p.posX), y + Math.ulp(p.posY), z + Math.ulp(p.posZ));
			Cube c = new Cube(0, 0.1f, 0, 1, 1, 1, 4, 64);

			PlayerDataHandler ph = PlayerDataHandler.get(p);
			if (ph != null)
			{
				Color clr = SoulSword.getcPhColor(ph);
				GL11.glColor4d(clr.red / 255d, clr.green / 255d, clr.blue / 255d, clr.alpha / 255d);
			}
			c.render(new ResourceLocation("aam:textures/blocks/altar_base.png"));
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return null;
	}

}
