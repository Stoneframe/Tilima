package christaul.tilima.tiles;

public class EmptyTile
	extends Tile
{
	public EmptyTile(int id)
	{
		super(null, id);
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}
}
