package AAM.Client.Renderer.Block;

import org.lwjgl.opengl.GL11;

import AAM.Common.Blocks.BerryBush;
import AAM.Network.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class BushRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		int meta = Math.floorDiv(world.getBlockMetadata(x, y, z), 2);
		int beta = Math.floorMod(world.getBlockMetadata(x, y, z), 2);

		float atSideN = 0.45F;
		float atSideP = 0.55F;
		float notSideN = 0.05F;
		float notSideP = 0.15F;
		float needed = 0.8F;

		if (beta == 1)
		{
			renderer.setOverrideBlockTexture(BerryBush.berries[meta]);

			block.setBlockBounds(notSideN, atSideN - 0.2F, atSideN, notSideP, atSideP - 0.2F, atSideP);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(notSideN + needed, atSideN - 0.2F, atSideN, notSideP + needed, atSideP - 0.2F, atSideP);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN, atSideN - 0.2F, notSideN, atSideP, atSideP - 0.2F, notSideP);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN, atSideN - 0.2F, notSideN + needed, atSideP, atSideP - 0.2F, notSideP + needed);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN, notSideN + needed, atSideN - 0.2F, atSideP, notSideP + needed, atSideP - 0.2F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			// Second Berry

			block.setBlockBounds(notSideN, atSideN - 0.1F, atSideN + 0.2F, notSideP, atSideP - 0.1F, atSideP + 0.2F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(notSideN + needed, atSideN - 0.1F, atSideN + 0.2F, notSideP + needed, atSideP - 0.1F, atSideP + 0.2F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN + 0.2F, atSideN - 0.1F, notSideN, atSideP + 0.2F, atSideP - 0.1F, notSideP);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN + 0.2F, atSideN - 0.1F, notSideN + needed, atSideP + 0.2F, atSideP - 0.1F, notSideP + needed);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN + 0.2F, notSideN + needed, atSideN - 0.1F, atSideP + 0.2F, notSideP + needed, atSideP - 0.1F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			// Third Berry
			block.setBlockBounds(notSideN, atSideN + 0.25F, atSideN - 0.25F, notSideP, atSideP + 0.25F, atSideP - 0.25F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(notSideN + needed, atSideN + 0.25F, atSideN - 0.25F, notSideP + needed, atSideP + 0.25F, atSideP - 0.25F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN - 0.25F, atSideN + 0.25F, notSideN, atSideP - 0.25F, atSideP + 0.25F, notSideP);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN - 0.25F, atSideN + 0.25F, notSideN + needed, atSideP - 0.25F, atSideP + 0.25F, notSideP + needed);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(atSideN - 0.25F, notSideN + needed, atSideN + 0.25F, atSideP - 0.25F, notSideP + needed, atSideP + 0.25F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			renderer.clearOverrideBlockTexture();
		}
		// ===============================================
		renderer.setOverrideBlockTexture(Blocks.log.getIcon(2, 0));

		block.setBlockBounds(0.35F, 0.0F, 0.35F, 0.65F, 0.4F, 0.65F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.clearOverrideBlockTexture();

		renderer.setOverrideBlockTexture(BerryBush.berries[4]);
		for (int i = 0; i < 8; i++)
		{
			block.setBlockBounds(0.1F + 0.05F * i, 0.1F + 0.05F * i, 0.1F + 0.05F * i, 0.9F - 0.05F * i, 0.9F - 0.05F * i, 0.9F - 0.05F * i);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
		}
		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.1F, 0.1F, 0.1F, 0.9F, 0.9F, 0.9F);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return ClientProxy.getNextRenderId();
	}

}
