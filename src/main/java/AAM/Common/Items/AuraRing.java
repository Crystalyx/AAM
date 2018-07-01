package AAM.Common.Items;

import java.util.List;

import AAM.Common.Aura.AuraRegistry;
import AAM.Utils.Color;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AuraRing extends Item implements IBauble
{
	public AuraRing()
	{
		this.setHasSubtypes(true);
		this.setTextureName("aam:ring_aura");
		this.maxStackSize = 1;
	}

	@Override
	public void getSubItems(Item is, CreativeTabs p_150895_2_, List l)
	{
		for (int i = 0; i < 5; i++)
		{
			l.add(new ItemStack(is, 1, i));
		}
	}

	public static Color[] rings = new Color[] { new Color(58, 17, 186), new Color(212, 44, 5), new Color(255, 242, 53), new Color(19, 160, 10), new Color(3, 67, 194) };

	@Override
	public int getColorFromItemStack(ItemStack i, int pass)
	{
		return rings[i.getItemDamage()].hex;
	}

	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		return this.getUnlocalizedName() + i.getItemDamage();
	}

	@Override
	public BaubleType getBaubleType(ItemStack var1)
	{
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack i, EntityLivingBase e)
	{
		AuraRegistry.auras.get(i.getItemDamage()).startTick(e);
	}

	@Override
	public void onEquipped(ItemStack var1, EntityLivingBase var2)
	{
	}

	@Override
	public void onUnequipped(ItemStack var1, EntityLivingBase var2)
	{

	}

	@Override
	public boolean canEquip(ItemStack var1, EntityLivingBase var2)
	{
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack var1, EntityLivingBase var2)
	{
		return true;
	}
}
