package aam.common.items.artifacts;

import aam.api.abstraction.ItemArtifact;
import net.minecraft.entity.EntityLivingBase;
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
