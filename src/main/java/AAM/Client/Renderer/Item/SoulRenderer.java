package AAM.Client.Renderer.Item;

import org.lwjgl.opengl.GL11;

import AAM.Common.Items.ModItems;
import AAM.Common.Items.Artifacts.CrystalBow;
import AAM.Common.Items.Soul.Artifact;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Common.Soul.WarriorType;
import AAM.Common.Soul.WeaponType;
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
			// double angle = MiscUtils.getTimedAngle(16);
			// double append =
			// MiscUtils.getSawValue(MiscUtils.getTimedValue(2048), 1);

			// GL11.glRotated(angle, 0, 1, 0);
			GL11.glTranslated(-0.85, 0, 0);
			// GL11.glTranslated(0, append - 0.25, 0);
		}
		if (item.isOnItemFrame())
		{
			GL11.glTranslated(-0.5, -0.1, 0);
		}
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

		String way = "aam:textures/items/" + SoulSword.ways[0];
		String art = "aam:textures/items/soulsword/component_nil";
		String bow = "aam:textures/items/" + CrystalBow.ways[0];

		if (item.getItem() == ModItems.SoulSword)
		{
			way = "aam:textures/items/" + SoulSword.ways[item.getItemDamage()];
			if (ph.arbitur)
				way = "aam:textures/items/" + SoulSword.ways_arbitur[item.getItemDamage()];

			if (ph.art)
				art = "aam:textures/items/" + Artifact.ways[ph.stype.ordinal()];
		}

		GL11.glTranslated(0, 0.125, 0);
		if (WeaponType.values()[item.getItemDamage()].equals(WeaponType.Spear))
		{
			GL11.glScaled(2.0, 2.0, 1.5);
			GL11.glTranslated(-0.25, -0.25, 0);
			GL11.glTranslated(-0.125, +0.125, 0);
		}
		if (WeaponType.values()[item.getItemDamage()].equals(WeaponType.Hammer))
		{
			GL11.glScaled(2.0, 2.0, 1.5);
			GL11.glTranslated(-0.25, -0.25, 0);
			GL11.glTranslated(-0.125, +0.125, 0);
		}

		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(way + ".png"));
		Tessellator tess = Tessellator.instance;
		RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, 0.05F);

		float tough = 0.05f;

		if (type.equals(type.INVENTORY))
		{
			tough = 0;
		}

		GL11.glScaled(0.25, 0.25, 1.0);
		GL11.glTranslated(2.5, 0.5, 0);
		if (WeaponType.values()[item.getItemDamage()].warrior.equals(WarriorType.Carry))
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(art + ".png"));
			RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 16, 16, tough);
			if (ph.bow)
			{
				GL11.glTranslated(0, 0, 0.01);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
				RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, tough);

				GL11.glTranslated(0, 0, -0.02);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
				RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, tough);

				GL11.glTranslated(0, 0, 0.01);
			}
		}

		if (WeaponType.values()[item.getItemDamage()].warrior.equals(WarriorType.Tank))
		{
			GL11.glTranslated(-1.625, 1.625, 0);
			if (ph.bow)
			{
				GL11.glPushMatrix();
				GL11.glTranslated(0, 0, 0.01);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
				RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, tough);

				GL11.glTranslated(0, 0, -0.02);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
				RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, tough);

				GL11.glTranslated(0, 0, 0.01);
				GL11.glPopMatrix();
			}
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(art + ".png"));
			RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 16, 16, tough);
		}

		if (WeaponType.values()[item.getItemDamage()].warrior.equals(WarriorType.Caster))
		{
			GL11.glTranslated(-2.25, 2.25, 0);
			if (ph.bow)
			{
				GL11.glPushMatrix();
				GL11.glTranslated(0, 0, 0.01);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
				RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, tough);

				GL11.glTranslated(0, 0, -0.02);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(bow + ".png"));
				RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 64, 64, tough);

				GL11.glTranslated(0, 0, 0.01);
				GL11.glPopMatrix();
			}
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(art + ".png"));
			RenderUtils.renderTextureIn2D(tess, 0.0F, 0.0F, 1.0F, -1.0F, 16, 16, tough);
		}

		GL11.glPopMatrix();
	}

}
