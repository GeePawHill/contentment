package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public interface Actor
{
	public Group group();

	public String nickname();

	public Step draw(double ms);

	public Actor sketch();
	public Actor called(String name);
}
