package christaul.tilima.entities;

import java.awt.Graphics;

import christaul.tilima.Handler;
import christaul.tilima.gfx.Animation;
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

	protected Vector2D direction;

	protected Phase phase;

	public Creature(Handler handler, int width, int height, Vector2D position, Vector2D direction)
	{
		super(handler, width, height, position);

		this.direction = direction;
	}

	@Override
	public void update()
	{
		phase.update();
	}

	@Override
	public void draw(Graphics g)
	{
		phase.draw(g);
	}

	private abstract class Phase
	{
		public abstract void update();

		public void draw(Graphics g)
		{
			g.drawImage(
				getCurrentAnimation().getCurrentFrame(),
				(int)(currentPosition.getX() - handler.getGameCamera().getXOffset()),
				(int)(currentPosition.getY() - handler.getGameCamera().getYOffset()),
				Tile.WIDTH,
				Tile.HEIGHT,
				null);
		}

		protected abstract Animation getCurrentAnimation();
	}

	protected abstract class MovementPhase
		extends Phase
	{
		protected Vector2D targetPosition;

		private int tileX;
		private int tileY;

		private int tilesMoved;

		public MovementPhase()
		{
			targetPosition = currentPosition;

			tileX = (int)(currentPosition.getX() / Tile.WIDTH);
			tileY = (int)(currentPosition.getY() / Tile.HEIGHT);

			tilesMoved = 0;
		}

		public void update()
		{
			if (tilesMoved > 10) return;

			if (collisionAt(targetPosition))
			{
				targetPosition = currentPosition;
			}

			if (isMoving())
			{
				move(direction, SPEED);

				int nextTileX = (int)(currentPosition.getX() / Tile.WIDTH);
				int nextTileY = (int)(currentPosition.getY() / Tile.HEIGHT);

				if (nextTileX != tileX || nextTileY != tileY) tilesMoved++;

				tileX = nextTileX;
				tileY = nextTileY;
			}
			else
			{
				updateInput();
			}

			getCurrentAnimation().update();
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
				if (entity.equals(Creature.this)) continue;

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

		protected abstract void updateInput();
	}
}
