package AAM.Common.Recipes;

import AAM.Common.Items.ModItems;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Soul;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class EnchantingRecipe implements IRecipe
{

	public EnchantingRecipe(int count, Soul type)
	{
		this.count = count;
		this.type = type;
	}

	public int count;
	public Soul type;

	@Override
	public boolean matches(InventoryCrafting i, World w)
	{
		boolean ret = false;
		if (MiscUtils.contains(i, ModItems.SoulSword) && MiscUtils.count(i, ModItems.SoulSword) == 1)
		{
			if (MiscUtils.contains(i, Items.iron_ingot) && MiscUtils.count(i, Items.iron_ingot) == this.count)
			{
				int slot = MiscUtils.get(i, ModItems.SoulSword);
				ItemStack is = i.getStackInSlot(slot);
				if (is.hasTagCompound())
				{
					String nickname = is.getTagCompound().getString("Owner");
					EntityPlayer p = w.getPlayerEntityByName(nickname);
					if (p != null)
					{
						PlayerDataHandler ph = PlayerDataHandler.get(p);
						if (ph.stype == this.type)
						{
							return true;
						}
					}
				}
			}
		}
		return ret;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting i)
	{
		return new ItemStack(ModItems.materials, this.count, 4 + this.type.ordinal());
	}

	@Override
	public int getRecipeSize()
	{
		return this.count + 1;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(ModItems.materials, this.count, 4 + this.type.ordinal());
	}

}
