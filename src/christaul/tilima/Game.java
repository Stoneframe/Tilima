package christaul.tilima;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import christaul.tilima.gfx.GameCamera;
import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.states.GameState;
import christaul.tilima.states.State;

public class Game
{
	private int width;
	private int height;

	private PlayerInput input;

	private boolean running;

	private State[] states;
	private State currentState;

	private GameLoop gameLoop;
	private Thread gameThread;

	private GameCamera gameCamera;

	private Handler handler;

	public Game(int width, int height, PlayerInput input)
	{
		this.width = width;
		this.height = height;

		this.input = input;

		running = false;
	}

	public synchronized void start(Canvas canvas)
	{
		if (running) return;

		running = true;

		gameLoop = new GameLoop(canvas);

		gameThread = new Thread(gameLoop);
		gameThread.start();
	}

	public synchronized void stop()
	{
		if (!running) return;

		running = false;

		try
		{
			gameThread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public GameCamera getGameCamera()
	{
		return gameCamera;
	}

	private class GameLoop
		implements
			Runnable
	{
		private Canvas canvas;

		private Graphics g;

		public GameLoop(Canvas canvas)
		{
			this.canvas = canvas;

			gameCamera = new GameCamera(width, height, 0, 0);

			handler = new Handler(Game.this);

			states = new State[]
			{
					new GameState(handler, input)
			};

			currentState = states[0];
		}

		@Override
		public void run()
		{
			while (true)
			{
				update();
				draw(g);

				try
				{
					Thread.sleep(17);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

		private void update()
		{
			currentState.update();
		}

		private void draw(Graphics g)
		{
			BufferStrategy bs = canvas.getBufferStrategy();

			if (bs == null)
			{
				canvas.createBufferStrategy(3);
				return;
			}

			g = bs.getDrawGraphics();

			g.fillRect(0, 0, width, height);

			currentState.draw(g);

			bs.show();
			g.dispose();
		}
	}
}
