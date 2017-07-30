package AAM.Common.Items;

import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class ItemChalk extends ItemTool
{
	public ItemChalk()
	{
		super(0, EnumHelper.addToolMaterial("aamchalk", 0, 128, 0, 0, 0), null);
		this.setTextureName("aam:tools/chalk/chalk_blank");
	}
}
