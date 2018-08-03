package christaul.tilima.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;

	public static BufferedImage grass;

	public static BufferedImage player;

	static
	{
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png"));

		grass = tileSheet.crop(1 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);

		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));

		player = playerSheet.crop(0 * 64, 2 * 64, 64, 64);
	}
}
