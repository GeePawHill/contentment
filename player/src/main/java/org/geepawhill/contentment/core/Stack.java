package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public class Stack extends GenericActor
{
	
	private Group group;

	public Stack(ScriptWorld world)
	{
		super(world);
		this.group = new Group();
	}
	
	@Override
	public Step draw(double ms)
	{
		return new Phrase();
	}
}
