package christaul.tilima.paths;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import christaul.tilima.levels.Level;
import christaul.tilima.tiles.Tile;
import christaul.tilima.util.Vector2D;

public class PathFinder
{
	private Level level;

	public PathFinder(Level level)
	{
		this.level = level;
	}

	public Path findPath(Vector2D origin, Vector2D destination)
	{
		return findPath(toNode(origin), toNode(destination));
	}

	private Path findPath(Node origin, Node destination)
	{
		List<Node> openList = new LinkedList<>();
		List<Node> closedList = new LinkedList<>();

		openList.add(origin);

		while (!openList.isEmpty())
		{
			Node current = openList.get(0);

			openList.remove(current);
			closedList.add(current);

			if (current.equals(destination))
			{
				return createPath(current);
			}

			List<Node> children = getAdjacentNodes(current);

			for (Node child : children)
			{
				if (closedList.contains(child)) continue;

				Node node = openList.stream().filter(n -> n.equals(child)).findFirst().orElse(null);

				if (node != null && child.g() > node.g())
				{
					continue;
				}

				openList.add(child);
			}

			openList.sort(Comparator.comparingDouble(n -> n.f(destination)));
		}

		return new Path();
	}

	private Node toNode(Vector2D position)
	{
		return new Node(
				(int)(position.getX() / Tile.WIDTH),
				(int)(position.getY() / Tile.HEIGHT));
	}

	private static Path createPath(Node node)
	{
		List<PathNode> pathNodes = new LinkedList<>();

		Node current = node;

		do
		{
			pathNodes.add(0, new PathNode(current.getX(), current.getY()));

			current = current.getParent();
		}
		while (current != null);

		return new Path(pathNodes);
	}

	private List<Node> getAdjacentNodes(Node node)
	{
		List<Node> children = new LinkedList<>();

		Node up = new Node(node, node.getX(), node.getY() - 1);
		Node down = new Node(node, node.getX(), node.getY() + 1);
		Node left = new Node(node, node.getX() - 1, node.getY());
		Node right = new Node(node, node.getX() + 1, node.getY());

		if (isValidNode(up)) children.add(up);
		if (isValidNode(down)) children.add(down);
		if (isValidNode(left)) children.add(left);
		if (isValidNode(right)) children.add(right);

		return children;
	}

	private boolean isValidNode(Node node)
	{
		Tile tile = level.getTile(node.getX(), node.getY());

		return !tile.isSolid();
	}

	private class Node
	{
		private Node parent;

		private int x;
		private int y;

		public Node(Node parent, int x, int y)
		{
			this.parent = parent;
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y)
		{
			this(null, x, y);
		}

		public Node getParent()
		{
			return parent;
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}

		public double g()
		{
			double length = 0;

			Node current = parent;

			while (current != null)
			{
				length++;

				current = current.getParent();
			}

			return length;
		}

		public double h(Node destination)
		{
			return Math.sqrt(Math.pow(destination.x - x, 2) + Math.pow(destination.y - y, 2));
		}

		public double f(Node destination)
		{
			return g() + h(destination);
		}

		@Override
		public boolean equals(Object obj)
		{
			if (obj instanceof Node)
			{
				Node other = (Node)obj;

				return this.x == other.x && this.y == other.y;
			}

			return false;
		}

		@Override
		public String toString()
		{
			return String.format("[%d, %d]", x, y);
		}
	}
}
