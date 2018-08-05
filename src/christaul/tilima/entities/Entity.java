package christaul.tilima.entities;

import java.awt.Graphics;

import christaul.tilima.Game;
import christaul.tilima.util.Vector2D;

public abstract class Entity
{
	protected Game game;
	
	protected int width;
	protected int height;

	protected Vector2D position;

	public Entity(Game game, int width, int height, Vector2D position)
	{
		this.game = game;
		
		this.width = width;
		this.height = height;

		this.position = position;
	}

	public Vector2D getPosition()
	{
		return position;
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
