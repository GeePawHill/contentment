package org.geepawhill.contentment.actor;

import javafx.scene.Group;
import javafx.scene.paint.Paint;

public interface Actor extends Commandable
{
	public Group group();

	public Actor reColor(Paint paint);
}
