package aam.client.renderer.item;

import aam.common.items.alchemy.PhilosophersStone;
import aam.utils.Color;
import aam.utils.MathUtils;
import aam.utils.MiscUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class PhiloRenderer implements IItemRenderer
{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	private static final ResourceLocation Model = new ResourceLocation("aam", "models/rhombododecahedron.obj");
	private static final ResourceLocation Texture = new ResourceLocation("aam", "models/philo.png");

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{

		IModelCustom model = AdvancedModelLoader.loadModel(Model);

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if (type != ItemRenderType.INVENTORY)
		{
			GL11.glTranslated(0, 0.25, 0);
			GL11.glRotated(-45D, 0, 1, 0);
		}
		GL11.glScaled(0.5d, 0.5d, 0.5d);
		PhilosophersStone ps = (PhilosophersStone) item.getItem();
		double k = ps.getStoredEnergy(item) / ps.getMaxStoredEnergy(item);
		// GL11.glColor4d(1, 0, 0, k * 0.35d + 0.5d);
		Color phil = MathUtils.rainbow(k * 500 + 4.5);
		GL11.glColor4d(phil.red / 255d, phil.green / 255d, phil.blue / 255d, phil.alpha / 255d);
		MiscUtils.bindTexture(new ResourceLocation("aam", "models/philo.png"));
		model.renderAll();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4d(1, 1, 1, 1);
		GL11.glPopMatrix();
	}
}
