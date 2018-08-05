package christaul.tilima.states;

import java.awt.Graphics;

import christaul.tilima.Game;
import christaul.tilima.entities.Player;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.levels.Level;
import christaul.tilima.util.Vector2D;

public class GameState
	extends State
{
	private Game game;

	private Level level;

	private Player player;

	public GameState(Game game, PlayerInput input)
	{
		this.game = game;

		level = new Level(game);

		player = new Player(game, 32, 32, new Vector2D(64, 64), input);
	}

	@Override
	public void update()
	{
		level.update();
		player.update();

		game.getGameCamera().centerOnEntity(player);
	}

	@Override
	public void draw(Graphics g)
	{
		level.draw(g);
		player.draw(g);
	}
}
