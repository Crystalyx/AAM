package aam.common.event;

import aam.api.GameWeapon;
import aam.api.interfaces.IExtendedReach;
import aam.api.interfaces.IUpgradableItem;
import aam.api.abstraction.MeleeWeapon;
import aam.api.abstraction.RangedWeapon;
import aam.common.blocks.building.ModBlocks;
import aam.common.entity.SoulCharge;
import aam.common.entity.StaffCharge;
import aam.common.items.ModItems;
import aam.common.items.artifacts.LuckyCoin;
import aam.common.items.soul.SoulSword;
import aam.common.soul.*;
import aam.common.transmutations.EnergyProvider;
import aam.common.weapon.WeaponManager;
import aam.common.weapon.WeaponUpgrade;
import aam.core.AAMConfig;
import aam.core.AAMCore;
import aam.network.ClientProxy;
import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.MessageExtendedReachAttack;
import aam.network.packages.PlayerSyncMessage;
import aam.utils.InventoryUtils;
import aam.utils.Logger;
import aam.utils.MathUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Vec2;
import aam.utils.vectors.VectorWorld;
import aam.utils.vectors.Wec3;
import baubles.api.BaublesApi;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SoulEvent
{

	public static AttributeModifier am = new AttributeModifier(UUID.randomUUID(), "aam_watery_art", 1f, 2);

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void entityUpdate(LivingUpdateEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;
			PlayerDataHandler ph = PlayerDataHandler.get(p);
			ItemStack is = p.getCurrentEquippedItem();
			if (ClientProxy.soul.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
			{
				callSwordRecreation(ph);
			}

			if (ph.stype.equals(Soul.Watery))
			{
				VectorWorld vw = new VectorWorld(p.worldObj);
				Wec3 ps = new Wec3(p).add(new Wec3(ForgeDirection.DOWN));
				if (vw.getBlock(ps).getMaterial() == Material.water || vw.getBlock(ps).getMaterial() == Material.lava)
				{
					if (p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).func_111122_c().contains(am))
					{
						p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(am);
					}
				}
				else
				{
					p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(am);
				}

			}

			if (ClientProxy.member.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
			{
				AAMCore.proxy.addMember();
			}
			if (Minecraft.getMinecraft().objectMouseOver != null)
			{
				if (FMLClientHandler.instance().getClient().inGameHasFocus && Minecraft.getMinecraft().objectMouseOver.typeOfHit == MovingObjectType.MISS && !p.isBlocking() && ph.lastTickBlocked)
				{
					if (is != null)
					{
						if (is.getItem() instanceof SoulSword)
						{
							if (ph.getBowIndex() > 0 && MathUtils.randWPercent(40 + ph.upgLevel[SoulUpgrade.Cast.ordinal()] * 5 + 60))
							{
								if (!p.worldObj.isRemote && ph.blockDuration >= ph.getBowMaxCooldown() - 2)
								{
									if (ph.consumeSoul((int) ph.getTrait(Trait.Level)))
									{
										SoulCharge s = new SoulCharge(p.worldObj, p);
										s.setLife(500);
										Wec3 look = new Wec3(p.getLookVec()).mult(2);
										look.ptm(s);
										p.worldObj.spawnEntityInWorld(s);
									}
								}
							}
						}
						if (is.getItem() instanceof RangedWeapon)
						{
							if (!p.worldObj.isRemote && ph.blockDuration >= WeaponManager.getRangedCooldown(is))
							{
								if (ph.consumeSoul(WeaponManager.getRangedSoulConsumed(is)))
								{
									StaffCharge s = new StaffCharge(p.worldObj, p, WeaponManager.getRangedDamage(is) * (1 + WeaponManager.getUpgradeLevel(is) * 0.05f));
									s.setLife(500);
									Wec3 look = new Wec3(p.getLookVec()).mult(2);
									look.ptm(s);
									p.worldObj.spawnEntityInWorld(s);
								}
							}
						}
					}
				}
			}
			ph.lastTickBlocked = p.isBlocking();
		}
	}

	public static void callSwordRecreation(PlayerDataHandler ph)
	{
		EntityPlayer p = ph.player;
		int soul = 0;

		for (int i = 0; i < p.inventory.getSizeInventory(); i++)
		{
			if (p.inventory.getStackInSlot(i) != null)
			{
				if (p.inventory.getStackInSlot(i).getItem() == ModItems.SoulSword)
				{
					if (p.inventory.getStackInSlot(i).hasTagCompound())
					{
						if (p.inventory.getStackInSlot(i).getTagCompound().getString("Owner").equals(p.getGameProfile().getName()))
						{
							soul += 15;
							p.inventory.setInventorySlotContents(i, null);
						}
					}
				}
			}
		}
		if (!p.isSneaking())
		{
			if (soul + ph.getCurrentSoul() >= 15)
			{
				p.inventory.addItemStackToInventory(ph.getSwordStack());
			}
			ph.addSoul(soul - 15);
		}
		else
		{
			ph.addSoul(soul);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void tooltip(ItemTooltipEvent e)
	{
		if (e.itemStack.getItem() == ModItems.SoulSword)
		{
			e.toolTip.set(0, EnumChatFormatting.AQUA + e.toolTip.get(0));
			e.toolTip.remove(e.toolTip.size() - 1);

			if (e.itemStack.hasTagCompound())
			{
				String name = e.itemStack.getTagCompound().getString("Owner");
				if (!name.equals(""))
				{
					PlayerDataHandler ph = PlayerDataHandler.get(e.entityPlayer.worldObj.getPlayerEntityByName(name));

					e.toolTip.add(EnumChatFormatting.DARK_AQUA + "Owner: " + name);

					e.toolTip.add(EnumChatFormatting.BLUE + "+" + ph.getFullMeleeDamage(false) + "" + EnumChatFormatting.DARK_PURPLE + " soul Damage");
					if (ph.getBowIndex() > 0)
					{
						e.toolTip.add(EnumChatFormatting.BLUE + "+" + ph.getFullRangedDamage(false) + "" + EnumChatFormatting.DARK_PURPLE + " Ranged soul Damage");
					}

					if (ph.sword.equals(SoulWeaponType.Spear))
					{
						e.toolTip.add(EnumChatFormatting.BLUE + "+4 Reach Distance");
					}
					if (ph.art)
					{
						e.toolTip.add("Additional:");
						ArtifactTooltips.addToTooltip(ph.stype, ph.player, (int) ph.getTrait(Trait.Level), e.toolTip);
					}

				}
				else
				{
					e.toolTip.add(EnumChatFormatting.BLUE + "+" + 5 + "" + EnumChatFormatting.DARK_PURPLE + " soul Damage");
				}
			}
			else
			{
				e.toolTip.add(EnumChatFormatting.BLUE + "+" + 5 + "" + EnumChatFormatting.DARK_PURPLE + " soul Damage");
			}
		}

		if (e.itemStack.getItem() instanceof GameWeapon)
		{
			e.toolTip.remove(e.toolTip.size() - 1);
			if (e.itemStack.hasTagCompound())
			{
				if (((IUpgradableItem) e.itemStack.getItem()).getDurability(e.itemStack) > 0)
				{
					if (WeaponManager.isBroken(e.itemStack))
					{
						e.toolTip.add("Broken");
					}
					else
					{
						e.toolTip.add("Durability: " + (WeaponManager.getMaxDamagePoints(e.itemStack) - WeaponManager.getDamagePoints(e.itemStack)) + "/" + WeaponManager.getMaxDamagePoints(e.itemStack));
					}
				}
			}
		}

		if (e.itemStack.getItem() instanceof MeleeWeapon)

		{
			String name = e.toolTip.get(0);
			float dmg = WeaponManager.getWeaponMeleeDamage(e.entityPlayer, e.itemStack);
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MathUtils.roundStr(dmg, 1) + " Attack Damage");
			float reach = WeaponManager.getReachValue(e.itemStack, e.entityPlayer);
			if (reach > 4)
			{
				e.toolTip.add(EnumChatFormatting.BLUE + "+" + (reach - 4) + " Reach Distance");

			}
		}

		if (e.itemStack.getItem() instanceof RangedWeapon)
		{
			String name = e.toolTip.get(0);
			float dmgM = ((RangedWeapon) e.itemStack.getItem()).getMeleeDamage(e.itemStack) * (1 + ((RangedWeapon) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			float dmgR = ((RangedWeapon) e.itemStack.getItem()).getRangedDamage(e.itemStack) * (1 + ((RangedWeapon) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MathUtils.roundStr(dmgM, 1) + " Attack Damage");
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MathUtils.roundStr(dmgR, 1) + " Ranged Damage");
		}

		if (e.itemStack.getItem() == ModItems.Artifact)
		{

			PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

			int meta = e.itemStack.getItemDamage();
			ArtifactTooltips.addToTooltip(Soul.values()[meta], ph.player, (int) ph.getTrait(Trait.Level), e.toolTip);
		}

		if (e.itemStack.getItem() instanceof IUpgradableItem)
		{
			if (((IUpgradableItem) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) > 0)
			{
				if (e.toolTip.get(0).contains("("))
				{
					e.toolTip.set(0, e.toolTip.get(0).replace("(", "(+" + ((IUpgradableItem) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) + ") ("));
				}
				else
				{
					e.toolTip.set(0, e.toolTip.get(0) + " (+" + ((IUpgradableItem) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) + ")");
				}
			}

		}

		if (EnergyProvider.hasValue(e.itemStack))
		{
			e.toolTip.add(EnumChatFormatting.GRAY + "Energy Type: " + EnergyProvider.getType(e.itemStack));
			e.toolTip.add(EnumChatFormatting.GRAY + "Energy: " + MathUtils.roundStr(EnergyProvider.getValue(e.itemStack), 1));
			if (e.itemStack.stackSize > 1)
			{
				e.toolTip.add(EnumChatFormatting.GRAY + "Stack Energy: " + MathUtils.roundStr(EnergyProvider.getValue(e.itemStack) * e.itemStack.stackSize, 1));
			}

		}
		if (EnergyProvider.getStoredEnergy(e.itemStack) > 0)
		{
			e.toolTip.add(EnumChatFormatting.GRAY + "Stored Energy: " + MathUtils.roundStr(EnergyProvider.getStoredEnergy(e.itemStack), 1));
		}
	}

	@SubscribeEvent
	public void attacked(AttackEntityEvent e)
	{
		EntityPlayer ep = e.entityPlayer;
		if (ep == null)
		{
			ep = e.entityPlayer;
		}
		PlayerDataHandler ph = PlayerDataHandler.get(ep);
		if (e.entityPlayer.getCurrentEquippedItem() != null)
		{
			if (e.entityPlayer.getCurrentEquippedItem().getItem() == ModItems.SoulSword && e.entityPlayer.getCurrentEquippedItem().hasTagCompound())
			{
				ItemStack is = e.entityPlayer.getCurrentEquippedItem();
				String name = is.getTagCompound().getString("Owner");
				EntityPlayer epI = e.entityPlayer.worldObj.getPlayerEntityByName(name);
				if (ep == null)
				{
					ep = e.entityPlayer;
				}
				PlayerDataHandler phI = PlayerDataHandler.get(ep);
				if (!e.target.isDead && e.target.canAttackWithItem() && !e.target.isEntityInvulnerable())
				{
					if (is.hasTagCompound())
					{
						SoulDamageSource src = new SoulDamageSource(phI);
						if (phI.sword.bypassesArmor)
						{
							src.setDamageBypassesArmor();
						}
						if (e.target instanceof EntityLivingBase)
						{
							EntityLivingBase l = (EntityLivingBase) e.target;

							if (phI.upgLevel[SoulUpgrade.Blood.ordinal()] != 0)
							{

								double f = Math.rint(l.getMaxHealth() * 100) / 100;

								float regen = (float) (f * 0.1F * phI.upgLevel[SoulUpgrade.Blood.ordinal()]);
								ep.heal(regen);
							}

							float dmg = phI.getFullMeleeDamageAgainst(l, true);
							e.target.attackEntityFrom(src, dmg);
							for (int i = 0; i < phI.upgLevel.length; i++)
							{
								if (phI.upgLevel[i] > 0)
									SoulUpgrade.values()[i].onAttack(ep, l, dmg);
							}
						}
						else
						{
							e.target.attackEntityFrom(src, 5.0F);
						}
					}
					Random r = new Random();
					if (r.nextDouble() < 0.25)
					{
						if (e.target.isDead)
						{
							if (!e.entityPlayer.worldObj.isRemote)
							{
								EntityItem et = new EntityItem(e.entityPlayer.worldObj, e.entityPlayer.posX, e.entityPlayer.posY, e.entityPlayer.posZ, new ItemStack(ModItems.materials, 1, 9));
								et.setVelocity(r.nextDouble() * 0.6, r.nextDouble() * 0.6, r.nextDouble() * 0.6);
								e.entityPlayer.worldObj.spawnEntityInWorld(et);
								et.onCollideWithPlayer(e.entityPlayer);
							}
						}
					}
				}
			}

			if (e.target instanceof EntityLivingBase)
			{
				EntityLivingBase l = (EntityLivingBase) e.target;
				if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof IUpgradableItem)
				{
					ItemStack weaponStack = e.entityPlayer.getCurrentEquippedItem();
					WeaponManager.setCompounds(weaponStack);
					if (((IUpgradableItem) weaponStack.getItem()).getDurability(weaponStack) > 0)
					{
						if (WeaponManager.isBroken(weaponStack))
						{
							e.setCanceled(true);
							return;
						}
						else
						{
							if (!l.isDead)
							{
								WeaponManager.addDamagePoints(weaponStack, 1);
							}
							if (WeaponManager.getDamagePoints(weaponStack) > WeaponManager.getMaxDamagePoints(weaponStack))
							{
								if (WeaponManager.getRepairs(weaponStack) < WeaponManager.getMaxRepairs(weaponStack))
								{
									WeaponManager.setBroken(weaponStack, true);
								}
								else
								{
									ep.destroyCurrentEquippedItem();
								}
							}
						}
					}

					float dmg = WeaponManager.getWeaponMeleeDamage(ep, weaponStack);
					dmg += WeaponManager.getSpecificWeaponMeleeDamage(ep, l, weaponStack);
					float baseDmg = dmg;
					for (int i = 0; i < WeaponManager.getSlotCount(weaponStack); i++)
					{
						WeaponUpgrade wu = WeaponManager.getUpgradeAt(weaponStack, i);
						if (wu != null)
						{
							dmg += WeaponManager.getUpgradeAt(weaponStack, i).getMeleeDamageBonus(ph, 0, baseDmg, true) + WeaponManager.getUpgradeAt(weaponStack, i).getSpecificMeleeDamageBonus(ph, l, 0, baseDmg);
						}
					}
					if (weaponStack.getItem() instanceof GameWeapon)
					{
						((GameWeapon) weaponStack.getItem()).onAttack(ep, l, dmg);
						((GameWeapon) weaponStack.getItem()).applySpecificPotionEffects(ph, l, ((GameWeapon) weaponStack.getItem()).getUpgradeLevel(weaponStack), baseDmg);
					}
					for (int i = 0; i < WeaponManager.getSlotCount(weaponStack); i++)
					{
						if (WeaponManager.getUpgradeAt(weaponStack, i) != null)
						{
							WeaponManager.getUpgradeAt(weaponStack, i).onAttack(ep, l, dmg);
							WeaponManager.getUpgradeAt(weaponStack, i).applySpecificPotionEffects(ph, l, 0, baseDmg);
						}
					}
				}
				attackEntityFrom(e.entityPlayer.getCurrentEquippedItem(), ph, l);

			}

			int mod = 1;
			if (BaublesApi.getBaubles(e.entityPlayer).getStackInSlot(0) != null)
			{
				if (BaublesApi.getBaubles(e.entityPlayer).getStackInSlot(0).getItem() instanceof LuckyCoin)
				{
					mod += 2;
				}

			}
			if (MathUtils.randWPercent(75d))
			{
				boolean drop = MathUtils.randWPercent(100 * mod / 50d);
				if (drop)
				{
					InventoryUtils.dropStack(e.entityPlayer.worldObj, new Wec3(e.entityPlayer), new ItemStack(ModItems.coins, 1, 2));
				}
				else
				{
					drop = MathUtils.randWPercent(100 * mod / 15d);
					if (drop)
					{
						InventoryUtils.dropStack(e.entityPlayer.worldObj, new Wec3(e.entityPlayer), new ItemStack(ModItems.coins, 1, 1));
					}
					else
					{
						drop = MathUtils.randWPercent(100 * mod / 5d);
						if (drop)
						{
							InventoryUtils.dropStack(e.entityPlayer.worldObj, new Wec3(e.entityPlayer), new ItemStack(ModItems.coins, mod, 0));
						}
					}
				}
			}
		}
		int bronze = InventoryUtils.count(ep.inventory, ModItems.coins, 0);
		Logger.info(bronze);
		int dSilver = 0;
		if (bronze >= 100)
		{
			dSilver = Math.floorDiv(bronze, 100);
			int leftToRemove = dSilver * 100;
			List<Integer> lbr = InventoryUtils.getList(ep.inventory, ModItems.coins, 0);
			for (int i = 0; leftToRemove > 0 && i < lbr.size(); i++)
			{
				ItemStack bris = ep.inventory.getStackInSlot(lbr.get(i));
				if (bris.stackSize <= leftToRemove)
				{
					leftToRemove -= bris.stackSize;
					ep.inventory.setInventorySlotContents(lbr.get(i), null);
				}
				else
				{
					bris.stackSize -= leftToRemove;
					leftToRemove = 0;
				}
			}
		}
		InventoryUtils.dropStack(ep.worldObj, new Wec3(ep), new ItemStack(ModItems.coins, dSilver, 1));
		int silver = InventoryUtils.count(ep.inventory, ModItems.coins, 1);
		int dGold = 0;
		if (silver >= 100)
		{
			dGold = Math.floorDiv(silver, 100);
			int leftToRemove = dGold * 100;
			List<Integer> lbr = InventoryUtils.getList(ep.inventory, ModItems.coins, 1);
			for (int i = 0; leftToRemove > 0 && i < lbr.size(); i++)
			{
				ItemStack bris = ep.inventory.getStackInSlot(lbr.get(i));
				if (bris.stackSize <= leftToRemove)
				{
					leftToRemove -= bris.stackSize;
					ep.inventory.setInventorySlotContents(lbr.get(i), null);
				}
				else
				{
					bris.stackSize -= leftToRemove;
					leftToRemove = 0;
				}
			}
		}
		InventoryUtils.dropStack(ep.worldObj, new Wec3(ep), new ItemStack(ModItems.coins, dGold, 2));
	}

	private float attackEntityFrom(ItemStack item, PlayerDataHandler ph, EntityLivingBase l)
	{
		float dmg = 0;
		double knock = 0;
		SoulDamageSource src = new SoulDamageSource(ph);
		if (item != null)
		{
			if (item.getItem() instanceof MeleeWeapon)
			{
				MeleeWeapon sw = (MeleeWeapon) item.getItem();
				if (sw.getBypassesArmor(item))
				{
					src.setDamageBypassesArmor();
				}
				dmg = WeaponManager.getWeaponMeleeDamage(ph.player, item);
				knock = sw.knockback;
			}
			else
				if (item.getItem() instanceof RangedWeapon)
				{
					RangedWeapon sw = (RangedWeapon) item.getItem();
					if (sw.getBypassesArmor(item))
					{
						src.setDamageBypassesArmor();
					}
					dmg = sw.getMeleeDamage(item) * (1 + sw.getUpgradeLevel(item) * 0.05f);
					knock = sw.knockback;
				}
				else
					if (item.getItem() instanceof GameWeapon)
					{
						GameWeapon sw = (GameWeapon) item.getItem();
						if (sw.getBypassesArmor(item))
						{
							src.setDamageBypassesArmor();
						}
						dmg = sw.getBaseDamage(item) * (1 + sw.getUpgradeLevel(item) * 0.05f);
						knock = sw.knockback;
					}
			l.attackEntityFrom(src, dmg);
			if (knock != 0)
			{
				Vec2 a = new Vec2(ph.player.posX, ph.player.posY);
				Vec2 b = new Vec2(l.posX, l.posY);
				Vec2 ba = b.sub(a).multiply(-1 * knock);
				ba = ba.divide(ba.length() * ba.length() + 1E-200);
				l.knockBack(ph.player, 0.0f, ba.x, ba.y);
			}
			return dmg;
		}
		return 0;
	}

	@SubscribeEvent
	public void onPlayerDeath(LivingHurtEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;
			if (e.ammount >= p.getHealth())
			{
				if (InventoryUtils.contains(p.inventory, ModItems.RessurectionStone, 0))
				{
					List<Integer> fx = InventoryUtils.getList(p.inventory, ModItems.RessurectionStone, 0);
					for (int i = 0; i < fx.size(); i++)
					{
						p.inventory.getStackInSlot(fx.get(i)).setItemDamage(p.dimension == AAMConfig.dungDimId ? Math.max(12000 / fx.size(), 4500) : Math.max(6000 / fx.size(), 1500));
					}
					p.heal(p.getMaxHealth());
					e.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void entityKilled(LivingDeathEvent e)
	{
		if (e.source.damageType == SoulDamageSource.id)
		{
			PlayerDataHandler ph = ((SoulDamageSource) e.source).ph;
			int kp = (int) (e.entityLiving.getMaxHealth() - e.entityLiving.getMaxHealth() / (ph.getTrait(Trait.Level) + 1));
			if (ph.player.getCurrentEquippedItem() == null || ph.player.getCurrentEquippedItem().getItem() != ModItems.SoulSword)
			{
				ph.soulxp += kp / 4;
			}
			else
			{
				ph.soulxp += kp;
			}
			if (!e.entityLiving.worldObj.isRemote)
			{
				PlayerSyncMessage psm = new PlayerSyncMessage(ph.player);
				AlchemicalDispatcher.sendToClient(psm, ph.player);
			}
		}
	}

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		World world = event.world;
		MovingObjectPosition pos = event.target;
		Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
		int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

		if (block == ModBlocks.BloodBlock && meta == 0)
		{
			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			event.result = new ItemStack(ModItems.BloodBucket, 1, 0);
			event.setResult(Result.ALLOW);
		}

	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void enderTeleport(EnderTeleportEvent event)
	{
		World w = event.entityLiving.worldObj;
		List<EntityPlayer> l = w.getEntitiesWithinAABB(EntityPlayer.class, new Wec3(event.entityLiving).extendBoth(16, 16, 16));
		if (!l.isEmpty())
		{
			for (EntityPlayer p : l)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				if (ph.stype.onEnderTeleport(p, event.entityLiving))
					event.setCanceled(true);
				for (int i = 0; i < ph.upgLevel.length; i++)
				{
					if (ph.upgLevel[i] > 0 && SoulUpgrade.values()[i].onEnderTeleport(p, event.entityLiving))
					{
						event.setCanceled(true);
					}
				}
				ItemStack is = p.getCurrentEquippedItem();
				if (is != null)
				{
					if (is.getItem() instanceof GameWeapon)
					{
						if (((GameWeapon) is.getItem()).onEnderTeleport(p, event.entityLiving))
							event.setCanceled(true);

						for (int i = 0; i < WeaponManager.getSlotCount(is); i++)
						{
							WeaponUpgrade wu = WeaponManager.getUpgradeAt(is, i);
							if (wu != null)
							{
								if (wu.onEnderTeleport(p, event.entityLiving))
								{
									event.setCanceled(true);
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void itemDestroy(PlayerDestroyItemEvent e)
	{
		ItemStack i = e.original;
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				WeaponManager.setCompounds(i);
				if (i.getTagCompound().getInteger("Repairs") < 2)
				{
					i.getTagCompound().setBoolean("Broken", true);
					e.setCanceled(true);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(MouseEvent event)
	{
		if (event.button == 0 && event.buttonstate)
		{
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer p = mc.thePlayer;
			if (p != null)
			{
				ItemStack itemstack = p.getCurrentEquippedItem();
				IExtendedReach ieri;
				if (itemstack != null)
				{
					if (itemstack.getItem() instanceof IExtendedReach)
					{
						ieri = (IExtendedReach) itemstack.getItem();
					}
					else
					{
						ieri = null;
					}

					if (ieri != null)
					{
						float reach = ieri.getReachValue(p, p.getCurrentEquippedItem());
						MovingObjectPosition mov = getMouseOverExtended(reach);

						if (mov != null)
						{
							if (mov.entityHit != null && mov.entityHit.hurtResistantTime == 0)
							{
								if (mov.entityHit != p)
								{
									AlchemicalDispatcher.sendToServer(new MessageExtendedReachAttack(mov.entityHit.getEntityId()));
								}
							}
						}
					}
				}
			}
		}
	}

	// This is mostly copied from the EntityRenderer#getMouseOver() method
	public static MovingObjectPosition getMouseOverExtended(float dist)
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityLivingBase renderViewEntity = mc.renderViewEntity;
		AxisAlignedBB theViewBoundingBox = AxisAlignedBB.getBoundingBox(renderViewEntity.posX - 0.5D, renderViewEntity.posY - 0.0D, renderViewEntity.posZ - 0.5D, renderViewEntity.posX + 0.5D, renderViewEntity.posY + 1.5D,
				renderViewEntity.posZ + 0.5D);
		MovingObjectPosition returnMOP = null;
		if (mc.theWorld != null)
		{
			double var2 = dist;
			returnMOP = renderViewEntity.rayTrace(var2, 0);
			double calcdist = var2;

			Vec3 pos = Wec3.getWorldPos(renderViewEntity).add(new Wec3(0, renderViewEntity.getEyeHeight(), 0)).toVec();
			var2 = calcdist;
			if (returnMOP != null)
			{
				calcdist = returnMOP.hitVec.distanceTo(pos);
			}

			Vec3 lookvec = renderViewEntity.getLook(0);
			Vec3 var8 = pos.addVector(lookvec.xCoord * var2, lookvec.yCoord * var2, lookvec.zCoord * var2);
			Entity pointedEntity = null;
			float var9 = 1.0F;
			@SuppressWarnings("unchecked")
			List<Entity> list = mc.theWorld.getEntitiesWithinAABBExcludingEntity(renderViewEntity, theViewBoundingBox.addCoord(lookvec.xCoord * var2, lookvec.yCoord * var2, lookvec.zCoord * var2).expand(var9, var9, var9));
			double d = calcdist;

			for (Entity entity : list)
			{
				if (entity.canBeCollidedWith())
				{
					float bordersize = entity.getCollisionBorderSize();
					AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(entity.posX - entity.width / 2, entity.posY, entity.posZ - entity.width / 2, entity.posX + entity.width / 2, entity.posY + entity.height, entity.posZ + entity.width / 2);
					aabb.expand(bordersize, bordersize, bordersize);
					MovingObjectPosition mop0 = aabb.calculateIntercept(pos, var8);

					if (aabb.isVecInside(pos))
					{
						if (0.0D < d || d == 0.0D)
						{
							pointedEntity = entity;
							d = 0.0D;
						}
					}
					else
						if (mop0 != null)
						{
							double d1 = pos.distanceTo(mop0.hitVec);

							if (d1 < d || d == 0.0D)
							{
								pointedEntity = entity;
								d = d1;
							}
						}
				}
			}

			if (pointedEntity != null && (d < calcdist || returnMOP == null))
			{
				returnMOP = new MovingObjectPosition(pointedEntity);
			}
		}
		return returnMOP;
	}

}
