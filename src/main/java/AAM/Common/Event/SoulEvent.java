package AAM.Common.Event;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import AAM.Common.Entity.SoulCharge;
import AAM.Common.Items.LuckyCoin;
import AAM.Common.Items.ModItems;
import AAM.Common.Items.SoulSword;
import AAM.Common.Skills.ModSkills;
import AAM.Core.AAMConfig;
import AAM.Core.AAMCore;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Soul;
import AAM.Utils.SoulDamageSource;
import AAM.Utils.WorldPos;
import DummyCore.Utils.EnumRarityColor;
import baubles.api.BaublesApi;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class SoulEvent
{

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void entityUpdate(LivingUpdateEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;

			if (AAMCore.soul.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				// AlchemyAndMagicCore.proxy.addMember();
				int meta = 0;
				UUID uuid = p.getUniqueID();
				UUID lord = UUID.fromString("55275053-cfff-4307-bdc3-aecec93caa38");
				if (uuid.equals(lord))
				{
					meta = 3 + ph.sword.ordinal();
				}
				else
				{
					meta = ph.sword.ordinal();
				}
				int mana = 0;
				int c = 0;
				for (int i = 0; i < 36; i++)
				{
					if (p.inventory.mainInventory[i] != null)
					{
						if (p.inventory.mainInventory[i].getItem() == ModItems.SoulSword)
						{
							if (p.inventory.mainInventory[i].hasTagCompound())
							{
								if (p.inventory.mainInventory[i].getTagCompound().getString("Owner") == p.getGameProfile().getName())
								{
									mana += 15;
									c += 1;
								}
							}
						}
					}
				}
				int count = (mana % 15) + 1;
				c = 0;
				mana -= 15;
				if (ph.consumeSoul(-mana))
				{
					for (int i = 0; i < 36; i++)
					{
						if (p.inventory.mainInventory[i] != null)
						{
							if (p.inventory.mainInventory[i].getItem() == ModItems.SoulSword)
							{
								if (p.inventory.mainInventory[i].hasTagCompound())
								{
									if (p.inventory.mainInventory[i].getTagCompound().getString("Owner") == p.getGameProfile().getName())
									{
										if (c >= count)
										{
											PlayerDataHandler.get(p).soulTag = p.inventory.mainInventory[i].getTagCompound();

										}
										p.inventory.mainInventory[i] = null;
										c += 1;
									}
								}
							}
						}
					}
					ph.soulTag.setString("Owner", p.getGameProfile().getName());
					ItemStack sword = new ItemStack(ModItems.SoulSword, 1, meta);
					sword.setTagCompound(PlayerDataHandler.get(p).soulTag);
					p.inventory.addItemStackToInventory(sword);

				}
			}

			if (AAMCore.member.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
			{
				AAMCore.proxy.addMember();
			}

			// if (AAMCore.skills.getIsKeyPressed() &&
			// FMLClientHandler.instance().getClient().inGameHasFocus)
			// {
			// AAMCore.proxy.getSkill();
			// }

			if (Minecraft.getMinecraft().objectMouseOver != null)
			{
				if (Minecraft.getMinecraft().gameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindAttack) && FMLClientHandler.instance().getClient().inGameHasFocus
						&& Minecraft.getMinecraft().objectMouseOver.typeOfHit == MovingObjectType.MISS)
				{
					UUID uuid = p.getUniqueID();
					UUID lord = UUID.fromString("55275053-cfff-4307-bdc3-aecec93caa38");
					PlayerDataHandler ph = PlayerDataHandler.get(p);
					if (p.getCurrentEquippedItem() != null)
					{
						if (p.getCurrentEquippedItem().getItem() instanceof SoulSword)
						{
							Random r = new Random();
							if (ph.bow && r.nextDouble() < 0.4 + ph.castUpg * 0.05)
							{
								if (ph.consumeSoul(1) && !p.worldObj.isRemote)
								{
									SoulCharge s = new SoulCharge(p.worldObj, p);
									double sp = 0.5;
									s.setLife(500);
									WorldPos look = new WorldPos(p.getLookVec()).modify(2);
									look.ptm(s);
									p.worldObj.spawnEntityInWorld(s);
								}
							}
						}
					}
				}
			}
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
				PlayerDataHandler ph;
				String name = e.itemStack.getTagCompound().getString("Owner");
				if (name != "")
				{
					ph = PlayerDataHandler.get(e.entityPlayer.worldObj.getPlayerEntityByName(name));

					e.toolTip.add(EnumChatFormatting.DARK_AQUA + "Owner: " + name);
					e.toolTip.add(EnumChatFormatting.BLUE + "+" + ph.soulDamage + "" + EnumChatFormatting.DARK_PURPLE + " Soul Damage");
					if (ph.bow)
						e.toolTip.add(EnumChatFormatting.BLUE + "+" + (ph.soulDamage - 4 * ph.swords.size()) + "" + EnumChatFormatting.DARK_PURPLE + " Ranged Soul Damage");
					// e.toolTip.add(EnumRarityColor.EPIC.getRarityColor() +
					// "Current skill: " +
					// ModSkills.skills.get(ph.soulTag.getInteger("Skill")).name);

					e.toolTip.add("Additional:");
					if (ph.stype.equals(Soul.Light))
					{
						e.toolTip.add(EnumRarityColor.EPIC.getRarityColor() + (20 + 2 * (ph.soulLevel - 1)) + "% Attack Damage vs Hostile");
						e.toolTip.add(EnumRarityColor.EPIC.getRarityColor() + (15 + 2 * (ph.soulLevel - 1)) + "% Attack Damage vs Undead");
					}
					if (ph.stype.equals(Soul.Normal))
					{
						e.toolTip.add(EnumRarityColor.RARE.getRarityColor() + (25 + 2 * (ph.soulLevel - 1)) + "% Attack Damage vs Non-Undead and Non-Arthopods");
					}
					if (ph.stype.equals(Soul.Blood))
					{
						e.toolTip.add(EnumRarityColor.LEGENDARY.getRarityColor() + (25 + 2 * (ph.soulLevel - 1)) + "% Attack Damage");
						e.toolTip.add(EnumRarityColor.LEGENDARY.getRarityColor() + (25 + 2 * (ph.soulLevel - 1)) + "% Attack Damage vs Players");
						e.toolTip.add(EnumRarityColor.TURQUOISE.getRarityColor() + "+1 Soul per Attack");
					}
					if (ph.stype.equals(Soul.Lunar))
					{
						e.toolTip.add(EnumChatFormatting.DARK_AQUA + "" + (15 + 2 * (ph.soulLevel - 1)) + "% Attack Damage");
						e.toolTip.add(EnumRarityColor.EXCEPTIONAL.getRarityColor() + (25 + 4 * (ph.soulLevel - 1) * ph.player.worldObj.getCurrentMoonPhaseFactor()) + "% Attack Damage at current night");
					}
					if (ph.stype.equals(Soul.Plant))
					{
					}
				}
				else
					e.toolTip.add(EnumChatFormatting.BLUE + "+" + 5 + "" + EnumChatFormatting.DARK_PURPLE + " Soul Damage");
			}
			else
				e.toolTip.add(EnumChatFormatting.BLUE + "+" + 5 + "" + EnumChatFormatting.DARK_PURPLE + " Soul Damage");

		}
	}

	@SubscribeEvent
	public void crafted(ItemCraftedEvent e)
	{
	}

	@SubscribeEvent
	public void attacked(AttackEntityEvent e)
	{
		if (e.entityPlayer.getCurrentEquippedItem() != null)
		{
			if (e.entityPlayer.getCurrentEquippedItem().getItem() == ModItems.SoulSword && e.entityPlayer.getCurrentEquippedItem().hasTagCompound())
			{
				ItemStack is = e.entityPlayer.getCurrentEquippedItem();
				String name = is.getTagCompound().getString("Owner");
				EntityPlayer ep = e.entityPlayer.worldObj.getPlayerEntityByName(name);
				if(ep == null)
				{
					ep = e.entityPlayer;
				}
				PlayerDataHandler ph = PlayerDataHandler.get(ep);
				if (!e.target.isDead)
				{
					if (!e.target.isDead && e.target.canAttackWithItem() && !e.target.isEntityInvulnerable())
					{
						if (is.hasTagCompound())
						{
							Logger.info(ph.bloodUpg);
							if (ph.bloodUpg != 0 && e.target instanceof EntityLivingBase)
							{
								EntityLivingBase l = (EntityLivingBase) e.target;

								double f = Math.rint(l.getMaxHealth() * 100) / 100;

								float regen = (float) (f * 0.1F * ph.bloodUpg);
								ep.heal(regen);
							}
							e.target.attackEntityFrom(new SoulDamageSource(ph).causePlayerDamage(ph.player), ph.soulDamage * ph.tempmodif);

							if (e.target instanceof EntityLivingBase)
							{
								EntityLivingBase l = (EntityLivingBase) e.target;

								if (l instanceof EntityMob && ph.stype.equals(Soul.Light))
								{
									l.attackEntityFrom(new SoulDamageSource(ph).causePlayerDamage(ph.player), ph.soulDamage * ph.tempmodif * (0.20F));
									if (l.getCreatureAttribute().equals(EnumCreatureAttribute.UNDEAD))
									{
										l.attackEntityFrom(new SoulDamageSource(ph).causePlayerDamage(ph.player), ph.soulDamage * ph.tempmodif * (0.15F));
									}
								}
								if (l instanceof EntityMob && ph.stype.equals(Soul.Normal))
								{
									if (l.getCreatureAttribute().equals(EnumCreatureAttribute.UNDEFINED))
									{
										l.attackEntityFrom(new SoulDamageSource(ph).causePlayerDamage(ph.player), ph.soulDamage * ph.tempmodif * (0.25F));
									}
								}

								if (ph.stype.equals(Soul.Blood))
								{
									if (ph.consumeSoul(1))
									{
										l.attackEntityFrom(new SoulDamageSource(ph).causePlayerDamage(ph.player), ph.soulDamage * ph.tempmodif * (0.25F));
										if (l instanceof EntityPlayer)
										{
											l.attackEntityFrom(new SoulDamageSource(ph).causePlayerDamage(ph.player), ph.soulDamage * ph.tempmodif * (0.25F));
										}
									}
								}
							}
							else
							{
								e.target.attackEntityFrom(new SoulDamageSource(ph).causePlayerDamage(ph.player), 5.0F);
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
			}

			if (BaublesApi.getBaubles(e.entityPlayer).getStackInSlot(0) != null)
			{
				int mod = 1;
				if (BaublesApi.getBaubles(e.entityPlayer).getStackInSlot(0).getItem() instanceof LuckyCoin)
				{
					mod += 2;
				}
				if (MiscUtils.randWPercent(75d))
				{
					int beta = e.entityPlayer.worldObj.rand.nextInt(50);
					boolean drop = MiscUtils.randWPercent(mod / 24d);
					if (drop)
					{
						MiscUtils.dropStack(e.entityPlayer.worldObj, new WorldPos(e.entityPlayer), new ItemStack(ModItems.coins, 1, 2));
					}
					if (!drop)
					{
						drop = MiscUtils.randWPercent(mod / 6d);
						if (drop)
						{
							MiscUtils.dropStack(e.entityPlayer.worldObj, new WorldPos(e.entityPlayer), new ItemStack(ModItems.coins, 1, 1));
						}
					}
					if (!drop)
					{
						MiscUtils.dropStack(e.entityPlayer.worldObj, new WorldPos(e.entityPlayer), new ItemStack(ModItems.coins, mod, 0));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerDeath(LivingHurtEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e.entityLiving;
			if (e.ammount >= p.getHealth())
			{
				// Logger.info(MiscUtils.contains(p.inventory,
				// ModItems.RessurectionStone, 0));
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
				if (MiscUtils.contains(p.inventory, ModItems.MassRessurectionStone, 0))
				{
					// p.inventory.mainInventory[MiscUtils.get(p.inventory,
					// ModItems.RessurectionStone, 0)] = null;
					p.heal(p.getMaxHealth());
					p.setAbsorptionAmount(20);
					List<String> ps = PlayerDataHandler.get(p).party;
					for (String name : ps)
					{
						if (p.worldObj.getPlayerEntityByName(name) != null)
						{
							EntityPlayer pp = p.worldObj.getPlayerEntityByName(name);
							pp.heal(pp.getMaxHealth());
							pp.setAbsorptionAmount(20);
						}
					}
					p.inventory.getStackInSlot(MiscUtils.get(p.inventory, ModItems.MassRessurectionStone, 0))
							.setItemDamage(p.dimension == AAMConfig.dungDimId ? Math.max((1 + ps.size()) * 24000 / 7, 4500) : Math.max((1 + ps.size()) * 12000 / 7, 1500));

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
			((SoulDamageSource) e.source).ph.soulxp += (e.entityLiving.getMaxHealth() - (e.entityLiving.getMaxHealth() / ((SoulDamageSource) e.source).ph.soulLevel));
			((SoulDamageSource) e.source).ph.soulxp = Math.rint(((SoulDamageSource) e.source).ph.soulxp * 100) / 100;
			// if (Minecraft.getSystemTime() % 100 == 1)
			{
				if (!e.entityLiving.worldObj.isRemote)
				{
					PlayerSyncMessage psm = new PlayerSyncMessage(((SoulDamageSource) e.source).ph.player);
					AlchemicalDispatcher.sendToClient(psm, ((SoulDamageSource) e.source).ph.player);
				}
			}
		}
	}
}
