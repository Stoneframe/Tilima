package christaul.tilima.states;

import java.awt.Graphics;

import christaul.tilima.entities.Player;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.levels.Level;

public class GameState
	extends State
{
	private Level level;

	private Player player;

	public GameState(PlayerInput input)
	{
		level = new Level();

		player = new Player(64, 64, input);
	}

	@Override
	public void update()
	{
		level.update();
		player.update();
	}

	@Override
	public void draw(Graphics g)
	{
		level.draw(g);
		player.draw(g);
	}
}
