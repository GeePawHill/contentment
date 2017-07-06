package org.geepawhill.contentment.actors;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.format.Aligner;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;

public class Letters implements Actor
{
	private final String nickname;
	private final Group group;
	private String source;
	private LettersStep step;
	private Aligner aligner;

	public Letters(String source, Point center, Format format)
	{
		this(source,center,format,HPos.CENTER);
	}

	public Letters(String source, Point center, Format format, HPos align)
	{
		this(source,center,format,new Aligner(align,VPos.TOP));
	}
	
	public Letters(String source, Point center, Format format, Aligner aligner)
	{
		this.nickname = Names.make(getClass());
		this.step = new LettersStep(Timing.weighted(1d), source, center, format, aligner);
		this.source = source;
		this.group = new Group(step.text);
		this.aligner = aligner;
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
	
	public void setY(double y)
	{
		step.setY(y);
	}
	
	@Override
	public String toString()
	{
		return nickname()+" ["+source+"]";
	}

}
