package AAM.Common.Items.Artifacts;

import java.util.List;

import AAM.API.Abstract.ItemArtifact;
import AAM.Utils.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class KingsStone extends ItemArtifact
{
	public KingsStone()
	{
		super("aam.kstone", "aam.kstone.descr", 3);
		this.setTextureName("aam:kstone");
		this.setUnlocalizedName("aam.kstone");
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity p, int slot, boolean hand)
	{
		Wec3 pp = new Wec3(p);
		AxisAlignedBB aabb = pp.extend(2.5f);

		List<Entity> es = w.getEntitiesWithinAABB(Entity.class, aabb);
		es.remove(p);
		for (Entity e : es)
		{
			// e.setVelocity(0.0, 0.0, 0.0);
			Wec3 ep = new Wec3(e);
			Wec3 vec = ep.sub(pp);
			vec = vec.pow(-1).mult(1);
			vec.ptm(e);

		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		Wec3 pp = new Wec3(p);
		AxisAlignedBB aabb = pp.extend(5.5f);

		List<Entity> es = w.getEntitiesWithinAABB(Entity.class, aabb);
		es.remove(p);
		for (Entity e : es)
		{
			// e.setVelocity(0.0, 0.0, 0.0);
			Wec3 ep = new Wec3(e);
			Wec3 vec = ep.sub(pp);
			vec = vec.pow(-1).mult(8);
			vec = vec.add(new Wec3(0, 0.5, 0));
			vec.ptm(e);

		}
		return is;
	}
}
