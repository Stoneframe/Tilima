package christaul.tilima.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile
{
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;

	public static Tile[] tiles = new Tile[256];
	public static Tile emptyTile = new EmptyTile(0);
	public static Tile grassTile = new GrassTile(1);
	public static Tile stoneTile = new StoneTile(2);

	protected BufferedImage texture;

	protected final int id;

	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;

		tiles[id] = this;
	}

	public int getId()
	{
		return id;
	}

	public boolean isSolid()
	{
		return false;
	}

	public void update()
	{
	}

	public void draw(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, null);
	}
}
