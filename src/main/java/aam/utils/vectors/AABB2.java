package aam.utils.vectors;

import aam.utils.MathUtils;

public class AABB2
{
	public double ox;
	public double oy;
	public double tx;
	public double ty;
	public String tag = "";

	public AABB2(double ox, double oy, double tx, double ty)
	{
		this.ox = ox;
		this.oy = oy;
		this.tx = tx;
		this.ty = ty;
		arrange();
	}

	public AABB2(Vec2 o, Vec2 t)
	{
		ox = o.x;
		oy = o.y;
		tx = t.x;
		ty = t.y;
		arrange();
	}

	public void arrange()
	{
		if (ox > tx)
		{
			double dx = ox;
			ox = tx;
			tx = dx;
		}
		if (oy > ty)
		{
			double dy = oy;
			oy = ty;
			ty = dy;
		}
	}

	public boolean intersects(AABB2 aabb)
	{
		return MathUtils.isInLimit(aabb.ox, ox, tx) && MathUtils.isInLimit(aabb.oy, oy, ty) || MathUtils.isInLimit(aabb.tx, ox, tx) && MathUtils.isInLimit(aabb.oy, oy, ty)
				|| MathUtils.isInLimit(aabb.tx, ox, tx) && MathUtils.isInLimit(aabb.ty, oy, ty) || MathUtils.isInLimit(aabb.ty, oy, ty) && MathUtils.isInLimit(aabb.ox, ox, tx);
	}

	public Vec2 getOrigin()
	{
		return new Vec2(ox, oy);
	}

	public Vec2 getTarget()
	{
		return new Vec2(tx, ty);
	}

	public AABB2 multiply(double scale)
	{
		double ox = this.ox * scale;
		double oy = this.oy * scale;
		double tx = this.tx * scale;
		double ty = this.ty * scale;

		return new AABB2(ox, oy, tx, ty).setTag(tag);
	}

	@Override
	public String toString()
	{
		return "[" + ox + ";" + oy + ";" + tx + ";" + ty + ";" + "]";
	}

	public AABB2 extend(int scale)
	{
		AABB2 aabb = new AABB2(ox, oy, tx + scale, ty + scale).setTag(tag);
		return aabb;
	}

	public AABB2 extendBoth(int scale)
	{
		AABB2 aabb = new AABB2(ox - scale, oy - scale, tx + scale, ty + scale).setTag(tag);
		return aabb;
	}

	public AABB2 cut(int dx, int dy, int i, int j)
	{
		return new AABB2(ox + dx, oy + dy, tx - i, ty - j).setTag(tag);
	}

	public AABB2 setTag(String tag)
	{
		this.tag = tag;
		return this;
	}

	public AABB2 move(Vec2 v)
	{
		return new AABB2(ox + v.x, oy + v.y, tx + v.x, ty + v.y).setTag(tag);
	}
}
