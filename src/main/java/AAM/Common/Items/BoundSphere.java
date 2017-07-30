package AAM.Common.Items;

import java.util.List;

import AAM.Common.Blocks.ModBlocks;
import AAM.Core.AAMCore;
import AAM.Utils.Logger;
import AAM.Utils.Structure;
import AAM.Utils.StructureApi;
import AAM.Utils.WorldPos;
//import DummyCore.Utils.StructureApi;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class BoundSphere extends Item
{
	public BoundSphere()
	{
		this.setUnlocalizedName("aam.boundsphere");
		this.setHasSubtypes(true);
	}

	public static IIcon green;
	public static IIcon red;
	public static IIcon blue;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir)
	{
		this.green = ir.registerIcon(AAMCore.modid + ":sphere_green");
		this.red = ir.registerIcon(AAMCore.modid + ":sphere_red");
		this.blue = ir.registerIcon(AAMCore.modid + ":sphere_blue");
	}

	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
		l.add(new ItemStack(i, 1, 1));
		l.add(new ItemStack(i, 1, 2));
	}

	public IIcon getIconFromDamage(int meta)
	{
		if (meta == 1)
			return this.green;
		if (meta == 2)
			return this.red;
		return this.blue;
	}

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
				i.setItemDamage(2);
			else
				if (i.getItemDamage() == 2)
					i.setItemDamage(0);
		}
		return i;
	}

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
						NBTTagCompound tag = StructureApi.readStructure(w, new WorldPos(x, y, z), new WorldPos(is.getTagCompound().getInteger("BlockX"), is.getTagCompound().getInteger("BlockY"), is.getTagCompound().getInteger("BlockZ")),
								Blocks.bedrock, ModBlocks.Bricks, ModBlocks.StructureBlock);
						is.getTagCompound().removeTag("BlockX");
						is.getTagCompound().removeTag("BlockY");
						is.getTagCompound().removeTag("BlockZ");
						is.getTagCompound().removeTag("Dim");
						is.setTagCompound(tag);
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
				l.add("Not Bound");
		}
	}
}
