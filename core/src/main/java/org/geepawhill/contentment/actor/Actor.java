package org.geepawhill.contentment.actor;


import org.geepawhill.contentment.core.Sequence;

import javafx.scene.Group;

public interface Actor
{
	public Group group();
	public String nickname();
	public Sequence draw(double ms);

}
