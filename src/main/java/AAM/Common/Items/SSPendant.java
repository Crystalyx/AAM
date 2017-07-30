package AAM.Common.Items;

import AAM.Utils.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SSPendant extends Item
{
	public SSPendant()
	{
		this.setTextureName("aam:tools/chalk/pendant");
		this.setHasSubtypes(true);
	}

	@Override
	public boolean hasEffect(ItemStack i, int pass)
	{
		return i.getItemDamage() == 1;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		Logger.info(p.getCurrentEquippedItem().getItemDamage());
		if (p.getCurrentEquippedItem().getItemDamage() == 0)
		{
			p.getCurrentEquippedItem().setItemDamage(1);
			Logger.info(p.getCurrentEquippedItem().getItemDamage());

		}
		return true;
	}
}
