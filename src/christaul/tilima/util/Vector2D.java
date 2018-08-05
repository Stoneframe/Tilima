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

	public Vector2D mul(float factor)
	{
		return new Vector2D(factor * x, factor * y);
	}

	public double length()
	{
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}
