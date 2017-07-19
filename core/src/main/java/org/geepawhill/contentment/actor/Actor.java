package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public interface Actor<BUILDER>
{
	public Group group();

	public String nickname();

	public Step draw(double ms);

	BUILDER builder();
}
