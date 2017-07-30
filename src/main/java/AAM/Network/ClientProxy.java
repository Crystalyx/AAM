package AAM.Network;

import AAM.Client.Gui.GuiAddMember;
import AAM.Client.Gui.GuiArmoury;
import AAM.Client.Gui.GuiSoulAltar;
import AAM.Client.Gui.GuiSpellTable;
import AAM.Client.Gui.SkillsGui;
import AAM.Client.Renderer.Block.ArmouryItemRenderer;
import AAM.Client.Renderer.Block.BushRenderer;
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
import AAM.Client.Renderer.Entity.SoulChargeRenderer;
import AAM.Client.Renderer.Entity.SubwerRenderer;
import AAM.Client.Renderer.Entity.WastelandRenderer;
import AAM.Client.Renderer.Tile.ArmouryRenderer;
import AAM.Client.Renderer.Tile.CauldronRenderer;
import AAM.Client.Renderer.Tile.CreativeCauldronRenderer;
import AAM.Client.Renderer.Tile.CrystalRenderer;
import AAM.Client.Renderer.Tile.ModificationAnvilRender;
import AAM.Client.Renderer.Tile.SoulAltarRenderer;
import AAM.Client.Renderer.Tile.SpellTableRenderer;
import AAM.Client.Renderer.Tile.TeleporterRenderer;
import AAM.Client.Renderer.Tile.TransCircleRenderer;
import AAM.Common.Container.ContainerArmoury;
import AAM.Common.Container.SoulAltarContainer;
import AAM.Common.Container.SpellTableContainer;
import AAM.Common.Entity.Elemental;
import AAM.Common.Entity.ElementalGuard;
import AAM.Common.Entity.EntityBloodball;
import AAM.Common.Entity.SoulCharge;
import AAM.Common.Entity.Subwer;
import AAM.Common.Entity.WastelandCreature;
import AAM.Common.Tiles.CauldronTileEntity;
import AAM.Common.Tiles.CreativeCauldronTileEntity;
import AAM.Common.Tiles.GraviterTileEntity;
import AAM.Common.Tiles.TECrystal;
import AAM.Common.Tiles.TEModificationAnvil;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Tiles.TeleporterTileEntity;
import AAM.Common.Tiles.TileArmoury;
import AAM.Common.Tiles.TileSoulAltar;
import AAM.Common.Tiles.TileSpellTable;
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
			return new GuiSpellTable(new SpellTableContainer(p.inventory, (TileSpellTable) w.getTileEntity(x, y, z)));
		case (1):
			return new GuiSoulAltar(new SoulAltarContainer(p.inventory, (TileSoulAltar) w.getTileEntity(x, y, z)));
		case (2):
			return new GuiArmoury(new ContainerArmoury(p.inventory, (TileArmoury) w.getTileEntity(x, y, z)));

		}
		return null;

	}

	@Override
	public void addMember()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiAddMember());
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
		RenderingRegistry.registerBlockHandler(new ArmouryItemRenderer());
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

		ClientRegistry.bindTileEntitySpecialRenderer(CauldronTileEntity.class, new CauldronRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(CreativeCauldronTileEntity.class, new CreativeCauldronRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSpellTable.class, new SpellTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEModificationAnvil.class, new ModificationAnvilRender());

		ClientRegistry.bindTileEntitySpecialRenderer(TETransCircle.class, new TransCircleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSoulAltar.class, new SoulAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileArmoury.class, new ArmouryRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TECrystal.class, new CrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(GraviterTileEntity.class, new AAM.Client.Renderer.Tile.GraviterRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TeleporterTileEntity.class, new TeleporterRenderer());

		RenderingRegistry.registerEntityRenderingHandler(SoulCharge.class, new SoulChargeRenderer());
		RenderingRegistry.registerEntityRenderingHandler(Elemental.class, new ElementalRenderer());
		RenderingRegistry.registerEntityRenderingHandler(ElementalGuard.class, new ElemGuardRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodball.class, new BloodballRenderer());
		RenderingRegistry.registerEntityRenderingHandler(WastelandCreature.class, new WastelandRenderer());
		RenderingRegistry.registerEntityRenderingHandler(Subwer.class, new SubwerRenderer());

	}

	@Override
	public void spawnParticle(EntityFX e)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(e);
	}

	public World getClientWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}
}
