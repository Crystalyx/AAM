package aam.client.renderer.block;

import aam.client.models.Graviter;
import aam.network.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class GraviterRender implements ISimpleBlockRenderingHandler
{
	public ResourceLocation texture = new ResourceLocation("aam:textures/models/graviter.png");
	public Graviter model = new Graviter();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		GL11.glTranslatef(0, -1.0F, 0);
		float scale = 0.0625F;
		GL11.glScalef(scale, scale, scale);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
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
