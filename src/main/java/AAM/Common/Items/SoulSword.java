package AAM.Common.Items;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Multimap;

import AAM.Client.Renderer.Item.SoulRenderer;
import AAM.Common.Items.Resources.SwordDye;
import AAM.Utils.Color;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.SwordType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class SoulSword extends ItemSword
{

	public SoulSword(ToolMaterial mat)
	{
		super(mat);
		MinecraftForgeClient.registerItemRenderer(this, new SoulRenderer());
		this.setContainerItem(this);
		this.setHasSubtypes(true);
	}

	@Override
	public boolean isDamageable()
	{
		return false;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_)
	{
		return false;
	}

	/**
	 * Damage (I think...)
	 */
	public float func_150931_i()
	{
		return PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer).soulDamage;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
//		if (i.hasTagCompound())
//		{
//			if (i.getTagCompound().getString("Owner") == p.getGameProfile().getName())
//			{
//				int skil = i.getTagCompound().getInteger("Skill");
//				PlayerDataHandler ph = PlayerDataHandler.get(p);
//				if (skil < ph.skills.size())
//				{
//					ph.skills.get(skil).act.act(w, p);;
//				}
//			}
//		}
		return super.onItemRightClick(i, w, p);
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	public void onUpdate(ItemStack i, World w, Entity e, int meta, boolean inhand)
	{

	}

	public static Color[] colors = new Color[]
	{ new Color(0.8, 0, 0), new Color(0.0, 0.8, 0.0), new Color(0.0, 0.0, 0.8), new Color(0.0, 0.8, 0.8), new Color(0.8, 0.0, 0.8), new Color(0.8, 0.8, 0.0), new Color(54, 0, 115),
			new Color(253, 0, 161), new Color(95, 36, 156) };

	public static Color getcPhColor(PlayerDataHandler ph)
	{
		if (ph.partType >= 0)
		{
			return colors[ph.partType];
		} else
		{
			return new Color(1.0, 1.0, 1.0);
		}
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit
	 * damage.
	 */
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(field_111210_e, "Weapon modifier", PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer).soulDamage, 0));
		return multimap;
	}

	/**
	 * ItemStack sensitive version of getItemAttributeModifiers
	 */
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = super.getItemAttributeModifiers();
		if (stack.hasTagCompound())
		{
			EntityPlayer p = Minecraft.getMinecraft().thePlayer.worldObj.getPlayerEntityByName(stack.getTagCompound().getString("Owner"));
			if (p != null)
			{
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
						new AttributeModifier(field_111210_e, "Soul modifier", PlayerDataHandler.get(p).soulDamage, 0));

			} else
			{
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 5, 0));

			}
		} else
		{
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 5, 0));

		}
		return multimap;
	}

	/**
	 * Called each tick while using an item.
	 * 
	 * @param stack
	 *            The Item being used
	 * @param player
	 *            The Player using the item
	 * @param count
	 *            The amount of time in tick the item has been used for
	 *            continuously
	 */
	public void onUsingTick(ItemStack stack, EntityPlayer p, int count)
	{
		MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
		// = new MovingObjectPosition((int) p.posX, (int) p.posY, (int) p.posZ,
		// 1, p.getLookVec());
		if (mop.typeOfHit.equals(mop.typeOfHit.BLOCK))
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(mop.blockX, mop.blockY + 1, mop.blockZ, mop.blockX + 1, mop.blockY + 2, mop.blockZ + 1);
			List<EntityItem> entities = p.worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
			if (!entities.isEmpty())
			{
				int time = 10;
				PlayerDataHandler ph = PlayerDataHandler.get(p);

				EntityItem item = entities.get(0);
				if (item.getEntityItem().getItem() instanceof ItemSword && !ph.swords.contains(item.getEntityItem().getItem())
						&& !(item.getEntityItem().getItem() instanceof SoulSword))
				{
					Random r = new Random();
//					if (p.worldObj.isRemote)
//					{
//						if (ph.partType == -1)
//						{
//							AlchemicalParticle ap = new AlchemicalParticle(CWrap.getClientWorld(), mop.blockX, mop.blockY, mop.blockZ, 4);
//							ap.setColor(0.3f, 0.3f, 0.4f);
//							ap.setVelocity(p.posX + r.nextDouble() - 0.5, p.posY + r.nextDouble() - 0.5, p.posZ + r.nextDouble() - 0.5);
//							CWrap.spawnParticle(ap);
//						} else
//						{
//							AlchemicalParticle ap = new AlchemicalParticle(CWrap.getClientWorld(), mop.blockX, mop.blockY, mop.blockZ, 4);
//							ap.setColor(0.3f, 0.3f, 0.4f);
//							ap.setVelocity(p.posX + r.nextDouble() - 0.5, p.posY + r.nextDouble() - 0.5, p.posZ + r.nextDouble() - 0.5);
//							ap.setColor(colors[ph.partType]);
//							CWrap.spawnParticle(ap);
//						}
//					}
					if (item.getEntityItem().hasTagCompound())
					{
						if (item.getEntityItem().getTagCompound().getInteger("Consumption") >= time)
						{
							ph.soulDamage += ((ItemSword) item.getEntityItem().getItem()).func_150931_i() + 4;
							ph.swords.add((ItemSword) item.getEntityItem().getItem());
							item.setDead();
						} else
						{
							item.getEntityItem().getTagCompound().setInteger("Consumption", item.getEntityItem().getTagCompound().getInteger("Consumption") + 1);
						}
					} else
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger("Consumption", 0);
						item.getEntityItem().setTagCompound(tag);
					}
				}
				if (item.getEntityItem().getItem() instanceof SwordDye)
				{
					Random r = new Random();
//					if (p.worldObj.isRemote)
//					{
//						if (ph.partType == -1)
//						{
//							AlchemicalParticle ap = new AlchemicalParticle(CWrap.getClientWorld(), mop.blockX, mop.blockY, mop.blockZ, 4);
//							ap.setColor(0.3f, 0.3f, 0.4f);
//							ap.setVelocity(p.posX + r.nextDouble() - 0.5, p.posY + r.nextDouble() - 0.5, p.posZ + r.nextDouble() - 0.5);
//							CWrap.spawnParticle(ap);
//						} else
//						{
//							AlchemicalParticle ap = new AlchemicalParticle(CWrap.getClientWorld(), mop.blockX, mop.blockY, mop.blockZ, 4);
//							ap.setColor(0.3f, 0.3f, 0.4f);
//							ap.setVelocity(p.posX + r.nextDouble() - 0.5, p.posY + r.nextDouble() - 0.5, p.posZ + r.nextDouble() - 0.5);
//							ap.setColor(colors[ph.partType]);
//							CWrap.spawnParticle(ap);
//						}
//					}
					if (ph.partType != item.getEntityItem().getItemDamage())
					{
						if (item.getEntityItem().hasTagCompound())
						{
							if (item.getEntityItem().getTagCompound().getInteger("Consumption") >= time)
							{

								ph.partType = item.getEntityItem().getItemDamage();
								item.setDead();

							} else
							{
								item.getEntityItem().getTagCompound().setInteger("Consumption", item.getEntityItem().getTagCompound().getInteger("Consumption") + 1);
							}
						} else
						{
							NBTTagCompound tag = new NBTTagCompound();
							tag.setInteger("Consumption", 0);
							item.getEntityItem().setTagCompound(tag);
						}
					}
				}

				if (item.getEntityItem().getItem() instanceof ItemBow && !ph.bow)
				{
					Random r = new Random();
//					if (p.worldObj.isRemote)
//					{
//						if (ph.partType == -1)
//						{
//							AlchemicalParticle ap = new AlchemicalParticle(CWrap.getClientWorld(), mop.blockX, mop.blockY, mop.blockZ, 4);
//							ap.setColor(0.3f, 0.3f, 0.4f);
//							ap.setVelocity(p.posX + r.nextDouble() - 0.5, p.posY + r.nextDouble() - 0.5, p.posZ + r.nextDouble() - 0.5);
//							CWrap.spawnParticle(ap);
//						} else
//						{
//							AlchemicalParticle ap = new AlchemicalParticle(CWrap.getClientWorld(), mop.blockX, mop.blockY, mop.blockZ, 4);
//							ap.setColor(0.3f, 0.3f, 0.4f);
//							ap.setVelocity(p.posX + r.nextDouble() - 0.5, p.posY + r.nextDouble() - 0.5, p.posZ + r.nextDouble() - 0.5);
//							ap.setColor(colors[ph.partType]);
//							CWrap.spawnParticle(ap);
//						}
//					}
					if (item.getEntityItem().hasTagCompound())
					{
						if (item.getEntityItem().getTagCompound().getInteger("Consumption") >= time)
						{

							ph.bow = true;
							item.setDead();

						} else
						{
							item.getEntityItem().getTagCompound().setInteger("Consumption", item.getEntityItem().getTagCompound().getInteger("Consumption") + 1);
						}
					} else
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger("Consumption", 0);
						item.getEntityItem().setTagCompound(tag);
					}
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean inHand)
	{
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return icon[stack.getItemDamage()];
	}

	@Override
	public IIcon getIconFromDamage(int i)
	{
		return icon[i];
	}

	public static IIcon[] icon = new IIcon[6];
	public static String[] ways = new String[6];

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < 3; i++)
		{
			icon[i] = ir.registerIcon("aam:soulsword_" + SwordType.values()[i]);
			ways[i] = "soulsword_" + SwordType.values()[i];
			icon[i + 3] = ir.registerIcon("aam:excalibur_" + SwordType.values()[i]);
			ways[i + 3] = "excalibur_" + SwordType.values()[i];
		}

	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	public String getUnlocalizedName(ItemStack i)
	{
		switch (i.getItemDamage())
		{
		case (0):
			return "aam.soul_sword";
		case (1):
			return "aam.excalibur";
		default:
			return "aam.soul_sword";
		}
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item it, CreativeTabs tab, List l)
	{
		for (int i = 0; i < 3; i++)
		{
			ItemStack is = new ItemStack(it, 1, i);
			l.add(is);
			ItemStack is2 = new ItemStack(it, 1, i + 3);
			l.add(is2);
		}
	}
}
