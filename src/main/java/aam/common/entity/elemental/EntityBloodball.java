package aam.common.entity.elemental;

import aam.utils.MiscUtils;
import aam.utils.vectors.BlockState;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityBloodball extends EntityThrowable
{

	public int life = 0;
	public int maxLife = 40;
	public EntityLivingBase shootingEntity;

	public EntityBloodball(World w)
	{
		super(w);
	}

	public EntityBloodball(World w, EntityLivingBase el)
	{
		super(w, el);
	}

	public EntityBloodball(World w, double x, double y, double z)
	{
		super(w, x, y, z);
	}

	public EntityBloodball(World p_i1755_1_, EntityLivingBase archer, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_)
	{
		super(p_i1755_1_);
		renderDistanceWeight = 10.0D;
		shootingEntity = archer;

		posY = archer.posY + archer.getEyeHeight() - 0.10000000149011612D;
		double d0 = p_i1755_3_.posX - archer.posX;
		double d1 = p_i1755_3_.boundingBox.minY + p_i1755_3_.height / 3.0F - posY - 2;
		double d2 = p_i1755_3_.posZ - archer.posZ;
		double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) -(Math.atan2(d1, d3) * 180.0D / Math.PI);
			double d4 = 2 * d0 / d3;
			double d5 = 2 * d2 / d3;
			this.setLocationAndAngles(archer.posX + d4, posY, archer.posZ + d5, f2, f3);
			yOffset = 0.0F;
			float f4 = (float) d3 * 0.2F;
			this.setThrowableHeading(d0, d1 + f4, d2, p_i1755_4_, p_i1755_5_);
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (mop.entityHit != null)
		{
			if (mop.entityHit instanceof EntityLivingBase)
			{
				EntityLivingBase el = (EntityLivingBase) mop.entityHit;
				el.addPotionEffect(new PotionEffect(Potion.wither.id, 100));
				el.attackEntityFrom(DamageSource.causeMobDamage(shootingEntity), (float) shootingEntity.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
				this.setDead();
			}
		}
		if (mop.typeOfHit.equals(MovingObjectType.BLOCK))
		{
			Wec3 tile = new Wec3(mop.blockX, mop.blockY, mop.blockZ);
			for (int i = 1; i <= 1; i++)
			{
				for (int j = 1; j <= 1; j++)
				{
					int h = MiscUtils.getLowerHighBlock(worldObj, new Wec3(i, 2, j).add(tile), 3);
					if (worldObj.getBlock((int) tile.x + i, h + 1, (int) tile.z + j).isAir(worldObj, (int) tile.x + i, h + 1, (int) tile.z + j) && worldObj.getBlock((int) tile.x + i, h, (int) tile.z + j).isNormalCube())
					{
						BlockState p = new BlockState(Blocks.fire, (int) (i + tile.x), h + 1, (int) (j + tile.z));
						p.print(worldObj);
					}
				}
			}
		}

	}

	public void setLife(int ticks)
	{
		maxLife = ticks;
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0f;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (life >= maxLife)
		{
			this.setDead();
		}
		else
		{
			life += 1;
		}
	}

}
