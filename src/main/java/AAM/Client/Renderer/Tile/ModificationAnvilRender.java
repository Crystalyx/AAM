/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Common.Tiles.TEModificationAnvil;
import DummyCore.Utils.DrawUtils;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class ModificationAnvilRender extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		TEModificationAnvil te = (TEModificationAnvil) tile;
		RenderHelper.disableStandardItemLighting();
		//
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.95F, (float) z + 0.3F);
		if (te.getStackInSlot(0) != null)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(-0.1, 0, 0);
			GL11.glRotated(90, 1, 0, 0);
			GL11.glRotated(-45, 0, 0, 1);
			DrawUtils.renderItemStack_Full(te.getStackInSlot(0), te.xCoord + 0.5f, te.yCoord + 1, te.zCoord + 0.5f, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, true);

			GL11.glPopMatrix();
		}
		else
		{
			if (te.getStackInSlot(3) != null)
			{
				GL11.glPushMatrix();
				GL11.glTranslated(-0.1, 0, 0);
				GL11.glRotated(90, 1, 0, 0);
				GL11.glRotated(-45, 0, 0, 1);
				DrawUtils.renderItemStack_Full(te.getStackInSlot(3), te.xCoord + 0.5f, te.yCoord + 1, te.zCoord + 0.5f, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, true);

				GL11.glPopMatrix();
			}
		}

		if (te.getStackInSlot(2) != null)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(0, 0.2, -0.2);
			long time = te.craftTime;
			int period = 20;
			long md = Math.floorMod(time, period * 2);
			if (md > period)
			{
				md = period * 2 - md;
			}

			GL11.glRotated(40 + 50f * md / period, 1, 0, 0);
			GL11.glScaled(1 / 2f, 1 / 2f, 1 / 2f);

			DrawUtils.renderItemStack_Full(te.getStackInSlot(2), te.xCoord + 0.5f, te.yCoord + 1, te.zCoord + 0.5f, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, true);

			GL11.glPopMatrix();
		}

		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();

	}
}
