package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

public class Letters implements Actor
{
	private final String nickname;
	private final Group group;
	private String source;
	private Point center;
	private LettersStep step;

	public Letters(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.step = new LettersStep(Timing.weighted(1d), source, center, format);
		this.source = source;
		this.center = center;
		this.group = new Group(step.text);
	}

	public void move(Sequence sequence, double newX, double newY)
	{
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(group);
		transition.setToX(newX - center.x);
		transition.setToY(newY - center.y);
		transition.setDuration(Duration.millis(1000d));
		sequence.add(new TransitionStep(transition));
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
		return new Sequence(step).schedule(ms);
	}
	
	@Override
	public String toString()
	{
		return nickname()+" ["+source+"]";
	}

}
