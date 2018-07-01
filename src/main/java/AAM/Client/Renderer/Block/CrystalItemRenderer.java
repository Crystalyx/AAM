package AAM.Client.Renderer.Block;

import AAM.Client.Renderer.Tile.CrystalRenderer;
import AAM.Network.ClientProxy;
import AAM.Utils.MiscUtils;
import AAM.Utils.Render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class CrystalItemRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block b, int meta, int modelId, RenderBlocks renderer)
	{
		b.setBlockBounds(0.1f, 0, 0.025f, 0.9f, 0.2f, 0.975f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.025f, 0, 0.1f, 0.975f, 0.2f, 0.9f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.2f, 0.18f, 0.075f, 0.8f, 0.38f, 0.925f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.075f, 0.18f, 0.2f, 0.925f, 0.38f, 0.8f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.30f, 0.28f, 0.1f, 0.7f, 0.48f, 0.9f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.1f, 0.28f, 0.30f, 0.9f, 0.48f, 0.7f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.12f, 0f, 0.12f, 0.88f, 0.38f, 0.88f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.19f, 0.38f, 0.19f, 0.81f, 0.56f, 0.81f);
		renderer.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(renderer, b, MiscUtils.getIconArray(b, meta), true);

		CrystalRenderer cr = new CrystalRenderer();
		cr.renderTileEntityAt(null, -0.5, -0.5, -0.5, 1);

		b.setBlockBounds(1 / 8F, 0, 1 / 8F, 1 - 1 / 8F, 1, 1 - 1 / 8F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block b, int modelId, RenderBlocks renderer)
	{
		b.setBlockBounds(0.1f, 0, 0.025f, 0.9f, 0.2f, 0.975f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.025f, 0, 0.1f, 0.975f, 0.2f, 0.9f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.2f, 0.18f, 0.075f, 0.8f, 0.38f, 0.925f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.075f, 0.18f, 0.2f, 0.925f, 0.38f, 0.8f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.30f, 0.28f, 0.1f, 0.7f, 0.48f, 0.9f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.1f, 0.28f, 0.30f, 0.9f, 0.48f, 0.7f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.12f, 0f, 0.12f, 0.88f, 0.38f, 0.88f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.19f, 0.38f, 0.19f, 0.81f, 0.56f, 0.81f);
		renderer.setRenderBoundsFromBlock(b);
		renderer.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(1 / 8F, 0, 1 / 8F, 1 - 1 / 8F, 1, 1 - 1 / 8F);
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
