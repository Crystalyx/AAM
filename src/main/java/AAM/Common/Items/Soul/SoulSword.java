package AAM.Common.Items.Soul;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Multimap;

import AAM.Client.Renderer.Item.SoulRenderer;
import AAM.Common.Items.Artifacts.CrystalBow;
import AAM.Common.Items.Resources.SwordDye;
import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
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
	@Override
	public float func_150931_i()
	{
		return PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer).soulDamage;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		return super.onItemRightClick(i, w, p);
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int meta, boolean inhand)
	{

	}

	public static Color[] colors = new Color[] { new Color(0.8, 0, 0), new Color(0.0, 0.8, 0.0), new Color(0.0, 0.0, 0.8), new Color(0.0, 0.8, 0.8), new Color(0.8, 0.0, 0.8), new Color(0.8, 0.8, 0.0), new Color(54, 0, 115),
			new Color(253, 0, 161), new Color(95, 36, 156) };

	public static Color getcPhColor(PlayerDataHandler ph)
	{
		if (ph.partType >= 0)
		{
			return colors[ph.partType];
		}
		else
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
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Soul modifier", PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer).soulDamage, 0));
		return multimap;
	}

	/**
	 * ItemStack sensitive version of getItemAttributeModifiers
	 */
	@Override
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = super.getItemAttributeModifiers();
		if (stack.hasTagCompound())
		{
			EntityPlayer p = Minecraft.getMinecraft().thePlayer.worldObj.getPlayerEntityByName(stack.getTagCompound().getString("Owner"));
			if (p != null)
			{
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Soul modifier", PlayerDataHandler.get(p).soulDamage, 0));

			}
			else
			{
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Soul modifier", 5, 0));

			}
		}
		else
		{
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Soul modifier", 5, 0));

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
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer p, int count)
	{
		MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
		if (mop.typeOfHit.equals(mop.typeOfHit.BLOCK))
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(mop.blockX, mop.blockY + 1, mop.blockZ, mop.blockX + 1, mop.blockY + 2, mop.blockZ + 1);
			List<EntityItem> entities = p.worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
			if (!entities.isEmpty())
			{
				int time = 10;
				PlayerDataHandler ph = PlayerDataHandler.get(p);

				EntityItem item = entities.get(0);
				if (item.getEntityItem().getItem() instanceof ItemSword && !ph.swords.contains(item.getEntityItem().getItem()) && !(item.getEntityItem().getItem() instanceof SoulSword))
				{
					Random r = new Random();
					if (item.getEntityItem().hasTagCompound())
					{
						if (item.getEntityItem().getTagCompound().getInteger("Consumption") >= time)
						{
							ph.soulDamage += ((ItemSword) item.getEntityItem().getItem()).func_150931_i() + 4;
							ph.swords.add((ItemSword) item.getEntityItem().getItem());
							item.setDead();
						}
						else
						{
							item.getEntityItem().getTagCompound().setInteger("Consumption", item.getEntityItem().getTagCompound().getInteger("Consumption") + 1);
						}
					}
					else
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger("Consumption", 0);
						item.getEntityItem().setTagCompound(tag);
					}
				}
				if (item.getEntityItem().getItem() instanceof SwordDye)
				{
					Random r = new Random();
					if (ph.partType != item.getEntityItem().getItemDamage())
					{
						if (item.getEntityItem().hasTagCompound())
						{
							if (item.getEntityItem().getTagCompound().getInteger("Consumption") >= time)
							{

								ph.partType = item.getEntityItem().getItemDamage();
								item.setDead();

							}
							else
							{
								item.getEntityItem().getTagCompound().setInteger("Consumption", item.getEntityItem().getTagCompound().getInteger("Consumption") + 1);
							}
						}
						else
						{
							NBTTagCompound tag = new NBTTagCompound();
							tag.setInteger("Consumption", 0);
							item.getEntityItem().setTagCompound(tag);
						}
					}
				}

				if (item.getEntityItem().getItem() instanceof ItemBow && !ph.bow)
				{
					if (item.getEntityItem().hasTagCompound())
					{
						if (item.getEntityItem().getTagCompound().getInteger("Consumption") >= time)
						{

							ph.bow = true;
							item.setDead();

						}
						else
						{
							item.getEntityItem().getTagCompound().setInteger("Consumption", item.getEntityItem().getTagCompound().getInteger("Consumption") + 1);
						}
					}
					else
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
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Owner"))
		{
			if (Minecraft.getMinecraft().theWorld.getPlayerEntityByName(stack.getTagCompound().getString("Owner")) != null)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().theWorld.getPlayerEntityByName(stack.getTagCompound().getString("Owner")));
				int dt = MiscUtils.boolToNum(ph.arbitur) * 4 * MiscUtils.boolToNum(ph.getPermission() > 0, 1, 2);

				IIcon sword = icon[stack.getItemDamage() + dt];
				IIcon art = passartnil;
				if (ph.art)
					art = Artifact.pass[ph.stype.ordinal()];
				IIcon bow = CrystalBow.pass;
				switch (pass)
				{
				case 0:
					return sword;
				case 1:
					return art;
				case 2:
					return ph.bow ? bow : nil;
				}
			}
		}
		return icon[stack.getItemDamage()];
	}

	@Override
	public IIcon getIconFromDamage(int i)
	{
		return icon[i];
	}

	@Override
	public int getRenderPasses(int metadata)
	{
		return 3;
	}

	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	public static IIcon[] icon = new IIcon[12];
	public static String[] ways = new String[12];
	public static IIcon nil;
	public static IIcon passartnil;
	public static IIcon artnil;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		nil = ir.registerIcon("aam:null");
		passartnil = ir.registerIcon("aam:soulsword/passes/component_nil");
		artnil = ir.registerIcon("aam:soulsword/component_nil");

		for (int i = 0; i < 4; i++)
		{
			icon[i] = ir.registerIcon("aam:soulsword/soulsword_" + SwordType.values()[i].toString().toLowerCase());
			ways[i] = "soulsword/soulsword_" + SwordType.values()[i].toString().toLowerCase();
			icon[i + 4] = ir.registerIcon("aam:soulsword/excalibur_" + SwordType.values()[i].toString().toLowerCase());
			ways[i + 4] = "soulsword/excalibur_" + SwordType.values()[i].toString().toLowerCase();
			icon[i + 8] = ir.registerIcon("aam:soulsword/arbitur_" + SwordType.values()[i].toString().toLowerCase());
			ways[i + 8] = "soulsword/arbitur_" + SwordType.values()[i].toString().toLowerCase();
		}

	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		return i.getItemDamage() > 2 ? "aam.excalibur" : "aam.soul_sword";
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item it, CreativeTabs tab, List l)
	{
		for (int i = 0; i < 12; i++)
		{
			ItemStack is = new ItemStack(it, 1, i);
			l.add(is);
		}
	}
}
