package AAM.Common.Transmutations;

import AAM.Core.AAMCore;
import net.minecraft.util.ResourceLocation;

public class CirclePart
{
	public String ptId;
	public String bw = "textures/blocks/circles/part_";
	public String iw = "tools/chalk/paper_";
	public String ext = ".png";

	public ResourceLocation block;
	public ResourceLocation blockRev;
	public ResourceLocation item;
	public ResourceLocation itemRev;
	public String name;
	public boolean extended = false;

	public CirclePart(String ptId, String block, String blockRev, String name)
	{
		this.ptId = ptId;
		String id = AAMCore.modid;
		this.block = new ResourceLocation(id, bw + block + ext);
		this.item = new ResourceLocation(id, iw + block);
		this.name = name;
		this.blockRev = new ResourceLocation(id, bw + blockRev + ext);
		this.itemRev = new ResourceLocation(id, iw + blockRev);
	}

	public CirclePart(String ptId, String block, String name)
	{
		this.ptId = ptId;
		String id = AAMCore.modid;
		this.block = new ResourceLocation(id, bw + block + ext);
		this.item = new ResourceLocation(id, iw + block);
		this.name = name;
		this.blockRev = this.block;
		this.itemRev = this.item;
	}

	public CirclePart(String ptId, String block, String blockRev, String name, boolean extended)
	{
		this.ptId = ptId;
		String id = AAMCore.modid;
		if (extended)
		{
			bw = "textures/blocks/circles/extended/part_";
		}
		this.block = new ResourceLocation(id, bw + block + ext);
		this.item = new ResourceLocation(id, iw + block);
		this.name = name;
		this.blockRev = new ResourceLocation(id, bw + blockRev + ext);
		this.itemRev = new ResourceLocation(id, iw + blockRev);
		this.extended = extended;
	}

	public CirclePart(String ptId, String block, String name, boolean extended)
	{
		this.ptId = ptId;
		String id = AAMCore.modid;
		if (extended)
		{
			bw = "textures/blocks/circles/extended/part_";
		}
		this.block = new ResourceLocation(id, bw + block + ext);
		this.item = new ResourceLocation(id, iw + block);
		this.name = name;
		this.blockRev = this.block;
		this.itemRev = this.item;
		this.extended = extended;
	}
}
