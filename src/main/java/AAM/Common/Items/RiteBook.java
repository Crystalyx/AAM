/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Common.Items;

import AAM.Common.Event.SoulEvent;
import AAM.Common.Soul.Trait;
import AAM.Common.Soul.WeaponType;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
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

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		int f = 0;
		switch (f)
		{
		case 0:
			ph.sword = WeaponType.values()[MiscUtils.cycle(ph.sword.ordinal() + 1, 0, WeaponType.values().length - 1)];
			break;
		case 1:
			ph.arbitur = !ph.arbitur;
			break;
		case 2:
			if (w.isRemote)
				for (int j = 0; j < Enchantment.enchantmentsList.length; j++)
				{
					if (Enchantment.enchantmentsList[j] != null)
					{
						ChatComponentText cct = new ChatComponentText(MiscUtils.compact(Enchantment.enchantmentsList[j].effectId, Enchantment.enchantmentsList[j].getTranslatedName(1)));
						p.addChatMessage(cct);
					}
				}
			break;
		case 3:
			ph.setTraitBase(Trait.Level, 1);
			break;
		case 4:
			ph.soulxp += ph.getTrait(Trait.Soul) - ph.soulxp;
			break;
		}
		SoulEvent.callSwordRecreation(ph);
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
