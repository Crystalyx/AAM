package aam.client.renderer.tile;

import aam.common.tiles.TECauldron;
import aam.utils.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CauldronRenderer extends TileEntitySpecialRenderer
{
	public static final ResourceLocation texture = new ResourceLocation("aam:textures/misc/Cauldron-textureName.png");
	public static final ResourceLocation fluid = new ResourceLocation("aam:textures/misc/fluid.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		if (tile != null)
		{
			if (tile instanceof TECauldron)
			{
				TECauldron cauld = (TECauldron) tile;
				double n = (double) cauld.fluid.amount / 2000 + 0.05;
				Tessellator tess = Tessellator.instance;
				GL11.glPushMatrix();
				GL11.glTranslated(x, y + 1D + n, z + 1);
				GL11.glScaled(1, 1, 1);
				GL11.glRotated(180, 1, 0, 0);
				Minecraft.getMinecraft().getTextureManager().bindTexture(fluid);

				Color col = cauld.color;
				tess.startDrawingQuads();
				double index = (int) (tile.getWorldObj().getWorldTime() % 1024 % 32);
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
