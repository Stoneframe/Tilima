package christaul.tilima.entities;

import christaul.tilima.Handler;
import christaul.tilima.util.Vector2D;

public abstract class Creature
	extends Entity
{
	private static final double SPEED = 2.0;

	protected static Vector2D UP = new Vector2D(0, -1);
	protected static Vector2D DOWN = new Vector2D(0, 1);
	protected static Vector2D LEFT = new Vector2D(-1, 0);
	protected static Vector2D RIGHT = new Vector2D(1, 0);

	protected Vector2D targetPosition;

	public Creature(Handler handler, int width, int height, Vector2D position)
	{
		super(handler, width, height, position);

		targetPosition = position;
	}

	@Override
	public void update()
	{
		if (collisionWithTileAt(targetPosition))
		{
			targetPosition = currentPosition;
		}

		if (currentPosition.sub(targetPosition).length() > 0.1)
		{
			move(getDirection(), SPEED);
		}
		else
		{
			getInput();
		}
	}

	protected Vector2D getDirection()
	{
		return Vector2D.unit(currentPosition, targetPosition);
	}

	protected void move(Vector2D direction, double speed)
	{
		currentPosition = currentPosition.add(direction.mul(speed));
	}

	protected boolean collisionWithTileAt(Vector2D position)
	{
		return handler
			.getLevel()
			.getTileAtPixel((int)position.getX(), (int)position.getY())
			.isSolid();
	}

	protected abstract void getInput();
}
