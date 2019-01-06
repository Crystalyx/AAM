/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Item;

import GF.Registry.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class SandOreBucket extends Item
{

	public SandOreBucket(int type) 
	{
		super();
		this.fullType=type;
		this.setCreativeTab(Registry.gftab);
		this.setTextureName("goldflushing:bucket"+Registry.names[type]);
		this.setUnlocalizedName("sandbucket"+Registry.names[type]);
		this.setHasSubtypes(true);
		this.setMaxStackSize(8);
	}
	
	public int fullType;
	
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i,1,0));
		l.add(new ItemStack(i,1,1));
		l.add(new ItemStack(i,1,2));
		l.add(new ItemStack(i,1,3));
		l.add(new ItemStack(i,1,4));
		l.add(new ItemStack(i,1,5));
		l.add(new ItemStack(i,1,6));
		l.add(new ItemStack(i,1,7));

	}
	
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean bool)
	{
		l.add((8-i.getItemDamage())+" from "+8 + " Shovels of " + Registry.names[this.fullType] + " Sand");
	}
	
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float px, float py, float pz)
	{
		ForgeDirection sidePlaced = ForgeDirection.getOrientation(side);
		w.setBlock(x+sidePlaced.offsetX, y+sidePlaced.offsetY, z+sidePlaced.offsetZ, Registry.sand[this.fullType-1], i.getItemDamage(), 2);
		--p.getCurrentEquippedItem().stackSize;
		return true;		
	}

}
