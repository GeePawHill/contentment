package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.step.Phrase;

public class TestActor extends GenericActor
{

	public TestActor(ScriptWorld world)
	{
		super(world);
	}

	@Override
	public TestActor draw(double ms)
	{
		world.add(new Phrase());
		return this;
	}

}