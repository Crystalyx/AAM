package AAM.Common.Items;

import AAM.API.ItemArtifact;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SeaShard extends ItemArtifact
{

	public SeaShard()
	{
		super("aam.seashard", "aam.seashard.descr",2);
		this.setMaxStackSize(1);
	}
	
	@Override
	public String getHealthModifierName(ItemStack i)
	{
		return "AEAAB";
	}
	
	@Override
	public double getHealthModifierValue(ItemStack i)
	{
		return 20;
	}
	
	@Override
	public void onUpdate(ItemStack i, EntityPlayer p)
	{
		super.onUpdate(i, p);
		p.setAir(1);
	}

	@Override
	public void onWornTick(ItemStack i, EntityLivingBase e)
	{
		
	}

}
