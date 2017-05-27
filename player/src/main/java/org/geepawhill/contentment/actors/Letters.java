package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.ActorOutliner;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.FadeStep;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Letters implements Actor
{
	private final String nickname;
	private final Group group;
	private String source;
	private Point center;
	private Text text;
	private Format format;

	public Letters(String source, Point center, Format format)
	{
		this.format = format;
		this.nickname = Names.make(getClass());
		this.source = source;
		this.center = center;
		this.text = new Text();
		this.group = new Group(text);
	}
	
	public Sequence sketch(Sequence sequence, Timing timing)
	{
		if (sequence == null) sequence = new Sequence();
		sequence.add(new Entrance(this));
		sequence.add(new LettersStep(timing, source, center, text, format));
		return sequence;
	}

	public void fadeIn(Sequence sequence, double ms)
	{
		LettersStep lettersStep = new LettersStep(FixedTiming.INSTANT, source, center, text, format);
		group().setOpacity(0d);
		sequence.add(lettersStep);
		sequence.add(new Entrance(this));
		sequence.add(new FadeStep(this, ms));
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
	public void outline(KvOutline output)
	{
		ActorOutliner outliner = new ActorOutliner(this, output);
		outliner.start();
		outliner.append("Source", source);
		outliner.startNode(text);
		if (outliner.visibility(text))
		{
			outliner.append("Current", text.getText());
			outliner.bounds(text);
			outliner.opacity(text);
			outliner.strokeWidth(text);
			outliner.lineColor(text);
		}
		outliner.endNode();
		outliner.end();
	}

	@Override
	public Group group()
	{
		return group;
	}

}
