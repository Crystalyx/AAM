package AAM.Common.Potions;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Core.AAMConfig;
import AAM.Utils.Wec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;

public class PotionEffects extends Potion
{

	public PotionEffects(int id, boolean bad)
	{
		super(id, bad, 0xffffff);
		int a = Math.floorMod(id - AAMConfig.genericPID, 14);
		int b = Math.floorDiv(id - AAMConfig.genericPID, 14);
		this.setIconIndex(a, b);
	}

	@Override
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
	@Override
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
			e.heal(0.25F + 0.15F * level);
		}
		if (id == ModPotions.ice.id)
		{
			Wec3 p = new Wec3(e);
			p = p.sub(new Wec3(0, 0, 0));
			if (e.worldObj.getBlock(p.ix, p.iy - 1, p.iz).isBlockSolid(e.worldObj, p.ix, p.iy - 1, p.iz, 1) && e.worldObj.isAirBlock(p.ix, p.iy, p.iz))
			{
				e.worldObj.setBlock(p.ix, p.iy, p.iz, Blocks.snow_layer);
			}

			for (int i = -3; i < 4; i++)
			{
				for (int j = -3; j < 4; j++)
				{
					for (int k = -3; k < 4; k++)
					{
						if (e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.water || e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.flowing_water)
						{
							e.worldObj.setBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k, Blocks.ice, 0, 2);
						}
						if (e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.lava || e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == Blocks.flowing_lava)
						{
							if (e.worldObj.getBlockMetadata((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == 0)
								e.worldObj.setBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k, Blocks.obsidian, 0, 2);
							else
								e.worldObj.setBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k, Blocks.cobblestone, 0, 2);
						}
						if (e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k) == ModBlocks.BloodBlock)
						{
							// if(MiscUtils.randWPercent(10))
							if (((BlockFluidClassic) e.worldObj.getBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k)).isSourceBlock(e.worldObj, (int) e.posX + i, (int) e.posY + j, (int) e.posZ + k))
								e.worldObj.setBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k, ModBlocks.miniumBlock, 0, 2);
							else
								e.worldObj.setBlock((int) e.posX + i, (int) e.posY + j, (int) e.posZ + k, Blocks.ice, 0, 2);
						}
					}
				}
			}
		}
		if (id == ModPotions.flame.id)

		{
			Wec3 p = new Wec3(e);
			p = p.sub(new Wec3(0, 0, +1));
			if (e.worldObj.getBlock(p.ix, p.iy - 1, p.iz).isBlockSolid(e.worldObj, p.ix, p.iy - 1, p.iz, 1) && e.worldObj.isAirBlock(p.ix, p.iy, p.iz))
			{
				e.worldObj.setBlock(p.ix, p.iy, p.iz, Blocks.fire);
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

	@Override
	public boolean isReady(int par1, int par2)
	{
		return true;
	}
}
