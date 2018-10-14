package aam.api;

import aam.common.tiles.TEModificationAnvil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class AnvilRecipe
{
	public abstract boolean matches(TEModificationAnvil ic, World w);

	public abstract ItemStack getCraftingResult(TEModificationAnvil ic);

	public abstract void onCrafted(TEModificationAnvil ic);
}
