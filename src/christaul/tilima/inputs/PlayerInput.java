package christaul.tilima.inputs;

public abstract class PlayerInput
{
	public static final int IDLE = 0;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	protected int movement;

	public int getMovement()
	{
		return movement;
	}

	public abstract void update();
}
