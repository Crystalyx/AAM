/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Client.Render;

import org.lwjgl.opengl.GL11;

import GF.Client.Model.SOWPModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class SOWPRender extends TileEntitySpecialRenderer
{
	SOWPModel model = new SOWPModel();
	ResourceLocation text = new ResourceLocation("goldflushing:textures/misc/SOWPModel-texture.png");
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		int cx =tile.xCoord;
		int cz =tile.zCoord;
		int cy =tile.yCoord;
        int dir = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

		GL11.glPushMatrix();
		Tessellator tess = Tessellator.instance;
		GL11.glTranslatef((float) x+0.5F, (float) y+2F-0.5F, (float) z+0.5F);
		
		float scale = 0.0625F;
		GL11.glScalef(scale, scale, scale);
		
		GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
		GL11.glRotatef(180F, 1, 0, 0);

		Minecraft.getMinecraft().renderEngine.bindTexture(text);

		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

}
