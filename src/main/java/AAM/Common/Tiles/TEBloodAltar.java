package AAM.Common.Tiles;

import AAM.Common.Blocks.Building.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class TEBloodAltar extends TETransCircle
{
	public FluidStack blood = new FluidStack(ModBlocks.blood, 0);

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (blood.amount > 10000)
		{
			blood.amount = 10000;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.blood.amount = tag.getInteger("Blood");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("Blood", this.blood.amount);

	}
}
