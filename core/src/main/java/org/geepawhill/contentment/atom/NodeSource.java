package org.geepawhill.contentment.atom;

import java.util.function.Supplier;

import javafx.scene.Node;

public interface NodeSource extends Supplier<Node>
{
	public final static NodeSource NONE = new NodeSource()
	{

		@Override
		public Node get()
		{
			throw new RuntimeException("No NodeSource set.");
		}
	};
}
