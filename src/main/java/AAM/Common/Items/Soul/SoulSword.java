package AAM.Common.Items.Soul;

import java.util.List;
import java.util.Random;

import AAM.API.GameWeapon;
import AAM.API.Interface.IExtendedReach;
import AAM.Client.Renderer.Item.SoulRenderer;
import AAM.Common.Items.Artifacts.CrystalBow;
import AAM.Common.Items.Resources.SwordDye;
import AAM.Common.Soul.Soul;
import AAM.Common.Soul.Trait;
import AAM.Common.Soul.WarriorType;
import AAM.Common.Soul.WeaponType;
import AAM.Utils.Color;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class SoulSword extends GameWeapon implements IExtendedReach
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
	public void addUpgradeLevel(World w, ItemStack is)
	{
		String name = is.getTagCompound().getString("Owner");
		if (w.getPlayerEntityByName(name) == null)
		{
			return;
		}
		PlayerDataHandler.get(w.getPlayerEntityByName(name)).addTraitBase(Trait.WeaponLevel, 1);
		super.addUpgradeLevel(w, is);
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is, int level)
	{
		String name = is.getTagCompound().getString("Owner");
		if (w.getPlayerEntityByName(name) == null)
		{
			return;
		}
		PlayerDataHandler.get(w.getPlayerEntityByName(name)).addTraitBase(Trait.WeaponLevel, level);
		super.addUpgradeLevel(w, is, level);
	}

	@Override
	public void setUpgradeLevel(World w, ItemStack is, int level)
	{
		if (!is.hasTagCompound())
		{
			NBTTagCompound tg = new NBTTagCompound();
			is.setTagCompound(tg);
		}
		String name = is.getTagCompound().getString("Owner");
		if (w.getPlayerEntityByName(name) == null)
		{
			return;
		}
		PlayerDataHandler.get(w.getPlayerEntityByName(name)).setTraitBase(Trait.WeaponLevel, level);

		super.setUpgradeLevel(w, is, level);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack is)
	{
		return false;
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
		if (ph.color >= 0)
		{
			return colors[ph.color];
		}
		else
		{
			return new Color(1.0, 1.0, 1.0);
		}
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
				int time = 100;
				PlayerDataHandler ph = PlayerDataHandler.get(p);

				EntityItem item = entities.get(0);
				// if (item.getEntityItem().getItem() instanceof ItemSword &&
				// !ph.swords.contains(item.getEntityItem().getItem()) &&
				// !(item.getEntityItem().getItem() instanceof SoulSword))
				// {
				// Random r = new Random();
				// if (item.getEntityItem().hasTagCompound())
				// {
				// if
				// (item.getEntityItem().getTagCompound().getInteger("Consumption")
				// >= time)
				// {
				// // ph.soulDamage += ((ItemSword)
				// // item.getEntityItem().getItem()).func_150931_i() +
				// // 4;
				// // ph.swords.add((ItemSword)
				// // item.getEntityItem().getItem());
				// // item.setDead();
				// }
				// else
				// {
				// item.getEntityItem().getTagCompound().setInteger("Consumption",
				// item.getEntityItem().getTagCompound().getInteger("Consumption")
				// + 1);
				// }
				// }
				// else
				// {
				// NBTTagCompound tag = new NBTTagCompound();
				// tag.setInteger("Consumption", 0);
				// item.getEntityItem().setTagCompound(tag);
				// }
				// }
				if (item.getEntityItem().getItem() instanceof SwordDye)
				{
					Random r = new Random();
					if (ph.color != item.getEntityItem().getItemDamage())
					{
						if (item.getEntityItem().hasTagCompound())
						{
							if (item.getEntityItem().getTagCompound().getInteger("Consumption") >= time)
							{

								ph.color = item.getEntityItem().getItemDamage();
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

				IIcon sword = icon[stack.getItemDamage()];
				if (ph.arbitur)
					sword = arbitur[stack.getItemDamage()];
				IIcon art = passSword[0];
				if (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Caster))
				{
					art = passStaff[0];
				}
				if (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Tank))
				{
					art = passTank[0];
				}
				if (ph.art)
				{
					if (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Carry))
						art = passSword[ph.stype.ordinal()];
					if (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Caster))
						art = passStaff[ph.stype.ordinal()];
					if (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Tank))
						art = passTank[ph.stype.ordinal()];
				}
				IIcon bow = WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Carry) ? CrystalBow.pass[0]
						: (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Caster) ? CrystalBow.pass[1] : CrystalBow.pass[2]);
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
		else
		{
			IIcon sword = icon[stack.getItemDamage()];
			IIcon art = passSword[0];
			if (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Caster))
			{
				art = passStaff[0];
			}
			if (WeaponType.values()[stack.getItemDamage()].warrior.equals(WarriorType.Tank))
			{
				art = passTank[0];
			}
			switch (pass)
			{
			case 0:
				return sword;
			case 1:
				return art;
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

	public static IIcon[] icon = new IIcon[WeaponType.values().length];
	public static IIcon[] arbitur = new IIcon[WeaponType.values().length];
	public static String[] ways = new String[WeaponType.values().length];
	public static String[] ways_arbitur = new String[WeaponType.values().length];

	public static IIcon[] passSword = new IIcon[Soul.values().length + 1];
	public static IIcon[] passStaff = new IIcon[Soul.values().length + 1];
	public static IIcon[] passTank = new IIcon[Soul.values().length + 1];

	public static IIcon nil;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		nil = ir.registerIcon("aam:null");

		for (int i = 0; i < WeaponType.values().length; i++)
		{
			icon[i] = ir.registerIcon("aam:" + WeaponType.values()[i].texture);
			arbitur[i] = ir.registerIcon("aam:" + WeaponType.values()[i].arbitur);
			ways[i] = WeaponType.values()[i].texture;
			ways_arbitur[i] = WeaponType.values()[i].arbitur;
		}

		passSword[0] = ir.registerIcon("aam:soulsword/swordpasses/component_nil");
		passStaff[0] = ir.registerIcon("aam:soulsword/staffpasses/component_nil");
		passTank[0] = ir.registerIcon("aam:soulsword/tankpasses/component_nil");
		for (int i = 1; i < passSword.length; i++)
		{
			passSword[i] = ir.registerIcon("aam:soulsword/swordpasses/component_" + i);
			passStaff[i] = ir.registerIcon("aam:soulsword/staffpasses/component_" + i);
			passTank[i] = ir.registerIcon("aam:soulsword/tankpasses/component_" + i);
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
		if (i.hasTagCompound() && i.getTagCompound().hasKey("Owner"))
		{
			if (Minecraft.getMinecraft().theWorld.getPlayerEntityByName(i.getTagCompound().getString("Owner")) != null)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().theWorld.getPlayerEntityByName(i.getTagCompound().getString("Owner")));
				return "aam." + ph.sword.toString().toLowerCase();
			}
		}
		return "aam." + WeaponType.values()[i.getItemDamage()].toString().toLowerCase();

	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item it, CreativeTabs tab, List l)
	{
		for (int i = 0; i < WeaponType.values().length; i++)
		{
			ItemStack is = new ItemStack(it, 1, i);
			l.add(is);
		}
	}

	@Override
	public float getReachValue(EntityPlayer p, ItemStack is)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		return MiscUtils.boolToNum(ph.sword.equals(WeaponType.Spear), 8, 4);
	}
}
