package PathMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class GoldKnife extends ItemSword
{
	public GoldKnife()
	{
		super(ToolMaterial.GOLD);
		this.setTextureName("pathmod:gold_knife");
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

}
