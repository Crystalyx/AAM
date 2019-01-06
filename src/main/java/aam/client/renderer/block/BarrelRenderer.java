package aam.client.renderer.block;

import aam.network.ClientProxy;
import aam.utils.MiscUtils;
import aam.utils.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class BarrelRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block b, int meta, int modelId, RenderBlocks r)
	{
		float t = 0.15F;
		float wd = 0.125F - 1 / 16f;
		float dt = 1 - t;
		float tough = 0.0625F;
		float th = tough / 2;
		float dwd = 1 - wd;
		float dwdtough = 1 - wd;
		float d = 0.000001f;
		b.setBlockBounds(t, t, wd, dt, dt, wd + tough);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t, t, dwd - tough, dt, dt, dwd);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t, t - th, 0, dt, t + th, 1);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t, dt - th, 0, dt, dt + th, 1);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t - th, t, 0, t + th, dt, 1);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(dt - th, t, 0, dt + th, dt, 1);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// legs
		b.setBlockBounds(t, 0, t, t + tough * 2, t * 2, t + tough * 2);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(dt - tough * 2, 0, t, dt, t * 2, t + tough * 2);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t, 0, dt - tough * 2, t + tough * 2, t * 2, dt);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(dt - tough * 2, 0, dt - tough * 2, dt, t * 2, dt);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// ======IRON======
		r.setOverrideBlockTexture(Blocks.iron_block.getIcon(0, 0));
		b.setBlockBounds(t - tough, t - th, wd, t, dt + th, wd + tough);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(dt, t - th, wd, dt + tough, dt + th, wd + tough);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t - th, t - tough, wd, dt + th, t, wd + tough);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t - th, dt, wd, dt + th, dt + tough, wd + tough);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);
		// ==================================
		b.setBlockBounds(t - tough, t - th, dwd - tough, t, dt + th, dwd);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(dt, t - th, dwd - tough, dt + tough, dt + th, dwd);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t - th, t - tough, dwd - tough, dt + th, t, dwd);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(t - th, dt, dwd - tough, dt + th, dt + tough, dwd);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		r.clearOverrideBlockTexture();

		b.setBlockBounds(0, 0.0F, 0, 1, 1, 1);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block b, int mid, RenderBlocks r)
	{
		float t = 0.15F;
		float wd = 0.125F - 1 / 16f;
		float dt = 1 - t;
		float tough = 0.0625F;
		float th = tough / 2;
		float dwd = 1 - wd;
		float dwdtough = 1 - wd;
		b.setBlockBounds(t, t, wd, dt, dt, wd + tough);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t, t, dwd - tough, dt, dt, dwd);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t, t - th, 0, dt, t + th, 1);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t, dt - th, 0, dt, dt + th, 1);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t - th, t, 0, t + th, dt, 1);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(dt - th, t, 0, dt + th, dt, 1);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// legs
		b.setBlockBounds(t, 0, t, t + tough * 2, t * 2, t + tough * 2);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(dt - tough * 2, 0, t, dt, t * 2, t + tough * 2);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t, 0, dt - tough * 2, t + tough * 2, t * 2, dt);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(dt - tough * 2, 0, dt - tough * 2, dt, t * 2, dt);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// ======IRON======
		r.setOverrideBlockTexture(Blocks.iron_block.getIcon(0, 0));
		b.setBlockBounds(t - tough, t - th, wd, t, dt + th, wd + tough);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(dt, t - th, wd, dt + tough, dt + th, wd + tough);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t - th, t - tough, wd, dt + th, t, wd + tough);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t - th, dt, wd, dt + th, dt + tough, wd + tough);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);
		// ================Another=Side==================
		b.setBlockBounds(t - tough, t - th, dwd - tough, t, dt + th, dwd);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(dt, t - th, dwd - tough, dt + tough, dt + th, dwd);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t - th, t - tough, dwd - tough, dt + th, t, dwd);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		b.setBlockBounds(t - th, dt, dwd - tough, dt + th, dt + tough, dwd);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		r.clearOverrideBlockTexture();

		b.setBlockBounds(0, 0.0F, 0, 1, 1, 1);
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
