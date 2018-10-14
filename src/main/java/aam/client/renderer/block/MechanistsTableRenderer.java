package aam.client.renderer.block;

import aam.network.ClientProxy;
import aam.utils.MiscUtils;
import aam.utils.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class MechanistsTableRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block b, int meta, int modelId, RenderBlocks r)
	{
		float d = 1 / 16f;
		b.setBlockBounds(0, d * 9, 0, 1, 1 - d, 1);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(d, d * 3, d, d * 5, d * 9, d * 5);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(1 - d * 5, d * 3, d, 1 - d, d * 9, d * 5);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(d, d * 3, 1 - d * 5, d * 5, d * 9, 1 - d);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(1 - d * 5, d * 3, 1 - d * 5, 1 - d, d * 9, 1 - d);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0, 0, 0, 1, d * 3, 1);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0, 0, 0, 1, 1 - d, 1);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block b, int modelId, RenderBlocks r)
	{
		float d = 1 / 16f;
		b.setBlockBounds(0, d * 9, 0, 1, 1 - d, 1);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(d, d * 3, d, d * 5, d * 9, d * 5);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(1 - d * 5, d * 3, d, 1 - d, d * 9, d * 5);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(d, d * 3, 1 - d * 5, d * 5, d * 9, 1 - d);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(1 - d * 5, d * 3, 1 - d * 5, 1 - d, d * 9, 1 - d);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0, 0, 0, 1, d * 3, 1);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0, 0, 0, 1, 1 - d, 1);
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
