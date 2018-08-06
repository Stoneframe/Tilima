package christaul.tilima.entities;

import java.awt.Graphics;

import christaul.tilima.Handler;
import christaul.tilima.util.Rectangle2D;
import christaul.tilima.util.Vector2D;

public abstract class Entity
{
	protected Handler handler;

	protected int width;
	protected int height;

	protected Vector2D currentPosition;

	public Entity(Handler handler, int width, int height, Vector2D position)
	{
		this.handler = handler;

		this.width = width;
		this.height = height;

		this.currentPosition = position;
	}

	public Vector2D getPosition()
	{
		return currentPosition;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public Rectangle2D getBounds()
	{
		return new Rectangle2D(
				(int)currentPosition.getX(),
				(int)currentPosition.getY(),
				width,
				height);
	}

	public abstract void update();

	public abstract void draw(Graphics g);
}
