package AAM.Common.Items;

import AAM.API.ItemArtifact;
import AAM.Utils.PlayerDataHandler;
import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class LuckyCoin extends ItemArtifact
{
	public LuckyCoin()
	{
		super("","",0);
		this.setTextureName("aam:coin_lucky");
	}

	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		return "aam.luckycoin" + i.getItemDamage();
	}

	@Override
	public void onWornTick(ItemStack i, EntityLivingBase e)
	{

	}
}
