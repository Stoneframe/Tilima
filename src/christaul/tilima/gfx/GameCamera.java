package christaul.tilima.gfx;

import christaul.tilima.entities.Entity;
import christaul.tilima.util.Vector2D;

public class GameCamera
{
	private int width;
	private int height;

	private double xOffset;
	private double yOffset;

	public GameCamera(int width, int height, double xOffset, double yOffset)
	{
		this.width = width;
		this.height = height;

		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public double getXOffset()
	{
		return xOffset;
	}

	public double getYOffset()
	{
		return yOffset;
	}

	public void centerOnEntity(Entity entity)
	{
		Vector2D postion = entity.getPosition();

		xOffset = postion.getX() - width / 2 + entity.getWidth() / 2;
		yOffset = postion.getY() - height / 2 + entity.getHeight() / 2;
	}
}
