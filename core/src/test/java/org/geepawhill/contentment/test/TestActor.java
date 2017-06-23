package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public class TestActor implements Actor
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

}