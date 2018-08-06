package christaul.tilima.levels;

import java.awt.Graphics;

import christaul.tilima.Handler;
import christaul.tilima.tiles.Tile;

public class Level
{
	private Handler handler;

	private int width;
	private int height;

	private int[][] tiles;

	public Level(Handler handler)
	{
		this.handler = handler;

		width = 8;
		height = 8;

		tiles = new int[][]
		{
				{
						1, 1, 1, 1, 1, 1, 1, 1
				},
				{
						1, 0, 0, 0, 0, 0, 0, 1
				},
				{
						1, 0, 0, 1, 1, 1, 0, 1
				},
				{
						1, 0, 0, 1, 0, 1, 0, 1
				},
				{
						1, 0, 0, 1, 0, 1, 0, 1
				},
				{
						1, 0, 0, 0, 0, 0, 0, 1
				},
				{
						1, 0, 0, 0, 0, 0, 0, 1
				},
				{
						1, 1, 1, 1, 1, 1, 1, 1
				}
		};
	}

	public Tile getTileAtPixel(int x, int y)
	{
		return getTile(x / Tile.WIDTH, y / Tile.HEIGHT);
	}

	public Tile getTile(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
		{
			return Tile.grassTile;
		}

		Tile tile = Tile.tiles[tiles[x][y]];

		return tile != null ? tile : Tile.grassTile;
	}

	public void update()
	{

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
	}
}
