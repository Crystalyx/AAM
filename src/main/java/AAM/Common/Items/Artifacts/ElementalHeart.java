package AAM.Common.Items.Artifacts;

import AAM.API.ItemArtifact;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ElementalHeart extends ItemArtifact
{
	public ElementalHeart()
	{
		super("aam.eheart", "aam.eheart.descr", 3);
		this.setMaxStackSize(1);
		this.setTextureName("aam:elemental_heart");
	}

	@Override
	public String getHealthModifierName(ItemStack i)
	{
		return "EBEAFB";
	}

	@Override
	public String getKnockbackModifierName(ItemStack i)
	{
		return "EBEAFB";
	}

	@Override
	public float getKnockbackModifierValue(ItemStack par1ItemStack)
	{
		return 0.5f;
	}

	@Override
	public double getHealthModifierValue(ItemStack i)
	{
		return 20d;
	}

	@Override
	public void onWornTick(ItemStack i, EntityLivingBase e)
	{

	}

}
