package AAM.Client.Renderer.Block;

import AAM.Common.Blocks.Plants.BerryBush;
import AAM.Network.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class BushSproutRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		int beta = Math.floorMod(world.getBlockMetadata(x, y, z), 4);

		renderer.setOverrideBlockTexture(Blocks.log.getIcon(2, 0));

		block.setBlockBounds(0.35F, 0.0F, 0.35F, 0.65F, 0.4F, 0.65F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.clearOverrideBlockTexture();

		renderer.setOverrideBlockTexture(BerryBush.berries[4]);
		for (int i = 2; i < 2 + 2 * beta; i++)
		{
			block.setBlockBounds(0.1F + 0.05F * (7 - i), 0.1F + 0.05F * (7 - i), 0.1F + 0.05F * (7 - i), 0.9F - 0.05F * (7 - i), 0.9F - 0.05F * (7 - i), 0.9F - 0.05F * (7 - i));
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
