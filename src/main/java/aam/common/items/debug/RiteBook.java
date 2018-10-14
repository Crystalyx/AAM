/**
 * This Class Created By Lord_Crystalyx.
 */
package aam.common.items.debug;

import aam.common.event.SoulEvent;
import aam.common.soul.SoulWeaponType;
import aam.common.soul.Trait;
import aam.common.transmutations.ModCircles;
import aam.common.weapon.WeaponManager;
import aam.utils.InventoryUtils;
import aam.utils.MathUtils;
import aam.utils.MiscUtils;
import aam.utils.PlayerDataHandler;
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
		this.setUnlocalizedName("aam.rite_book");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		int f = 6;
		switch (f)
		{
		case 0:
			ph.sword = SoulWeaponType.values()[MathUtils.cycle(ph.sword.ordinal() + 1, 0, SoulWeaponType.values().length - 1)];
			break;
		case 1:
			ph.arbitur = !ph.arbitur;
			break;
		case 2:
			if (w.isRemote)
			{
				for (Enchantment element : Enchantment.enchantmentsList)
				{
					if (element != null)
					{
						ChatComponentText cct = new ChatComponentText(MiscUtils.compact(element.effectId, element.getTranslatedName(1)));
						p.addChatMessage(cct);
					}
				}
			}
			break;
		case 3:
			ph.setTraitBase(Trait.Level, 1);
			break;
		case 4:
			ph.soulxp += ph.getTrait(Trait.Soul) - ph.soulxp;
			break;
		case 5:
			ModCircles.reloadCircles();
			break;
		case 6:
			ItemStack weapon = WeaponManager.createMeleeWeapon("test", new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
			InventoryUtils.addItemStack(p, weapon);
		}
		SoulEvent.callSwordRecreation(ph);
		return i;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		itemIcon = ir.registerIcon("aam:dark_book");
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float px, float py, float pz)
	{

		return true;
	}
}
