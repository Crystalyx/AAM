package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Common.Tiles.TEBloodAltar;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.Circle;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
import DummyCore.Utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class TransCircleRenderer extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		TETransCircle te = (TETransCircle) tile;
		RenderHelper.disableStandardItemLighting();
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		Wec3 tp = new Wec3(te);

		int meta = tile.getWorldObj().getBlockMetadata((int) tp.x, (int) tp.y, (int) tp.z);
		ForgeDirection dir = ForgeDirection.getOrientation(meta);

		GL11.glRotated(90, 0, 1, 0);
		GL11.glRotated(90, dir.offsetX, dir.offsetY, dir.offsetZ);
		if (dir.offsetX != 0)
			GL11.glRotated(90, 0, dir.offsetX, 0);
		if (dir.offsetZ < 0)
			GL11.glRotated(180, 0, 1, 0);
		GL11.glTranslated(0, -0.45, 0);

		if (dir.equals(ForgeDirection.DOWN))
		{
			GL11.glTranslated(0, 0.9, 0);
		}
		GL11.glRotated(-90, 0, 1, 0);

		for (Circle par : te.circle)
		{
			par.render(te, par.scale, te.completeTimer);
		}
		GL11.glColor3d(1, 1, 1);
		if (te.transm != null && te.state.equals(TETransCircle.State.complete))
		{
			te.transm.action.renderTick(te.getWorldObj(), tp, te, te.completeTimer, te.potency, dir);
		}
		if (te.is != null)
		{
			double dy = 0;
			if (te.state.equals(TETransCircle.State.active))
			{
				dy = Math.min((double) te.completeTimer / te.transm.prepTime, 1) * 0.5;
			}
			if (te.state.equals(TETransCircle.State.complete))
			{
				dy = 0.5;
			}
			DrawUtils.renderItemStack_Full(te.is, 0, 0, 0, 0, 0.7 + dy, 0, 90 - Minecraft.getMinecraft().thePlayer.rotationYawHead, 0, 1, 1, 1, 0, -0.25F, 0, true);
		}
		if (te.isLink)
		{
			Tessellator t = Tessellator.instance;

			MiscUtils.bindTexture("aam:textures/blocks/circles/linkobol.png");

			t.startDrawingQuads();

			t.addVertexWithUV(-0.5, 0, -0.5, 0.0, 0.0);
			t.addVertexWithUV(-0.5, 0, 0.5, 0.0, 1.0);
			t.addVertexWithUV(0.5, 0, 0.5, 1.0, 1.0);
			t.addVertexWithUV(0.5, 0, -0.5, 1.0, 0.0);

			t.draw();
		}
		if (tile instanceof TEBloodAltar)
		{
			GL11.glTranslated(-0.5, 0.012, -0.5);
			TEBloodAltar tb = (TEBloodAltar) tile;
			double ds = 1 / 16d;
			double n = (tb.blood.amount / 10_000d) * ds * 5;
			Tessellator t = Tessellator.instance;

			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("aam:textures/blocks/blood_still.png"));

			t.startDrawingQuads();
			double index = (int) ((tile.getWorldObj().getWorldTime() % 1024) % 32);

			t.addVertexWithUV(ds * 3, ds * 6 + n, ds * 3, 0, index / 32.0);
			t.addVertexWithUV(1 - ds * 3, ds * 6 + n, ds * 3, 1, index / 32.0);
			t.addVertexWithUV(1 - ds * 3, ds * 6 + n, 1 - ds * 3, 1, (index + 1) / 32.0);
			t.addVertexWithUV(ds * 3, ds * 6 + n, 1 - ds * 3, 0, (index + 1) / 32.0);

			t.addVertexWithUV(ds * 3, ds * 6 + n, 1 - ds * 3, 0, (index + 1) / 32.0);
			t.addVertexWithUV(1 - ds * 3, ds * 6 + n, 1 - ds * 3, 1, (index + 1) / 32.0);
			t.addVertexWithUV(1 - ds * 3, ds * 6 + n, ds * 3, 1, index / 32.0);
			t.addVertexWithUV(ds * 3, ds * 6 + n, ds * 3, 0, index / 32.0);

			t.draw();
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();
	}
}
