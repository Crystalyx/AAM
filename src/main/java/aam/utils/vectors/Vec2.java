package aam.utils.vectors;

public class Vec2
{
	public double x;
	public double y;

	public Vec2()
	{
		x = 0;
		y = 0;
	}

	public Vec2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double length()
	{
		return Math.hypot(x, y);
	}

	public Vec2 add(Vec2 v)
	{
		return new Vec2(x + v.x, y + v.y);
	}

	public Vec2 sub(Vec2 v)
	{
		return new Vec2(x - v.x, y - v.y);
	}

	public Vec2 multiply(double l)
	{
		return new Vec2(x * l, y * l);
	}

	public Vec2 multiply(double lx, double ly)
	{
		return new Vec2(x * lx, y * ly);
	}

	public Vec2 divide(double l)
	{
		return new Vec2(x / l, y / l);
	}

	public Vec2 divide(double lx, double ly)
	{
		return new Vec2(x / lx, y / ly);
	}

	public void normalize()
	{
		double l = this.length();
		if (l == 0)
		{
			x = 0;
			y = 0;
			return;
		}
		x /= l;
		y /= l;
	}

	public double dotProduct(Vec2 v)
	{
		return x * v.x + y * v.y;
	}

	/**
	 * this - vector - row
	 * 
	 * @param v
	 * @return
	 */
	public Vec2 crossProduct(Matrix2 v)
	{
		return new Vec2(x * v.arr[0][0] + y * v.arr[0][1], x * v.arr[1][0] + y * v.arr[1][1]);
	}

	public AABB2 extend(double l)
	{
		return new AABB2(this, this.add(new Vec2(l, l)));
	}

	public AABB2 extend(Vec2 v)
	{
		return new AABB2(this, this.add(v));
	}

	public AABB2 extendBoth(double l)
	{
		Vec2 el = new Vec2(l, l);
		return new AABB2(this.sub(el), this.add(el));
	}

	public AABB2 extendBoth(Vec2 v)
	{
		return new AABB2(this.sub(v), this.add(v));
	}

	@Override
	public String toString()
	{
		return "[" + x + "; " + y + "]";
	}

	public Vec2 copy()
	{
		return new Vec2(x, y);
	}

	public double distanceTo(Vec2 v)
	{
		Vec2 dv = this.sub(v);
		return dv.length();
	}
}
