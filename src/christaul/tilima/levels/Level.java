package christaul.tilima.levels;

import java.awt.Graphics;

import christaul.tilima.tiles.Tile;

public class Level
{
	private int width;
	private int height;

	private int[][] tiles;

	public Level()
	{
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
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				getTile(x, y).draw(g, x * Tile.WIDTH, y * Tile.HEIGHT);
			}
		}
	}
}
