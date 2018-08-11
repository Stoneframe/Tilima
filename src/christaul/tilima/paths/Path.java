package christaul.tilima.paths;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Path
{
	private List<PathNode> pathNodes;

	public Path(List<PathNode> pathNodes)
	{
		this.pathNodes = pathNodes;
	}

	public Path()
	{
		this(Collections.emptyList());
	}

	@Override
	public String toString()
	{
		return String.join(
			"->",
			pathNodes.stream().map(PathNode::toString).collect(Collectors.toList()));
	}
}
