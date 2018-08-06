package christaul.tilima.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import christaul.tilima.Handler;
import christaul.tilima.gfx.Animation;
import christaul.tilima.gfx.Assets;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.tiles.Tile;
import christaul.tilima.util.Vector2D;

public class Player
	extends Creature
{
	private PlayerInput input;

	private Animation animationUp;
	private Animation animationDown;
	private Animation animationLeft;
	private Animation animationRight;

	public Player(
			Handler handler,
			int width,
			int height,
			Vector2D position,
			Vector2D direction,
			PlayerInput input)
	{
		super(handler, width, height, position, direction);

		this.input = input;

		animationUp = new Animation(Assets.player_moving_up, 62);
		animationDown = new Animation(Assets.player_moving_down, 62);
		animationLeft = new Animation(Assets.player_moving_left, 62);
		animationRight = new Animation(Assets.player_moving_right, 62);
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawImage(
			getCurrentAnimationFrame(),
			(int)(currentPosition.getX() - handler.getGameCamera().getXOffset()),
			(int)(currentPosition.getY() - handler.getGameCamera().getYOffset()),
			Tile.WIDTH,
			Tile.HEIGHT,
			null);
	}

	private BufferedImage getCurrentAnimationFrame()
	{
		if (direction.equals(UP))
		{
			return isMoving() ? animationUp.getCurrentFrame() : Assets.player_up;
		}
		else if (direction.equals(DOWN))
		{
			return isMoving() ? animationDown.getCurrentFrame() : Assets.player_down;
		}
		else if (direction.equals(LEFT))
		{
			return isMoving() ? animationLeft.getCurrentFrame() : Assets.player_left;
		}
		else if (direction.equals(RIGHT))
		{
			return isMoving() ? animationRight.getCurrentFrame() : Assets.player_right;
		}
		else
		{
			System.exit(1);
			return null;
		}
	}

	@Override
	protected void updateAnimation()
	{
		if (isMoving())
		{
			if (direction.equals(UP))
			{
				animationUp.update();
			}
			else if (direction.equals(DOWN))
			{
				animationDown.update();
			}
			else if (direction.equals(LEFT))
			{
				animationLeft.update();
			}
			else if (direction.equals(RIGHT))
			{
				animationRight.update();
			}
		}
	}

	@Override
	protected void updateInput()
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

		if (isMoving())
		{
			direction = getDirection();
		}
	}
}
