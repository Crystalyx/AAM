package aam.client.renderer.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class SheathedSwordRenderer implements IItemRenderer
{
	@Override
	public boolean handleRenderType(ItemStack _item, ItemRenderType _type)
	{
		return !_type.equals(ItemRenderType.INVENTORY) &&
				_item.hasTagCompound() && _item.getTagCompound().getBoolean("Unequipped");
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType _type, ItemStack _item, ItemRendererHelper _helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType _type, ItemStack _item, Object... _data)
	{

	}
}
