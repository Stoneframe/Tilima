package christaul.tilima.entities.statics;

import christaul.tilima.Handler;
import christaul.tilima.entities.Entity;
import christaul.tilima.util.Vector2D;

public abstract class StaticEntity
	extends Entity
{
	public StaticEntity(Handler handler, int width, int height, Vector2D position)
	{
		super(handler, width, height, position);
	}
}
