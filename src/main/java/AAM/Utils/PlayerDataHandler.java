package AAM.Utils;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Items.ModItems;
import AAM.Common.Soul.Soul;
import AAM.Common.Soul.SoulUpgrade;
import AAM.Common.Soul.WarriorType;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerDataHandler implements IExtendedEntityProperties
{
	public final static String ExtendedDataId = "AAoMPlayer";
	public final static String ExtendedSoulDataId = "ASoulPlayer";

	public final EntityPlayer player;

	// /** Custom inventory slots will be stored here - be sure to save to NBT!
	// */TODO
	// public final InventoryCustomPlayer inventory = new
	// InventoryCustomPlayer();

	public NBTTagCompound soulTag = new NBTTagCompound();

	public List<String> party = new ArrayList<String>();
	public String addMember = "";
	public int dungLevel = 0;

	public boolean lastTickBlocked = false;

	public float soulDamage = 5.0F;
	public int soul = 100;
	public int maxSoul = 100;
	public int soulRegenTimer = 0;
	public int deficit = 0;

	public int soulLevel = 1;
	public int soulxp = 0;

	public List<ItemSword> swords = new ArrayList<ItemSword>();
	public int partType = -1;
	public boolean bow = false;
	public Soul stype = Soul.Normal;
	public SwordType sword = SwordType.Broad;
	public WarriorType warrior = WarriorType.NotSelected;
	public boolean art;
	public boolean arbitur;

	public int[] upgLevel = new int[SoulUpgrade.values().length];

	public static final int SoulWatcherId = 22;

	public PlayerDataHandler(EntityPlayer player)
	{
		this.player = player;
		this.player.getDataWatcher().addObject(SoulWatcherId, this.maxSoul);
		clearProperties(true);
	}

	public void clearProperties(boolean construct)
	{
		if (this.getPermission() > 0)
		{
			this.soulDamage = 12.0F;
		}
		else
			this.soulDamage = 5.0F;
		this.soulTag = new NBTTagCompound();
		if (!construct)
			this.soulTag.setString("Owner", this.player.getGameProfile().getName());
		this.soul = 100;
		this.soulLevel = 1;
		this.soulRegenTimer = 0;
		this.maxSoul = Math.max(1, this.soulLevel * 100);
		this.swords = new ArrayList<ItemSword>();
		this.bow = false;
		this.partType = -1;
		this.stype = Soul.Normal;
		this.sword = SwordType.Broad;
		this.warrior = WarriorType.NotSelected;
		this.upgLevel = new int[SoulUpgrade.values().length];
		this.soulxp = 0;
		this.deficit = 0;
		this.party = new ArrayList<String>();
		this.addMember = "";
		this.dungLevel = 0;
		this.arbitur = false;
		this.art = false;
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
		return (PlayerDataHandler) player.getExtendedProperties(ExtendedDataId);
	}

	/**
	 * Copies additional player data from the given PlayerDataHandler instance
	 * Avoids NBT disk I/O overhead when cloning a player after respawn
	 */
	public void copy(PlayerDataHandler props)
	{
		// inventory.copy(props.inventory);
		this.soulDamage = props.soulDamage;
		this.soul = props.soul;
		this.soulLevel = props.soulLevel;
		this.soulRegenTimer = props.soulRegenTimer;
		this.maxSoul = props.maxSoul;
		this.soulTag = props.soulTag;
		this.swords = props.swords;
		this.bow = props.bow;
		this.partType = props.partType;
		this.stype = props.stype;
		this.sword = props.sword;
		this.warrior = props.warrior;
		this.soulxp = props.soulxp;
		this.upgLevel = props.upgLevel;
		this.deficit = props.deficit;
		this.party = props.party;
		this.addMember = props.addMember;
		this.dungLevel = props.dungLevel;
		this.arbitur = props.arbitur;
		this.art = props.art;
	}

	@Override
	public final void saveNBTData(NBTTagCompound compound)
	{
		// We store all of our data nested in a single tag;
		// this way, we never have to worry about conflicting with other
		// mods that may also be writing to the player's tag compound
		NBTTagCompound properties = new NBTTagCompound();

		// Write everything to our new tag:
		// inventory.writeToNBT(properties);

		properties.setInteger("CurrentSoul", player.getDataWatcher().getWatchableObjectInt(SoulWatcherId));
		properties.setFloat("SoulDamage", this.soulDamage);
		properties.setInteger("SoulCharge", this.soul);
		properties.setInteger("SoulLevel", this.soulLevel);
		properties.setInteger("SoulRegenTimer", this.soulRegenTimer);
		properties.setInteger("SoulXp", this.soulxp);
		properties.setInteger("DeficitSoul", this.deficit);
		properties.setInteger("SoulType", this.stype.ordinal());

		properties.setTag("NBT", this.soulTag);
		properties.setInteger("Size", this.swords.size());
		for (int i = 0; i < this.swords.size(); i++)
		{
			properties.setInteger("Sw" + i, Item.getIdFromItem(this.swords.get(i)));
		}
		properties.setInteger("PtSize", this.party.size());
		for (int i = 0; i < this.party.size(); i++)
		{
			properties.setString("Pt" + i, this.party.get(i));
		}

		properties.setBoolean("Bow", this.bow);
		properties.setInteger("PartType", this.partType);
		properties.setString("Owner", this.player.getGameProfile().getName());
		properties.setInteger("SwordType", this.sword.ordinal());
		properties.setInteger("WarriorType", this.warrior.ordinal());

		for (int j = 0; j < upgLevel.length; j++)
		{
			properties.setInteger("Upg" + j, this.upgLevel[j]);
		}
		properties.setString("addMember", this.addMember);
		properties.setInteger("Dungeon", this.dungLevel);
		properties.setBoolean("Arbitur", this.arbitur);
		properties.setBoolean("Art", this.art);

		// Finally, set the tag with our unique identifier:
		compound.setTag(ExtendedDataId, properties);
	}

	@Override
	public final void loadNBTData(NBTTagCompound compound)
	{
		// Pretty much the reverse of saveNBTData - get our
		// unique tag and then load everything from it:
		NBTTagCompound tag = (NBTTagCompound) compound.getTag(ExtendedDataId);
		// inventory.readFromNBT(properties);

		player.getDataWatcher().updateObject(SoulWatcherId, tag.getInteger("CurrentSoul"));
		this.soulDamage = tag.getFloat("SoulDamage");
		this.soul = tag.getInteger("SoulCharge");
		this.soulLevel = tag.getInteger("SoulLevel");
		this.soulRegenTimer = tag.getInteger("SoulRegenTimer");
		this.maxSoul = Math.max(1, this.soulLevel * 100);

		this.soulxp = tag.getInteger("SoulXp");
		this.deficit = tag.getInteger("DeficitSoul");
		this.stype = Soul.values()[tag.getInteger("SoulType")];

		this.soulTag = tag.getCompoundTag("NBT");

		this.swords.clear();
		for (int i = 0; i < tag.getInteger("Size"); i++)
		{
			this.swords.add((ItemSword) Item.getItemById(tag.getInteger("Sw" + i)));
		}
		this.party.clear();
		for (int i = 0; i < tag.getInteger("PtSize"); i++)
		{
			this.party.add(tag.getString("Pt" + i));
		}
		this.bow = tag.getBoolean("Bow");
		this.partType = tag.getInteger("PartType");
		this.sword = SwordType.values()[tag.getInteger("SwordType")];
		this.warrior = WarriorType.values()[tag.getInteger("WarriorType")];

		for (int j = 0; j < upgLevel.length; j++)
		{
			this.upgLevel[j] = tag.getInteger("Upg" + j);
		}

		this.addMember = tag.getString("addMember");
		this.dungLevel = tag.getInteger("Dungeon");
		this.arbitur = tag.getBoolean("Arbitur");
		this.art = tag.getBoolean("Art");

	}

	@Override
	public void init(Entity entity, World world)
	{

	}

	public void setDeficit(int deficit)
	{
		this.deficit = deficit;
	}

	public int getDeficit()
	{
		return deficit;
	}

	public void setDamage(float soulDamage)
	{
		this.soulDamage = soulDamage;
	}

	public float getDamage()
	{
		return this.soulDamage;
	}

	/**
	 * Updates anything that needs to be updated each tick NOT called
	 * automatically, so you must call it yourself from LivingUpdateEvent or a
	 * TickHandler
	 */
	public void onUpdate()
	{
		this.maxSoul = this.soulLevel * 100;
		this.soulDamage = Math.max(this.soulLevel + 4, this.soulDamage);
		// checking levelup (maxSoul is needed xp)
		if (this.soulxp >= this.maxSoul && this.soulxp != 0)
		{
			this.soulxp -= this.maxSoul;
			this.soulLevel += 1;
			this.maxSoul = Math.max(1, this.soulLevel * 100);
			this.soulDamage += 1;

			if (player.worldObj.isRemote)
			{
				this.player.addChatComponentMessage(new ChatComponentText("You have reached " + this.soulLevel + " level of of soul mastery!"));
			}
		}
		// updating owner tag
		this.soulTag.setString("Owner", this.player.getDisplayName());

		// only want to update the timer and regen mana on the server:
		if (!player.worldObj.isRemote)
		{
			if (Minecraft.getMinecraft().theWorld.getTotalWorldTime() % 60 == 0)
				this.maxSoul = Math.max(1, this.soulLevel * 100);

			if (this.deficit <= 0 && updateSoulTimer())
			{
				addSoul(1);

				if (Minecraft.getMinecraft().theWorld.getWorldTime() % 1000 == 1)
				{
					if (!this.player.worldObj.isRemote)
					{
						PlayerSyncMessage psm = new PlayerSyncMessage(this.player);
						AlchemicalDispatcher.sendToClient(psm, this.player);
					}
				}

				int count = 0;
				for (int i = 0; i < 36; i++)
				{
					ItemStack item = this.player.inventory.getStackInSlot(i);

					if (item != null)
					{
						if (item.getItem() == ModItems.SoulSword && item.hasTagCompound() && item.getTagCompound().getString("Owner") == this.player.getGameProfile().getName())
						{
							count += 1;
						}
					}
				}
				if (count == 1)
				{
					int slot = 0;
					for (int i = 0; i < 36; i++)
					{
						ItemStack item = this.player.inventory.mainInventory[i];

						if (item != null)
						{
							if (item.getItem() == ModItems.SoulSword && item.hasTagCompound() && item.getTagCompound().getString("Owner") == this.player.getGameProfile().getName())
							{
								slot = i;
							}
						}
					}
					ItemStack sword = this.player.inventory.mainInventory[slot];
					this.soulTag = sword.getTagCompound();
				}
			}
			else
			{
				if (this.deficit > 0)
				{
					if (this.deficit <= this.getCurrentSoul())
					{
						this.consumeSoul(this.deficit);
						this.deficit = 0;
					}
					else
					{
						int excess = this.deficit - this.getCurrentSoul();
						this.setCurrentSoul(0);
						this.deficit = excess;
					}
				}
			}
		}
	}

	// ========================================================================================
	// ================================Soul====================================================
	// ========================================================================================
	private boolean updateSoulTimer()
	{
		if (getCurrentSoul() > maxSoul)
		{
			setCurrentSoul(maxSoul);
		}
		if (soulRegenTimer > 0)
		{
			--soulRegenTimer;
			int l = 0;
			if (this.player.isBlocking() && !this.bow)
			{
				soulRegenTimer -= 4;
				l += 1;
			}
			if (this.player.isSneaking())
			{
				soulRegenTimer -= 3;
				l += 1;
			}
			if (l > 0)
			{
				soulRegenTimer -= 2;
			}
			if (this.upgLevel[SoulUpgrade.Recharge.ordinal()] > 0)
			{
				int rechlev = this.upgLevel[SoulUpgrade.Recharge.ordinal()];
				soulRegenTimer -= rechlev;
			}

		}
		double spd = -1 + 2 / (Math.exp(-(this.soulLevel - 1) / 24d) + 1);
		if (soulRegenTimer <= 0)
		{
			soulRegenTimer = getCurrentSoul() < getMaxSoul() ? 20 - (int) (15 * spd) : 0;
			return true;
		}

		return false;
	}

	public int getDicountSoul(int amount)
	{
		return (int) Math.round(amount - amount * ((double) this.soulLevel) / 100D);
	}

	/**
	 * Returns true if the amount of soul was consumed or false if the player's
	 * current soul was insufficient
	 */
	public final boolean consumeSoul(int amount)
	{
		int rem = (int) Math.min(Math.round(amount - amount * ((double) this.soulLevel) / 100D), amount);
		boolean sufficient = rem <= getCurrentSoul();
		if (sufficient)
			setCurrentSoul(getCurrentSoul() - rem);
		return sufficient;
	}

	public final void addSoul(int amount)
	{
		setCurrentSoul(getCurrentSoul() + amount);
	}

	/**
	 * Simple method sets current Soul to max Soul
	 */
	public final void replenishSoul()
	{
		this.player.getDataWatcher().updateObject(SoulWatcherId, this.maxSoul);
	}

	/**
	 * Returns current Soul amount
	 */
	public final int getCurrentSoul()
	{
		return player.getDataWatcher().getWatchableObjectInt(SoulWatcherId);
	}

	/**
	 * Sets current Soul to amount or maxSoul, whichever is lesser
	 */
	public final void setCurrentSoul(int amount)
	{
		player.getDataWatcher().updateObject(SoulWatcherId, amount > 0 ? (amount < maxSoul ? amount : maxSoul) : 0);
	}

	/**
	 * Returns max Soul amount
	 */
	public final int getMaxSoul()
	{
		return maxSoul;
	}

	public final int getSoulLevel()
	{
		return soulLevel;
	}

	public int getPermission()
	{
		switch (this.player.getUniqueID().toString())
		{
		case ("55275053-cfff-4307-bdc3-aecec93caa38"):// Lord_Faceless
			return 1;
		}
		return 0;
	}

	public ItemStack getSwordStack()
	{
		int meta = this.sword.ordinal();
		this.soulTag.setString("Owner", this.player.getGameProfile().getName());
		ItemStack sword = new ItemStack(ModItems.SoulSword, 1, meta);
		sword.setTagCompound(PlayerDataHandler.get(this.player).soulTag);
		return sword.copy();
	}

	public float getFullMeleeDamage(boolean inAttack)
	{
		return this.stype.getMeleeDamage(this, soulLevel, soulDamage, inAttack) + getUpgMeleeDamage();
	}

	public float getFullMeleeDamageAgainst(EntityLivingBase l, boolean inAttack)
	{
		return this.stype.getFullMeleeDamage(this, l, soulLevel, soulDamage, inAttack) + getUpgMeleeDamageAgainst(l);
	}

	public float getFullRangedDamage(boolean inAttack)
	{
		return this.stype.getRangedDamage(this, soulLevel, soulDamage, inAttack) + getUpgRangedDamage();
	}

	public float getFullRangedDamageAgainst(EntityLivingBase l, boolean inAttack)
	{
		return this.stype.getFullRangedDamage(this, l, soulLevel, soulDamage, inAttack) + getUpgRangedDamageAgainst(l);
	}

	public float getUpgMeleeDamage()
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getMeleeDamage(this, this.soulLevel, this.soulDamage, true);
		}
		return upgdam;
	}

	public float getUpgMeleeDamageAgainst(EntityLivingBase l)
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getMeleeDamage(this, this.soulLevel, this.soulDamage, true) + SoulUpgrade.values()[i].getSpecificMeleeDamage(this, l, this.soulLevel, this.soulDamage);
		}
		return upgdam;
	}

	public float getUpgRangedDamage()
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getRangedDamage(this, this.soulLevel, this.soulDamage, true);
		}
		return upgdam;
	}

	public float getUpgRangedDamageAgainst(EntityLivingBase l)
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getRangedDamage(this, this.soulLevel, this.soulDamage, true) + SoulUpgrade.values()[i].getSpecificRangedDamage(this, l, this.soulLevel, this.soulDamage);
		}
		return upgdam;
	}

}