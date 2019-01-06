package aam.common.entity.elemental;

import aam.utils.vectors.Wec3;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Subwer extends EntityMob
{
	public Wec3 spawnPosition;

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		motionY *= 0.6000000238418579D;
	}

	public Subwer(World w)
	{
		super(w);
		this.getNavigator().setSpeed(0.5);
		tasks.addTask(2, new EntityAIWander(this, 1.0D));
		tasks.addTask(8, new EntityAIWander(this, 1.0D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		dataWatcher.addObject(16, new Byte((byte) 0));
		this.setSize(0.5F, 0.9F);
	}

	@Override
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.83000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	public float getSoundVolume()
	{
		return 0.1F;
	}

	@Override
	public void updateAITasks()
	{
		super.updateAITasks();

		if (spawnPosition != null && (!worldObj.isAirBlock((int) spawnPosition.x, (int) spawnPosition.y, (int) spawnPosition.z) || spawnPosition.y < 1))
		{
			spawnPosition = null;
		}

		if (spawnPosition == null || rand.nextInt(30) == 0 || spawnPosition.distanceTo(new Wec3((int) posX, (int) posY, (int) posZ)) < 4.0F)
		{
			spawnPosition = new Wec3((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));
		}

		double d0 = spawnPosition.x + 0.5D - posX;
		double d1 = spawnPosition.y + 0.2D - posY;
		double d2 = spawnPosition.z + 0.5D - posZ;
		motionX += (Math.signum(d0) * 0.5D - motionX) * 0.10000000149011612D;
		motionY += (Math.signum(d1) * 0.699999988079071D - motionY) * 0.10000000149011612D;
		motionZ += (Math.signum(d2) * 0.5D - motionZ) * 0.10000000149011612D;
		float f = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float f1 = MathHelper.wrapAngleTo180_float(f - rotationYaw);
		moveForward = 0.5F;
		rotationYaw += f1;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	@Override
	public float getSoundPitch()
	{
		return super.getSoundPitch() * 0.95F;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	public String getLivingSound()
	{
		return rand.nextInt(4) != 0 ? null : "mob.bat.idle";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	public String getHurtSound()
	{
		return "mob.bat.hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	public String getDeathSound()
	{
		return "mob.bat.death";
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		return false;
	}

	/**
	 * Returns true if the newer entity AI code should be run
	 */
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void fall(float p_70069_1_)
	{
	}

	@Override
	public void updateFallState(double p_70064_1_, boolean p_70064_3_)
	{
	}
}
