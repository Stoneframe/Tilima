package christaul.tilima.states;

import java.awt.Graphics;

import christaul.tilima.Handler;
import christaul.tilima.entities.Creature;
import christaul.tilima.entities.Player;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.levels.Level;
import christaul.tilima.util.Vector2D;

public class GameState
	extends State
{
	private Level level;

	public GameState(Handler handler, PlayerInput input)
	{
		super(handler);

		PlayerInput emptyInput = new PlayerInput()
		{
			public void update()
			{
			}
		};

		level = new Level(
				handler,
				new Player(handler, 32, 32, new Vector2D(64, 64), Creature.DOWN, input),
				new Player(handler, 32, 32, new Vector2D(96, 64), Creature.DOWN, emptyInput));

		handler.setLevel(level);
	}

	@Override
	public void update()
	{
		level.update();
	}

	@Override
	public void draw(Graphics g)
	{
		level.draw(g);
	}
}
