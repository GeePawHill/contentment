package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.geometry.HPos;
import javafx.scene.Group;

public class Letters implements Actor
{
	private final String nickname;
	private final Group group;
	private String source;
	private LettersStep step;

	public Letters(String source, Point center, Format format)
	{
		this(source,center,format,HPos.CENTER);
	}

	public Letters(String source, Point center, Format format, HPos align)
	{
		this.nickname = Names.make(getClass());
		this.step = new LettersStep(Timing.weighted(1d), source, center, format, align);
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
	public Step draw(double ms)
	{
		return new Timed(ms).add(step);
	}
	
	@Override
	public String toString()
	{
		return nickname()+" ["+source+"]";
	}

}
