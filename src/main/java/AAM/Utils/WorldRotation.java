package AAM.Utils;

import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldRotation
{
	public Wec3 rotVec;
	public double angle;
	public Wec3 rotX = new Wec3(1, 0, 0);
	public Wec3 rotY = new Wec3(0, 1, 0);
	public Wec3 rotZ = new Wec3(0, 0, 1);

	public WorldRotation(ForgeDirection dir, double angle)
	{
		this.rotVec = new Wec3(dir);
		this.angle = angle;
		buildMatrix();
	}

	public WorldRotation(double x, double y, double z, double angle)
	{
		this.rotVec = new Wec3(x, y, z);
		this.angle = angle;
		buildMatrix();
	}

	public WorldRotation(Vec3 dir, double angle)
	{
		this.rotVec = new Wec3(dir);
		this.angle = angle;
		buildMatrix();
	}

	public void buildMatrix()
	{
		double cos = cos(this.angle);
		double sin = sin(this.angle);

		double x = this.rotVec.x;
		double y = this.rotVec.y;
		double z = this.rotVec.z;

		double a = cos + (1 - cos) * sqr(x);
		double b = (1 - cos) * x * y - sin * z;
		double c = (1 - cos) * x * z + sin * y;

		double e = (1 - cos) * y * x + sin * z;
		double f = cos + (1 - cos) * sqr(y);
		double g = (1 - cos) * y * z + sin * x;

		double h = (1 - cos) * z * x - sin * y;
		double i = (1 - cos) * z * y + sin * x;
		double k = cos + (1 - cos) * sqr(z);

		this.rotX = new Wec3(a, e, h);
		this.rotY = new Wec3(b, f, i);
		this.rotZ = new Wec3(c, g, k);

	}

	public static double cos(double a)
	{
		return Math.cos(a);
	}

	public static double sin(double a)
	{
		return Math.sin(a);
	}

	public static double sqr(double a)
	{
		return a * a;
	}
}
