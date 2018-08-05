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

	public Player(Handler handler, int width, int height, Vector2D position, PlayerInput input)
	{
		super(handler, width, height, position);

		this.input = input;
	}

	@Override
	protected void getInput()
	{
		input.update();

		switch (input.getMovement())
		{
			case PlayerInput.UP:
				targetPosition = currentPosition.add(UP.mul(Tile.HEIGHT));
				break;
			case PlayerInput.DOWN:
				targetPosition = currentPosition.add(DOWN.mul(Tile.HEIGHT));
				break;
			case PlayerInput.LEFT:
				targetPosition = currentPosition.add(LEFT.mul(Tile.WIDTH));
				break;
			case PlayerInput.RIGHT:
				targetPosition = currentPosition.add(RIGHT.mul(Tile.WIDTH));
				break;
		}
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage(
			Assets.player,
			(int)(currentPosition.getX() - handler.getGameCamera().getXOffset()),
			(int)(currentPosition.getY() - handler.getGameCamera().getYOffset()),
			Tile.WIDTH,
			Tile.HEIGHT,
			null);
	}
}
