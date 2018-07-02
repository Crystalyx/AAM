package AAM.Common.Transmutations.Actions;

import java.util.List;

import org.lwjgl.opengl.GL11;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Wec3;
import AAM.Utils.Functions.CylFunction;
import AAM.Utils.Render.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActBarrier extends TransAction
{
	@Override
	public boolean renderTick(World w, Wec3 tile, TETransCircle te, int time, final double potency, ForgeDirection dir)
	{
		CylFunction f = new CylFunction()
		{
			@Override
			public Double count2(Double angle, Double h)
			{
				return potency / 1.9;
			}
		};
		GL11.glColor4d(1, 1, 1, 0.5);
		GL11.glRotated(MiscUtils.getTimedAngle(80), 0, 1, 0);
		RenderUtils.renderFunction("aam:textures/misc/barrier.png", f, 0, 0, 0, 0, 0, Math.PI * 2 * 0.01, -0.1, 6);
		GL11.glColor4d(1, 1, 1, 1);

		return true;
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		if (w.getWorldTime() % 20 == 1)
			if (!ph.consumeSoul((int) Math.sqrt(potency)))
				return false;
		tile.centralize();
		List<Entity> l = w.getEntitiesWithinAABB(Entity.class, tile.extendBoth((float) potency * 2, 6, (float) potency * 2));
		double r = potency / 1.84;
		double dr = 0.3 + Math.max(potency - 3, 0) * 0.1;
		for (Entity e : l)
		{
			Wec3 ep = new Wec3(e);
			if (dir.offsetX != 0)
				ep.x = tile.x;
			if (dir.offsetY != 0)
				ep.y = tile.y;
			if (dir.offsetZ != 0)
				ep.z = tile.z;
			Wec3 pep = new Wec3(e.prevPosX, e.prevPosY, e.prevPosZ);
			if (MiscUtils.isInLimit(ep.distanceTo(tile), r, r + dr) && MiscUtils.isInLimit(pep.distanceTo(tile), r, r + dr))
			{
				double dd = ep.distanceTo(tile) - r;
				Wec3 vec = ep.sub(tile);
				vec.normalize();
				vec = vec.mult(dr - dd);
				vec.ptm(e);
			}
			else
			{
				if (MiscUtils.isInLimit(ep.distanceTo(tile), r - dr, r) && MiscUtils.isInLimit(pep.distanceTo(tile), r - dr, r))
				{
					double dd = -ep.distanceTo(tile) + r;

					Wec3 vec = tile.sub(ep);
					vec.normalize();
					vec = vec.mult(0.25);
					vec.ptm(e);
				}
			}
			if ((MiscUtils.isInLimit(ep.distanceTo(tile), r, r + dr) && !MiscUtils.isInLimit(pep.distanceTo(tile), r, r + dr)))
			{
				double dd = ep.distanceTo(tile) - r;

				Wec3 vec = ep.sub(tile);
				vec.normalize();
				vec = vec.mult(dr - dd);
				vec.ptm(e);
			}
			if (MiscUtils.isInLimit(ep.distanceTo(tile), r - dr, r) && !MiscUtils.isInLimit(pep.distanceTo(tile), r - dr, r))
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
