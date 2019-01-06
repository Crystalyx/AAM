/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Common.Item;

import GF.Client.Render.GoldPanRender;
import GF.Registry.Registry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemGoldPan extends Item 
{
	public ItemGoldPan(int type)
	{
		this.fullType=type;
		this.setFull3D();
		MinecraftForgeClient.registerItemRenderer(this, (IItemRenderer) new GoldPanRender(type));		
		if(type==0)
		{			
			this.setUnlocalizedName("goldpan");
			this.setMaxStackSize(64);
		}
		else
		{
			this.setUnlocalizedName("goldpanfull"+Registry.names[type]);
			this.setMaxStackSize(1);
		}
		this.setCreativeTab(Registry.gftab);
	}
	
	public boolean isFull()
	{
		return this.fullType>0;
	}	
	
	public void onUsingTick(ItemStack stack, EntityPlayer p, int count)
	{		
		if(p.getCurrentEquippedItem().getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("Wash", 100);
			p.getCurrentEquippedItem().setTagCompound(tag);
		}
		if(p.getCurrentEquippedItem().getTagCompound().hasKey("Wash"))
		{
			p.getCurrentEquippedItem().getTagCompound().setInteger("Wash", p.getCurrentEquippedItem().getTagCompound().getInteger("Wash")-p.worldObj.rand.nextInt(5));
			if(p.getCurrentEquippedItem().getTagCompound().getInteger("Wash")<=0)
			{
				p.destroyCurrentEquippedItem();
				p.inventory.addItemStackToInventory(new ItemStack(Registry.goldpanfull[0]));
				p.inventory.addItemStackToInventory(new ItemStack(Registry.dusts[this.fullType-1]));
			}
		}
	}
	
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int cx, int cy, int cz, int meta, float px, float py, float pz)
	{
		if(p.getCurrentEquippedItem().getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("Wash", 100);
			p.getCurrentEquippedItem().setTagCompound(tag);
		}
		if(w.getBlock(cx, cy+1, cz)== Blocks.water || w.getBlock(cx, cy, cz) == Blocks.flowing_water)
		{
			if(this.fullType>0)
			{
				p.getCurrentEquippedItem().getTagCompound().setInteger("Wash", p.getCurrentEquippedItem().getTagCompound().getInteger("Wash")-w.rand.nextInt(5));
				if(p.getCurrentEquippedItem().getTagCompound().getInteger("Wash")<=0)
				{
					p.destroyCurrentEquippedItem();
					p.inventory.addItemStackToInventory(new ItemStack(Registry.goldpanfull[0]));
					p.inventory.addItemStackToInventory(new ItemStack(Registry.dusts[this.fullType-1]));
				}
			}			
		}
		return true;		
	}
		
	public int fullType=0;
	
	public MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer, boolean par3)
	{
		float f = 1.0F;
		float f1 = par2EntityPlayer.prevRotationPitch
				+ (par2EntityPlayer.rotationPitch - par2EntityPlayer.prevRotationPitch)
				* f;
		float f2 = par2EntityPlayer.prevRotationYaw
				+ (par2EntityPlayer.rotationYaw - par2EntityPlayer.prevRotationYaw)
				* f;
		double d0 = par2EntityPlayer.prevPosX
				+ (par2EntityPlayer.posX - par2EntityPlayer.prevPosX)
				* (double) f;
		double d1 = par2EntityPlayer.prevPosY
				+ (par2EntityPlayer.posY - par2EntityPlayer.prevPosY)
				* (double) f
				+ (double) (par1World.isRemote ? par2EntityPlayer
						.getEyeHeight()
						- par2EntityPlayer.getDefaultEyeHeight()
						: par2EntityPlayer.getEyeHeight());
		double d2 = par2EntityPlayer.prevPosZ
				+ (par2EntityPlayer.posZ - par2EntityPlayer.prevPosZ)
				* (double) f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		if (par2EntityPlayer instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) par2EntityPlayer).theItemInWorldManager
					.getBlockReachDistance();
		}

		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3,
				(double) f8 * d3);
		return par1World.func_147447_a(vec3, vec31, par3, !par3, false);
	}
}
