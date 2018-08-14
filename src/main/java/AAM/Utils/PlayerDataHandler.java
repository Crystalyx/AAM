package AAM.Utils;

import java.util.ArrayList;
import java.util.List;

import AAM.API.TraitModifier;
import AAM.Common.Items.ModItems;
import AAM.Common.Items.Soul.ModHammer;
import AAM.Common.Items.Soul.ModStaff;
import AAM.Common.Soul.Soul;
import AAM.Common.Soul.SoulUpgrade;
import AAM.Common.Soul.Trait;
import AAM.Common.Soul.TraitStack;
import AAM.Common.Soul.WarriorType;
import AAM.Common.Soul.WeaponType;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
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

public class PlayerDataHandler implements IExtendedEntityProperties
{
	public final static String ExtendedDataId = "AAoMPlayer";

	public final EntityPlayer player;

	// /** Custom inventory slots will be stored here - be sure to save to NBT!
	// */TODO
	// public final InventoryCustomPlayer inventory = new
	// InventoryCustomPlayer();

	public NBTTagCompound soulTag = new NBTTagCompound();

	public List<String> party = new ArrayList<String>();
	public String addMember = "";

	public TraitStack[] traits = new TraitStack[Trait.values().length];

	public boolean lastTickBlocked = false;

	public int soulRegenTimer = 0;
	public int soulxp = 0;

	public int blockDuration = 0;

