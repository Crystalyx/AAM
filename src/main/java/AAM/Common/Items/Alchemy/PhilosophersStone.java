package AAM.Common.Items.Alchemy;

import AAM.API.EnergyStorage;
import AAM.API.ICircleExtender;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PhilosophersStone extends EnergyStorage implements ICircleExtender
{
	public PhilosophersStone()
	{
		this.setTextureName("aam:tools/philosophers_stone");
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
		return Integer.MAX_VALUE / 1024d;
	}

	@Override
	public void onExtended(ItemStack i, EntityPlayer p, World w, Wec3 pos)
	{

	}
}
