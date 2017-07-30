package AAM.API;

import AAM.Utils.WorldPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class StructureCore extends TileEntity implements IStructureCore
{
	public boolean formed = false;

	public void updateEntity()
	{
		super.updateEntity();
		if (this.worldObj.getWorldTime() % 200 == 5)
		{
			boolean f = this.formed;
			this.formed = this.getStructure().checkStructure(this.worldObj, new WorldPos(this).subtruct(this.getOffset()));
			if (f != this.formed)
			{
				if (this.formed)
					this.onFormed();
				if (!this.formed)
					this.onUnFormed();
			}
		}
	}

	public abstract void onFormed();

	public abstract void onUnFormed();

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setBoolean("StrFormed", this.formed);
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		this.formed = tag.getBoolean("StrFormed");
		super.readFromNBT(tag);
	}

}
