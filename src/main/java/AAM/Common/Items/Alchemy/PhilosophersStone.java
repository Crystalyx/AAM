package aam.common.items.alchemy;

import aam.api.abstraction.EnergyStorage;
import aam.client.renderer.item.PhiloRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

public class PhilosophersStone extends EnergyStorage
{
	public PhilosophersStone()
	{
		this.setTextureName("aam:tools/philosophers_stone");
		MinecraftForgeClient.registerItemRenderer(this, new PhiloRenderer());
		this.setMaxStackSize(1);
	}

	@Override
	public boolean canBeCharged(ItemStack is)
	{
		return false;
	}

	@Override
	public boolean canBeDischarged(ItemStack is)
	{
		return true;
	}

	@Override
	public double getMaxStoredEnergy(ItemStack i)
	{
		return Integer.MAX_VALUE;
	}
}
