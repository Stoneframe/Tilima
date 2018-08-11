package christaul.tilima.levels;

import java.awt.Graphics;

import christaul.tilima.Handler;
import christaul.tilima.entities.EntityManager;
import christaul.tilima.entities.Player;
import christaul.tilima.tiles.Tile;
import christaul.tilima.util.Vector2D;

public class Level
{
	private Handler handler;

	private int width;
	private int height;

	private int[][] tiles;

	private EntityManager entityManager;

	public Level(Handler handler, Player... players)
	{
		this.handler = handler;

		width = 8;
		height = 8;

		tiles = new int[][]
		{
				{
						2, 2, 2, 2, 2, 2, 2, 2
				},
				{
						2, 1, 1, 1, 1, 1, 1, 2
				},
				{
						2, 1, 1, 2, 2, 2, 1, 2
				},
				{
						2, 1, 1, 2, 1, 2, 1, 2
				},
				{
						2, 1, 1, 2, 1, 2, 1, 2
				},
				{
						2, 1, 1, 1, 1, 1, 1, 2
				},
				{
						2, 1, 1, 1, 1, 1, 1, 2
				},
				{
						2, 2, 2, 2, 2, 2, 2, 2
				}
		};

		entityManager = new EntityManager(handler, players);
	}

	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	public Tile getTileAt(Vector2D position)
	{
		return getTile((int)(position.getX() / Tile.WIDTH), (int)(position.getY() / Tile.HEIGHT));
	}

	public Tile getTileAtPixel(int x, int y)
	{
		return getTile(x / Tile.WIDTH, y / Tile.HEIGHT);
	}

	public Tile getTile(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
		{
			return Tile.emptyTile;
		}

		Tile tile = Tile.tiles[tiles[x][y]];

		return tile != null ? tile : Tile.grassTile;
	}

	public void update()
	{
		entityManager.update();
	}

	public void draw(Graphics g)
	{
		double xOffset = handler.getGameCamera().getXOffset();
		double yOffset = handler.getGameCamera().getYOffset();

		int xStart = (int)Math.max(0, xOffset / Tile.WIDTH);
		int yStart = (int)Math.max(0, yOffset / Tile.HEIGHT);

		int xEnd = (int)Math.min(width, (xOffset + handler.getWidth()) / Tile.WIDTH + 1);
		int yEnd = (int)Math.min(height, (yOffset + handler.getHeight()) / Tile.HEIGHT + 1);

		for (int y = yStart; y < yEnd; y++)
		{
			for (int x = xStart; x < xEnd; x++)
			{
				getTile(x, y).draw(
					g,
					(int)(x * Tile.WIDTH - xOffset),
					(int)(y * Tile.HEIGHT - yOffset));
			}
		}

		entityManager.draw(g);
	}
}
