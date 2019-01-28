package aam.client.renderer.tile;

import aam.client.models.SpellTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SpellTableRenderer extends TileEntitySpecialRenderer
{
	public static final ResourceLocation texture = new ResourceLocation("aam", "textures/misc/SpellTable-textureName.png");
	public static final ResourceLocation book = new ResourceLocation("textures/entity/enchanting_table_book.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		GL11.glRotated(180, 1.0, 0.0, 0.0);

		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		GL11.glRotated(meta * 90, 0, 1, 0);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		SpellTable model = new SpellTable();
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5, y + 0.9, z + 0.5);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		GL11.glRotated(180, 1.0, 0.0, 0.0);
		GL11.glRotated(meta * 90 + 90, 0, 1, 0);

		GL11.glRotated(-70, 0.0, 0.0, 1.0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(book);

		ModelBook bookMod = new ModelBook();
		bookMod.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();
	}

}
