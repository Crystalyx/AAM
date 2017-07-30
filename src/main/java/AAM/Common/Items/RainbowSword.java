package AAM.Common.Items;

import java.util.List;

import com.google.common.collect.Multimap;

import AAM.Client.Renderer.Item.RSwordRenderer;
import AAM.Core.AAMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import thaumcraft.api.aspects.AspectList;

public class RainbowSword extends ItemSword
{

	public RainbowSword(ToolMaterial p_i45356_1_)
	{
		super(p_i45356_1_);
		this.setTextureName("aam:spacesword");
		MinecraftForgeClient.registerItemRenderer(this, new RSwordRenderer());
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	public void onUpdate(ItemStack i, World w, Entity e, int p_77663_4_, boolean inHand)
	{
		if (w.getWorldTime() % 100 == 1)
		{
			if (i.getItemDamage() < i.getMaxDamage())
			{
				if (w.canBlockSeeTheSky((int) e.posX, (int) e.posY, (int) e.posZ))
				{
					i.setItemDamage(i.getItemDamage() - 1);
				}
			}
		}

		if (inHand)
		{
			if (e instanceof EntityPlayer)
			{
				EntityPlayer el = (EntityPlayer) e;
				// el.heal(1.0F);
				AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(e.posX - 3, e.posY - 3, e.posZ - 3, e.posX + 3, e.posY + 3, e.posZ + 3);
				List<Entity> entities = e.worldObj.getEntitiesWithinAABB(Entity.class, aabb);
				for (Entity entityItem : entities)
				{
					// if (/** entityItem != el && */
					// GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindUseItem))
					// {
					// entityItem.setVelocity(0.0, 0.0, 0.0);
					// }
				}

				el.addPotionEffect(new PotionEffect(1, 2, 4));

				if (!el.onGround)
				{
					// el.motionY /= 2;
					if (GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump))
					{
						if (i.hasTagCompound())
						{
							if (!el.capabilities.isCreativeMode && !el.getCommandSenderName().equals("Player298"))
							{
								if (i.getTagCompound().getInteger("Exp") > 0)
								{
									i.getTagCompound().setInteger("Exp", i.getTagCompound().getInteger("Exp") - 1);
								}
								else
								{
									if (el.experienceLevel > 0)
									{
										int xp = (int) Math.round(el.experienceLevel * 3.5D);
										if (!el.worldObj.isRemote)
											el.addExperienceLevel(-1);
										i.getTagCompound().setInteger("Exp", i.getTagCompound().getInteger("Exp") + xp);
									}
									else
										if (AAMCore.cfg.enableRemovingRSword)
											el.destroyCurrentEquippedItem();
								}
							}
							double modf = 3.0;

							double rad = Math.toRadians(-(el.rotationPitch) / 8) * 8;
							double ny = modf * Math.sin(rad) + 1;
							double mod = modf * Math.cos(rad);

							rad = Math.toRadians(el.rotationYaw + 90);
							double nx = mod * Math.cos(rad);
							double nz = mod * Math.sin(rad);
							double pt = Math.sqrt(nx * nx + nz * nz);

							el.moveEntity(nx, ny, nz);

							el.fallDistance = -100;
						}
						else
						{
							NBTTagCompound tag = new NBTTagCompound();
							tag.setInteger("Exp", 0);
							i.setTagCompound(tag);
						}
					}
				}
			}
		}
	}

	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 */
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean shift)
	{
		if (i.hasTagCompound())
			l.add("Currently Hold " + i.getTagCompound().getInteger("Exp") + " Expirience");
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit
	 * damage.
	 */
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) 64, 0));
		return multimap;
	}

}
