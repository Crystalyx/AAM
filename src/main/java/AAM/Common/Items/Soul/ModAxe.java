package AAM.Common.Items.Soul;

import java.util.List;

import AAM.API.GameWeapon;
import AAM.API.Interface.IExtendedReach;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

public class ModAxe extends GameWeapon implements IExtendedReach
{
	public int baseDmg = 5;
	public boolean bypassesArmor = false;
	public EnumChatFormatting rarity = EnumChatFormatting.WHITE;
	public String texture = "";
	public String name = "";

	public ModAxe(String name, int baseDmg, boolean bypassesArmor, EnumChatFormatting rarity, String texture)
	{
		super(ToolMaterial.IRON);
		this.setUnlocalizedName(name);
		this.name = name;
		this.baseDmg = baseDmg;
		this.bypassesArmor = bypassesArmor;
		this.rarity = rarity;
		this.texture = texture;
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

	@Override
	public float getReachValue(EntityPlayer p, ItemStack is)
	{
		return 8;
	}

}
