package aam.api.abstraction;

import aam.api.interfaces.IStructureCore;
import aam.utils.vectors.Wec3;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class StructureCore extends TileEntity implements IStructureCore
{
	public boolean formed = false;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (worldObj.getWorldTime() % 200 == 5)
		{
			boolean formBefore = formed;
			if (this.getStructure() != null)
			{
				formed = this.getStructure().checkStructure(worldObj, new Wec3(this).sub(this.getOffset()));
				if (formBefore != formed)
				{
					if (formed)
					{
						this.onFormed();
					}
					if (!formed)
					{
						this.onUnFormed();
					}
				}
			}
		}
	}

	public abstract void onFormed();

	public abstract void onUnFormed();

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setBoolean("StrFormed", formed);
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		formed = tag.getBoolean("StrFormed");
		super.readFromNBT(tag);
	}

}
