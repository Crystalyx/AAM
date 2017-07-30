package AAM.Common.Transmutations;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Tiles.TETransCircle.State;
import AAM.Core.AAMCore;
import AAM.Utils.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class CirclePart
{
	public static final String bw = "textures/blocks/circles/part_";
	public static final String iw = "tools/chalk/paper_";
	public static final String ext = ".png";

	public ResourceLocation block;
	public ResourceLocation blockRev;
	public ResourceLocation item;
	public ResourceLocation itemRev;
	public String name;

	public CirclePart(String block, String blockRev, String name)
	{
		String id = AAMCore.modid;
		this.block = new ResourceLocation(id, bw + block + ext);
		this.item = new ResourceLocation(id, iw + block);
		this.name = name;
		this.blockRev = new ResourceLocation(id, bw + blockRev + ext);
		this.itemRev = new ResourceLocation(id, iw + blockRev);
	}

	public CirclePart(String block, String name)
	{
		String id = AAMCore.modid;
		this.block = new ResourceLocation(id, bw + block + ext);
		this.item = new ResourceLocation(id, iw + block);
		this.name = name;
		this.blockRev = this.block;
		this.itemRev = this.item;

	}
}
