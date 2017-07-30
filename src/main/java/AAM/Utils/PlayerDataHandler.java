package AAM.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import AAM.Common.Items.ModItems;
import AAM.Common.Skills.ModSkills;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerDataHandler implements IExtendedEntityProperties
{
	public final static String ExtendedDataId = "ExtendedPlayer";

	public final EntityPlayer player;

	// /** Custom inventory slots will be stored here - be sure to save to NBT!
	// */
	// public final InventoryCustomPlayer inventory = new
	// InventoryCustomPlayer();

	public NBTTagCompound soulTag = new NBTTagCompound();
	public List<String> party = new ArrayList<String>();
	public String addMember = "";
	public int dung = 0;

	public float soulDamage = 5.0F;
	public float pretempmodif = 1.0F;
	public float tempmodif = 1.0F;

	public int soulCharge = 100;
	public int deficitsoul = 0;
	public int slag = 0;
	public int slagMax = 100;

	public int soulRegenTimer = 0;
	public int maxSoul = 100;
	public int soulLevel = 1;
	public int xpPoints = 0;
	public List<ItemSword> swords = new ArrayList<ItemSword>();
	public int partType = -1;
	public boolean bow = false;
	public Soul stype = Soul.Normal;
	public SwordType sword = SwordType.broad;
	public double soulxp = 0.0;

	// public List<Skill> skills = new ArrayList<Skill>();

	public ItemStack[] sphInv = new ItemStack[54];
	public boolean playerIsSeeker = false;
	public int patronage = 0;

	public int castUpg = 0;
	public int bloodUpg = 0;
	public int moonUpg = 0;

	public static final int SoulWatcherId = 22;

	public PlayerDataHandler(EntityPlayer player)
	{
		this.player = player;

		UUID uuid = player.getUniqueID();
		UUID lord = UUID.fromString("55275053-cfff-4307-bdc3-aecec93caa38");
		if (uuid.equals(lord))
		{
			this.soulDamage = 12.0F;
		}
		else
			this.soulDamage = 5.0F;
		this.soulCharge = 100;
		this.soulLevel = 1;
		this.soulRegenTimer = 0;
		this.maxSoul = Math.max(1, this.soulLevel * 100);
		this.swords = new ArrayList<ItemSword>();
		this.bow = false;
		this.partType = -1;
		this.stype = Soul.Normal;
		this.sword = SwordType.broad;
		this.soulxp = 0.0;
		this.player.getDataWatcher().addObject(SoulWatcherId, this.maxSoul);
		this.castUpg = 0;
		this.bloodUpg = 0;
		this.moonUpg = 0;
		this.deficitsoul = 0;
		this.slag = 0;
		this.slagMax = this.soulLevel * 100;
		this.party = new ArrayList<String>();
		this.addMember = "";
		this.dung = 0;
	}

	public void clearProperties()
	{
		UUID uuid = player.getUniqueID();
		UUID lord = UUID.fromString("55275053-cfff-4307-bdc3-aecec93caa38");
		if (uuid.equals(lord))
		{
			this.soulDamage = 12.0F;
		}
		else
			this.soulDamage = 5.0F;
		this.soulTag.setString("Owner", this.player.getGameProfile().getName());
		this.soulCharge = 100;
		this.soulLevel = 1;
		this.xpPoints = 0;
		this.soulRegenTimer = 0;
		this.maxSoul = Math.max(1, this.soulLevel * 100);
		this.soulTag = new NBTTagCompound();
		this.swords = new ArrayList<ItemSword>();
		this.bow = false;
		this.partType = -1;
		this.stype = Soul.Normal;
		this.sword = SwordType.broad;
		this.soulxp = 0.0;
		this.castUpg = 0;
		this.bloodUpg = 0;
		this.moonUpg = 0;
		this.deficitsoul = 0;
		this.slag = 0;
		this.slagMax = this.soulLevel * 100;
		this.party = new ArrayList<String>();
		this.addMember = "";
		this.dung = 0;
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
		this.soulCharge = props.soulCharge;
		this.soulLevel = props.soulLevel;
		this.soulRegenTimer = props.soulRegenTimer;
		this.maxSoul = props.maxSoul;
		this.soulTag = props.soulTag;
		this.swords = props.swords;
		this.bow = props.bow;
		this.partType = props.partType;
		this.stype = props.stype;
		this.sword = props.sword;
		this.soulxp = props.soulxp;
		this.castUpg = props.castUpg;
		this.bloodUpg = props.bloodUpg;
		this.moonUpg = props.moonUpg;
		this.deficitsoul = props.deficitsoul;
		this.slag = props.slag;
		this.slagMax = props.slagMax;
		this.party = props.party;
		this.addMember = props.addMember;
		this.xpPoints = props.xpPoints;
		this.dung = props.dung;

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
		properties.setInteger("SoulCharge", this.soulCharge);

		properties.setInteger("SoulLevel", this.soulLevel);
		properties.setInteger("SoulXPPoint", this.xpPoints);
		properties.setInteger("SoulRegenTimer", this.soulRegenTimer);
		properties.setTag("NBT", this.soulTag);
		properties.setInteger("Size", this.swords.size());
		properties.setBoolean("Seeker", this.playerIsSeeker);
		properties.setInteger("Patronage", this.patronage);
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
		properties.setInteger("SoulType", this.stype.ordinal());
		properties.setInteger("SwordType", this.sword.ordinal());
		properties.setDouble("SoulXp", this.soulxp);

		properties.setInteger("CastUpg", this.castUpg);
		properties.setInteger("BloodUpg", this.bloodUpg);
		properties.setInteger("MoonUpg", this.moonUpg);
		properties.setInteger("DeficitSoul", this.deficitsoul);
		properties.setInteger("Slag", this.slag);
		properties.setInteger("SlagMax", this.slagMax);
		properties.setString("addMember", this.addMember);
		properties.setInteger("Dungeon", this.dung);

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
		this.soulCharge = tag.getInteger("SoulCharge");
		this.soulRegenTimer = tag.getInteger("SoulRegenTimer");
		this.maxSoul = Math.max(1, this.soulLevel * 100);
		this.soulLevel = tag.getInteger("SoulLevel");
		this.xpPoints = tag.getInteger("SoulXPPoint");

		this.soulTag = tag.getCompoundTag("NBT");

		this.playerIsSeeker = tag.getBoolean("Seeker");
		this.patronage = tag.getInteger("Patronage");
		this.sphInv = new ItemStack[54];
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.sphInv.length)
			{
				this.sphInv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

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
		this.stype = Soul.values()[tag.getInteger("SoulType")];
		this.sword = SwordType.values()[tag.getInteger("SwordType")];

		this.soulxp = tag.getDouble("SoulXp");

		this.castUpg = tag.getInteger("CastUpg");
		this.bloodUpg = tag.getInteger("BloodUpg");
		this.moonUpg = tag.getInteger("MoonUpg");
		this.deficitsoul = tag.getInteger("DeficitSoul");

		this.slag = tag.getInteger("Slag");
		this.slagMax = tag.getInteger("SlagMax");
		this.addMember = tag.getString("addMember");
		this.dung = tag.getInteger("Dungeon");

	}

	@Override
	public void init(Entity entity, World world)
	{

	}

	public void setDeficit(int deficit)
	{
		this.deficitsoul = deficit;
	}

	public float getDamage()
	{
		return this.soulDamage * this.tempmodif;
	}

	/**
	 * Updates anything that needs to be updated each tick NOT called
	 * automatically, so you must call it yourself from LivingUpdateEvent or a
	 * TickHandler
	 */
	public void onUpdate()
	{
		this.maxSoul = Math.max(1, this.soulLevel * 100);

		if (this.player.worldObj.getWorldTime() % 40 == 1)
		{
			this.tempmodif = this.pretempmodif;

			this.pretempmodif = (float) Math.rint((1.0F + 0.1F * this.moonUpg + 0.02F * (this.soulLevel - 1)) * 1000) / 1000;
			this.pretempmodif += this.player.worldObj.rand.nextDouble() < ((float) this.slag) / this.slagMax ? 0.2F : 0.0F;
		}

		for (int i = 0; i < this.party.size(); i++)
		{
			if (this.party.get(i) == "")
			{
				this.party.remove(i);
			}
		}

		if (this.player.worldObj.getWorldTime() % 400 == 1 && this.slag > 0)
		{
			this.slag--;
		}
		if (this.soulxp >= this.maxSoul && this.soulxp != 0)
		{
			this.soulxp -= (double) this.maxSoul;
			this.soulLevel += 1;
			this.maxSoul = Math.max(1, this.soulLevel * 100);
			this.soulDamage += 1;

			if (!player.worldObj.isRemote)
			{
				this.player.addChatComponentMessage(new ChatComponentText("You have reached " + this.soulLevel + " level of of soul mastery!"));
			}
		}
		this.soulTag.setString("Owner", this.player.getDisplayName());

		// only want to update the timer and regen mana on the server:
		if (!player.worldObj.isRemote)
		{
			this.maxSoul = Math.max(1, this.soulLevel * 100);
			this.slagMax = Math.max(1, this.soulLevel * 100);

			if (this.deficitsoul <= 0 && updateSoulTimer())
			{
				regenSoul(1);

				if (this.getCurrentSoul() != this.getMaxSoul())
				{
					if (Minecraft.getSystemTime() % 100 == 1)
					{
						if (!this.player.worldObj.isRemote)
						{
							PlayerSyncMessage psm = new PlayerSyncMessage(this.player);
							AlchemicalDispatcher.sendToClient(psm, this.player);
						}
					}
				}
				int count = 0;
				for (int i = 0; i < 36; i++)
				{
					if (this.player.inventory.mainInventory[i] != null)
					{
						if (this.player.inventory.mainInventory[i].getItem() == ModItems.SoulSword)
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
						if (this.player.inventory.mainInventory[i] != null)
						{
							if (this.player.inventory.mainInventory[i].getItem() == ModItems.SoulSword)
							{
								slot = i;
							}
						}
					}
					ItemStack sword = this.player.inventory.mainInventory[slot];
					if (sword.getTagCompound() != null)
						this.soulTag = sword.getTagCompound();
				}
			}
			else
			{
				if (this.deficitsoul > 0)
				{
					if (this.deficitsoul <= this.getCurrentSoul())
					{
						this.consumeSoul(this.deficitsoul);
						this.deficitsoul = 0;
					}
					else
					{
						int excess = this.deficitsoul - this.getCurrentSoul();
						this.setCurrentSoul(0);
						this.deficitsoul = excess;
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
			if (this.player.isBlocking())
			{
				soulRegenTimer -= 6;
			}
			if (this.player.isSneaking())
			{
				soulRegenTimer -= 5;
			}
		}
		if (soulRegenTimer <= 0)
		{
			soulRegenTimer = getCurrentSoul() < getMaxSoul() ? 20 : 0;
			return true;
		}

		return false;
	}

	public final void regenSoul(int amount)
	{
		addSoul(amount);
	}

	/**
	 * Returns true if the amount of soul was consumed or false if the player's
	 * current soul was insufficient
	 */
	public final boolean consumeSoul(int amount)
	{
		int rem = (int) Math.round(amount - amount * ((double) this.soulLevel) / 100D);
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

	/**
	 * Sets max Soul to amount or 0 if amount is less than 0
	 */
	public final void setMaxSoul(int amount)
	{
		maxSoul = (amount > 0 ? amount : 0);
		// if your extended properties contains a lot of data, it would be
		// better
		// to make an individual packet for maxSoul, rather than sending all of
		// the data each time max Soul changes...

		AlchemicalDispatcher.sendToClient(new PlayerSyncMessage(player), (EntityPlayerMP) player);
	}

	public void addModif(float mod)
	{
		this.pretempmodif += mod;
	}

}