package AAM.Common.Aura;

import java.util.ArrayList;
import java.util.List;

import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class AuraRegistry
{
	public static ArrayList<AuraBase> auras = new ArrayList<AuraBase>();

	public static void register(String name, float size, ResourceLocation texture)
	{
		AuraBase reg = new AuraBase(name, size, texture);
		auras.add(reg);
	}

	public static void register(String name, float size, String texture)
	{
		AuraBase reg = new AuraBase(name, size, texture);
		auras.add(reg);
	}

	public static void register(AuraBase reg)
	{
		auras.add(reg);
	}

	public static void load()
	{
		register(vacuum);
		register(flame);
		register(air);
		register(earth);
		register(water);
	}

	public static AuraBase vacuum = new AuraBase("void", 1.5F, new ResourceLocation("aam:textures/misc/void.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 1.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new Wec3(p)).toAABB().expand(this.size, this.size * 2, this.size));
			for (EntityLivingBase entl : ents)
			{
				if (entl != p)
				{
					entl.attackEntityFrom(DamageSource.outOfWorld, this.size);

					Wec3 pPos = new Wec3(p);
					Wec3 ePos = new Wec3(entl);

					Wec3 vec = pPos.sub(ePos);

					entl.setVelocity(vec.x / 6, vec.y / 6, vec.z / 6);
				}
			}
			List<EntityItem> ei = p.worldObj.getEntitiesWithinAABB(EntityItem.class, (new Wec3(p)).toAABB().expand(this.size * 2, this.size * 2, this.size * 2));
			for (EntityItem entl : ei)
			{
				Wec3 pPos = new Wec3(p);
				Wec3 ePos = new Wec3(entl);

				Wec3 vec = pPos.sub(ePos);

				if (vec.length() <= 0.5)
				{
					if (p instanceof EntityPlayer)
					{
						entl.onCollideWithPlayer((EntityPlayer) p);
					}
				}
				if (entl.delayBeforeCanPickup <= 0 && entl.onGround)
				{
					entl.setVelocity(vec.x / 6, vec.y / 6, vec.z / 6);
				}
			}
		}
	};

	public static AuraBase flame = new AuraBase("fire", 1.5F, new ResourceLocation("aam:textures/misc/flame.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 0.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new Wec3(p)).toAABB().expand(this.size, this.size * 2, this.size));
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

	public static AuraBase air = new AuraBase("air", 1.5F, new ResourceLocation("aam:textures/misc/air.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 0.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new Wec3(p)).toAABB().expand(this.size, this.size * 2, this.size));
			for (EntityLivingBase entl : ents)
			{
				if (entl != p)
				{
					Wec3 pPos = new Wec3(p);
					Wec3 ePos = new Wec3(entl);

					Wec3 vec = ePos.sub(pPos);

					entl.setVelocity(6 / vec.x, 6 / vec.y, 6 / vec.z);
				}
			}
		}
	};

	public static AuraBase earth = new AuraBase("earth", 1.5F, new ResourceLocation("aam:textures/misc/earth.png"))
	{
		public void startTick(Entity p)
		{
			double radius = this.size + 0.5;
			List<EntityLivingBase> ents = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, (new Wec3(p)).toAABB().expand(this.size, this.size * 2, this.size));
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

	public static AuraBase water = new AuraBase("water", 1.5F, new ResourceLocation("aam:textures/misc/water.png"))
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
