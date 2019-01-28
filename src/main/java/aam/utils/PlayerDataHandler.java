package aam.utils;

import aam.api.TraitModifier;
import aam.api.abstraction.WearType;
import aam.common.items.ModItems;
import aam.common.soul.*;
import aam.common.weapon.WeaponManager;
import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.PlayerSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataHandler implements IExtendedEntityProperties
{
	public final static String ExtendedDataId = "AAoMPlayer";

	public final EntityPlayer player;

	// /** Custom inventory slots will be stored here - be sure to save to NBT!
	// */TODO
	// public final InventoryCustomPlayer inventory = new
	// InventoryCustomPlayer();

	public NBTTagCompound soulTag = new NBTTagCompound();

	public List<String> party = new ArrayList<>();
	public String addMember = "";

	public TraitStack[] traits = new TraitStack[Trait.values().length];
	public ItemStack sheath = null;
	public ItemStack sword = null;
	public WearType wearType = WearType.Side;

	public boolean lastTickBlocked = false;

	public int soulRegenTimer = 0;
	public int soulxp = 0;

	public int blockDuration = 0;

	public int color = -1;
	public boolean bow = false;
	public Soul stype = Soul.Normal;
	public SoulWeaponType swordType = SoulWeaponType.Broad;
	public WarriorType warrior = WarriorType.NotSelected;
	public boolean art;
	public boolean arbitur;
	public int[] upgLevel = new int[SoulUpgrade.values().length];

	public static final int SoulWatcherId = 22;

	public PlayerDataHandler(EntityPlayer player)
	{
		this.player = player;
		clearProperties(true);
		this.player.getDataWatcher().addObject(SoulWatcherId, (int) this.getTrait(Trait.Soul));
		this.setCurrentSoul((int) this.getTrait(Trait.Soul));

	}

	/**
	 * Used to register these extended properties for the player during
	 * EntityConstructing event
	 */
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PlayerDataHandler.ExtendedDataId, new PlayerDataHandler(player));
	}

	/**
	 * Returns PlayerDataHandler properties for player
	 */
	public static final PlayerDataHandler get(EntityPlayer player)
	{
		if (player != null)
			return (PlayerDataHandler) player.getExtendedProperties(ExtendedDataId);
		return null;
	}

	/**
	 * Copies additional player data from the given PlayerDataHandler instance
	 * Avoids NBT disk I/O overhead when cloning a player after respawn
	 */
	public void copy(PlayerDataHandler props)
	{
		// inventory.copy(props.inventory);

		for (int i = 0; i < traits.length; i++)
		{
			traits[i] = props.traits[i].copy();
		}

		this.setCurrentSoul(props.getCurrentSoul());
		soulRegenTimer = props.soulRegenTimer;
		soulTag = props.soulTag;
		bow = props.bow;
		color = props.color;
		stype = props.stype;
		swordType = props.swordType;
		warrior = props.warrior;
		soulxp = props.soulxp;
		upgLevel = props.upgLevel;
		party = props.party;
		addMember = props.addMember;
		arbitur = props.arbitur;
		art = props.art;
		blockDuration = props.blockDuration;
		sword = props.sword;
		sheath = props.sheath;
		wearType = props.wearType;
	}

	public void clearProperties(boolean construct)
	{
		soulTag = new NBTTagCompound();
		if (!construct)
		{
			soulTag.setString("Owner", player.getGameProfile().getName());
		}

		for (int i = 0; i < traits.length; i++)
		{
			traits[i] = new TraitStack(Trait.values()[i]);
		}
		this.setTraitBase(Trait.Level, 1);

		soulRegenTimer = 0;
		blockDuration = 0;
		// ========
		bow = false;
		color = -1;
		stype = Soul.Normal;
		swordType = SoulWeaponType.Broad;
		warrior = WarriorType.NotSelected;
		if (this.getPermission() > 0)
		{
			TraitModifier tmM = new TraitModifier("Permission", Trait.MeleeDamage, 7.0f, TraitModifier.Operation.add);
			TraitModifier tmR = new TraitModifier("Permission", Trait.RangedDamage, 7.0f, TraitModifier.Operation.add);
			this.applyModifier(tmM);
			this.applyModifier(tmR);
		}
		upgLevel = new int[SoulUpgrade.values().length];
		// =================
		soulxp = 0;
		party = new ArrayList<>();
		addMember = "";
		arbitur = false;
		art = false;
		this.setTraitBase(Trait.MeleeDamage, swordType.baseDamage);
		this.setTraitBase(Trait.RangedDamage, swordType.baseDamage);

		sword = null;
		sheath = null;
		wearType = WearType.Side;
	}

	@Override
	public final void saveNBTData(NBTTagCompound compound)
	{
		// We store all of our data nested in a single tag;
		// this way, we never have to worry about conflicting with other
		// mods that may also be writing to the player's tag compound
		NBTTagCompound tag = new NBTTagCompound();

		// Write everything to our new tag:
		// inventory.writeToNBT(properties);

		NBTTagList tagl = new NBTTagList();
		for (TraitStack trait : traits)
		{
			NBTTagCompound tg = new NBTTagCompound();
			trait.saveToNBT(tg);
			tagl.appendTag(tg);
		}

		tag.setTag("Traits", tagl);

		tag.setInteger("CurrentSoul", player.getDataWatcher().getWatchableObjectInt(SoulWatcherId));
		tag.setInteger("SoulCharge", this.getCurrentSoul());
		tag.setInteger("SoulRegenTimer", soulRegenTimer);
		tag.setInteger("SoulXp", soulxp);
		tag.setInteger("SoulType", stype.ordinal());

		tag.setTag("NBT", soulTag);
		tag.setInteger("PtSize", party.size());
		for (int i = 0; i < party.size(); i++)
		{
			tag.setString("Pt" + i, party.get(i));
		}

		tag.setBoolean("Bow", bow);
		tag.setInteger("PartType", color);
		tag.setString("Owner", player.getGameProfile().getName());
		tag.setInteger("SwordType", swordType.ordinal());
		tag.setInteger("WarriorType", warrior.ordinal());

		for (int j = 0; j < upgLevel.length; j++)
		{
			tag.setInteger("Upg" + j, upgLevel[j]);
		}
		tag.setString("addMember", addMember);
		tag.setBoolean("Arbitur", arbitur);
		tag.setBoolean("Art", art);
		tag.setInteger("Cooldown", blockDuration);

		if(sword != null)
		{
			NBTTagCompound swordTag = new NBTTagCompound();
			sword.writeToNBT(swordTag);
			tag.setTag("Sword", swordTag);
		}

		if(sheath != null)
		{
			NBTTagCompound sheathTag = new NBTTagCompound();
			sheath.writeToNBT(sheathTag);
			tag.setTag("Sheath", sheathTag);
		}
		tag.setInteger("WearType",wearType.ordinal());
		// Finally, set the tag with our unique identifier:
		compound.setTag(ExtendedDataId, tag);
	}

	@Override
	public final void loadNBTData(NBTTagCompound compound)
	{
		// Pretty much the reverse of saveNBTData - get our
		// unique tag and then load everything from it:
		NBTTagCompound tag = (NBTTagCompound) compound.getTag(ExtendedDataId);
		// inventory.readFromNBT(properties);

		for (int i = 0; i < traits.length; i++)
		{
			traits[i] = new TraitStack(Trait.values()[i]);
		}

		if (tag.hasKey("Traits"))
		{
			NBTTagList tagl = tag.getTagList("Traits", 10);
			for (int i = 0; i < traits.length; i++)
			{
				NBTTagCompound tg = tagl.getCompoundTagAt(i);
				traits[i].loadFromNBT(tg);
			}
		}
		player.getDataWatcher().updateObject(SoulWatcherId, tag.getInteger("CurrentSoul"));
		this.setCurrentSoul(tag.getInteger("SoulCharge"));
		soulRegenTimer = tag.getInteger("SoulRegenTimer");

		soulxp = tag.getInteger("SoulXp");
		stype = Soul.values()[tag.getInteger("SoulType")];

		soulTag = tag.getCompoundTag("NBT");

		party.clear();
		for (int i = 0; i < tag.getInteger("PtSize"); i++)
		{
			party.add(tag.getString("Pt" + i));
		}
		bow = tag.getBoolean("Bow");
		color = tag.getInteger("PartType");
		swordType = SoulWeaponType.values()[tag.getInteger("SwordType")];
		warrior = WarriorType.values()[tag.getInteger("WarriorType")];

		for (int j = 0; j < upgLevel.length; j++)
		{
			upgLevel[j] = tag.getInteger("Upg" + j);
		}

		addMember = tag.getString("addMember");
		arbitur = tag.getBoolean("Arbitur");
		art = tag.getBoolean("Art");
		blockDuration = tag.getInteger("Cooldown");

		if (tag.hasKey("Sword"))
		{
			NBTTagCompound swordTag = tag.getCompoundTag("Sword");
			sword = ItemStack.loadItemStackFromNBT(swordTag);
		}
		if (tag.hasKey("Sheath"))
		{
			NBTTagCompound sheathTag = tag.getCompoundTag("Sheath");
			sheath = ItemStack.loadItemStackFromNBT(sheathTag);
		}
		wearType = WearType.values()[tag.getInteger("WearType")];

		this.setTraitBase(Trait.MeleeDamage, swordType.baseDamage);
		this.setTraitBase(Trait.RangedDamage, swordType.baseDamage);
	}

	public float getTrait(Trait t)
	{
		return traits[t.ordinal()].countValue();
	}

	public void setTraitBase(Trait t, float base)
	{
		traits[t.ordinal()].base = base;
	}

	public void addTraitBase(Trait t, float base)
	{
		traits[t.ordinal()].base += base;
	}

	public float getTraitBase(Trait t)
	{
		return traits[t.ordinal()].base;
	}

	public void applyModifier(TraitModifier tm)
	{
		traits[tm.trait.ordinal()].applyModifier(tm);
	}

	public TraitModifier findModifier(Trait t, String id)
	{
		if (traits[t.ordinal()].modifiers != null)
		{
			for (TraitModifier tm : traits[t.ordinal()].modifiers)
			{
				if (tm.id == id)
				{
					return tm;
				}
			}
		}
		return null;
	}

	public void removeModifier(TraitModifier tm)
	{
		traits[tm.trait.ordinal()].removeModifier(tm);
	}

	public void removeModifier(Trait t, String id)
	{
		traits[t.ordinal()].removeModifier(id);
	}

	public void applyAllModifiers(List<TraitModifier> tml)
	{
		for (TraitModifier tm : tml)
		{
			applyModifier(tm);
		}
	}

	public void removeAllModifiers(List<TraitModifier> tml)
	{
		for (TraitModifier tm : tml)
		{
			removeModifier(tm);
		}
	}

	@Override
	public void init(Entity entity, World world)
	{

	}

	/**
	 * Updates anything that needs to be updated each tick NOT called
	 * automatically, so you must call it yourself from LivingUpdateEvent or a
	 * TickHandler
	 */
	public void onUpdate()
	{
		// You couldn't increase blockDuration using anything but SoulSword
		// blockDuration used to render 6 weapons behind player and to count
		// hammer damage bonus
		if (player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() == ModItems.SoulSword || WeaponManager.isHammer(player.getCurrentEquippedItem()) || WeaponManager.isRanged(player.getCurrentEquippedItem()))
				&& player.isBlocking())
		{
			// Either you have a not hammer as weapon or you have 1
			// soulCharge
			if (player.getCurrentEquippedItem().getItem() == ModItems.SoulSword && !swordType.equals(SoulWeaponType.Hammer) || WeaponManager.isRanged(player.getCurrentEquippedItem()) || player.worldObj.getWorldTime() % 2 != 0
					|| this.consumeSoul(1))
			{
				blockDuration += 1;
			}
			else
			{
				if (player.worldObj.getWorldTime() % 4 == 0)
				{
					blockDuration--;
				}
			}
		}
		else
			if (blockDuration > 0)
			{
				blockDuration--;
			}

		this.setTraitBase(Trait.MeleeDamage, swordType.baseDamage);
		this.setTraitBase(Trait.RangedDamage, swordType.baseDamage);
		// checking levelup (maxSoul is equal to needed xp)
		if (soulxp >= this.getTrait(Trait.Soul) && soulxp != 0)
		{
			soulxp -= this.getTrait(Trait.Soul);
			this.setTraitBase(Trait.Level, this.getTraitBase(Trait.Level) + 1);
			this.setTraitBase(Trait.Soul, this.getTraitBase(Trait.Level) * 100);
			TraitModifier damM = new TraitModifier("LevelUp", Trait.MeleeDamage, this.getTraitBase(Trait.Level), TraitModifier.Operation.add);
			TraitModifier damR = new TraitModifier("LevelUp", Trait.RangedDamage, this.getTraitBase(Trait.Level), TraitModifier.Operation.add);
			removeModifier(Trait.MeleeDamage, "LevelUp");
			removeModifier(Trait.RangedDamage, "LevelUp");
			applyModifier(damM);
			applyModifier(damR);

			if (player.worldObj.isRemote)
			{
				player.addChatComponentMessage(new ChatComponentText("You have reached " + (int) this.getTraitBase(Trait.Level) + " level of soul mastery!"));
			}
		}

		// updating owner tag
		soulTag.setString("Owner", player.getDisplayName());

		// only want to update the timer and regen soul on the server:
		if (!player.worldObj.isRemote)
		{
			if (updateSoulTimer())
			{
				addSoul(1);

				// syncronizing once in a minute
				if (Minecraft.getMinecraft().theWorld.getWorldTime() % 1200 == 1)
				{
					if (!player.worldObj.isRemote)
					{
						PlayerSyncMessage psm = new PlayerSyncMessage(player);
						AlchemicalDispatcher.sendToClient(psm, player);
					}
				}
			}
		}
	}

	// ========================================================================================
	// ================================soul====================================================
	// ========================================================================================
	private boolean updateSoulTimer()
	{
		if (getCurrentSoul() > this.getTrait(Trait.Soul))
		{
			setCurrentSoul((int) this.getTrait(Trait.Soul));
		}
		if (soulRegenTimer > 0)
		{
			--soulRegenTimer;
			int l = 0;
			if (player.isBlocking() && !bow)
			{
				soulRegenTimer -= 4;
				l += 1;
			}
			if (player.isSneaking())
			{
				soulRegenTimer -= 3;
				l += 1;
			}
			if (l > 1)
			{
				soulRegenTimer -= 4;
			}
			if (upgLevel[SoulUpgrade.Recharge.ordinal()] > 0)
			{
				int rechargelevel = upgLevel[SoulUpgrade.Recharge.ordinal()];
				soulRegenTimer -= rechargelevel * 2;
			}

		}
		double spd = -1 + 2 / (Math.exp(-(this.getTrait(Trait.Level) - 1) / 24d) + 1);
		if (soulRegenTimer <= 0)
		{
			soulRegenTimer += getCurrentSoul() < this.getTrait(Trait.Soul) ? 20 - (int) (15 * spd) : 0;
			return true;
		}

		return false;
	}

	public int getDicountedSoul(int amount)
	{
		return (int) Math.round(amount - amount * this.getTrait(Trait.Level) / 100D);
	}

	/**
	 * Returns true if the amount of soul was consumed or false if the player's
	 * current soul was insufficient
	 */

	public final boolean consumeSoul(int amount)
	{
		int rem = Math.min(getDicountedSoul(amount), amount);
		boolean sufficient = rem <= getCurrentSoul();
		if (sufficient)
		{
			setCurrentSoul(getCurrentSoul() - rem);
		}
		return sufficient;
	}

	/**
	 * Simple method sets current soul to max soul
	 */
	public final void replenishSoul()
	{
		player.getDataWatcher().updateObject(SoulWatcherId, (int) this.getTrait(Trait.Soul));
	}

	/**
	 * Returns current soul amount
	 */
	public final int getCurrentSoul()
	{
		return player.getDataWatcher().getWatchableObjectInt(SoulWatcherId);
	}

	public final void addSoul(int amount)
	{
		setCurrentSoul(getCurrentSoul() + amount);
	}

	/**
	 * Sets current soul to amount or maxSoul, whichever is lesser
	 */
	public final void setCurrentSoul(int amount)
	{
		player.getDataWatcher().updateObject(SoulWatcherId, amount > 0 ? amount < this.getTrait(Trait.Soul) ? amount : (int) this.getTrait(Trait.Soul) : 0);
	}

	public int getPermission()
	{
		switch (player.getUniqueID().toString())
		{
		case "55275053-cfff-4307-bdc3-aecec93caa38":// Lord_Faceless
			return 1;
		}
		return 0;
	}

	/**
	 * @return functional Itemstack for soul swordType
	 */
	public ItemStack getSwordStack()
	{
		int meta = swordType.ordinal();
		soulTag.setString("Owner", player.getGameProfile().getName());
		ItemStack sword = new ItemStack(ModItems.SoulSword, 1, meta);
		sword.setTagCompound(soulTag);
		sword.getTagCompound().setInteger("UpgradeLevel", (int) this.getTrait(Trait.WeaponLevel));
		return sword.copy();
	}

	public float getFullMeleeDamage(boolean inAttack)
	{
		float m = (float) (swordType.equals(SoulWeaponType.Hammer) ? 1 + 1 / (Math.exp(-(blockDuration - 160) / 10) + 1) : 1);
		return (stype.getMeleeDamageBonus(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), inAttack) + getUpgMeleeDamage()) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	public float getFullMeleeDamageAgainst(EntityLivingBase l, boolean inAttack)
	{
		float m = (float) (swordType.equals(SoulWeaponType.Hammer) ? 1 + 1 / (Math.exp(-(blockDuration - 160) / 10) + 1) : 1);
		return (stype.getFullMeleeDamage(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), inAttack) + getUpgMeleeDamageAgainst(l)) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	public float getFullRangedDamage(boolean inAttack)
	{
		float m = (float) (swordType.equals(SoulWeaponType.Hammer) ? 1 + 1 / (Math.exp(-(blockDuration - 160) / 10) + 1) : 1);
		return (stype.getRangedDamageBonus(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), inAttack) + getUpgRangedDamage()) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	public float getFullRangedDamageAgainst(EntityLivingBase l, boolean inAttack)
	{
		float m = (float) (swordType.equals(SoulWeaponType.Hammer) ? 1 + 1 / (Math.exp(-(blockDuration - 160) / 10) + 1) : 1);
		return (stype.getFullRangedDamage(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), inAttack) + getUpgRangedDamageAgainst(l)) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	/**
	 * @return sum of all passive melee upgrade bonuses
	 */
	public float getUpgMeleeDamage()
	{
		float upgdam = 0;
		for (int i = 0; i < upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getMeleeDamageBonus(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), true);
		}
		return upgdam;
	}

	/**
	 * @return sum of all passive and active melee upgrade bonuses
	 */
	public float getUpgMeleeDamageAgainst(EntityLivingBase l)
	{
		float upgdam = 0;
		for (int i = 0; i < upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getMeleeDamageBonus(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), true)
					+ SoulUpgrade.values()[i].getSpecificMeleeDamageBonus(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage));
		}
		return upgdam;
	}

	/**
	 * @return sum of all passive ranged upgrade bonuses
	 */
	public float getUpgRangedDamage()
	{
		float upgdam = 0;
		for (int i = 0; i < upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getRangedDamageBonus(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), true);
		}
		return upgdam;
	}

	/**
	 * @return sum of all passive and active ranged upgrade bonuses
	 */
	public float getUpgRangedDamageAgainst(EntityLivingBase l)
	{
		float upgdam = 0;
		for (int i = 0; i < upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getRangedDamageBonus(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), true)
					+ SoulUpgrade.values()[i].getSpecificRangedDamageBonus(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage));
		}
		return upgdam;
	}

	/**
	 * @return if bow>0 then player can use ranged attacks
	 */
	public int getBowIndex()
	{
		int bow = 0;
		bow += MathUtils.boolToNum(this.bow, 1, 0);
		bow += MathUtils.boolToNum(swordType.warrior.equals(WarriorType.Caster), 1, 0);
		return bow;
	}

	/**
	 * @return time (in ticks) needed to fully charge staff or crystal bow
	 */
	public int getBowMaxCooldown()
	{
		return 8 + (int) (this.getTrait(Trait.Cooldown) * (7 - upgLevel[SoulUpgrade.Cast.ordinal()])) / (int) this.getTrait(Trait.Level);
	}

}