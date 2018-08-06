package christaul.tilima.gfx;

import java.awt.image.BufferedImage;

public class Animation
{
	private BufferedImage[] frames;

	private int speed;
	private int index;

	private long lastTime;
	private long timer;

	public Animation(BufferedImage[] frames, int speed)
	{
		this.frames = frames;
		this.speed = speed;
		this.index = 0;

		lastTime = System.currentTimeMillis();
		timer = 0;
	}

	public void update()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed)
		{
			index++;
			timer = 0;

			if (index == frames.length) index = 0;
		}
	}

	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}
}
