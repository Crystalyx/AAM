package aam.common.entity;

import java.util.List;

import aam.common.soul.SoulDamageSource;
import aam.common.soul.SoulUpgrade;
import aam.common.soul.Trait;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class StaffCharge extends EntityThrowable
{

	public StaffCharge(World w)
	{
		super(w);
	}

	public String effs = "";
	public float dmg = 7f;

	public StaffCharge(World w, EntityLivingBase el, float dmg)
	{
		super(w, el);
		if (el instanceof EntityPlayer)
		{
			ph = PlayerDataHandler.get((EntityPlayer) el);
		}
		this.dmg = dmg;
	}

	public StaffCharge(World w, float dmg, double x, double y, double z)
	{
		super(w, x, y, z);
		this.dmg = dmg;
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
					e.attackEntityFrom(new SoulDamageSource(ph).setDamageBypassesArmor(), dmg);
				}
				else
				{
					e.attackEntityFrom(new SoulDamageSource(ph), dmg);
				}
			}
			if (effs.contains("F"))
			{
				e.setFire((int) (ph.getTrait(Trait.Level) * 2));
			}
			if (effs.contains("P"))
			{
				e.addPotionEffect(new PotionEffect(Potion.poison.id, (int) (ph.getTrait(Trait.Level) * 2), 2));
			}
			if (effs.contains("K"))
			{
				Wec3 vec = new Wec3(0, Math.min(Math.sqrt(ph.getTrait(Trait.Level)) / 3, 10), 0).add(new Wec3(motionX, motionY, motionZ));
				vec.ptm(e);
			}
			if (victims > 2)
			{
				this.setDead();
				return;
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
		return new Wec3(e).extendBoth(0.5f);
	}

}
