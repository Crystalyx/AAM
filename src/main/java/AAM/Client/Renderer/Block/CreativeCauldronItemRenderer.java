package AAM.Client.Renderer.Block;

import AAM.Network.ClientProxy;
import AAM.Utils.MiscUtils;
import AAM.Utils.Render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class CreativeCauldronItemRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block b, int meta, int modelId, RenderBlocks r)
	{// -z
		b.setBlockBounds(0.11F, 0.155F, 0.1F, 0.89F, 0.75F, 0.175F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// +z
		b.setBlockBounds(0.11F, 0.155F, 0.825F, 0.89F, 0.75F, 0.9F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// -x
		b.setBlockBounds(0.1F, 0.155F, 0.11F, 0.175F, 0.75F, 0.89F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// +x
		b.setBlockBounds(0.825F, 0.155F, 0.11F, 0.9F, 0.75F, 0.89F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// bottom
		b.setBlockBounds(0.11F, 0.145F, 0.11F, 0.89F, 0.185F, 0.89F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		float bef = 5f / 32f;
		float wdt = 3f / 32f;

		b.setBlockBounds(bef, 0, bef, bef + wdt, 0.145F, bef + wdt);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(1 - (bef + wdt), 0, bef, 1 - bef, 0.145F, bef + wdt);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(bef, 0, 1 - (bef + wdt), bef + wdt, 0.145F, 1 - bef);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(1 - (bef + wdt), 0, 1 - (bef + wdt), 1 - bef, 0.145F, 1 - bef);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0.0525F, 0.0F, 0.0525F, 0.9475F, 0.8875F, 0.9475F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block b, int mid, RenderBlocks r)
	{
		// -z
		b.setBlockBounds(0.11F, 0.155F, 0.1F, 0.89F, 0.75F, 0.175F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// +z
		b.setBlockBounds(0.11F, 0.155F, 0.825F, 0.89F, 0.75F, 0.9F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// -x
		b.setBlockBounds(0.1F, 0.155F, 0.11F, 0.175F, 0.75F, 0.89F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// +x
		b.setBlockBounds(0.825F, 0.155F, 0.11F, 0.9F, 0.75F, 0.89F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// bottom
		b.setBlockBounds(0.11F, 0.145F, 0.11F, 0.89F, 0.185F, 0.89F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		float bef = 5f / 32f;
		float wdt = 3f / 32f;

		b.setBlockBounds(bef, 0, bef, bef + wdt, 0.145F, bef + wdt);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(1 - (bef + wdt), 0, bef, 1 - bef, 0.145F, bef + wdt);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(bef, 0, 1 - (bef + wdt), bef + wdt, 0.145F, 1 - bef);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(1 - (bef + wdt), 0, 1 - (bef + wdt), 1 - bef, 0.145F, 1 - bef);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(0.0525F, 0.0F, 0.0525F, 0.9475F, 0.8875F, 0.9475F);
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
