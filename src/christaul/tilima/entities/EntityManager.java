package christaul.tilima.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import christaul.tilima.Handler;

public class EntityManager
{
	private Handler handler;

	private Player player;
	private List<Entity> entities;

	public EntityManager(Handler handler, Player... players)
	{
		this.handler = handler;
		this.player = players[0];

		entities = new ArrayList<>();

		for (Player player : players)
		{
			entities.add(player);
		}
	}

	public Handler getHandler()
	{
		return handler;
	}

	public void setHandler(Handler handler)
	{
		this.handler = handler;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public List<Entity> getEntities()
	{
		return entities;
	}

	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}

	public void update()
	{
		for (int i = 0; i < entities.size(); i++)
		{
			Entity entity = entities.get(i);

			entity.update();
		}

		handler.getGameCamera().centerOnEntity(player);
	}

	public void draw(Graphics g)
	{
		for (Entity entity : entities)
		{
			entity.draw(g);
		}
	}
}
