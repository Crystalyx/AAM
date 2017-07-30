/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Items;

import AAM.Core.AAMCore;
import AAM.Utils.PlayerDataHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class RiteBook extends Item
{
	public RiteBook()
	{
		this.setTextureName("aam:dark_book");
		this.setUnlocalizedName("aam.ritebook");
	}

	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (w.isRemote)
		{
			p.addChatMessage(new ChatComponentText("Player is seeker:" + PlayerDataHandler.get(p).playerIsSeeker));
			p.addChatMessage(new ChatComponentText("Player patronage:" + PlayerDataHandler.get(p).patronage));
		}
		return i;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon("aam:dark_book");
	}

	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float px, float py, float pz)
	{
		PlayerDataHandler.get(p).playerIsSeeker = true;
		PlayerDataHandler.get(p).patronage += 20;

		return true;
	}
}
