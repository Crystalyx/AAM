package AAM.Common.Items;

import AAM.API.ItemArtifact;
import AAM.Utils.PlayerDataHandler;
import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class FacelessCharm extends ItemArtifact
{

	public FacelessCharm(int soul)
	{
		super("", "", 0);
		this.setTextureName("aam:faceless_charm");
	}

	@Override
	public void onWornTick(ItemStack i, EntityLivingBase e)
	{
		// TODO
	}

}
