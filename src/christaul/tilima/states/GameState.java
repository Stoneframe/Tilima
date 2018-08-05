package christaul.tilima.states;

import java.awt.Graphics;

import christaul.tilima.Handler;
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

		level = new Level(handler);
		handler.setLevel(level);

		player = new Player(handler, 32, 32, new Vector2D(64, 64), input);
	}

	@Override
	public void update()
	{
		level.update();
		player.update();

		handler.getGameCamera().centerOnEntity(player);
	}

	@Override
	public void draw(Graphics g)
	{
		level.draw(g);
		player.draw(g);
	}
}
