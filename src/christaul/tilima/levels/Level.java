package christaul.tilima.levels;

import java.awt.Graphics;

import christaul.tilima.Game;
import christaul.tilima.tiles.Tile;

public class Level
{
	private Game game;

	private int width;
	private int height;

	private int[][] tiles;

	public Level(Game game)
	{
		this.game = game;

		width = 5;
		height = 5;

		tiles = new int[width][height];

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				tiles[x][y] = 0;
			}
		}
	}

	public Tile getTile(int x, int y)
	{
		Tile tile = Tile.tiles[tiles[x][y]];

		return tile != null ? tile : Tile.grassTile;
	}

	public void update()
	{

	}

	public void draw(Graphics g)
	{
		double xOffset = game.getGameCamera().getXOffset();
		double yOffset = game.getGameCamera().getYOffset();

		int xStart = (int)Math.max(0, xOffset / Tile.WIDTH);
		int yStart = (int)Math.max(0, yOffset / Tile.HEIGHT);

		int xEnd = (int)Math.min(width, (xOffset + game.getWidth()) / Tile.WIDTH + 1);
		int yEnd = (int)Math.min(height, (yOffset + game.getHeight()) / Tile.HEIGHT + 1);

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
