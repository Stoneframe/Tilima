package christaul.tilima.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardPlayerInput
	extends PlayerInput
	implements
		KeyListener
{
	private boolean[] keys = new boolean[256];

	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void update()
	{
		if (keys[KeyEvent.VK_UP])
		{
			movement = UP;
		}
		else if (keys[KeyEvent.VK_DOWN])
		{
			movement = DOWN;
		}
		else if (keys[KeyEvent.VK_LEFT])
		{
			movement = LEFT;
		}
		else if (keys[KeyEvent.VK_RIGHT])
		{
			movement = RIGHT;
		}
		else
		{
			movement = IDLE;
		}
	}
}
