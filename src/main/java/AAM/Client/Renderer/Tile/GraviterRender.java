package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Graviter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class GraviterRender extends TileEntitySpecialRenderer
{
	public ResourceLocation texture = new ResourceLocation("aam:textures/models/graviter.png");
	public Graviter model = new Graviter();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F, (float) z + 0.5F);
		float scale = 0.0625F;
		GL11.glScalef(scale, scale, scale);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		this.model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();

	}

}
