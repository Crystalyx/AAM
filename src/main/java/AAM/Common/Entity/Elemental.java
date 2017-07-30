package AAM.Common.Entity;

import AAM.Common.Items.ModItems;
import AAM.Utils.WorldPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Elemental extends EntityMob implements IBossDisplayData, IRangedAttackMob
{
	public Elemental(World w)
	{
		super(w);
		this.setSize(1.8f, 4f);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000);
	}

	public State state = State.Null;
	public int time = 0;

	/**
	 * Water - Creating Water Sphere,ice spikes; Fire - Fireball throwing, AoE
	 * fire; Air - Pushes player upwards, creating tornadoes; Earth - Throws
	 * blocks into the player, double damage vs player; Chaos - Explodes player,
	 * breaks player's items(armor, etc.); Order - Shield and ranged attacks,
	 * stops player
	 * 
	 * @author user
	 */
	public enum State
	{
		Water(-1), Fire(-1), Air(-1), Earth(-1), Chaos(-1), Order(-1), WaterA1(1), FireA1(1), AirA1(1), EarthA1(1), ChaosA1(1), OrderA1(1), WaterA2(2), FireA2(2), AirA2(2), EarthA2(2), ChaosA2(2), OrderA2(2), Null(0);
		public int max;

		State(int max)
		{
			this.max = max;
		}

		public static State next(State e)
		{
			if (e.ordinal() < 6)
			{
				return values()[e.ordinal() + 1];
			}
			else
			{
				return Water;
			}
		}
	}

	@Override
	protected boolean interact(EntityPlayer p)
	{
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() == ModItems.AnvilHammer)
			{
				this.state = State.next(this.state);
			}
		}
		return false;
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		// super.readFromNBT(tag);

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		// super.writeEntityToNBT(tag);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.time++;
		Entity e = this.worldObj.getClosestPlayerToEntity(this, 10);
		if (e != null)
		{
			this.rotationPitch = 1.0F;
			WorldPos elem = new WorldPos(this);
			WorldPos ewp = new WorldPos(e);
			WorldPos vec = elem.subtruct(ewp);
			vec.normalize();
			this.rotationYaw = (float) Math.toDegrees(Math.atan2(vec.z, vec.x));
		}
		else
		{
			this.rotationPitch = 0.0F;
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
	{

	}
}