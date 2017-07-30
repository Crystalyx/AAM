package AAM.Common.Potions;

import AAM.Core.AAMConfig;
import AAM.Utils.PlayerDataHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class PotionEffs extends Potion
{

	public PotionEffs(int id, boolean bad)
	{
		super(id, bad, 0xffffff);
		int a = Math.floorMod(id - AAMConfig.genericPID, 14);
		int b = Math.floorDiv(id - AAMConfig.genericPID, 14);
		this.setIconIndex(a, b);
	}

	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("aam", "textures/misc/potions.png"));
		return super.getStatusIconIndex();
	}

	@Override
	public void applyAttributesModifiersToEntity(EntityLivingBase e, BaseAttributeMap atr, int hz)
	{
		super.applyAttributesModifiersToEntity(e, atr, hz);
	}

	/**
	 * Returns true if the potion has an instant effect instead of a continuous
	 * one (eg Harming)
	 */
	public boolean isInstant()
	{
		return id == ModPotions.antidote.id;
	}

	@Override
	public void performEffect(EntityLivingBase e, int level)
	{
		int a = Math.floorMod(id - AAMConfig.genericPID, 14);
		int b = Math.floorDiv(id - AAMConfig.genericPID, 14);
		this.setIconIndex(a, b);
		if (id == ModPotions.heal.id)
		{
			e.heal(0.5F + 0.5F * level);
		}
		if (id == ModPotions.ice.id)
		{
			for (int i = -3; i < 4; i++)
			{
				for (int j = -3; j < 4; j++)
				{
					for (int k = -3; k < 4; k++)
					{
						if (e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.water
								|| e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.flowing_water)
						{
							e.worldObj.setBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k, Blocks.ice, 0, 2);
						}
						if (e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.lava
								|| e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.flowing_lava)
						{
							e.worldObj.setBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k, Blocks.obsidian, 0, 2);
						}
					}
				}
			}
		}
		if (id == ModPotions.antidote.id)
		{
			if (e.getActivePotionEffect(Potion.poison) != null)
			{
				PotionEffect eff = e.getActivePotionEffect(Potion.poison);
				e.getActivePotionEffects().remove(eff);
			}
			if (e.getActivePotionEffect(ModPotions.poison) != null)
			{
				PotionEffect eff = e.getActivePotionEffect(ModPotions.poison);
				e.getActivePotionEffects().remove(eff);
			}
		}
		if (id == ModPotions.poison.id)
		{
			if (e.getHealth() > 1.0F * level)
			{
				e.attackEntityFrom(DamageSource.magic, 1.0F + level);
			}
		}
	}

	public boolean isReady(int par1, int par2)
	{
		return true;
	}
}
