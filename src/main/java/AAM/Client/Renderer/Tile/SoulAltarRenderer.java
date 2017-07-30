package AAM.Client.Renderer.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import AAM.Client.Model.SoulAltar;
import AAM.Common.Items.Artifact;
import AAM.Common.Tiles.TECrystal;
import AAM.Common.Tiles.TileSoulAltar;
import AAM.Utils.MiscUtils;
import AAM.Utils.WorldPos;
import DummyCore.Utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class SoulAltarRenderer extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		
		//if (tile.getWorldObj().getWorldTime() % 10 == 1)
		for (int i = 0; i < 4; i++)
		{
			WorldPos tp = new WorldPos(tile).centralize().add(new WorldPos(ForgeDirection.UP).divide(2));

			tp = tp.add(MiscUtils.getPosBy3DAngle(Math.toRadians(45 + 90 * i), Math.toRadians(45), 5.5));

			if (tp.add(ForgeDirection.DOWN).getTileEntity(tile.getWorldObj()) instanceof TECrystal)
			{
				// WorldPos fluct =
				// MiscUtils.getPosBy3DAngle(Math.toRadians(this.worldObj.getWorldTime())*2,
				// Math.toRadians(this.worldObj.getWorldTime())*2, 5);

//				AlchemicalParticle ap = new AlchemicalParticle(tile.getWorldObj(), tp, 13);
//				ap.setColor(new Color(6, 223, 255));
//
//				WorldPos vec = new WorldPos(tile).centralize().subtruct(tp).modify(5);// .add(fluct);
//				//ap.setMotionIJK(true);
//				//Logger.info(vec.y);
//				ap.setTarget(new WorldPos(tile).centralize());
//				
//				//vec.ptm(ap);
//				
//				ap.setLifeTime(20);
//				AAMCore.proxy.spawnParticle(ap);
			}
		}
		
		GL11.glPushMatrix();
		RenderHelper.enableStandardItemLighting();

		long time = Minecraft.getMinecraft().theWorld.getWorldTime();
		double s = Math.sin(Math.toRadians((time % 360))) / 8;
		double sin = Math.sin(Math.toRadians((time % 360)))/ 4;
		double cos = Math.cos(Math.toRadians((time % 360)))/2;
		GL11.glTranslated(x + 0.5, y + 1.5 + s, z + 0.5);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		Random r = tile.getWorldObj().rand;

		GL11.glScaled(8, 8, 8);
		if (tile instanceof TileSoulAltar)
		{
			TileSoulAltar t = (TileSoulAltar) tile;

			if (t.getStackInSlot(0) != null)
			{
				DrawUtils.renderItemStack_Full(t.getStackInSlot(0), 0, 0, 0, 0, 0, 0, tile.getWorldObj().getWorldTime() % 360, 0, 1, 1, 1, 0, -0.25F, 0, true);
			}
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 4; i < 10; i++)
			{
				if (t.getStackInSlot(i) != null)
				{
					ids.add(i);
				}
			}
			if (t.getStackInSlot(2) != null)
			{
				if (t.getStackInSlot(2).getItem() instanceof Artifact)
				{
					ItemStack i = t.getStackInSlot(2);
					DrawUtils.renderItemStack_Full(i, 0, 0, 0, 0, 0, 0, 90, 90, 1, 1, 1, 0.15F, -1F, 0, true);
				}
			}
			float angle = (float) Math.toRadians(360F / ids.size());
			for (int i = 0; i < ids.size(); i++)
			{
				int time2 = (int) (t.getWorldObj().getWorldTime() % 360);
				float px = (float) Math.cos(angle * i + Math.toRadians(time2)) / 1.5F;
				float pz = (float) Math.sin(angle * i + Math.toRadians(time2)) / 1.5F;

				DrawUtils.renderItemStack_Full(t.getStackInSlot(ids.get(i)), 0, 0, 0, 0, 0, 0, 90, 90, 1, 1, 1, px + 0.125f, -1F, pz, true);
			}
		}

		GL11.glScaled(0.0625, 0.0625, 0.0625);
		GL11.glScaled(2, 2, 2);
		GL11.glRotated(180, 1.0, 0.0, 0.0);
		GL11.glScaled(16, 16, 16);
		GL11.glTranslated(0, -0.3, 0);
		GL11.glScaled(0.0625, 0.0625, 0.0625);
		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		GL11.glRotated(meta * 90, 0, 1, 0);

		ResourceLocation texture = new ResourceLocation("aam", "textures/models/altar_texture.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		SoulAltar model = new SoulAltar();
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();
	}
}
