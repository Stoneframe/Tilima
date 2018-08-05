package christaul.tilima.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import christaul.tilima.Handler;
import christaul.tilima.util.Vector2D;

public abstract class Entity
{
	protected Handler handler;

	protected int width;
	protected int height;

	protected Vector2D currentPosition;

	protected Rectangle bounds;

	public Entity(Handler handler, int width, int height, Vector2D position)
	{
		this.handler = handler;

		this.width = width;
		this.height = height;

		this.currentPosition = position;

		bounds = new Rectangle((int)position.getX(), (int)position.getY(), width, height);
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

	public abstract void update();

	public abstract void draw(Graphics g);
}
