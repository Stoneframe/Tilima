package christaul.tilima.util;

import java.awt.Point;
import java.awt.Rectangle;

public class Rectangle2D
{
	private Rectangle rectangle;

	public Rectangle2D(int x, int y, int width, int height)
	{
		rectangle = new Rectangle(x, y, width, height);
	}

	public Rectangle2D(Vector2D position, int width, int height)
	{
		this((int)position.getX(), (int)position.getY(), width, height);
	}

	public boolean intersects(Rectangle2D rectangle)
	{
		return this.rectangle.intersects(rectangle.rectangle);
	}

	public boolean overlaps(Vector2D vector)
	{
		return this.rectangle.contains(new Point((int)vector.getX(), (int)vector.getY()));
	}
}
