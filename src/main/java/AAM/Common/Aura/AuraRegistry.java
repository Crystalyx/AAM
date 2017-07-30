package AAM.Common.Aura;

import java.util.List;

import AAM.Utils.MiscUtils;
import AAM.Utils.WorldPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class AuraRegistry
{
	public static AuraBase[] auras = new AuraBase[5];

	public static void registerAura(String name, float size, ResourceLocation texture)
	{
		AuraBase reg = new AuraBase(name, size, texture);

		auras[auras.length - 1] = reg;
		expandArray();
	}

	private static void expandArray()
	{
		AuraBase[] temp = new AuraBase[auras.length + 1];
		for (int i = 0; i < auras.length; i++)
		{
			temp[i] = auras[i];
		}
		auras = temp;
	}

	public static void registerAura(String name, float size, String texture)
	{
		AuraBase reg = new AuraBase(name, size, texture);

		auras[auras.length - 1] = reg;
		MiscUtils.expandArray(auras, 1);
	}

	public static void registerAura(AuraBase reg)
	{
		auras[auras.length - 1] = reg;
		MiscUtils.expandArray(auras, 1);
	}

	public static void register()
	{
		auras[0] = vacuum;
		auras[1] = flame;
		auras[2] = air;
		auras[3] = earth;
		auras[4] = water;
	}

	public static AuraBase vacuum = new AuraBase("Void", 1.5F, new ResourceLocation("aam:textures/misc/void.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 1.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new WorldPos(p)).toAABB().expand(this.size, this.size * 2, this.size));
			for (EntityLivingBase entl : ents)
			{
				if (entl != p)
				{
					entl.attackEntityFrom(DamageSource.outOfWorld, this.size);

					WorldPos pPos = new WorldPos(p);
					WorldPos ePos = new WorldPos(entl);

					WorldPos vec = pPos.subtruct(ePos);

					entl.setVelocity(vec.getX() / 6, vec.getY() / 6, vec.getZ() / 6);
				}
			}
			List<EntityItem> ei = p.worldObj.getEntitiesWithinAABB(EntityItem.class, (new WorldPos(p)).toAABB().expand(this.size * 2, this.size * 2, this.size * 2));
			for (EntityItem entl : ei)
			{
				WorldPos pPos = new WorldPos(p);
				WorldPos ePos = new WorldPos(entl);

				WorldPos vec = pPos.subtruct(ePos);

				if (vec.distance() <= 0.5)
				{
					if (p instanceof EntityPlayer)
					{
						entl.onCollideWithPlayer((EntityPlayer) p);
					}
				}
				if (entl.delayBeforeCanPickup <= 0 && entl.onGround)
				{
					entl.setVelocity(vec.getX() / 6, vec.getY() / 6, vec.getZ() / 6);
				}
			}
		}
	};

	public static AuraBase flame = new AuraBase("Fire", 1.5F, new ResourceLocation("aam:textures/misc/flame.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 0.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new WorldPos(p)).toAABB().expand(this.size, this.size * 2, this.size));
			for (EntityLivingBase entl : ents)
			{
				if (entl != p)
				{
					entl.setFire(3);
					entl.attackEntityFrom(DamageSource.inFire, this.size / 10);
				}
			}
		}
	};

	public static AuraBase air = new AuraBase("Air", 1.5F, new ResourceLocation("aam:textures/misc/air.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 0.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new WorldPos(p)).toAABB().expand(this.size, this.size * 2, this.size));
			for (EntityLivingBase entl : ents)
			{
				if (entl != p)
				{
					WorldPos pPos = new WorldPos(p);
					WorldPos ePos = new WorldPos(entl);

					WorldPos vec = ePos.subtruct(pPos);

					entl.setVelocity(6 / vec.getX(), 6 / vec.getY(), 6 / vec.getZ());
				}
			}
		}
	};

	public static AuraBase earth = new AuraBase("Earth", 1.5F, new ResourceLocation("aam:textures/misc/earth.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 0.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new WorldPos(p)).toAABB().expand(this.size, this.size * 2, this.size));
			for (EntityLivingBase entl : ents)
			{
				if (entl != p)
				{
					entl.motionX = entl.motionX / 6;
					entl.motionY = entl.motionY * 6;
					entl.motionZ = entl.motionZ / 6;

					if (entl.motionY >= 0)
						entl.motionX *= -1;
				}
			}
		}
	};

	public static AuraBase water = new AuraBase("Water", 1.5F, new ResourceLocation("aam:textures/misc/water.png"))
	{
		public void startTick(Entity p)
		{
			p.extinguish();
			if (p.isInWater())
			{
				if (p.getAir() < 20)
					p.setAir(20);
			}
		}
	};
}
