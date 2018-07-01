package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Cauldron;
import AAM.Common.Tiles.TECreativeCauldron;
import AAM.Utils.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class CreativeCauldronRenderer extends TileEntitySpecialRenderer
{
	public static final ResourceLocation texture = new ResourceLocation("aam:textures/misc/Cauldron-creative-texture.png");
	public static final ResourceLocation fluid = new ResourceLocation("aam:textures/misc/fluid.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
		GL11.glRotated(180, 1, 0, 0);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		Cauldron model = new Cauldron();
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();

		if (tile != null)
		{
			if (tile instanceof TECreativeCauldron)
			{
				TECreativeCauldron cauld = (TECreativeCauldron) tile;
				double n = (((double) cauld.fluid.amount) / 1700) + 0.065;
				Tessellator tess = Tessellator.instance;
				GL11.glPushMatrix();
				GL11.glTranslated(x, y + 1D + n, z + 1);
				GL11.glScaled(1, 1, 1);
				GL11.glRotated(180, 1, 0, 0);
				Minecraft.getMinecraft().getTextureManager().bindTexture(fluid);

				Color col = cauld.color;
				tess.startDrawingQuads();
				double index = (int) ((tile.getWorldObj().getWorldTime() % 1024) % 32);
				tess.setColorRGBA(col.red, col.green, col.blue, 255);

				tess.addVertexWithUV(0.125D, 0.875D, 0.125D, 0, index / 32.0);
				tess.addVertexWithUV(0.875D, 0.875D, 0.125D, 1, index / 32.0);
				tess.addVertexWithUV(0.875D, 0.875D, 0.875D, 1, (index + 1) / 32.0);
				tess.addVertexWithUV(0.125D, 0.875D, 0.875D, 0, (index + 1) / 32.0);

				tess.draw();
				GL11.glPopMatrix();
			}
		}
	}

}
