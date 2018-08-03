package christaul.tilima;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import christaul.tilima.inputs.PlayerInput;
import christaul.tilima.states.GameState;
import christaul.tilima.states.State;

public class Game
{
	private int width;
	private int height;

	private boolean running;

	private State[] states;
	private State currentState;

	private GameLoop gameLoop;

	public Game(int width, int height, PlayerInput input)
	{
		this.width = width;
		this.height = height;

		running = false;

		states = new State[]
		{
				new GameState(input)
		};

		currentState = states[0];
	}

	public synchronized void start(Canvas canvas)
	{
		if (running) return;

		running = true;

		gameLoop = new GameLoop(canvas);
		gameLoop.start();
	}

	public synchronized void stop()
	{
		if (!running) return;

		running = false;

		try
		{
			gameLoop.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private class GameLoop
		extends Thread
	{
		private Canvas canvas;

		private Graphics g;

		public GameLoop(Canvas canvas)
		{
			this.canvas = canvas;
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
