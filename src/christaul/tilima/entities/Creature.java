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

	protected Creature(
			Handler handler,
			int width,
			int height,
			Vector2D position,
			Vector2D direction)
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
		g.drawImage(
			animation.getCurrentFrame(),
			(int)(currentPosition.getX() - handler.getGameCamera().getXOffset()),
			(int)(currentPosition.getY() - handler.getGameCamera().getYOffset()),
			Tile.WIDTH,
			Tile.HEIGHT,
			null);
	}

	protected abstract class Phase
	{
		public void update()
		{
			updateInternal();

			animation = getCurrentAnimation();
			animation.update();
		}

		protected abstract void updateInternal();

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

		@Override
		protected void updateInternal()
		{
			if (tilesMoved >= 10) return;

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
		}

		protected abstract void updateInput();

		protected boolean shouldMove()
		{
			return !currentPosition.equals(targetPosition);
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
			currentPosition = currentPosition.add(direction.mul(SPEED));

			int nextTileX = (int)(currentPosition.getX() / Tile.WIDTH);
			int nextTileY = (int)(currentPosition.getY() / Tile.HEIGHT);

			if (nextTileX != tileX || nextTileY != tileY) tilesMoved++;

			tileX = nextTileX;
			tileY = nextTileY;
		}
	}

	protected abstract class TestPhase
		extends Phase
	{
		private boolean endPhasePrinted = false;

		@Override
		public void update()
		{
			if (!endPhasePrinted)
			{
				System.out.println("");

				endPhasePrinted = true;
			}
		}

		@Override
		public abstract Animation getCurrentAnimation();
	}
}
