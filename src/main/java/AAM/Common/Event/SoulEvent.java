package AAM.Common.Event;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import AAM.API.Interface.IExtendedReach;
import AAM.API.Interface.IUpgradableItem;
import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Entity.SoulCharge;
import AAM.Common.Entity.StaffCharge;
import AAM.Common.Items.ModItems;
import AAM.Common.Items.Artifacts.LuckyCoin;
import AAM.Common.Items.Soul.ModAxe;
import AAM.Common.Items.Soul.ModHammer;
import AAM.Common.Items.Soul.ModSpear;
import AAM.Common.Items.Soul.ModStaff;
import AAM.Common.Items.Soul.ModSword;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Common.Soul.ArtifactTooltips;
import AAM.Common.Soul.Soul;
import AAM.Common.Soul.SoulDamageSource;
import AAM.Common.Soul.SoulUpgrade;
import AAM.Common.Soul.Trait;
import AAM.Common.Soul.WeaponType;
import AAM.Common.Transmutations.EnergyProvider;
import AAM.Core.AAMConfig;
import AAM.Core.AAMCore;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.MessageExtendedReachAttack;
import AAM.Network.Packages.PlayerSyncMessage;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.VectorWorld;
import AAM.Utils.Wec3;
import DummyCore.Utils.EnumRarityColor;
import baubles.api.BaublesApi;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
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

public class SoulEvent
{

	public static AttributeModifier am = new AttributeModifier(UUID.randomUUID(), "aam_watery_art", 0.35f, 2);

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void entityUpdate(LivingUpdateEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;
			PlayerDataHandler ph = PlayerDataHandler.get(p);

			if (AAMCore.soul.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
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
						p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(am);
				}
				else
					p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(am);

			}

