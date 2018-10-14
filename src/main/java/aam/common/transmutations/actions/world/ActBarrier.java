package aam.common.transmutations.actions.world;

import java.util.List;

import org.lwjgl.opengl.GL11;

import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.MathUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.functions.CylFunction;
import aam.utils.render.RenderUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActBarrier extends TransAction
{
	public ActBarrier(int size)
	{
		super(size);
	}

	@Override
	public boolean renderTick(World w, Wec3 tile, TETransCircle te, int time, final double potency, ForgeDirection dir)
	{
		int s = size;
		CylFunction f = new CylFunction()
		{
			@Override
			public Double count2(Double angle, Double h)
			{
				return potency / 1.9 - s / 2;
			}
		};
		GL11.glColor4d(1, 1, 1, 0.5);
		GL11.glRotated(MathUtils.getTimedAngle(80), 0, 1, 0);
		RenderUtils.renderFunction("aam:textures/misc/barrier.png", f, 0, 0, 0, 0, 0, Math.PI * 2 * 0.01, -0.1, 6);
		GL11.glColor4d(1, 1, 1, 1);

		return true;
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		if (p == null)
		{
			return false;
		}
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		if (w.getWorldTime() % 20 == 1)
		{
			if (!ph.consumeSoul((int) Math.sqrt(potency)))
			{
				return false;
			}
		}
		tile.centralize();
		List<Entity> l = w.getEntitiesWithinAABB(Entity.class, tile.extendBoth((float) potency * 2, 6, (float) potency * 2));
		double r = potency / 1.9 - size;
		double dr = 0.3 + Math.max(potency - 3, 0) * 0.1;
		for (Entity e : l)
		{
			Wec3 ep = new Wec3(e);
			if (dir.offsetX != 0)
			{
				ep.x = tile.x;
			}
			if (dir.offsetY != 0)
			{
				ep.y = tile.y;
			}
			if (dir.offsetZ != 0)
			{
				ep.z = tile.z;
			}
			Wec3 pep = new Wec3(e.prevPosX, e.prevPosY, e.prevPosZ);
			if (MathUtils.isInLimit(ep.distanceTo(tile), r, r + dr) && MathUtils.isInLimit(pep.distanceTo(tile), r, r + dr))
			{
				double dd = ep.distanceTo(tile) - r;
				Wec3 vec = ep.sub(tile);
				vec.normalize();
				vec = vec.mult(dr - dd);
				vec.ptm(e);
			}
			else
			{
				if (MathUtils.isInLimit(ep.distanceTo(tile), r - dr, r) && MathUtils.isInLimit(pep.distanceTo(tile), r - dr, r))
				{
					double dd = -ep.distanceTo(tile) + r;

					Wec3 vec = tile.sub(ep);
					vec.normalize();
					vec = vec.mult(0.25);
					vec.ptm(e);
				}
			}
			if (MathUtils.isInLimit(ep.distanceTo(tile), r, r + dr) && !MathUtils.isInLimit(pep.distanceTo(tile), r, r + dr))
			{
				double dd = ep.distanceTo(tile) - r;

				Wec3 vec = ep.sub(tile);
				vec.normalize();
				vec = vec.mult(dr - dd);
				vec.ptm(e);
			}
			if (MathUtils.isInLimit(ep.distanceTo(tile), r - dr, r) && !MathUtils.isInLimit(pep.distanceTo(tile), r - dr, r))
			{
				double dd = -ep.distanceTo(tile) + r;

				Wec3 vec = tile.sub(ep);
				vec.normalize();
				vec = vec.mult(0.15);
				vec.ptm(e);
			}
		}
		return true;
	}
}
