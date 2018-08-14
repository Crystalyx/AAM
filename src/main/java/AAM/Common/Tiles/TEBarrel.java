package AAM.Common.Tiles;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Potions.IngridientItem;
import net.minecraft.tileentity.TileEntity;

public class TEBarrel extends TileEntity
{
	public int burnTime = 0;
	public List<IngridientItem> ingrs = new ArrayList<IngridientItem>();
	public int[] potion = new int[0];

	@Override
	public void updateEntity()
	{
		if (this.potion.length > 0 && this.burnTime < 400)
		{
			this.burnTime += 1;
		}

	}
}
