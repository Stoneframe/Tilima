package christaul.tilima.entities;

import christaul.tilima.Handler;
import christaul.tilima.util.Vector2D;

public abstract class Creature
	extends Entity
{
	public static Vector2D UP = new Vector2D(0, -1);
	public static Vector2D DOWN = new Vector2D(0, 1);
	public static Vector2D LEFT = new Vector2D(-1, 0);
	public static Vector2D RIGHT = new Vector2D(1, 0);

	private static final double SPEED = 1.0;

	protected Vector2D targetPosition;

	protected Vector2D direction;

	public Creature(Handler handler, int width, int height, Vector2D position, Vector2D direction)
	{
		super(handler, width, height, position);

		targetPosition = position;

		this.direction = direction;
	}

	@Override
	public void update()
	{
		if (collisionAt(targetPosition))
		{
			targetPosition = currentPosition;
		}

		if (isMoving())
		{
			move(direction, SPEED);
		}
		else
		{
			updateInput();
		}

		updateAnimation();
	}

	protected boolean collisionAt(Vector2D position)
	{
		return collisionWithTileAt(position) || collisionWithEntityAt(position);
	}

	protected boolean collisionWithTileAt(Vector2D position)
	{
		return handler.getLevel().getTileAt(position).isSolid();
	}

	protected boolean collisionWithEntityAt(Vector2D position)
	{
		for (Entity entity : handler.getLevel().getEntityManager().getEntities())
		{
			if (entity.equals(this)) continue;

			if (entity.getBounds().overlaps(position))
			{
				return true;
			}
		}

		return false;
	}

	protected boolean isMoving()
	{
		return !currentPosition.equals(targetPosition);
	}

	protected void move(Vector2D direction, double speed)
	{
		currentPosition = currentPosition.add(direction.mul(speed));
	}

	protected Vector2D getDirection()
	{
		return Vector2D.unit(currentPosition, targetPosition);
	}

	protected abstract void updateAnimation();

	protected abstract void updateInput();
}
