package AAM.Common.Items.Soul;

import java.util.List;

import AAM.API.GameWeapon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

public class ModStaff extends GameWeapon
{
	public int meleeDmg = 3;
	public int rangedDmg = 7;
	public boolean bypassesArmor = false;
	public EnumChatFormatting rarity = EnumChatFormatting.WHITE;
	public String texture = "";
	public String name = "";
	public int soulConsumed = 2;
	public int cd = 20;

	public ModStaff(String name, int meleeDmg, int rangedDmg, int soulConsumed, boolean bypassesArmor, EnumChatFormatting rarity, String texture)
	{
		super(ToolMaterial.IRON);
		this.setUnlocalizedName(name);
		this.name = name;
		this.meleeDmg = meleeDmg;
		this.rangedDmg = rangedDmg;
		this.bypassesArmor = bypassesArmor;
		this.rarity = rarity;
		this.texture = texture;
		this.soulConsumed = soulConsumed;
	}

	public ModStaff setCooldown(int cd)
	{
		this.cd = cd;
		return this;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean hand)
	{
		l.set(0, this.rarity.toString() + l.get(0));

	}

	public IIcon icon;

	@Override
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon(texture);
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return this.icon;
	}

}
