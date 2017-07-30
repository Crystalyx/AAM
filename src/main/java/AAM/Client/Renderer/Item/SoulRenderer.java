package AAM.Client.Renderer.Item;

import org.lwjgl.opengl.GL11;

import AAM.Common.Items.Artifact;
import AAM.Common.Items.CrystalBow;
import AAM.Common.Items.ModItems;
import AAM.Common.Items.SoulSword;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class SoulRenderer implements IItemRenderer
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
		// if (item.getItemDamage() != 1)
		// {
		GL11.glPushMatrix();
		if (type == ItemRenderType.INVENTORY)
		{
			GL11.glTranslated(0.0, 2.0, 0.0);
			GL11.glScaled(16.0, 16.0, 16.0);
			GL11.glRotated(180, 1.0, 0.0, 0.0);
			GL11.glRotated(180, 0.0, 1.0, 0.0);
			GL11.glTranslated(-1.0, -1.0, 0.0);
		}
		if (type == ItemRenderType.ENTITY && !item.isOnItemFrame())
		{
			int angle = (int) ((Minecraft.getMinecraft().getSystemTime() % 5760) / 16);
			double append = ((double) (Minecraft.getMinecraft().getSystemTime() % 2048) / 2048);
			if (append > 0.5)
			{
				append = 1 - append;
			}

			if (item.getItemDamage() == 0)
				GL11.glScaled(2.0, 2.0, 2.0);
			GL11.glRotated(angle, 0, 1, 0);
			GL11.glTranslated(-0.5, 0, 0);
			GL11.glTranslated(0, append - 0.25, 0);
		}
		if (item.isOnItemFrame())
		{
			GL11.glTranslated(-0.5, -0.1, 0);
		}
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

		String way = "aam:textures/items/" + SoulSword.ways[0];
		String art = "aam:textures/items/" + Artifact.ways[0];
		String bow = "aam:textures/items/" + CrystalBow.ways[0];

		if (item.getItem() == ModItems.SoulSword)
		{
			way = "aam:textures/items/" + SoulSword.ways[item.getItemDamage()];
			art = "aam:textures/items/" + Artifact.ways[ph.stype.ordinal()];
		}

		GL11.glTranslated(0, 0.125, 0);
		GL11.glScaled(1.0, 1.0, 1.0);

		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way + ".png"));
		Tessellator tess = Tessellator.instance;
		RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);

		if (item.getItemDamage() == 1 || item.getItemDamage() == 4)
		{
			String way1 = "aam:textures/items/" + SoulSword.ways[item.getItemDamage()] + "_" + 1;
			String way2 = "aam:textures/items/" + SoulSword.ways[item.getItemDamage()] + "_" + 2;
			String way3 = "aam:textures/items/" + SoulSword.ways[item.getItemDamage()] + "_" + 3;

			GL11.glPushMatrix();
			GL11.glTranslated(0, 0, 1 / 20d);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way1 + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);

			GL11.glTranslated(0, 0, 1 / 20d);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way2 + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);

			GL11.glTranslated(0, 0, 1 / 20d);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way3 + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glTranslated(0, 0, -1 / 20d);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way1 + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);

			GL11.glTranslated(0, 0, -1 / 20d);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way2 + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);

			GL11.glTranslated(0, 0, -1 / 20d);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way3 + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);
			GL11.glPopMatrix();

		}

		GL11.glScaled(0.25, 0.25, 1.0);
		GL11.glTranslated(2.5, 0.5, 0);
		if (ph.bow)
		{
			GL11.glTranslated(0, 0, 0.01);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);

			GL11.glTranslated(0, 0, -0.02);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
			RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 64, 64, 0.05F);

			GL11.glTranslated(0, 0, 0.01);
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(art + ".png"));
		RenderUtils.renderItemIn2D(tess, 0.0F, -1.0F, -1.0F, 0.0F, 16, 16, 0.05F);
		GL11.glPopMatrix();
	}

}