	public int color = -1;
	public boolean bow = false;
	public Soul stype = Soul.Normal;
	public WeaponType sword = WeaponType.Broad;
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
		return (PlayerDataHandler) player.getExtendedProperties(ExtendedDataId);
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
			this.traits[i] = props.traits[i].copy();
		}

		this.setCurrentSoul(props.getCurrentSoul());
		this.soulRegenTimer = props.soulRegenTimer;
		this.soulTag = props.soulTag;
		this.bow = props.bow;
		this.color = props.color;
		this.stype = props.stype;
		this.sword = props.sword;
		this.warrior = props.warrior;
		this.soulxp = props.soulxp;
		this.upgLevel = props.upgLevel;
		this.party = props.party;
		this.addMember = props.addMember;
		this.arbitur = props.arbitur;
		this.art = props.art;
		this.blockDuration = props.blockDuration;

	}

	public void clearProperties(boolean construct)
	{
		this.soulTag = new NBTTagCompound();
		if (!construct)
		{
			this.soulTag.setString("Owner", this.player.getGameProfile().getName());
		}

		for (int i = 0; i < traits.length; i++)
		{
			traits[i] = new TraitStack(Trait.values()[i]);
		}
		this.setTraitBase(Trait.Level, 1);

		this.soulRegenTimer = 0;
		this.blockDuration = 0;
		// ========
		this.bow = false;
		this.color = -1;
		this.stype = Soul.Normal;
		this.sword = WeaponType.Broad;
		this.warrior = WarriorType.NotSelected;
		if (this.getPermission() > 0)
		{
			TraitModifier tmM = new TraitModifier("Permission", Trait.MeleeDamage, 7.0f, TraitModifier.Operation.add);
			TraitModifier tmR = new TraitModifier("Permission", Trait.RangedDamage, 7.0f, TraitModifier.Operation.add);
			this.applyModifier(tmM);
			this.applyModifier(tmR);
		}
		this.upgLevel = new int[SoulUpgrade.values().length];
		// =================
		this.soulxp = 0;
		this.party = new ArrayList<String>();
		this.addMember = "";
		this.arbitur = false;
		this.art = false;
		this.setTraitBase(Trait.MeleeDamage, this.sword.baseDamage);
		this.setTraitBase(Trait.RangedDamage, this.sword.baseDamage);
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
		for (int i = 0; i < traits.length; i++)
		{
			NBTTagCompound tg = new NBTTagCompound();
			traits[i].saveToNBT(tg);
			tagl.appendTag(tg);
		}

		tag.setTag("Traits", tagl);

		tag.setInteger("CurrentSoul", player.getDataWatcher().getWatchableObjectInt(SoulWatcherId));
		tag.setInteger("SoulCharge", this.getCurrentSoul());
		tag.setInteger("SoulRegenTimer", this.soulRegenTimer);
		tag.setInteger("SoulXp", this.soulxp);
		tag.setInteger("SoulType", this.stype.ordinal());

		tag.setTag("NBT", this.soulTag);
		tag.setInteger("PtSize", this.party.size());
		for (int i = 0; i < this.party.size(); i++)
		{
			tag.setString("Pt" + i, this.party.get(i));
		}

		tag.setBoolean("Bow", this.bow);
		tag.setInteger("PartType", this.color);
		tag.setString("Owner", this.player.getGameProfile().getName());
		tag.setInteger("SwordType", this.sword.ordinal());
		tag.setInteger("WarriorType", this.warrior.ordinal());

		for (int j = 0; j < upgLevel.length; j++)
		{
			tag.setInteger("Upg" + j, this.upgLevel[j]);
		}
		tag.setString("addMember", this.addMember);
		tag.setBoolean("Arbitur", this.arbitur);
		tag.setBoolean("Art", this.art);
		tag.setInteger("Cooldown", this.blockDuration);

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
		this.soulRegenTimer = tag.getInteger("SoulRegenTimer");

		this.soulxp = tag.getInteger("SoulXp");
		this.stype = Soul.values()[tag.getInteger("SoulType")];

		this.soulTag = tag.getCompoundTag("NBT");

		this.party.clear();
		for (int i = 0; i < tag.getInteger("PtSize"); i++)
		{
			this.party.add(tag.getString("Pt" + i));
		}
		this.bow = tag.getBoolean("Bow");
		this.color = tag.getInteger("PartType");
		this.sword = WeaponType.values()[tag.getInteger("SwordType")];
		this.warrior = WarriorType.values()[tag.getInteger("WarriorType")];

		for (int j = 0; j < upgLevel.length; j++)
		{
			this.upgLevel[j] = tag.getInteger("Upg" + j);
		}

		this.addMember = tag.getString("addMember");
		this.arbitur = tag.getBoolean("Arbitur");
		this.art = tag.getBoolean("Art");
		this.blockDuration = tag.getInteger("Cooldown");

		this.setTraitBase(Trait.MeleeDamage, this.sword.baseDamage);
		this.setTraitBase(Trait.RangedDamage, this.sword.baseDamage);
	}

	public float getTrait(Trait t)
	{
		return this.traits[t.ordinal()].countValue();
	}

	public void setTraitBase(Trait t, float base)
	{
		this.traits[t.ordinal()].base = base;
	}

	public void addTraitBase(Trait t, float base)
	{
		this.traits[t.ordinal()].base += base;
	}

	public float getTraitBase(Trait t)
	{
		return this.traits[t.ordinal()].base;
	}

	public void applyModifier(TraitModifier tm)
	{
		this.traits[tm.trait.ordinal()].applyModifier(tm);
	}

	public TraitModifier findModifier(Trait t, String id)
	{
		if (this.traits[t.ordinal()].modifiers != null)
		{
			for (TraitModifier tm : this.traits[t.ordinal()].modifiers)
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
		this.traits[tm.trait.ordinal()].removeModifier(tm);
	}

	public void removeModifier(Trait t, String id)
	{
		this.traits[t.ordinal()].removeModifier(id);
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
		if (player.getCurrentEquippedItem() != null
				&& (player.getCurrentEquippedItem().getItem() == ModItems.SoulSword || player.getCurrentEquippedItem().getItem() instanceof ModHammer || player.getCurrentEquippedItem().getItem() instanceof ModStaff) && player.isBlocking())
		{
			// Either you have a not hammer as weapon or you have 1
			// soulCharge
			if ((player.getCurrentEquippedItem().getItem() == ModItems.SoulSword && !this.sword.equals(WeaponType.Hammer)) || player.getCurrentEquippedItem().getItem() instanceof ModStaff
					|| (this.player.worldObj.getWorldTime() % 2 != 0 || this.consumeSoul(1)))
			{
				this.blockDuration += 1;
			}
			else
			{
				if (this.player.worldObj.getWorldTime() % 4 == 0)
					this.blockDuration--;
			}
		}
		else
			if (this.blockDuration > 0)
			{
				this.blockDuration--;
			}

		this.setTraitBase(Trait.MeleeDamage, this.sword.baseDamage);
		this.setTraitBase(Trait.RangedDamage, this.sword.baseDamage);
		// checking levelup (maxSoul is equal to needed xp)
		if (this.soulxp >= this.getTrait(Trait.Soul) && this.soulxp != 0)
		{
			this.soulxp -= this.getTrait(Trait.Soul);
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
				this.player.addChatComponentMessage(new ChatComponentText("You have reached " + ((int) this.getTraitBase(Trait.Level)) + " level of soul mastery!"));
			}
		}

		// updating owner tag
		this.soulTag.setString("Owner", this.player.getDisplayName());

		// only want to update the timer and regen soul on the server:
		if (!player.worldObj.isRemote)
		{
			if (updateSoulTimer())
			{
				addSoul(1);

				// syncronizing once in a minute
				if (Minecraft.getMinecraft().theWorld.getWorldTime() % 1200 == 1)
				{
					if (!this.player.worldObj.isRemote)
					{
						PlayerSyncMessage psm = new PlayerSyncMessage(this.player);
						AlchemicalDispatcher.sendToClient(psm, this.player);
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
		if (getCurrentSoul() > this.getTrait(Trait.Soul))
		{
			setCurrentSoul((int) this.getTrait(Trait.Soul));
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
			if (l > 1)
			{
				soulRegenTimer -= 4;
			}
			if (this.upgLevel[SoulUpgrade.Recharge.ordinal()] > 0)
			{
				int rechargelevel = this.upgLevel[SoulUpgrade.Recharge.ordinal()];
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
		return (int) Math.round(amount - amount * (this.getTrait(Trait.Level)) / 100D);
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
			setCurrentSoul(getCurrentSoul() - rem);
		return sufficient;
	}

	/**
	 * Simple method sets current Soul to max Soul
	 */
	public final void replenishSoul()
	{
		this.player.getDataWatcher().updateObject(SoulWatcherId, (int) this.getTrait(Trait.Soul));
	}

	/**
	 * Returns current Soul amount
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
	 * Sets current Soul to amount or maxSoul, whichever is lesser
	 */
	public final void setCurrentSoul(int amount)
	{
		player.getDataWatcher().updateObject(SoulWatcherId, amount > 0 ? (amount < this.getTrait(Trait.Soul) ? amount : (int) this.getTrait(Trait.Soul)) : 0);
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

	/**
	 * @param w
	 * @return functional Itemstack for soul sword
	 */
	public ItemStack getSwordStack()
	{
		int meta = this.sword.ordinal();
		this.soulTag.setString("Owner", this.player.getGameProfile().getName());
		ItemStack sword = new ItemStack(ModItems.SoulSword, 1, meta);
		sword.setTagCompound(this.soulTag);
		sword.getTagCompound().setInteger("UpgradeLevel", (int) this.getTrait(Trait.WeaponLevel));
		return sword.copy();
	}

	public float getFullMeleeDamage(boolean inAttack)
	{
		float m = (float) (this.sword.equals(WeaponType.Hammer) ? 1 + 1 / (Math.exp(-(this.blockDuration - 160) / 10) + 1) : 1);
		return (this.stype.getMeleeDamage(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), inAttack) + getUpgMeleeDamage()) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	public float getFullMeleeDamageAgainst(EntityLivingBase l, boolean inAttack)
	{
		float m = (float) (this.sword.equals(WeaponType.Hammer) ? 1 + 1 / (Math.exp(-(this.blockDuration - 160) / 10) + 1) : 1);
		return (this.stype.getFullMeleeDamage(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), inAttack) + getUpgMeleeDamageAgainst(l)) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	public float getFullRangedDamage(boolean inAttack)
	{
		float m = (float) (this.sword.equals(WeaponType.Hammer) ? 1 + 1 / (Math.exp(-(this.blockDuration - 160) / 10) + 1) : 1);
		return (this.stype.getRangedDamage(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), inAttack) + getUpgRangedDamage()) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	public float getFullRangedDamageAgainst(EntityLivingBase l, boolean inAttack)
	{
		float m = (float) (this.sword.equals(WeaponType.Hammer) ? 1 + 1 / (Math.exp(-(this.blockDuration - 160) / 10) + 1) : 1);
		return (this.stype.getFullRangedDamage(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), inAttack) + getUpgRangedDamageAgainst(l)) * (this.getTrait(Trait.WeaponLevel) * 0.05f + 1) * m;
	}

	/**
	 * @return sum of all passive melee upgrade bonuses
	 */
	public float getUpgMeleeDamage()
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getMeleeDamage(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), true);
		}
		return upgdam;
	}

	/**
	 * @return sum of all passive and active melee upgrade bonuses
	 */
	public float getUpgMeleeDamageAgainst(EntityLivingBase l)
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getMeleeDamage(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage), true)
					+ SoulUpgrade.values()[i].getSpecificMeleeDamage(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.MeleeDamage));
		}
		return upgdam;
	}

	/**
	 * @return sum of all passive ranged upgrade bonuses
	 */
	public float getUpgRangedDamage()
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getRangedDamage(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), true);
		}
		return upgdam;
	}

	/**
	 * @return sum of all passive and active ranged upgrade bonuses
	 */
	public float getUpgRangedDamageAgainst(EntityLivingBase l)
	{
		float upgdam = 0;
		for (int i = 0; i < this.upgLevel.length; i++)
		{
			upgdam += SoulUpgrade.values()[i].getRangedDamage(this, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage), true)
					+ SoulUpgrade.values()[i].getSpecificRangedDamage(this, l, (int) this.getTrait(Trait.Level), this.getTrait(Trait.RangedDamage));
		}
		return upgdam;
	}

	/**
	 * @return if bow>0 then player can use ranged attacks
	 */
	public int getBowIndex()
	{
		int bow = 0;
		bow += MiscUtils.boolToNum(this.bow, 1, 0);
		bow += MiscUtils.boolToNum(this.sword.warrior.equals(WarriorType.Caster), 1, 0);
		return bow;
	}

	/**
	 * @return time (in ticks) needed to fully charge staff or crystal bow
	 */
	public int getBowMaxCooldown()
	{
		return 8 + ((int) (this.getTrait(Trait.Cooldown) * (7 - this.upgLevel[SoulUpgrade.Cast.ordinal()])) / (int) this.getTrait(Trait.Level));
	}

}