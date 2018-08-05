package christaul.tilima.states;

import java.awt.Graphics;

import christaul.tilima.Handler;

public abstract class State
{
	protected Handler handler;

	protected State(Handler handler)
	{
		this.handler = handler;
	}

	public abstract void update();

	public abstract void draw(Graphics g);
}
