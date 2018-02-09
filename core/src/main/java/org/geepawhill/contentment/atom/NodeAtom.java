package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.position.Position;

public interface NodeAtom extends Fragment, NodeSource
{
	public void at(Position position);
}
