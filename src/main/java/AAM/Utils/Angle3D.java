package AAM.Utils;

import java.util.Random;

public class Angle3D
{
	public double yaw;
	public double pitch;

	public Angle3D(double yaw, double pitch)
	{
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public static Angle3D getRandomAngle()
	{
		Random rand = new Random();
		return new Angle3D(rand.nextDouble() * 2 * Math.PI, rand.nextDouble() * Math.PI);
	}

	public String toString()
	{
		return "Yaw:" + this.yaw + " Pitch:" + this.pitch;
	}
}
