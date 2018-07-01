package AAM.Common.Items;

import java.util.Set;

import com.google.common.collect.Sets;

import AAM.Client.Renderer.Item.HammerRenderer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.MinecraftForgeClient;

public class AnvilHammer extends ItemTool
{
	public static Set blocks = Sets.newHashSet(new Block[] { Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore,
			Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail,
			Blocks.golden_rail, Blocks.activator_rail });

	public AnvilHammer(float attack, ToolMaterial mat)
	{
		super(attack, mat, blocks);

		MinecraftForgeClient.registerItemRenderer(this, new HammerRenderer());
	}

}