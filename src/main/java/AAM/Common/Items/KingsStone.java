package AAM.Common.Items;

import java.util.List;

import AAM.Utils.WorldPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class KingsStone extends Item
{
	public KingsStone()
	{
		this.setTextureName("aam:kstone");
		this.setUnlocalizedName("aam.kstone");
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity p, int slot, boolean hand)
	{
		WorldPos pp = new WorldPos(p);
		AxisAlignedBB aabb = pp.getAABB(2.5f);

		List<Entity> es = w.getEntitiesWithinAABB(Entity.class, aabb);
		es.remove(p);
		for (Entity e : es)
		{
			// e.setVelocity(0.0, 0.0, 0.0);
			WorldPos ep = new WorldPos(e);
			WorldPos vec = ep.subtruct(pp);
			vec = vec.power(-1).modify(1);
			vec.ptm(e);

		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		WorldPos pp = new WorldPos(p);
		AxisAlignedBB aabb = pp.getAABB(5.5f);

		List<Entity> es = w.getEntitiesWithinAABB(Entity.class, aabb);
		es.remove(p);
		for (Entity e : es)
		{
			// e.setVelocity(0.0, 0.0, 0.0);
			WorldPos ep = new WorldPos(e);
			WorldPos vec = ep.subtruct(pp);
			vec = vec.power(-1).modify(2);
			vec = vec.add(new WorldPos(0, 0.5, 0));
			vec.ptm(e);

		}
		return is;
	}
}
