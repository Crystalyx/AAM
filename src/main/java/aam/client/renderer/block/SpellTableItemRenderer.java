package aam.client.renderer.block;

import aam.client.models.SpellTable;
import aam.network.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class SpellTableItemRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(0.5, 1.5, 0.5);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		GL11.glRotated(180, 1.0, 0.0, 0.0);

		int meta = 0;
		GL11.glRotated(meta * 90, 0, 1, 0);

		ResourceLocation texture = new ResourceLocation("aam", "textures/misc/SpellTable-texture.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		SpellTable model = new SpellTable();
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		GL11.glPushMatrix();

		GL11.glTranslated(0.5, 0.9, 0.5);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		GL11.glRotated(180, 1.0, 0.0, 0.0);
		GL11.glRotated(meta * 90 + 90, 0, 1, 0);

		GL11.glRotated(-70, 0.0, 0.0, 1.0);

		ResourceLocation book = new ResourceLocation("textures/entity/enchanting_table_book.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(book);

		ModelBook bookMod = new ModelBook();
		bookMod.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ClientProxy.getNextRenderId();
	}

}
