package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Crystal;
import AAM.Client.Model.CrystalBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class CrystalRenderer extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		if (te != null)
		{
			double lig = Math.sqrt(te.getWorldObj().getLightBrightness(te.xCoord, te.yCoord, te.zCoord));
			GL11.glColor4d(lig, lig, lig, 1);
		}

		Crystal p = new Crystal();
		ResourceLocation prl = new ResourceLocation("aam", "textures/models/crystal_texture.png");

		int v = 40;
		long time = (Minecraft.getSystemTime() % (360 * v)) / v;

		GL11.glScaled(0.0625, 0.0625, 0.0625);

		GL11.glRotated(time, 0, 1, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(prl);
		p.render(null, 0, 0, 0, 1, 1, 1);

		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
	}

}
