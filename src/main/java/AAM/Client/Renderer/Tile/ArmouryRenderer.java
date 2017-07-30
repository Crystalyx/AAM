package AAM.Client.Renderer.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.Armoury;
import AAM.Common.Tiles.TileArmoury;
import DummyCore.Utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

public class ArmouryRenderer extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		RenderHelper.enableStandardItemLighting();

		long time = Minecraft.getMinecraft().theWorld.getWorldTime();

		if (tile != null)
		{
			Random r = tile.getWorldObj().rand;

			if (tile instanceof TileArmoury)
			{
				TileArmoury t = (TileArmoury) tile;
				List<Integer> ids = new ArrayList<Integer>();
				for (int i = 0; i < 27; i++)
				{
					if (t.getStackInSlot(i) != null)
					{
						ids.add(i);
					}
				}
				float angle = (float) Math.toRadians(360F / ids.size());
				for (int i = 0; i < ids.size(); i++)
				{
					int time2 = (int) (t.getWorldObj().getWorldTime() % 360);
					float px = (float) Math.cos(angle * i + Math.toRadians(time2));
					float pz = (float) Math.sin(angle * i + Math.toRadians(time2));

					ItemStack is = t.getStackInSlot(ids.get(i));
					if (MinecraftForgeClient.getItemRenderer(is, ItemRenderType.EQUIPPED) == null)
					{
						GL11.glPushMatrix();

						GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
						GL11.glScaled(0.0625, 0.0625, 0.0625);
						GL11.glScaled(8, 8, 8);

						DrawUtils.renderItemStack_Full(is, 0, 0, 0, 0, 0, 0, 0, 135, 1, 1, 1, px + 0.125f, -1F, pz, true);
						GL11.glPopMatrix();

					} else
					{
						GL11.glPushMatrix();
						px = (float) Math.cos(angle * i + Math.toRadians(time2)) / 1.8F;
						pz = (float) Math.sin(angle * i + Math.toRadians(time2)) / 1.8F;
						GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
						GL11.glTranslated(px + 0.175, -0.6, pz + 0.08);
						GL11.glRotated(135, 0, 0, 1);
						GL11.glScaled(0.0625, 0.0625, 0.0625);
						GL11.glScaled(8, 8, 8);
						GL11.glScaled(0.5, 0.5, 0.5);
						MinecraftForgeClient.getItemRenderer(is, ItemRenderType.EQUIPPED).renderItem(ItemRenderType.EQUIPPED, is, 0);
						GL11.glPopMatrix();
					}
				}
			}
		}
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		GL11.glScaled(8, 8, 8);
		GL11.glScaled(0.125, 0.125, 0.125);
		GL11.glRotated(180, 1.0, 0, 0);

		ResourceLocation texture = new ResourceLocation("aam", "textures/misc/Armoury-texture.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		Armoury model = new Armoury();
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();
	}
}
