package AAM.Client.Renderer.Tile;

import java.util.List;

import org.lwjgl.opengl.GL11;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.Circle;
import AAM.Utils.MiscUtils;
import AAM.Utils.WorldPos;
import AAM.Utils.Functions.Function;
import AAM.Utils.Render.RenderUtils;
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
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		WorldPos tp = new WorldPos(te);

		int meta = tile.getWorldObj().getBlockMetadata((int) tp.x, (int) tp.y, (int) tp.z);
		ForgeDirection dir = ForgeDirection.getOrientation(meta);
		ForgeDirection up = ForgeDirection.UP;
		ForgeDirection rotvec = up.getRotation(dir).getOpposite();

		GL11.glRotated(90, -rotvec.offsetX, -rotvec.offsetY, -rotvec.offsetZ);
		if (dir.offsetX != 0)
			GL11.glRotated(90, 0, dir.offsetX, 0);
		if (dir.offsetZ < 0)
			GL11.glRotated(180, 0, 1, 0);
		GL11.glTranslated(0, -0.45, 0);
		if (dir.equals(ForgeDirection.DOWN))
		{
			GL11.glTranslated(0, 0.9, 0);
		}

		for (Circle par : te.circle)
		{
			par.render(x, y, z, te, par.scale, te.completeTimer);
		}
		GL11.glColor3d(1, 1, 1);
		if (te.last != null)
		{
			te.last.action.renderTick(te.getWorldObj(), new WorldPos(te), te, te.completeTimer);
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

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();
	}
}
