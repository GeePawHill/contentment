package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.style.StyleId;

import javafx.animation.TranslateTransition;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Label implements Actor
{
	final String nickname;
	final String text;

	private final Group group;
	private Text label;

	private double xCenter;
	private double yCenter;

	private static int index = 0;

	public Label(String text, double xCenter, double yCenter)
	{
		this.nickname = Names.make(getClass());
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.text = text;
		label = new Text(xCenter, yCenter, "");
		label.setTextOrigin(VPos.CENTER);
		this.group = JfxUtility.makeGroup(index++, this, label);
	}
	
	public String nickname()
	{
		return nickname;
	}
	
	public Step sketch(double ms)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(1d, this::animateDrawText),
		};
		return new TimedSequence(ms, group,new SubStep(1d, this::resetText), substeps);
	}

	public Step fadeIn(double ms)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(500d, this::fadeIn)
		};
		return new TimedSequence(ms, group, substeps);
	}
	
	protected void resetText(double frac, Context context)
	{
		label.setText("");
	}


	protected void animateDrawText(double frac, Context context)
	{
		context.styles.get(StyleId.Font).apply(label);
		context.styles.get(StyleId.LineColor).apply(label);
		String newText = text.substring(0, (int) (frac * text.length()));
		label.setText(newText);
		label.setX(xCenter - label.getBoundsInParent().getWidth() / 2d);
		label.setY(yCenter);
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
		transition.setToX(newX - xCenter);
		transition.setToY(newY - yCenter);
		transition.setDuration(Duration.millis(1000d));
		return new TransitionStep(transition);
	}

	@Override
	public Group group()
	{
		return group;
	}

}
