package AAM.Common.Items;

import AAM.Common.Entity.SoulCharge;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.WorldPos;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class CrystalBow extends Item
{
	public CrystalBow()
	{
		this.canRepair = true;
		this.setHasSubtypes(true);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (i.hasTagCompound())
		{
			if (i.getTagCompound().getInteger("State") < 5)
			{
				i.getTagCompound().setInteger("State", (i.getTagCompound().getInteger("State") + 1));
			} else
			{
				SoulCharge s = new SoulCharge(p.worldObj, p);
				s.setLife(500);
				WorldPos look = new WorldPos(p.getLookVec());
				look.ptm(s);
				p.worldObj.spawnEntityInWorld(s);
				i.getTagCompound().setInteger("State", 0);
				i.damageItem(1, p);
			}
		}
		return i;
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int tick, boolean hand)
	{
		if (e instanceof EntityPlayer)
		{
			PlayerDataHandler ph = PlayerDataHandler.get((EntityPlayer) e);
			if (i.hasTagCompound())
			{
				i.getTagCompound().setInteger("Art", ph.stype.ordinal());
			} else
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Art", ph.stype.ordinal());
				tag.setInteger("State", 0);
				i.setTagCompound(tag);
			}
		}
	}

	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public int getRenderPasses(int metadata)
	{
		return 2;
	}

	public static IIcon[] icon = new IIcon[3];
	public static String[] ways = new String[3];

	@Override
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < 3; i++)
		{
			icon[i] = ir.registerIcon("aam:bow_art_" + i);
			ways[i] = "bow_art_" + i;
		}
	}

	@Override
	public IIcon getIcon(ItemStack i, int p)
	{
		if (i.hasTagCompound())
		{
			if (p == 0)
			{
				return Artifact.icons[i.getTagCompound().getInteger("Art")];
			} else if (p == 1)
				return icon[Math.floorDiv(i.getTagCompound().getInteger("State"), 2)];
		}
		return icon[0];
	}

}
