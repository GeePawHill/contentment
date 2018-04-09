package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Entrance;
import org.geepawhill.contentment.position.Position;

public interface Actor
{
	Entrance entrance();
	void format(Format format);
	void at(Position position);
	Actor draw(double ms);
}