package christaul.tilima.entities;

import christaul.tilima.Game;
import christaul.tilima.util.Vector2D;

public abstract class Creature
	extends Entity
{
	protected static Vector2D UP = new Vector2D(0, -1);
	protected static Vector2D DOWN = new Vector2D(0, 1);
	protected static Vector2D LEFT = new Vector2D(-1, 0);
	protected static Vector2D RIGHT = new Vector2D(1, 0);

	protected Vector2D targetPosition;

	public Creature(Game game, int width, int height, Vector2D position)
	{
		super(game, width, height, position);

		targetPosition = position;
	}

	protected void move(Vector2D direction, float speed)
	{
		position = position.add(direction.mul(speed));
	}
}
