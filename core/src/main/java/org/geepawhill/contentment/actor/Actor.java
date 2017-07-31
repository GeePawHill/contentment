package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.atom.GroupSource;

import javafx.scene.Group;
import javafx.scene.paint.Paint;

public interface Actor extends Commandable
{
	public Group group();
	
	public GroupSource groupSource();

	public Actor reColor(Paint paint);
}
