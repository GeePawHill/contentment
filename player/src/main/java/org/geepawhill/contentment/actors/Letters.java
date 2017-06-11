package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Letters implements Actor
{
	private final String nickname;
	private final Group group;
	private String source;
	private LettersStep step;

	public Letters(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.step = new LettersStep(Timing.weighted(1d), source, center, format);
		this.source = source;
		this.group = new Group(step.text);
	}

	@Override
	public String nickname()
	{
		return nickname;
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Sequence draw(double ms)
	{
		return new Sequence().add(step).schedule(ms);
	}
	
	@Override
	public String toString()
	{
		return nickname()+" ["+source+"]";
	}

}
