package AAM.Common.Entity;

import java.util.List;

import AAM.Common.Soul.SoulDamageSource;
import AAM.Common.Soul.SoulUpgrade;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

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
			this.ph = PlayerDataHandler.get((EntityPlayer) el);
		}
	}

	public SoulCharge(World w, double x, double y, double z)
	{
		super(w, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (this.ph != null)
			this.handleAttacks(new Wec3(mop));
	}

	public PlayerDataHandler ph;

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.life >= this.maxLife)
		{
			this.setDead();
		}
		else
			this.life += 1;
		if (this.ph != null)
			this.handleAttacks(new Wec3(this));
	}

	public void handleAttacks(Wec3 wp)
	{
		float r = 0.5f * ph.upgLevel[SoulUpgrade.Cast.ordinal()];
		List<EntityLivingBase> l = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, wp.extend(2f + r));
		l.remove(this.ph.player);
		int victims = 0;
		for (EntityLivingBase e : l)
		{
			if (!this.effs.contains("D"))
			{
				if (this.ph.sword.bypassesArmor)
					e.attackEntityFrom(new SoulDamageSource(this.ph).setDamageBypassesArmor(), this.ph.getFullRangedDamageAgainst(e, true));
				else
					e.attackEntityFrom(new SoulDamageSource(this.ph), this.ph.getFullRangedDamageAgainst(e, true));
			}
			if (this.effs.contains("F"))
			{
				e.setFire(this.ph.getSoulLevel() * 2);
			}
			if (this.effs.contains("P"))
			{
				e.addPotionEffect(new PotionEffect(19, this.ph.getSoulLevel() * 2, 2));
			}
			if (this.effs.contains("K"))
			{
				Wec3 vec = new Wec3(0, Math.min(Math.sqrt(this.ph.getSoulLevel()) / 3, 10), 0).add(new Wec3(this.motionX, this.motionY, this.motionZ));
				vec.ptm(e);
			}
			if (victims > 2)
			{
				this.setDead();
				break;
			}
			else
				victims++;
		}
	}

	public int life = 0;
	public int maxLife = 100;

	public void setLife(int ticks)
	{
		this.maxLife = ticks;
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
