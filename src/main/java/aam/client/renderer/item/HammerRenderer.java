package aam.client.renderer.item;

import aam.client.models.Hammer;
import aam.utils.MiscUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class HammerRenderer implements IItemRenderer
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

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(0, 2, 0);
		GL11.glRotated(180, 1, 0, 0);
		GL11.glScaled(0.25, 0.25, 0.25);

		if (type.equals(ItemRenderType.EQUIPPED))
		{
			GL11.glTranslated(0, 2, 0);
			GL11.glTranslated(-2, 0, 2);
			GL11.glRotated(-45, 0, 1, 0);
			GL11.glRotated(-60, 1, 0, 0);
		}

		if (type.equals(ItemRenderType.INVENTORY))
		{
			GL11.glTranslated(0, 5, 0);

			GL11.glScaled(0.5, 0.5, 0.5);

		}

		if (type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON))
		{
			GL11.glScaled(0.7, 0.7, 0.7);
			GL11.glRotated(35, 0, 1, 0);
			GL11.glTranslated(0, 0, -2);

		}

		Hammer model = new Hammer();
		MiscUtils.bindTexture("aam:textures/models/hammer_texture.png");
		model.render(null, 0, 0, 0, 1, 1, 1);

		GL11.glPopMatrix();
	}
}
