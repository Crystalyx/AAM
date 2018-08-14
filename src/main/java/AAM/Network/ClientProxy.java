package AAM.Network;

import AAM.Client.Gui.GuiModificationAnvil;
import AAM.Client.Gui.GuiParty;
import AAM.Client.Gui.GuiSoulAltar;
import AAM.Client.Gui.GuiSpellTable;
import AAM.Client.Gui.SkillsGui;
import AAM.Client.Renderer.Block.BarrelRenderer;
import AAM.Client.Renderer.Block.BloodAltarRenderer;
import AAM.Client.Renderer.Block.BushRenderer;
import AAM.Client.Renderer.Block.BushSproutRenderer;
import AAM.Client.Renderer.Block.CauldronItemRenderer;
import AAM.Client.Renderer.Block.CreativeCauldronItemRenderer;
import AAM.Client.Renderer.Block.CrystalItemRenderer;
import AAM.Client.Renderer.Block.GraviterRender;
import AAM.Client.Renderer.Block.ModificationAnvilItemRender;
import AAM.Client.Renderer.Block.PillarRenderer;
import AAM.Client.Renderer.Block.SoulAltarItemRenderer;
import AAM.Client.Renderer.Block.SpellTableItemRenderer;
import AAM.Client.Renderer.Block.TeleporterItemRenderer;
import AAM.Client.Renderer.Block.UnrenderableBlock;
import AAM.Client.Renderer.Entity.BloodballRenderer;
import AAM.Client.Renderer.Entity.ElemGuardRenderer;
import AAM.Client.Renderer.Entity.ElementalRenderer;
import AAM.Client.Renderer.Entity.GolemRenderer;
import AAM.Client.Renderer.Entity.SoulChargeRenderer;
import AAM.Client.Renderer.Entity.StaffChargeRenderer;
import AAM.Client.Renderer.Entity.SubwerRenderer;
import AAM.Client.Renderer.Entity.WastelandRenderer;
import AAM.Client.Renderer.Tile.CauldronRenderer;
import AAM.Client.Renderer.Tile.CreativeCauldronRenderer;
import AAM.Client.Renderer.Tile.CrystalRenderer;
import AAM.Client.Renderer.Tile.ModificationAnvilRender;
import AAM.Client.Renderer.Tile.SoulAltarRenderer;
import AAM.Client.Renderer.Tile.SpellTableRenderer;
import AAM.Client.Renderer.Tile.TeleporterRenderer;
import AAM.Client.Renderer.Tile.TransCircleRenderer;
import AAM.Common.Container.ModificationAnvilContainer;
import AAM.Common.Container.SoulAltarContainer;
import AAM.Common.Container.SpellTableContainer;
import AAM.Common.Entity.SoulCharge;
import AAM.Common.Entity.StaffCharge;
import AAM.Common.Entity.Elemental.ElementalBoss;
import AAM.Common.Entity.Elemental.ElementalGuard;
import AAM.Common.Entity.Elemental.EntityBloodball;
import AAM.Common.Entity.Elemental.Subwer;
import AAM.Common.Entity.Elemental.WastelandCreature;
import AAM.Common.Entity.Golem.GolemBoss;
import AAM.Common.Tiles.TECauldron;
import AAM.Common.Tiles.TECreativeCauldron;
import AAM.Common.Tiles.TECrystal;
import AAM.Common.Tiles.TEGraviter;
import AAM.Common.Tiles.TEModificationAnvil;
import AAM.Common.Tiles.TESoulAltar;
import AAM.Common.Tiles.TESpellTable;
import AAM.Common.Tiles.TETeleporter;
import AAM.Common.Tiles.TETransCircle;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer p, World w, int x, int y, int z)
	{
		switch (ID)
		{
		case (0):
			return new GuiSpellTable(new SpellTableContainer(p.inventory, (TESpellTable) w.getTileEntity(x, y, z)));
		case (1):
			return new GuiSoulAltar(new SoulAltarContainer(p.inventory, (TESoulAltar) w.getTileEntity(x, y, z)));
		case (2):
			return new GuiModificationAnvil(new ModificationAnvilContainer(p.inventory, (TEModificationAnvil) w.getTileEntity(x, y, z)));

		}
		return null;

	}

	@Override
	public void addMember()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiParty());
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

		ClientRegistry.bindTileEntitySpecialRenderer(TECauldron.class, new CauldronRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TECreativeCauldron.class, new CreativeCauldronRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TESpellTable.class, new SpellTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEModificationAnvil.class, new ModificationAnvilRender());

		ClientRegistry.bindTileEntitySpecialRenderer(TETransCircle.class, new TransCircleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TESoulAltar.class, new SoulAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TECrystal.class, new CrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEGraviter.class, new AAM.Client.Renderer.Tile.GraviterRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TETeleporter.class, new TeleporterRenderer());

		RenderingRegistry.registerEntityRenderingHandler(SoulCharge.class, new SoulChargeRenderer());
		RenderingRegistry.registerEntityRenderingHandler(ElementalBoss.class, new ElementalRenderer());
		RenderingRegistry.registerEntityRenderingHandler(ElementalGuard.class, new ElemGuardRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodball.class, new BloodballRenderer());
		RenderingRegistry.registerEntityRenderingHandler(WastelandCreature.class, new WastelandRenderer());
		RenderingRegistry.registerEntityRenderingHandler(Subwer.class, new SubwerRenderer());

		RenderingRegistry.registerEntityRenderingHandler(GolemBoss.class, new GolemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(StaffCharge.class, new StaffChargeRenderer());

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
