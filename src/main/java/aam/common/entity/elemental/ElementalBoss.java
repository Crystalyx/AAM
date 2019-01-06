package aam.common.entity.elemental;

import aam.common.items.ModItems;
import aam.utils.MathUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ElementalBoss extends EntityMob implements IBossDisplayData, IRangedAttackMob
{
	public ElementalBoss(World w)
	{
		super(w);
		this.setSize(1.8f, 4f);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(500);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1);

	}

	public State state = State.Clear;
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
		Clear, Water, Fire, Air, Earth, Chaos, Order;

		public static State next(State e)
		{
			if (e.ordinal() < 6)
			{
				return values()[e.ordinal() + 1];
			}
			else
			{
				return Clear;
			}
		}

		public State random()
		{
			return values()[MathUtils.getIntInRange(1, 7)];
		}
	}

	@Override
	protected boolean interact(EntityPlayer p)
	{
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() == ModItems.AnvilHammer)
			{
				state = State.next(state);
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
		super.readFromNBT(tag);

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		time++;
		Entity e = worldObj.getClosestPlayerToEntity(this, 10);
		if (e != null)
		{
			rotationPitch = 1.0F;
			Wec3 elem = new Wec3(this);
			Wec3 ewp = new Wec3(e);
			Wec3 vec = elem.sub(ewp);
			vec.normalize();
			rotationYaw = (float) Math.toDegrees(Math.atan2(vec.z, vec.x));
		}
		else
		{
			rotationPitch = 0.0F;
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
	{

	}
}