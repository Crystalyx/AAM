/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Item;

import GF.Registry.Registry;
import GF.Registry.SandOreGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDebugGenerator extends Item
{
	public ItemDebugGenerator()
	{
		this.setCreativeTab(Registry.gftab);
		this.setTextureName("goldflushing:debugger");
		this.setUnlocalizedName("debugger");
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int cx, int cy, int cz, int side, float px, float py, float pz)
	{
		p.addChatComponentMessage(new ChatComponentText("x:" + cx + " y:" + cy + " z:" + cz));
		// if (!w.isRemote)
		{
			SandOreGenerator gen = new SandOreGenerator();
			gen.generate(w.rand, cx, cy, cz, w);
		}
		return true;
	}

	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		return i;
	}
}
