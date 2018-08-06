package christaul.tilima.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;

	public static BufferedImage grass;
	public static BufferedImage stone;

	public static BufferedImage[] player_idle_up;
	public static BufferedImage[] player_idle_down;
	public static BufferedImage[] player_idle_left;
	public static BufferedImage[] player_idle_right;

	public static BufferedImage[] player_moving_up;
	public static BufferedImage[] player_moving_left;
	public static BufferedImage[] player_moving_down;
	public static BufferedImage[] player_moving_right;

	static
	{
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png"));

		grass = tileSheet.crop(1 * WIDTH + 1, 1 * HEIGHT + 1, WIDTH, HEIGHT);
		stone = tileSheet.crop(4 * WIDTH + 4, 0 * HEIGHT + 0, WIDTH, HEIGHT);

		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));

		player_idle_up = cropPlayerImages(0, 0, 1, playerSheet);
		player_idle_left = cropPlayerImages(1, 0, 1, playerSheet);
		player_idle_down = cropPlayerImages(2, 0, 1, playerSheet);
		player_idle_right = cropPlayerImages(3, 0, 1, playerSheet);

		player_moving_up = cropPlayerImages(0, 1, 8, playerSheet);
		player_moving_left = cropPlayerImages(1, 1, 8, playerSheet);
		player_moving_down = cropPlayerImages(2, 1, 8, playerSheet);
		player_moving_right = cropPlayerImages(3, 1, 8, playerSheet);
	}

	protected static
		BufferedImage[] cropPlayerImages(int row, int start, int length, SpriteSheet sheet)
	{
		BufferedImage[] images = new BufferedImage[length];

		for (int col = start; col < start + length; col++)
		{
			images[col - start] = cropPlayerImage(col, row, sheet);
		}

		return images;
	}

	private static BufferedImage cropPlayerImage(int x, int y, SpriteSheet sheet)
	{
		final int side = 64;

		return sheet.crop(x * side, y * side, side, side);
	}
}
