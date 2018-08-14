package AAM.Client.Renderer.Block;

import AAM.Client.Model.Altar;
import AAM.Network.ClientProxy;
import AAM.Utils.MiscUtils;
import AAM.Utils.Render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class ModificationAnvilItemRender implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block b, int meta, int id, RenderBlocks r)
	{
		// -z
		b.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.2F, 0.8F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// +z
		b.setBlockBounds(0.25F, 0.2F, 0.25F, 0.75F, 0.3F, 0.75F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// -x
		b.setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.6F, 0.7F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		// +x
		b.setBlockBounds(6 / 32f, 0.55F, 0F, 1 - 6 / 32f, 0.9F, 1F);
		r.setRenderBoundsFromBlock(b);
		RenderUtils.drawSides(r, b, MiscUtils.getIconArray(b, meta), true);

		b.setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	public static final Altar model = new Altar();
	public static final ResourceLocation textures = new ResourceLocation("aam:textures/misc/altar.png");

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block b, int id, RenderBlocks r)
	{
		// -z
		b.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.2F, 0.8F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// +z
		b.setBlockBounds(0.25F, 0.2F, 0.25F, 0.75F, 0.3F, 0.75F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// -x
		b.setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.6F, 0.7F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

		// +x
		b.setBlockBounds(6 / 32f, 0.55F, 0F, 1 - 6 / 32f, 0.9F, 1F);
		r.setRenderBoundsFromBlock(b);
		r.renderStandardBlock(b, x, y, z);

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
