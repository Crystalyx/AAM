package AAM.Utils.Render;

import java.util.List;

import AAM.Utils.WorldPos;
import net.minecraft.client.model.ModelBase;

public class MagicRenderer
{
	/** The size of the texture file's width in pixels. */
	public float textureWidth;
	/** The size of the texture file's height in pixels. */
	public float textureHeight;
	/** The X offset into the texture used for displaying this model */
	public int textureOffsetX;
	/** The Y offset into the texture used for displaying this model */
	public int textureOffsetY;
	public float rotationPointX;
	public float rotationPointY;
	public float rotationPointZ;
	public float rotateAngleX;
	public float rotateAngleY;
	public float rotateAngleZ;
	public boolean compiled;
	/** The GL display list rendered by the Tessellator for this model */
	public int displayList;
	public boolean mirror;
	public boolean showModel;
	/** Hides the model. */
	public boolean isHidden;
	public List cubeList;
	public List childModels;
	public String boxName;
	public ModelBase baseModel;
	public float offsetX;
	public float offsetY;
	public float offsetZ;

	public MagicRenderer(ModelBase model, int x, int y, float px, float py, float pz, int dx, int dy, int dz, float scale)
	{
		this.textureWidth = x;
		this.textureHeight = y;
		this.Boxpos = new WorldPos(px, py, pz);
		this.BoxSize = new WorldPos(dx, dy, dz);
		this.scale = scale;
	}

	public MagicRenderer(ModelBase model, int x, int y)
	{
		this.textureWidth = x;
		this.textureHeight = y;
	}

	public void addChild(MagicRenderer model)
	{
	}

	public void setRotation(float x, float y, float z)
	{
	}

	public WorldPos Boxpos;
	public WorldPos BoxSize;
	public float scale;
	public MagicRenderer parent;
	public WorldPos origin;
	public WorldPos end;
	public WorldPos vec;
	public int cord = -1;

	public void setOrigin(WorldPos w)
	{
	}

	public void setOrigin(float x, float y, float z)
	{
	}

	public void setEnd(WorldPos w)
	{
	}

	public void setEnd(float x, float y, float z)
	{
	}

	public void setRotationPoint(float f, float g, float h)
	{
		this.rotationPointX = f;
		this.rotationPointY = g;
		this.rotationPointZ = h;

	}

	public void render(float f5)
	{

	}

}
