package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Snap;
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

public class TargetBox implements Actor
{
	final String text;
	
	private final Group group;
	private Text label;
	private Rectangle rectangle;
	
	private Bounds bounds;

	private static final double VMARGIN = 8d;
	private static final double HMARGIN = 8d;

	private double x;
	private double y;

	private double width;

	private double height;
	
	private static int index;

	public TargetBox(String text, double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		label = new Text(x, y, "");
		label.setTextOrigin(VPos.CENTER);
		rectangle = new Rectangle();
		this.group = JfxUtility.makeGroup(index++,this,rectangle,label);
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
//		label.setX(x-label.getBoundsInParent().getWidth()/2d);
//		label.setY(y);
	}

	protected void animateComputeBox(double frac, Context context)
	{
		bounds = label.getBoundsInParent();
		bounds = new BoundingBox(bounds.getMinX() - HMARGIN, bounds.getMinY() - VMARGIN, width,
				height);
		rectangle.setFill(Color.ALICEBLUE);
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
		rectangle.setWidth(width * frac);
		rectangle.setHeight(height * frac);
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
		transition.setToX(newX-x);
		transition.setToY(newY-y);
		transition.setDuration(Duration.millis(1000d));
		return new TransitionStep(transition);
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Snap snap()
	{
		// TODO Auto-generated method stub
		return null;
	}

}