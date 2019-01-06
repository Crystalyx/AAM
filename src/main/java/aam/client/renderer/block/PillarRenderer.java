package aam.client.renderer.block;

import aam.network.ClientProxy;
import aam.utils.MiscUtils;
import aam.utils.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class PillarRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block b, int meta, int modelId, RenderBlocks renderer)
	{
		b.setBlockBounds(1 / 8F, 0, 1 / 8F, 1 - 1 / 8F, 1, 1 - 1 / 8F);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.1f, 0, 0.025f, 0.9f, 0.2f, 0.975f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.025f, 0, 0.1f, 0.975f, 0.2f, 0.9f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.1f, 0.8f, 0.025f, 0.9f, 1f, 0.975f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.025f, 0.8f, 0.1f, 0.975f, 1f, 0.9f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(1 / 8F, 0, 1 / 8F, 1 - 1 / 8F, 1, 1 - 1 / 8F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block b, int modelId, RenderBlocks renderer)
	{
		if (w.getBlock(x, y - 1, z) != b)
		{
			b.setBlockBounds(0.1f, 0, 0.025f, 0.9f, 0.2f, 0.975f);
			renderer.setRenderBoundsFromBlock(b);
			renderer.renderStandardBlock(b, x, y, z);

			b.setBlockBounds(0.025f, 0, 0.1f, 0.975f, 0.2f, 0.9f);
			renderer.setRenderBoundsFromBlock(b);
			renderer.renderStandardBlock(b, x, y, z);
		}

		if (w.getBlock(x, y + 1, z) != b)
		{
			b.setBlockBounds(0.1f, 0.8f, 0.025f, 0.9f, 1f, 0.975f);
			renderer.setRenderBoundsFromBlock(b);
			renderer.renderStandardBlock(b, x, y, z);

			b.setBlockBounds(0.025f, 0.8f, 0.1f, 0.975f, 1f, 0.9f);
			renderer.setRenderBoundsFromBlock(b);
			renderer.renderStandardBlock(b, x, y, z);
		}

		b.setBlockBounds(1 / 8F, 0, 1 / 8F, 1 - 1 / 8F, 1, 1 - 1 / 8F);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);
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
