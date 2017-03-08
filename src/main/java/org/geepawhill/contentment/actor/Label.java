package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.StyleId;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.newstep.Entrance;
import org.geepawhill.contentment.newstep.LettersStep;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;
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

	public Step fadeIn(double ms)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(500d, this::fadeIn)
		};
		return new TimedSequence(ms, group, substeps);
	}
	

	@Override
	public void outline(KvOutline output)
	{
		
	}
	
	protected void fadeIn(double frac, Context context)
	{
		if (frac == 0d)
		{
			group.setOpacity(0d);
			animateDrawText(1d, context);
		}
		group.setOpacity(frac);
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
	
	protected void animateDrawText(double frac, Context context)
	{
		context.apply(StyleId.ShapePen, text);
		context.styles.get(StyleId.Font).apply(text);
		String newText = source.substring(0, (int) (frac * source.length()));
		text.setText(newText);
		text.setX(center.x - text.getBoundsInParent().getWidth() / 2d);
		text.setY(center.y);
	}

	@Override
	public Group group()
	{
		return group;
	}

}
