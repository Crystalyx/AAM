package aam.client.renderer.tile;

import aam.client.models.Graviter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class GraviterRender extends TileEntitySpecialRenderer
{
	public static final ResourceLocation texture = new ResourceLocation("aam:textures/models/graviter.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y - 0.5, z + 0.5);
		double scale = 0.0625;
		GL11.glScaled(scale, scale, scale);
		Graviter model = new Graviter();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();

	}

}
