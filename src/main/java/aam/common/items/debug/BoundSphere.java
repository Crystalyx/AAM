package aam.common.items.debug;

import aam.common.blocks.building.ModBlocks;
import aam.core.AAMCore;
import aam.utils.Logger;
import aam.utils.Structure;
import aam.utils.StructureApi;
import aam.utils.vectors.Wec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Lord_Crystalyx
 */
public class BoundSphere extends Item
{
	public BoundSphere()
	{
		this.setUnlocalizedName("aam.bound_sphere");
		this.setHasSubtypes(true);
	}

	public IIcon green;
	public IIcon red;
	public IIcon blue;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir)
	{
		green = ir.registerIcon(AAMCore.modid + ":sphere_green");
		red = ir.registerIcon(AAMCore.modid + ":sphere_red");
		blue = ir.registerIcon(AAMCore.modid + ":sphere_blue");
	}

	@Override
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
		// l.add(new ItemStack(i, 1, 1));
		// l.add(new ItemStack(i, 1, 2));
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if (meta == 1)
		{
			return green;
		}
		if (meta == 2)
		{
			return red;
		}
		return blue;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (!p.isSneaking())
		{
			NBTTagCompound strctag = i.getTagCompound();
			String st = Structure.parse(strctag);
			Logger.info(st);
		}
		else
		{
			if (i.getItemDamage() == 0)
			{
				i.setItemDamage(2);
			}
			else
				if (i.getItemDamage() == 2)
				{
					i.setItemDamage(0);
				}
		}
		return i;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float px, float py, float pz)
	{
		if (!p.isSneaking())
		{
			if (is != null)
			{
				if (is.getItemDamage() == 0)
				{
					if (is.getTagCompound() != null)
					{
						is.getTagCompound().setInteger("BlockX", x);
						is.getTagCompound().setInteger("BlockY", y);
						is.getTagCompound().setInteger("BlockZ", z);
						is.getTagCompound().setInteger("Dim", p.dimension);
					}
				}
				if (is.getItemDamage() == 1)
				{
					if (is.getTagCompound() != null)
					{
						NBTTagCompound tag = StructureApi.readStructure(w, new Wec3(x, y, z), new Wec3(is.getTagCompound().getInteger("BlockX"), is.getTagCompound().getInteger("BlockY"), is.getTagCompound().getInteger("BlockZ")),
								Blocks.bedrock, Blocks.air, ModBlocks.Bricks, ModBlocks.StructureBlock);
						is.getTagCompound().removeTag("BlockX");
						is.getTagCompound().removeTag("BlockY");
						is.getTagCompound().removeTag("BlockZ");
						is.getTagCompound().removeTag("Dim");
						is.setTagCompound(tag);
						Logger.info(new Structure(tag).blocks);
					}
				}
				if (is.getItemDamage() == 2)
				{
					if (is.getTagCompound() != null)
					{

						StructureApi.print(x, y, z, is.getTagCompound(), w);
					}
				}
			}
		}
		else
		{
			is.getTagCompound().removeTag("BlockX");
			is.getTagCompound().removeTag("BlockY");
			is.getTagCompound().removeTag("BlockZ");
			is.getTagCompound().removeTag("Dim");

		}
		return true;
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int interesting, boolean isInHand)
	{
		if (i.getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			i.setTagCompound(tag);
		}

		if (i.getTagCompound().hasKey("BlockX") && i.getItemDamage() == 0)
		{
			i.setItemDamage(1);
		}
		if (!i.getTagCompound().hasKey("BlockX") && i.getItemDamage() == 1)
		{
			i.setItemDamage(0);
		}
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean in)
	{
		if (i.hasTagCompound())
		{
			if (i.getTagCompound().hasKey("BlockX"))
			{
				l.add("Bound to: X = " + i.getTagCompound().getInteger("BlockX") + "; Y = " + i.getTagCompound().getInteger("BlockY") + "; Z = " + i.getTagCompound().getInteger("BlockZ") + ";");
				l.add("In Dimension: " + i.getTagCompound().getInteger("Dim"));
			}
			else
			{
				l.add("Not Bound");
			}
		}
	}
}
