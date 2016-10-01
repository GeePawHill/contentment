package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.style.StyleId;

import javafx.animation.TranslateTransition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LabelBox implements Actor
{
	final String text;
	
	private final Group group;
	private Text label;
	private Rectangle rectangle;
	
	private Bounds bounds;

	private static final double VMARGIN = 8d;
	private static final double HMARGIN = 8d;

	private double xCenter;
	private double yCenter;
	
	private static int index = 0;

	public LabelBox(String text, double xCenter, double yCenter)
	{
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.text = text;
		label = new Text(xCenter, yCenter, "");
		label.setTextOrigin(VPos.CENTER);
		rectangle = new Rectangle();
		this.group = JfxUtility.makeGroup(index++,this,label,rectangle);
		bounds = label.getBoundsInParent();
	}

	public Step sketch(double ms)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(500d,this::animateDrawText),
				new SubStep(1d,this::animateComputeBox), 
				new SubStep(200d,this::animateDrawBox)
		};
		return new TimedSequence(ms, group, substeps);
	}
	
	public Step fadeIn(double ms)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(500d,this::fadeIn)
		};
		return new TimedSequence(ms, group, substeps);
	}
	
	protected void animateDrawText(double frac, Context context)
	{
		context.styles.get(StyleId.Font).apply(label);
		context.styles.get(StyleId.LineColor).apply(label);
		String newText = text.substring(0, (int) (frac * text.length()));
		label.setText(newText);
		label.setX(xCenter-label.getBoundsInParent().getWidth()/2d);
		label.setY(yCenter);
	}

	protected void animateComputeBox(double frac, Context context)
	{
		bounds = label.getBoundsInParent();
		bounds = new BoundingBox(bounds.getMinX() - HMARGIN, bounds.getMinY() - VMARGIN, bounds.getWidth() + 2 * HMARGIN,
				bounds.getHeight() + 2 * VMARGIN);
		rectangle.setFill(Color.TRANSPARENT);
		context.styles.get(StyleId.LineColor).apply(rectangle);
		context.styles.get(StyleId.PenWidth).apply(rectangle);
		context.styles.get(StyleId.Dash).apply(rectangle);
		rectangle.setX(bounds.getMinX());
		rectangle.setY(bounds.getMinY());
		rectangle.setWidth(0d);
		rectangle.setHeight(0d);
		rectangle.setVisible(false);
	}

	protected void animateDrawBox(double frac, Context context)
	{
		rectangle.setWidth(bounds.getWidth() * frac);
		rectangle.setHeight(bounds.getHeight() * frac);
		if (frac != 0d) rectangle.setVisible(true);
	}
	
	protected void fadeIn(double frac, Context context)
	{
		if(frac==0d)
		{
			group.setOpacity(0d);
			animateDrawText(1d,context);
			animateComputeBox(1d,context);
			animateDrawBox(1d,context);
		}
		group.setOpacity(frac);
	}
	
	public Step move(double newX,double newY)
	{
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(group);
		transition.setToX(newX-xCenter);
		transition.setToY(newY-yCenter);
		transition.setDuration(Duration.millis(1000d));
		return new TransitionStep(transition);
	}

	@Override
	public Group group()
	{
		return group;
	}

}
