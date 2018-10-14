package aam.common.items.alchemy;

import java.util.ArrayList;
import java.util.List;

import aam.common.blocks.building.ModBlocks;
import aam.common.items.ModItems;
import aam.common.transmutations.Circle;
import aam.common.transmutations.ModCircles;
import aam.common.transmutations.Transmutation;
import aam.utils.InventoryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class AlchPaper extends Item
{
	public AlchPaper()
	{
		this.setHasSubtypes(true);
	}

	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		if (p.isSneaking() && Minecraft.getMinecraft().objectMouseOver.typeOfHit.equals(MovingObjectType.MISS))
		{
			is.setItemDamage(0);
		}
		return is;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		if (is.getItemDamage() == 1)
		{
			if (w.getBlock(x, y, z) == ModBlocks.TransCircle || w.getBlock(x, y, z) == ModBlocks.MechanicalBase)
			{
				w.getBlock(x, y, z).onBlockActivated(w, x, y, z, p, side, fx, fy, fz);
			}
			else
			{
				if (InventoryUtils.contains(p.inventory, ModItems.ItemChalk))
				{
					ForgeDirection fd = ForgeDirection.getOrientation(side);
					w.setBlock(x + fd.offsetX, y + fd.offsetY, z + fd.offsetZ, ModBlocks.TransCircle, side, 2);
					w.getBlock(x + fd.offsetX, y + fd.offsetY, z + fd.offsetZ).onBlockActivated(w, x + fd.offsetX, y + fd.offsetY, z + fd.offsetZ, p, side, fx, fy, fz);
				}
			}
		}
		return false;
	}

	public Transmutation check(List<Circle> l)
	{
		for (Transmutation tr : ModCircles.circles)
		{
			if (InventoryUtils.containsOnly(ModCircles.getCodeStr(l), tr.parts))
			{
				return tr;
			}
		}
		return null;
	}

	public List<Circle> getCircles(ItemStack is)
	{
		NBTTagCompound tag = is.getTagCompound();
		List<Circle> l = new ArrayList<>();

		int count = tag.getInteger("Size");
		if (count > 0)
		{
			for (int i = 0; i < count; i++)
			{
				String code = tag.getString("Part_" + i);
				boolean rev = tag.getBoolean("rev_" + i);
				double scale = tag.getDouble("Scale_" + i);
				Circle c = new Circle(ModCircles.getprtsr(code), scale, rev);
				if (!l.contains(c))
				{
					l.add(c);
				}
			}
		}
		return l;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean hand)
	{
		if (is.getItemDamage() == 1)
		{
			Transmutation tr = check(getCircles(is));
			if (tr != null)
			{
				l.add("" + tr.name);
			}
		}
	}

	public static IIcon[] icons = new IIcon[2];

	@Override
	public void registerIcons(IIconRegister ir)
	{
		icons[0] = ir.registerIcon("aam:tools/chalk/paper_blank");
		icons[1] = ir.registerIcon("aam:tools/chalk/paper_circle");
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		if (pass == 0 || meta == 0)
		{
			return icons[0];
		}
		return icons[1];
	}
}
