package AAM.Common.Entity;

import AAM.Utils.WorldPos;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Subwer extends EntityMob
{
	public WorldPos spawnPosition;

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.motionY *= 0.6000000238418579D;
	}
	
	public Subwer(World w)
	{
		super(w);
		this.getNavigator().setSpeed(0.5);
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.dataWatcher.addObject(16, new Byte((byte) 0));
		this.setSize(0.5F, 0.9F);
	}

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
	public float getSoundVolume()
	{
		return 0.1F;
	}

	@Override
	public void updateAITasks()
	{
		super.updateAITasks();

		if (this.spawnPosition != null && (!this.worldObj.isAirBlock((int) this.spawnPosition.x, (int) this.spawnPosition.y, (int) this.spawnPosition.z) || this.spawnPosition.y < 1))
		{
			this.spawnPosition = null;
		}

		if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceTo(new WorldPos((int) this.posX, (int) this.posY, (int) this.posZ)) < 4.0F)
		{
			this.spawnPosition = new WorldPos((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		}

		double d0 = (double) this.spawnPosition.x + 0.5D - this.posX;
		double d1 = (double) this.spawnPosition.y + 0.2D - this.posY;
		double d2 = (double) this.spawnPosition.z + 0.5D - this.posZ;
		this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
		this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
		this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
		float f = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
		this.moveForward = 0.5F;
		this.rotationYaw += f1;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	public float getSoundPitch()
	{
		return super.getSoundPitch() * 0.95F;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	public String getLivingSound()
	{
		return this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	public String getHurtSound()
	{
		return "mob.bat.hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	public String getDeathSound()
	{
		return "mob.bat.death";
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	public boolean canBePushed()
	{
		return false;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	public void fall(float p_70069_1_)
	{
	}

	public void updateFallState(double p_70064_1_, boolean p_70064_3_)
	{
	}
}
