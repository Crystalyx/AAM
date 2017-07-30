package AAM.Client.Renderer.Tile;

import org.lwjgl.opengl.GL11;

import AAM.Common.Items.ModItems;
import AAM.Common.Items.SoulSword;
import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import DummyCore.Utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class TeleporterRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation Model = new ResourceLocation("aam", "models/teleporter.obj");
	private static final ResourceLocation Texture = new ResourceLocation("aam", "textures/models/teleporter.png");
	private static final ResourceLocation Vortex = new ResourceLocation("aam", "textures/misc/vortex.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f1)
	{
		IModelCustom model = AdvancedModelLoader.loadModel(Model);
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y, z + 0.5);
		GL11.glScaled(0.5, 0.5, 0.5);
		MiscUtils.bindTexture(Texture);
		model.renderAll();

		if (tile != null)
		{
			Tessellator t = Tessellator.instance;
			double v = 12;
			double time = -(Minecraft.getSystemTime() % (360 * v)) / v;
			GL11.glRotated(time, 0, 1, 0);
			GL11.glTranslated(0, Math.sin(time / 50) * 0.2+1.5, 0);
			GL11.glScaled(2, 2, 2);
			ItemStack is = new ItemStack(ModItems.Artifact, 1, ph.stype.ordinal());
			DrawUtils.renderItemStack_Full(is, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, -0.25F, 0, true);
		}
		GL11.glPopMatrix();
	}

}
