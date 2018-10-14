package aam.client.renderer.tile;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import DummyCore.Utils.DrawUtils;
import aam.client.models.SoulAltar;
import aam.common.items.soul.Artifact;
import aam.common.tiles.TESoulAltar;
import aam.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class SoulAltarRenderer extends TileEntitySpecialRenderer
{
	public static final ResourceLocation texture = new ResourceLocation("aam", "textures/models/altar_texture.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		RenderHelper.enableStandardItemLighting();

		double s = Math.sin(Math.toRadians(MathUtils.getTimedAngle(100))) / 8;
		GL11.glTranslated(x + 0.5, y + 1.5 + s, z + 0.5);

		GL11.glScaled(0.5, 0.5, 0.5);
		if (tile instanceof TESoulAltar)
		{
			TESoulAltar t = (TESoulAltar) tile;

			if (t.getStackInSlot(0) != null)
			{
				DrawUtils.renderItemStack_Full(t.getStackInSlot(0), 0, 0, 0, 0, 0, 0, tile.getWorldObj().getWorldTime() % 360, 0, 1, 1, 1, 0, -0.25F, 0, true);
			}
			List<Integer> ids = new ArrayList<>();
			for (int i = 4; i < 10; i++)
			{
				if (t.getStackInSlot(i) != null)
				{
					ids.add(i);
				}
			}
			if (t.getStackInSlot(2) != null)
			{
				if (t.getStackInSlot(2).getItem() instanceof Artifact)
				{
					ItemStack i = t.getStackInSlot(2);
					DrawUtils.renderItemStack_Full(i, 0, 0, 0, 0, 0, 0, 90, 90, 1, 1, 1, 0.15F, -1F, 0, true);
				}
			}
			float angle = (float) Math.toRadians(360F / ids.size());
			for (int i = 0; i < ids.size(); i++)
			{
				double time = MathUtils.getTimedAngle(20);
				float px = (float) Math.cos(angle * i + Math.toRadians(time)) / 1.5F;
				float pz = (float) Math.sin(angle * i + Math.toRadians(time)) / 1.5F;

				DrawUtils.renderItemStack_Full(t.getStackInSlot(ids.get(i)), 0, 0, 0, 0, 0, 0, 90, 90, 1, 1, 1, px + 0.125f, -1F, pz, true);
			}
		}

		GL11.glRotated(180, 1.0, 0.0, 0.0);
		GL11.glScaled(2, 2, 2);
		GL11.glTranslated(0, -0.3, 0);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		GL11.glRotated(meta * 90, 0, 1, 0);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		SoulAltar model = new SoulAltar();
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();
	}
}