			if (AAMCore.member.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
			{
				AAMCore.proxy.addMember();
			}
			if (Minecraft.getMinecraft().objectMouseOver != null)
			{
				if (FMLClientHandler.instance().getClient().inGameHasFocus && Minecraft.getMinecraft().objectMouseOver.typeOfHit == MovingObjectType.MISS && !p.isBlocking() && ph.lastTickBlocked)
				{
					if (p.getCurrentEquippedItem() != null)
					{
						if (p.getCurrentEquippedItem().getItem() instanceof SoulSword)
						{
							if (ph.getBowIndex() > 0 && MiscUtils.randWPercent(40 + ph.upgLevel[SoulUpgrade.Cast.ordinal()] * 5 + 60))
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
						if (p.getCurrentEquippedItem().getItem() instanceof ModStaff)
						{
							if (!p.worldObj.isRemote && ph.blockDuration >= ((ModStaff) p.getCurrentEquippedItem().getItem()).cd)
							{
								if (ph.consumeSoul(((ModStaff) p.getCurrentEquippedItem().getItem()).soulConsumed))
								{
									StaffCharge s = new StaffCharge(p.worldObj, p,
											((ModStaff) p.getCurrentEquippedItem().getItem()).rangedDmg * (1 + ((ModStaff) p.getCurrentEquippedItem().getItem()).getUpgradeLevel(p.getCurrentEquippedItem()) * 0.05f));
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
				p.inventory.addItemStackToInventory(ph.getSwordStack());
			ph.addSoul(soul - 15);
		}
		else
		{
			ph.addSoul(soul);
		}
	}

	@SubscribeEvent
	public void tooltip(ItemTooltipEvent e)
	{
		if (e.itemStack.getItem() == ModItems.SoulSword)
		{
			e.toolTip.set(0, EnumRarityColor.EXCEPTIONAL.getRarityColor() + e.toolTip.get(0));
			e.toolTip.remove(e.toolTip.size() - 1);

			if (e.itemStack.hasTagCompound())
			{
				String name = e.itemStack.getTagCompound().getString("Owner");
				if (!name.equals(""))
				{
					PlayerDataHandler ph = PlayerDataHandler.get(e.entityPlayer.worldObj.getPlayerEntityByName(name));

					e.toolTip.add(EnumChatFormatting.DARK_AQUA + "Owner: " + name);

					e.toolTip.add(EnumChatFormatting.BLUE + "+" + ph.getFullMeleeDamage(false) + "" + EnumChatFormatting.DARK_PURPLE + " Soul Damage");
					if (ph.getBowIndex() > 0)
						e.toolTip.add(EnumChatFormatting.BLUE + "+" + ph.getFullRangedDamage(false) + "" + EnumChatFormatting.DARK_PURPLE + " Ranged Soul Damage");

					if (ph.sword.equals(WeaponType.Spear))
						e.toolTip.add(EnumChatFormatting.BLUE + "+4 Reach Distance");
					if (ph.art)
					{
						e.toolTip.add("Additional:");
						ArtifactTooltips.addToTooltip(ph.stype, ph.player, (int) ph.getTrait(Trait.Level), e.toolTip);
					}

				}
				else
					e.toolTip.add(EnumChatFormatting.BLUE + "+" + 5 + "" + EnumChatFormatting.DARK_PURPLE + " Soul Damage");
			}
			else
				e.toolTip.add(EnumChatFormatting.BLUE + "+" + 5 + "" + EnumChatFormatting.DARK_PURPLE + " Soul Damage");
		}
		if (e.itemStack.getItem() instanceof ModSword)
		{
			String name = e.toolTip.get(0);
			e.toolTip.remove(e.toolTip.size() - 1);
			float dmg = ((ModSword) e.itemStack.getItem()).baseDmg * (1 + ((ModSword) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MiscUtils.roundStr(dmg, 1) + " Attack Damage");
		}

		if (e.itemStack.getItem() instanceof ModStaff)
		{
			String name = e.toolTip.get(0);
			e.toolTip.remove(e.toolTip.size() - 1);
			float dmgM = ((ModStaff) e.itemStack.getItem()).meleeDmg * (1 + ((ModStaff) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			float dmgR = ((ModStaff) e.itemStack.getItem()).rangedDmg * (1 + ((ModStaff) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MiscUtils.roundStr(dmgM, 1) + " Attack Damage");
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MiscUtils.roundStr(dmgR, 1) + " Ranged Damage");
		}

		if (e.itemStack.getItem() instanceof ModSpear)
		{
			String name = e.toolTip.get(0);
			e.toolTip.remove(e.toolTip.size() - 1);
			float dmg = ((ModSpear) e.itemStack.getItem()).baseDmg * (1 + ((ModSpear) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MiscUtils.roundStr(dmg, 1) + " Attack Damage");
			e.toolTip.add(EnumChatFormatting.BLUE + "+4 Reach Distance");
		}

		if (e.itemStack.getItem() instanceof ModHammer)
		{
			String name = e.toolTip.get(0);
			e.toolTip.remove(e.toolTip.size() - 1);
			float dmg = ((ModHammer) e.itemStack.getItem()).baseDmg * (1 + ((ModHammer) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MiscUtils.roundStr(dmg, 1) + " Attack Damage");
		}
		if (e.itemStack.getItem() instanceof ModAxe)
		{
			String name = e.toolTip.get(0);
			e.toolTip.remove(e.toolTip.size() - 1);
			float dmg = ((ModAxe) e.itemStack.getItem()).baseDmg * (1 + ((ModAxe) e.itemStack.getItem()).getUpgradeLevel(e.itemStack) * 0.05f);
			e.toolTip.add(EnumChatFormatting.BLUE + "+" + MiscUtils.roundStr(dmg, 1) + " Attack Damage");
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
			e.toolTip.add(EnumChatFormatting.GRAY + "Energy: " + MiscUtils.roundStr(EnergyProvider.getValue(e.itemStack), 1));
			if (e.itemStack.stackSize > 1)
				e.toolTip.add(EnumChatFormatting.GRAY + "Stack Energy: " + MiscUtils.roundStr(EnergyProvider.getValue(e.itemStack) * e.itemStack.stackSize, 1));

		}
		if (EnergyProvider.getStoredEnergy(e.itemStack) > 0)
		{
			e.toolTip.add(EnumChatFormatting.GRAY + "Stored Energy: " + MiscUtils.roundStr(EnergyProvider.getStoredEnergy(e.itemStack), 1));
		}
	}

	@SubscribeEvent
	public void crafted(ItemCraftedEvent e)
	{
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

			if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof ModSword)
			{
				ItemStack is = e.entityPlayer.getCurrentEquippedItem();

				SoulDamageSource src = new SoulDamageSource(ph);
				if (ph.sword.bypassesArmor)
				{
					src.setDamageBypassesArmor();
				}
				ModSword sw = (ModSword) e.entityPlayer.getCurrentEquippedItem().getItem();
				e.target.attackEntityFrom(src, sw.baseDmg * (1 + sw.getUpgradeLevel(is) * 0.05f));

				if (e.target instanceof EntityLivingBase)
				{
					EntityLivingBase l = (EntityLivingBase) e.target;
					if (e.entityPlayer.getCurrentEquippedItem().getItem() == ModItems.swords.get(0) && MiscUtils.randWPercent(e.entityPlayer.worldObj.rand, 30))
					{
						double f = 2;

						float regen = (float) (f * 0.1F * 3);
						ep.heal(regen);
					}
				}
			}
			if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof ModAxe)
			{
				ItemStack is = e.entityPlayer.getCurrentEquippedItem();

				SoulDamageSource src = new SoulDamageSource(ph);
				if (ph.sword.bypassesArmor)
				{
					src.setDamageBypassesArmor();
				}
				ModAxe sw = (ModAxe) e.entityPlayer.getCurrentEquippedItem().getItem();
				e.target.attackEntityFrom(src, sw.baseDmg * (1 + sw.getUpgradeLevel(is) * 0.05f));

				// if (e.target instanceof EntityLivingBase)
				// {
				// EntityLivingBase l = (EntityLivingBase) e.target;
				// if (e.entityPlayer.getCurrentEquippedItem().getItem() ==
				// ModItems.swords.get(0) &&
				// MiscUtils.randWPercent(e.entityPlayer.worldObj.rand, 30))
				// {
				// double f = 2;
				//
				// float regen = (float) (f * 0.1F * 3);
				// ep.heal(regen);
				// }
				// }
			}
			if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof ModSpear)
			{
				ItemStack is = e.entityPlayer.getCurrentEquippedItem();

				SoulDamageSource src = new SoulDamageSource(ph);
				if (ph.sword.bypassesArmor)
				{
					src.setDamageBypassesArmor();
				}
				ModSpear sw = (ModSpear) e.entityPlayer.getCurrentEquippedItem().getItem();
				e.target.attackEntityFrom(src, sw.baseDmg * (1 + sw.getUpgradeLevel(is) * 0.05f));

				// if (e.target instanceof EntityLivingBase)
				// {
				// EntityLivingBase l = (EntityLivingBase) e.target;
				// if (e.entityPlayer.getCurrentEquippedItem().getItem() ==
				// ModItems.swords.get(0) &&
				// MiscUtils.randWPercent(e.entityPlayer.worldObj.rand, 30))
				// {
				// double f = 2;
				//
				// float regen = (float) (f * 0.1F * 3);
				// ep.heal(regen);
				// }
				// }
			}
			if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof ModHammer)
			{
				ItemStack is = e.entityPlayer.getCurrentEquippedItem();

				SoulDamageSource src = new SoulDamageSource(ph);
				if (ph.sword.bypassesArmor)
				{
					src.setDamageBypassesArmor();
				}
				ModHammer sw = (ModHammer) e.entityPlayer.getCurrentEquippedItem().getItem();
				e.target.attackEntityFrom(src, sw.baseDmg * (1 + sw.getUpgradeLevel(is) * 0.05f));

				// if (e.target instanceof EntityLivingBase)
				// {
				// EntityLivingBase l = (EntityLivingBase) e.target;
				// if (e.entityPlayer.getCurrentEquippedItem().getItem() ==
				// ModItems.swords.get(0) &&
				// MiscUtils.randWPercent(e.entityPlayer.worldObj.rand, 30))
				// {
				// double f = 2;
				//
				// float regen = (float) (f * 0.1F * 3);
				// ep.heal(regen);
				// }
				// }
			}
			if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof ModStaff)
			{
				ItemStack is = e.entityPlayer.getCurrentEquippedItem();
				SoulDamageSource src = new SoulDamageSource(ph);
				if (ph.sword.bypassesArmor)
				{
					src.setDamageBypassesArmor();
				}
				ModStaff sw = (ModStaff) e.entityPlayer.getCurrentEquippedItem().getItem();
				e.target.attackEntityFrom(src, sw.rangedDmg * (1 + sw.getUpgradeLevel(is) * 0.05f));
			}

			int mod = 1;
			if (BaublesApi.getBaubles(e.entityPlayer).getStackInSlot(0) != null)
			{
				if (BaublesApi.getBaubles(e.entityPlayer).getStackInSlot(0).getItem() instanceof LuckyCoin)
				{
					mod += 2;
				}

			}
			if (MiscUtils.randWPercent(75d))
			{
				boolean drop = MiscUtils.randWPercent(100 * mod / 50d);
				if (drop)
				{
					MiscUtils.dropStack(e.entityPlayer.worldObj, new Wec3(e.entityPlayer), new ItemStack(ModItems.coins, 1, 2));
				}
				else
				{
					drop = MiscUtils.randWPercent(100 * mod / 15d);
					if (drop)
					{
						MiscUtils.dropStack(e.entityPlayer.worldObj, new Wec3(e.entityPlayer), new ItemStack(ModItems.coins, 1, 1));
					}
					else
					{
						drop = MiscUtils.randWPercent(100 * mod / 5d);
						if (drop)
						{
							MiscUtils.dropStack(e.entityPlayer.worldObj, new Wec3(e.entityPlayer), new ItemStack(ModItems.coins, mod, 0));
						}
					}
				}
			}
		}
		int bronze = MiscUtils.count(ep.inventory, ModItems.coins, 0);
		Logger.info(bronze);
		int dSilver = 0;
		if (bronze >= 100)
		{
			dSilver = Math.floorDiv(bronze, 100);
			int leftToRemove = dSilver * 100;
			List<Integer> lbr = MiscUtils.getList(ep.inventory, ModItems.coins, 0);
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
		MiscUtils.dropStack(ep.worldObj, new Wec3(ep), new ItemStack(ModItems.coins, dSilver, 1));
		int silver = MiscUtils.count(ep.inventory, ModItems.coins, 1);
		int dGold = 0;
		if (silver >= 100)
		{
			dGold = Math.floorDiv(silver, 100);
			int leftToRemove = dGold * 100;
			List<Integer> lbr = MiscUtils.getList(ep.inventory, ModItems.coins, 1);
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
		MiscUtils.dropStack(ep.worldObj, new Wec3(ep), new ItemStack(ModItems.coins, dGold, 2));
	}

	@SubscribeEvent
	public void onPlayerDeath(LivingHurtEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;
			if (e.ammount >= p.getHealth())
			{
				if (MiscUtils.contains(p.inventory, ModItems.RessurectionStone, 0))
				{
					List<Integer> fx = MiscUtils.getList(p.inventory, ModItems.RessurectionStone, 0);
					for (int i = 0; i < fx.size(); i++)
					{
						p.inventory.getStackInSlot(fx.get(i)).setItemDamage(p.dimension == AAMConfig.dungDimId ? Math.max(12000 / fx.size(), 4500) : Math.max(6000 / fx.size(), 1500));
					}
					p.heal(p.getMaxHealth());
					e.setCanceled(true);
				}
				// if (MiscUtils.contains(p.inventory,
				// ModItems.MassRessurectionStone, 0))
				// {
				// p.heal(p.getMaxHealth());
				// p.setAbsorptionAmount(20);
				// List<String> ps = PlayerDataHandler.get(p).party;
				// for (String name : ps)
				// {
				// if (p.worldObj.getPlayerEntityByName(name) != null)
				// {
				// EntityPlayer pp = p.worldObj.getPlayerEntityByName(name);
				// pp.heal(pp.getMaxHealth());
				// pp.setAbsorptionAmount(20);
				// }
				// }
				// p.inventory.getStackInSlot(MiscUtils.get(p.inventory,
				// ModItems.MassRessurectionStone, 0))
				// .setItemDamage(p.dimension == AAMConfig.dungDimId ?
				// Math.max((1 + ps.size()) * 24000 / 7, 4500) : Math.max((1 +
				// ps.size()) * 12000 / 7, 1500));
				//
				// e.setCanceled(true);
				// }
			}
		}
	}

	@SubscribeEvent
	public void entityKilled(LivingDeathEvent e)
	{
		if (e.source.damageType == SoulDamageSource.id)
		{
			PlayerDataHandler ph = ((SoulDamageSource) e.source).ph;
			int kp = (int) (e.entityLiving.getMaxHealth() - (e.entityLiving.getMaxHealth() / (ph.getTrait(Trait.Level) + 1)));
			if (ph.player.getCurrentEquippedItem() == null || ph.player.getCurrentEquippedItem().getItem() != ModItems.SoulSword)
				ph.soulxp += kp / 4;
			else
				ph.soulxp += kp;
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
			for (EntityPlayer p : l)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				event.setCanceled(ph.upgLevel[SoulUpgrade.Ender.ordinal()] > 0);
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
