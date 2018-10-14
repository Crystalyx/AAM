package aam.client.renderer.item;

import org.lwjgl.opengl.GL11;

import aam.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RSwordRenderer implements IItemRenderer
{
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return !type.equals(ItemRenderType.INVENTORY);
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if (type == ItemRenderType.INVENTORY)
		{

			GL11.glScaled(16.0, 16.0, 16.0);
			GL11.glRotated(180, 1.0, 0.0, 0.0);
			GL11.glRotated(180, 0.0, 1.0, 0.0);
			GL11.glTranslated(-1.0, -1.0, 0.0);
		}
		else
		{
			if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			{
				GL11.glRotated(90, 0.0, 0.0, 1.0);
				GL11.glTranslated(0.15, -0.85, 0.0);
			}
			else
			{
				if (type == ItemRenderType.ENTITY && !item.isOnItemFrame())
				{
					Minecraft.getMinecraft();
					int angle = (int) (Minecraft.getSystemTime() % 5760 / 16);
					Minecraft.getMinecraft();
					double append = (double) (Minecraft.getSystemTime() % 2048) / 2048;
					if (append > 0.5)
					{
						append = 1 - append;
					}
					GL11.glRotated(angle, 0, 1, 0);
					GL11.glTranslated(-0.5, 0, 0);
					GL11.glTranslated(0, append - 0.25, 0);
				}
				if (item.isOnItemFrame())
				{
					GL11.glTranslated(-0.5, -0.1, 0);
				}
			}
		}
		String way = "textures/items/spacesword";

		GL11.glPushMatrix();
		GL11.glScaled(1.0, 1.0, 1.0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("aam", way + ".png"));
		Tessellator tess = Tessellator.instance;
		RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 2048, 2048, 0.05F);

		GL11.glPopMatrix();

	}
}
