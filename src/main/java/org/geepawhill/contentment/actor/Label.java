package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.ActorOutliner;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.newstep.Entrance;
import org.geepawhill.contentment.newstep.FadeStep;
import org.geepawhill.contentment.newstep.LettersStep;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Label implements Actor
{
	final String nickname;
	final String source;

	private final Group group;
	private Text text;

	private Point center;

	public Label(String source, double xCenter, double yCenter)
	{
		this.nickname = Names.make(getClass());
		this.center = new Point(xCenter,yCenter);
		this.source = source;
		text = new Text(xCenter, yCenter, "");
		text.setTextOrigin(VPos.CENTER);
		this.group = JfxUtility.makeGroup(this, text);
	}
	
	public String nickname()
	{
		return nickname;
	}
	
	public void sketch(Sequence sequence, double ms)
	{
		LettersStep lettersStep = new LettersStep(new FixedTiming(ms), source, center, text);
		sequence.add(new Entrance(this));
		sequence.add(lettersStep);
	}

	public void fadeIn(Sequence sequence,double ms)
	{
		LettersStep lettersStep = new LettersStep(FixedTiming.INSTANT, source, center, text);
		group().setOpacity(0d);
		sequence.add(lettersStep);
		sequence.add(new Entrance(this));
		sequence.add(new FadeStep(this,ms) );
	}
	

	@Override
	public void outline(KvOutline output)
	{
		ActorOutliner outliner = new ActorOutliner(this, output);
		outliner.start();
		outliner.append("Source",source);
		outliner.startNode(text);
		if (outliner.visibility(text))
		{
			outliner.append("Current",text.getText());
			outliner.bounds(text);
			outliner.opacity(text);
			outliner.strokeWidth(text);
			outliner.lineColor(text);
		}
		outliner.endNode();
		outliner.end();
	}
	
	public Step move(double newX, double newY)
	{
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(group);
		transition.setToX(newX - center.x);
		transition.setToY(newY - center.y);
		transition.setDuration(Duration.millis(1000d));
		return new TransitionStep(transition);
	}
	
	@Override
	public Group group()
	{
		return group;
	}

}
