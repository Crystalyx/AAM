/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Altar;
import AAM.Common.Tiles.TEModificationAnvil;
import DummyCore.Utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class ModificationAnvilRender extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 2.4F, (float) z + 0.5F);
		float scale = 0.1F;
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		this.model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		TEModificationAnvil tilee = (TEModificationAnvil) tile;

		float rotation = tilee.getWorldObj().getWorldTime() % 360;
		Minecraft.getMinecraft().renderEngine.bindTexture(arcane);

		Tessellator t = Tessellator.instance;

		t.startDrawingQuads();
		GL11.glScaled(14, 10, 14);
		GL11.glTranslated(0, 1.2, 0);

		t.addVertexWithUV(-0.5, 0, -0.5, 0, 0);
		t.addVertexWithUV(0.5, 0, -0.5, 1, 0);
		t.addVertexWithUV(0.5, 0, 0.5, 1, 1);
		t.addVertexWithUV(-0.5, 0, 0.5, 0, 1);

		t.draw();

		GL11.glPopMatrix();

		rotation = rotation + 360F / tilee.getWorldObj().getWorldTime() % 360;
		GL11.glPushMatrix();
		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(0), tilee.xCoord + 0.5D, tilee.yCoord + 10D, tilee.zCoord + 0.5D, x, y + 1.1D, z, rotation, 0F, 1, 1, 1, 0.5F, 0.375F,
				0.5F, true);

		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(1), tilee.xCoord + 0.5D, tilee.yCoord + 5D, tilee.zCoord, x, y + 0.85D, z - 0.6D, rotation, 0F, 1, 1, 1, 0.5F, 0.375F,
				0.5F, true);
		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(2), tilee.xCoord, tilee.yCoord + 5D, tilee.zCoord - 0.5D, x, y + 0.85D, z + 0.6D, rotation, 0F, 1, 1, 1, 0.5F, 0.375F,
				0.5F, true);

		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(3), tilee.xCoord + 0.5D, tilee.yCoord + 5D, tilee.zCoord, x + 0.525D, y + 0.85D, z - 0.3D, rotation, 0F, 1, 1, 1,
				0.375F, 0.5F, 0.5F, true);
		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(4), tilee.xCoord, tilee.yCoord + 5D, tilee.zCoord + 0.5D, x - 0.525D, y + 0.85D, z + 0.3D, rotation, 0F, 1, 1, 1,
				0.375F, 0.5F, 0.5F, true);
		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(5), tilee.xCoord - 0.5D, tilee.yCoord + 5D, tilee.zCoord, x + 0.55D, y + 0.85D, z + 0.3D, rotation, 0F, 1, 1, 1, 0.375F,
				0.5F, 0.5F, true);
		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(6), tilee.xCoord, tilee.yCoord + 5D, tilee.zCoord - 0.5D, x - 0.55D, y + 0.85D, z - 0.3D, rotation, 0F, 1, 1, 1, 0.375F,
				0.5F, 0.5F, true);

		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();

	}

	public static final Altar model = new Altar();
	public static final ResourceLocation textures = new ResourceLocation("aam:textures/misc/altar.png");
	public static final ResourceLocation arcane = new ResourceLocation("aam:textures/blocks/arcane.png");

}
