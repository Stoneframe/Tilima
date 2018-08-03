package christaul.tilima.entities;

import java.awt.Graphics;

import christaul.tilima.gfx.Assets;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.tiles.Tile;

public class Player
	extends Creature
{
	private PlayerInput input;

	private int direction;
	private float targetX, targetY;

	public Player(float x, float y, PlayerInput input)
	{
		super(x, y);

		targetX = x;
		targetY = y;

		this.input = input;
	}

	@Override
	public void update()
	{
		input.update();

		if (targetY != y || targetX != x)
		{
			switch (direction)
			{
				case PlayerInput.UP:
					y -= 0.2;
					break;
				case PlayerInput.DOWN:
					y += 0.2;
					break;
				case PlayerInput.LEFT:
					x -= 0.2;
					break;
				case PlayerInput.RIGHT:
					x += 0.2;
					break;
			}
		}
		else
		{
			direction = input.getMovement();

			switch (input.getMovement())
			{
				case PlayerInput.UP:
					targetY = y - Tile.HEIGHT;
					break;
				case PlayerInput.DOWN:
					targetY = y + Tile.HEIGHT;
					break;
				case PlayerInput.LEFT:
					targetX = x - Tile.WIDTH;
					break;
				case PlayerInput.RIGHT:
					targetX = x + Tile.WIDTH;
					break;
			}
		}
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage(
			Assets.player,
			(int)x,
			(int)y,
			Tile.WIDTH,
			Tile.HEIGHT,
			null);
	}
}
