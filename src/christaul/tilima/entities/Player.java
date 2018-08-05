package christaul.tilima.entities;

import java.awt.Graphics;

import christaul.tilima.Handler;
import christaul.tilima.gfx.Assets;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.tiles.Tile;
import christaul.tilima.util.Vector2D;

public class Player
	extends Creature
{
	private PlayerInput input;

	private int direction;

	public Player(Handler handler, int width, int height, Vector2D position, PlayerInput input)
	{
		super(handler, width, height, position);

		this.input = input;
	}

	@Override
	public void update()
	{
		float speed = 1.0f;

		if (position.sub(targetPosition).length() > 0.1)
		{
			switch (direction)
			{
				case PlayerInput.UP:
					move(UP, speed);
					break;
				case PlayerInput.DOWN:
					move(DOWN, speed);
					break;
				case PlayerInput.LEFT:
					move(LEFT, speed);
					break;
				case PlayerInput.RIGHT:
					move(RIGHT, speed);
					break;
			}
		}
		else
		{
			input.update();

			direction = input.getMovement();

			switch (input.getMovement())
			{
				case PlayerInput.UP:
					targetPosition = position.add(UP.mul(Tile.HEIGHT));
					break;
				case PlayerInput.DOWN:
					targetPosition = position.add(DOWN.mul(Tile.HEIGHT));
					break;
				case PlayerInput.LEFT:
					targetPosition = position.add(LEFT.mul(Tile.WIDTH));
					break;
				case PlayerInput.RIGHT:
					targetPosition = position.add(RIGHT.mul(Tile.WIDTH));
					break;
			}
		}
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage(
			Assets.player,
			(int)(position.getX() - handler.getGameCamera().getXOffset()),
			(int)(position.getY() - handler.getGameCamera().getYOffset()),
			Tile.WIDTH,
			Tile.HEIGHT,
			null);
	}
}
