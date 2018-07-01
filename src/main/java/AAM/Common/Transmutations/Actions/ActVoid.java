package AAM.Common.Transmutations.Actions;

import java.util.List;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
import AAM.Utils.Render.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActVoid extends TransAction
{

	public ActVoid()
	{
	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		Wec3 pp = tile.centralize().add(ForgeDirection.UP);
		float r = (float) ((MiscUtils.limit(time - 40, 0, 200) / 3d) * Math.log(potency + 1) / Math.log(3.4));
		AxisAlignedBB aabb = pp.extend(r);

		List<Entity> es = w.getEntitiesWithinAABB(Entity.class, aabb);
		for (Entity e : es)
		{
			Wec3 ep = new Wec3(e);
			Wec3 vec = pp.subtruct(ep);
			vec = vec.power(1).multiply(0.1f);
			vec.ptm(e);
		}
		if (te.completeTimer > 400)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean renderTick(World w, Wec3 tile, TETransCircle te, int time, final double potency, ForgeDirection dir)
	{
		double r = (MiscUtils.limit(time - 40, 0, 200) / 5d);
		RenderUtils.renderSphere("aam:textures/items/blank_upgrade.png", 0, 0, 0, r);

		return true;
	}

}
