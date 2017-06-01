package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Sequence;

public interface Drawable extends Actor
{
	public Sequence draw(double ms);
}
