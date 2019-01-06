package aam.network;

import aam.api.PouchInventory;
import aam.client.gui.*;
import aam.client.renderer.block.GraviterRender;
import aam.client.renderer.block.*;
import aam.client.renderer.entity.*;
import aam.client.renderer.item.SoulRenderer;
import aam.client.renderer.tile.*;
import aam.common.container.*;
import aam.common.entity.SoulCharge;
import aam.common.entity.StaffCharge;
import aam.common.entity.elemental.*;
import aam.common.entity.golem.GolemBoss;
import aam.common.items.ModItems;
import aam.common.tiles.*;
import aam.utils.vectors.Wec3;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy
{
	public static KeyBinding soul = new KeyBinding("aam.soul", Keyboard.KEY_G, "key.categories.inventory");
	public static KeyBinding member = new KeyBinding("aam.addmember", Keyboard.KEY_H, "key.categories.inventory");
	public static KeyBinding arts = new KeyBinding("aam.arts", Keyboard.KEY_J, "key.categories.inventory");

	@Override
	public void init()
	{
		ClientRegistry.registerKeyBinding(soul);
		ClientRegistry.registerKeyBinding(member);
		ClientRegistry.registerKeyBinding(arts);
		registerRenders();
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer p, World w, int x, int y, int z)
	{
		switch (ID)
		{
		case 0:
			return new GuiSpellTable(new SpellTableContainer(p.inventory, (TESpellTable) w.getTileEntity(x, y, z)));
		case 1:
			return new GuiSoulAltar(new SoulAltarContainer(p.inventory, (TESoulAltar) w.getTileEntity(x, y, z)));
		case 2:
			return new GuiModificationAnvil(new ModificationAnvilContainer(p.inventory, (TEModificationAnvil) w.getTileEntity(x, y, z)));
		case 3:
			return new GuiPouch(new PouchContainer(p.inventory, new PouchInventory(p.getCurrentEquippedItem())));
		case 4:
			return new MechanistsTableGui(new MechanistsTableContainer(p.inventory, (TEMechanistsTable) w.getTileEntity(x, y, z)));
		}
		return null;

	}

	@Override
	public void addMember()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiParty());
	}

	@Override
	public void openCircleGui(int x, int y, int z)
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiAlchemicalCircle(new Wec3(x, y, z)));
	}

	@Override
	public void getSkill()
	{
		Minecraft.getMinecraft().displayGuiScreen(new SkillsGui());
	}

	public static int lastId = 125;

	public static int getNextRenderId()
	{
		return lastId++;
	}

	public static void registerRenders()
	{
		// 125
		RenderingRegistry.registerBlockHandler(new BushRenderer());
		// 126
		RenderingRegistry.registerBlockHandler(new CauldronItemRenderer());
		// 127
		RenderingRegistry.registerBlockHandler(new CreativeCauldronItemRenderer());
		// 128
		RenderingRegistry.registerBlockHandler(new SpellTableItemRenderer());
		// 129
		getNextRenderId();
		// RenderingRegistry.registerBlockHandler(new ArmouryItemRenderer());
		// 130
		RenderingRegistry.registerBlockHandler(new UnrenderableBlock());
		// 131
		RenderingRegistry.registerBlockHandler(new SoulAltarItemRenderer());
		// 132
		RenderingRegistry.registerBlockHandler(new GraviterRender());
		// 133
		RenderingRegistry.registerBlockHandler(new PillarRenderer());
		// 134
		RenderingRegistry.registerBlockHandler(new CrystalItemRenderer());
		// 135
		RenderingRegistry.registerBlockHandler(new TeleporterItemRenderer());
		// 136
		RenderingRegistry.registerBlockHandler(new ModificationAnvilItemRender());
		// 137
		RenderingRegistry.registerBlockHandler(new BloodAltarRenderer());
		// 138
		RenderingRegistry.registerBlockHandler(new BushSproutRenderer());
		// 139
		RenderingRegistry.registerBlockHandler(new BarrelRenderer());
		// 140
		RenderingRegistry.registerBlockHandler(new MechanistsTableRenderer());

		ClientRegistry.bindTileEntitySpecialRenderer(TECauldron.class, new CauldronRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TECreativeCauldron.class, new CreativeCauldronRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TESpellTable.class, new SpellTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEModificationAnvil.class, new ModificationAnvilRender());

		ClientRegistry.bindTileEntitySpecialRenderer(TETransCircle.class, new TransCircleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TESoulAltar.class, new SoulAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TECrystal.class, new CrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEGraviter.class, new aam.client.renderer.tile.GraviterRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TETeleporter.class, new TeleporterRenderer());

		RenderingRegistry.registerEntityRenderingHandler(SoulCharge.class, new SoulChargeRenderer());
		RenderingRegistry.registerEntityRenderingHandler(ElementalBoss.class, new ElementalRenderer());
		RenderingRegistry.registerEntityRenderingHandler(ElementalGuard.class, new ElemGuardRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodball.class, new BloodballRenderer());
		RenderingRegistry.registerEntityRenderingHandler(WastelandCreature.class, new WastelandRenderer());
		RenderingRegistry.registerEntityRenderingHandler(Subwer.class, new SubwerRenderer());

		RenderingRegistry.registerEntityRenderingHandler(GolemBoss.class, new GolemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(StaffCharge.class, new StaffChargeRenderer());

		MinecraftForgeClient.registerItemRenderer(ModItems.SoulSword, new SoulRenderer());
	}

	@Override
	public void spawnParticle(EntityFX e)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(e);
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}
}
