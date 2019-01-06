package aam.common.event;

import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.PlayerSyncMessage;
import aam.utils.Logger;
import aam.utils.PlayerDataHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerDataEventHandler
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (PlayerDataHandler.get((EntityPlayer) event.entity) == null)
			{
				Logger.info("Registering extended properties for player");
				PlayerDataHandler.register((EntityPlayer) event.entity);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void entityUpdate(LivingUpdateEvent e)
	{
		if (e.entityLiving instanceof EntityPlayer)
		{
			if (PlayerDataHandler.get((EntityPlayer) e.entityLiving) != null)
			{
				EntityPlayer p = (EntityPlayer) e.entityLiving;

				PlayerDataHandler.get(p).onUpdate();
			}
		}
	}

	/**
	 * This event is on the FML bus
	 */
	/*
	 * @SubscribeEvent public void onPlayerLogIn(PlayerLoggedInEvent event) { if
	 * (event.player instanceof EntityPlayerMP) { // no longer needed because we
	 * can wait for the main thread before processing the packet
	 * TutorialMain.logger.info(
	 * "Player logged in, sending extended properties to client");
	 * PacketDispatcher.sendTo(new SyncPlayerPropsMessage(event.player),
	 * (EntityPlayerMP) event.player); } }
	 */

	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayerMP)
		{
			Logger.info("Player joined world, sending extended properties to client");
			AlchemicalDispatcher.sendToClient(new PlayerSyncMessage((EntityPlayerMP) event.entity), (EntityPlayerMP) event.entity);
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event)
	{
		Logger.info("Cloning player extended properties");
		PlayerDataHandler.get(event.entityPlayer).copy(PlayerDataHandler.get(event.original));
	}
}
