package aam.common.entity;

import aam.common.soul.SoulDamageSource;
import aam.common.soul.SoulUpgrade;
import aam.common.soul.Trait;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

public class SoulCharge extends EntityThrowable
{

	public SoulCharge(World w)
	{
		super(w);
	}

	public String effs = "";

	public SoulCharge(World w, EntityLivingBase el)
	{
		super(w, el);
		if (el instanceof EntityPlayer)
		{
			ph = PlayerDataHandler.get((EntityPlayer) el);
		}
	}

	public SoulCharge(World w, double x, double y, double z)
	{
		super(w, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (ph != null)
		{
			this.handleAttacks(new Wec3(mop));
		}
	}

	public PlayerDataHandler ph;

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
		if (ph != null)
		{
			this.handleAttacks(new Wec3(this));
		}
	}

	int victims = 0;

	public void handleAttacks(Wec3 wp)
	{
		float r = 0.5f * ph.upgLevel[SoulUpgrade.Cast.ordinal()];
		List<EntityLivingBase> l = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, wp.extend(2f + r));
		l.remove(ph.player);
		for (EntityLivingBase e : l)
		{
			if (!effs.contains("D"))
			{
				if (ph.sword.bypassesArmor)
				{
					e.attackEntityFrom(new SoulDamageSource(ph).setDamageBypassesArmor(), ph.getFullRangedDamageAgainst(e, true));
				}
				else
				{
					e.attackEntityFrom(new SoulDamageSource(ph), ph.getFullRangedDamageAgainst(e, true));
				}
			}
			if (effs.contains("F"))
			{
				e.setFire((int) (ph.getTrait(Trait.Level) * 2));
			}
			if (effs.contains("P"))
			{
				e.addPotionEffect(new PotionEffect(19, (int) (ph.getTrait(Trait.Level) * 2), 2));
			}
			if (effs.contains("K"))
			{
				Wec3 vec = new Wec3(0, Math.min(Math.sqrt(ph.getTrait(Trait.Level)) / 3, 10), 0).add(new Wec3(motionX, motionY, motionZ));
				vec.ptm(e);
			}
			if (victims > 2)
			{
				this.setDead();
				break;
			}
			else
			{
				victims++;
			}
		}
	}

	public int life = 0;
	public int maxLife = 100;

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
	public AxisAlignedBB getCollisionBox(Entity e)
	{
		return new Wec3(e).extend(1);
	}

}
