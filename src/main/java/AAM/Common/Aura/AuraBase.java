package AAM.Common.Aura;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Render.Cube;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class AuraBase
{
	public String name;
	public float size;
	public ResourceLocation texture;

	public AuraBase(String name, float size, ResourceLocation texture)
	{
		this.name = name;
		this.size = size;
		this.texture = texture;
	}

	public AuraBase(String name, float size, String texture)
	{
		this.name = name;
		this.size = size;
		this.texture = new ResourceLocation(texture);
	}

	public void startTick(Entity p)
	{
	}

	public void endTick(Entity p)
	{

	}

	@SideOnly(Side.CLIENT)
	public void renderAura(World w, Entity p, double x, double y, double z)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();

		Tessellator tess = Tessellator.instance;

		GL11.glTranslated(x, y, z);

		if (p instanceof EntityPlayer)
		{
			GL11.glTranslated(x, y - 1.6D, z);
		}

		GL11.glScaled(this.size, this.size, this.size);

		GL11.glRotatef(p.worldObj.getWorldTime() % 360, 0, 1, 0);

		ResourceLocation text = this.texture;

		double range = this.size / 2;
		Cube cub = new Cube(0, 0, 0, 2048, 1, 2048, (float) this.size, 2048);
		cub.render((ResourceLocation) text);
		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}

	@SideOnly(Side.CLIENT)
	public void renderWithScale(World w, Entity p, double x, double y, double z, float scale)
	{
		float oSize = this.size;
		this.size = scale;

		renderAura(w, p, x, y, z);

		this.size = oSize;
	}

	public void setSize(float size)
	{
		this.size = size;
	}

	public float getSize()
	{
		return this.size;
	}
}
