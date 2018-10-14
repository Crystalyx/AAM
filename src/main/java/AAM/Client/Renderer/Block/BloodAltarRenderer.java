package aam.client.renderer.block;

import aam.network.ClientProxy;
import aam.utils.MiscUtils;
import aam.utils.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class BloodAltarRenderer implements ISimpleBlockRenderingHandler
{
	public static float ds = 1 / 16F;

	@Override
	public void renderInventoryBlock(Block b, int meta, int modelId, RenderBlocks renderer)
	{

		b.setBlockBounds(0, 0, 0, 1, ds, 1);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds, ds, ds, 1 - ds, ds * 2, 1 - ds);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 2, ds * 2, ds * 2, 1 - ds * 2, ds * 3, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 3, ds * 3, ds * 3, 1 - ds * 3, ds * 4, 1 - ds * 3);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 4, ds * 4, ds * 4, 1 - ds * 4, ds * 5, 1 - ds * 4);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 3, ds * 5, ds * 3, 1 - ds * 3, ds * 6, 1 - ds * 3);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 2, ds * 6, ds * 2, 1 - ds * 2, ds * 7, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(1 - ds * 3, ds * 6, ds * 2, 1 - ds * 2, ds * 13, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 2, ds * 7, ds * 2, ds * 3, ds * 13, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 2, ds * 6, 1 - ds * 3, 1 - ds * 2, ds * 13, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(ds * 2, ds * 7, ds * 2, 1 - ds * 2, ds * 13, ds * 3);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0, 0, 0, 1, 1, 1);

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block b, int modelId, RenderBlocks renderer)
	{
		b.setBlockBounds(0, 0, 0, 1, ds, 1);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds, ds, ds, 1 - ds, ds * 2, 1 - ds);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 2, ds * 2, ds * 2, 1 - ds * 2, ds * 3, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 3, ds * 3, ds * 3, 1 - ds * 3, ds * 4, 1 - ds * 3);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 4, ds * 4, ds * 4, 1 - ds * 4, ds * 5, 1 - ds * 4);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 3, ds * 5, ds * 3, 1 - ds * 3, ds * 6, 1 - ds * 3);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 2, ds * 6, ds * 2, 1 - ds * 2, ds * 7, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(1 - ds * 3, ds * 6, ds * 2, 1 - ds * 2, ds * 13, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 2, ds * 7, ds * 2, ds * 3, ds * 13, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 2, ds * 6, 1 - ds * 3, 1 - ds * 2, ds * 13, 1 - ds * 2);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(ds * 2, ds * 7, ds * 2, 1 - ds * 2, ds * 13, ds * 3);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0, 0, 0, 1, 1, 1);
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
