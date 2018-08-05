package christaul.tilima.util;

public class Vector2D
{
	private double x;
	private double y;

	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public Vector2D add(Vector2D vector)
	{
		return new Vector2D(x + vector.x, y + vector.y);
	}

	public Vector2D sub(Vector2D vector)
	{
		return new Vector2D(x - vector.x, y - vector.y);
	}

	public Vector2D mul(double factor)
	{
		return new Vector2D(factor * x, factor * y);
	}

	public double length()
	{
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public static Vector2D unit(Vector2D point1, Vector2D point2)
	{
		Vector2D diff = point2.sub(point1);

		return new Vector2D(diff.getX() / diff.length(), diff.getY() / diff.length());
	}
}
