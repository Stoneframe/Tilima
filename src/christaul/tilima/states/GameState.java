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

	private Player player;

	public GameState(Handler handler, PlayerInput input)
	{
		super(handler);

		player = new Player(handler, 32, 32, new Vector2D(64, 64), Creature.DOWN, input);
		
		level = new Level(handler, player);
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
