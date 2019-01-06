package aam.common.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author Lord_Crystalyx
 */
public class TEGraviter extends TileEntity
{
	public int cooldown = 0;

	public TEGraviter()
	{
		super();
	}

	@Override
	public void updateEntity()
	{
		double rx = worldObj.rand.nextDouble() * 10;
		double ry = worldObj.rand.nextDouble() * 10;
		double rz = worldObj.rand.nextDouble() * 10;
		if (worldObj.rand.nextBoolean())
		{
			rx *= -1;
		}
		if (worldObj.rand.nextBoolean())
		{
			ry *= -1;
		}
		if (worldObj.rand.nextBoolean())
		{
			rz *= -1;
		}

		double radius = 42;
		List<Entity> ents = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord - radius, yCoord - radius, zCoord - radius, xCoord + radius, yCoord + radius, zCoord + radius));
		for (Entity entl : ents)
		{
			if (!entl.onGround)
			{
				entl.fallDistance = 0;
				entl.moveEntity(entl.motionX, 0, entl.motionZ);
				entl.motionY = 0.00005;

				if (entl instanceof EntityPlayer)
				{
					if (entl.isSneaking())
					{
						entl.motionY = -0.5;
					}
					if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
					{
						entl.motionY = 0.5;
					}
					PotionEffect pe = new PotionEffect(3, 10, 25);
					((EntityPlayer) entl).addPotionEffect(pe);
				}
			}

			// entl.setVelocity(this.xCoord-entl.posX+0.5,
			// this.yCoord-entl.posY+0.5, this.zCoord-entl.posZ+0.5);
		}

	}
}
