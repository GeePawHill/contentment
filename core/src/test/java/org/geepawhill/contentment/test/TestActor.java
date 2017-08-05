package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public class TestActor extends GenericActor
{

	public TestActor(ScriptWorld world)
	{
		super(world);
	}

	@Override
	public Step draw(double ms)
	{
		return new Phrase();
	}

}