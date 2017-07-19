package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericAgentBuilder;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public class TestActor implements Actor<GenericAgentBuilder<TestActor>>
{

	public final Group group;

	public TestActor()
	{
		group = new Group();
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public String nickname()
	{
		return "TestActor";
	}

	@Override
	public Step draw(double ms)
	{
		return new Phrase();
	}

	@Override
	public GenericAgentBuilder<TestActor> builder(ScriptWorld world)
	{
		return new GenericAgentBuilder<>(world,this);
	}

}