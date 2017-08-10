package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.position.Position;

public interface NodeAtom extends Atom, NodeSource
{
	public void at(Position position);
}
