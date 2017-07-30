package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Obelisk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class ObeliskRenderer extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
		GL11.glRotated(180, 1, 0, 0);

		ResourceLocation txt = new ResourceLocation("aam:textures/misc/Obelisk-texture.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(txt);

		Obelisk model = new Obelisk();
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();

	}
}
