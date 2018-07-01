package AAM.API;

import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICircleExtender
{
	public void onExtended(ItemStack i, EntityPlayer p, World w, Wec3 pos);
}
