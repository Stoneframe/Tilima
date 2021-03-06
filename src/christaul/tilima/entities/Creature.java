package christaul.tilima.entities;

import java.awt.Graphics;

import christaul.tilima.Handler;
import christaul.tilima.gfx.Animation;
import christaul.tilima.paths.Path;
import christaul.tilima.paths.PathNode;
import christaul.tilima.tiles.Tile;
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

	protected Path path;

	protected Creature(
			Handler handler,
			int width,
			int height,
			Vector2D position,
			Vector2D direction)
	{
		super(handler, width, height, position);

		this.targetPosition = position;
		this.direction = direction;
	}

	@Override
	public void update()
	{
		if (collision())
		{
			cancelMovement();
		}
		else if (shouldMove())
		{
			move();
		}
		else
		{
			updateInput();
		}

		animation = getCurrentAnimation();
		animation.update();
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage(
			animation.getCurrentFrame(),
			(int)(currentPosition.getX() - handler.getGameCamera().getXOffset()),
			(int)(currentPosition.getY() - handler.getGameCamera().getYOffset()),
			Tile.WIDTH,
			Tile.HEIGHT,
			null);
	}

	protected boolean shouldMove()
	{
		return path != null && !path.isEmpty();
	}

	private boolean collision()
	{
		return collisionWithTile() || collisionWithEntity();
	}

	private void cancelMovement()
	{
		targetPosition = currentPosition;
	}

	private boolean collisionWithTile()
	{
		return handler.getLevel().getTileAt(targetPosition).isSolid();
	}

	private boolean collisionWithEntity()
	{
		for (Entity entity : handler.getLevel().getEntityManager().getEntities())
		{
			if (entity.equals(Creature.this)) continue;

			if (entity.getBounds().overlaps(targetPosition))
			{
				return true;
			}
		}

		return false;
	}

	private void move()
	{
		PathNode pathNode = path.firstPathNode();

		targetPosition = new Vector2D(pathNode.getX() * Tile.WIDTH, pathNode.getY() * Tile.HEIGHT);

		if (currentPosition.equals(targetPosition))
		{
			path.remove(pathNode);

			if (path.isEmpty())
			{
				path = null;
			}
		}
		else
		{
			direction = Vector2D.unit(currentPosition, targetPosition);
			currentPosition = currentPosition.add(direction.mul(SPEED));
		}
	}

	protected abstract void updateInput();

	protected abstract Animation getCurrentAnimation();
}
