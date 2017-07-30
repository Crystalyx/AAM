package AAM.Client.Renderer.Block;

import AAM.Client.Renderer.Tile.CreativeCauldronRenderer;
import AAM.Network.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class CreativeCauldronItemRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		CreativeCauldronRenderer rend = new CreativeCauldronRenderer();
		rend.renderTileEntityAt(null, -0.5, -0.5, -0.5, 0.0F);
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
