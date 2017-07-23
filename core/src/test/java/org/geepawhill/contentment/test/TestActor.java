package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public class TestActor extends GenericActor
{

	public final Group group;

	public TestActor(ScriptWorld world)
	{
		super(world);
		group = new Group();
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Step draw(double ms)
	{
		return new Phrase();
	}

}