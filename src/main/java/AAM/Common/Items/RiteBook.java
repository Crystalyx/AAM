/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Items;

import AAM.Utils.PlayerDataHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		ph.consumeSoul(3000);
		// AttributeModifier am = new AttributeModifier("Rite", -60, 0);
		// p.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(am);
		return i;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon("aam:dark_book");
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float px, float py, float pz)
	{

		return true;
	}
}
