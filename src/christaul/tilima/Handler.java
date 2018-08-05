package christaul.tilima;

import christaul.tilima.gfx.GameCamera;
import christaul.tilima.levels.Level;

public class Handler
{
	private Game game;

	private Level level;

	public Handler(Game game)
	{
		this.game = game;
	}

	public GameCamera getGameCamera()
	{
		return game.getGameCamera();
	}

	public int getWidth()
	{
		return game.getWidth();
	}

	public int getHeight()
	{
		return game.getHeight();
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}

	public Level getLevel()
	{
		return level;
	}

	public void setLevel(Level level)
	{
		this.level = level;
	}
}
