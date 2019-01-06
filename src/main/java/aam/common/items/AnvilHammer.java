package aam.common.items;

import aam.api.interfaces.IUpgradableItem;
import aam.client.renderer.item.HammerRenderer;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.Set;

public class AnvilHammer extends ItemTool implements IUpgradableItem
{
	public static Set blocks = Sets.newHashSet(Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore,
			Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail,
			Blocks.golden_rail, Blocks.activator_rail);

	public AnvilHammer(float attack, ToolMaterial mat)
	{
		super(attack, mat, blocks);

		MinecraftForgeClient.registerItemRenderer(this, new HammerRenderer());
	}

	@Override
	public int getUpgradeLevel(ItemStack is)
	{
		assertHasTag(is);
		return is.getTagCompound().getInteger("Level");
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is)
	{
		assertHasTag(is);
		is.getTagCompound().setInteger("Level", 1 + getUpgradeLevel(is));
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is, int level)
	{
		assertHasTag(is);
		is.getTagCompound().setInteger("Level", level + getUpgradeLevel(is));

	}

	@Override
	public void setUpgradeLevel(World w, ItemStack is, int level)
	{
		assertHasTag(is);
		is.getTagCompound().setInteger("Level", level);

	}

	public void assertHasTag(ItemStack i)
	{
		if (!i.hasTagCompound())
		{
			i.setTagCompound(new NBTTagCompound());
		}
	}

	@Override
	public int getMaxLevel(ItemStack is)
	{
		return 15;
	}

	@Override
	public boolean enableLayers(ItemStack is)
	{
		return false;
	}

	@Override
	public int getMinSlotCount(ItemStack is)
	{
		return 0;
	}

	@Override
	public int getMaxSlotCount(ItemStack is)
	{
		return 1;
	}

	@Override
	public int getDurability(ItemStack is)
	{
		return -1;
	}

	@Override
	public int getMaxRepairCount(ItemStack is)
	{
		return 3;
	}

}