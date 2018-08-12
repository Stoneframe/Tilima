package christaul.tilima.entities;

import christaul.tilima.Handler;
import christaul.tilima.gfx.Animation;
import christaul.tilima.gfx.Assets;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.paths.PathFinder;
import christaul.tilima.util.Vector2D;

public class Player
	extends Creature
{
	private PlayerInput input;

	private Animation animationIdleUp;
	private Animation animationIdleDown;
	private Animation animationIdleLeft;
	private Animation animationIdleRight;

	private Animation animationMovingUp;
	private Animation animationMovingDown;
	private Animation animationMovingLeft;
	private Animation animationMovingRight;

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

		animationIdleUp = new Animation(Assets.player_idle_up, Integer.MAX_VALUE);
		animationIdleDown = new Animation(Assets.player_idle_down, Integer.MAX_VALUE);
		animationIdleLeft = new Animation(Assets.player_idle_left, Integer.MAX_VALUE);
		animationIdleRight = new Animation(Assets.player_idle_right, Integer.MAX_VALUE);

		animationMovingUp = new Animation(Assets.player_moving_up, 62);
		animationMovingDown = new Animation(Assets.player_moving_down, 62);
		animationMovingLeft = new Animation(Assets.player_moving_left, 62);
		animationMovingRight = new Animation(Assets.player_moving_right, 62);
	}

	@Override
	protected void updateInput()
	{
		input.update();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		if (mouseX != -1 && mouseY != -1)
		{
			Vector2D destination = new Vector2D(
					mouseX + handler.getGameCamera().getXOffset(),
					mouseY + handler.getGameCamera().getYOffset());

			PathFinder pathFinder = new PathFinder(handler.getLevel());

			path = pathFinder.findPath(currentPosition, destination);
		}
	}

	@Override
	protected Animation getCurrentAnimation()
	{
		if (direction.equals(UP))
		{
			return shouldMove() ? animationMovingUp : animationIdleUp;
		}
		else if (direction.equals(DOWN))
		{
			return shouldMove() ? animationMovingDown : animationIdleDown;
		}
		else if (direction.equals(LEFT))
		{
			return shouldMove() ? animationMovingLeft : animationIdleLeft;
		}
		else if (direction.equals(RIGHT))
		{
			return shouldMove() ? animationMovingRight : animationIdleRight;
		}
		else
		{
			return animationIdleDown;
		}
	}
}
