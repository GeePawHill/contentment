package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;
import org.geepawhill.contentment.style.StyleId;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class OvalText implements Actor
{
	final String nickname;
	final String text;
	
	private final Group group;
	private Text label;
	private Ellipse oval;
	
	private Bounds bounds;

	private static final double VMARGIN = 4d;
	private static final double HMARGIN = 20d;

	private double xCenter;

	private double yCenter;
	
	private static int index=0;
	
	public OvalText(String text, double xCenter, double yCenter)
	{
		this.nickname = Names.make(getClass());
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.text = text;
		label = new Text(xCenter, yCenter, "");
		oval = new Ellipse();
		this.group = JfxUtility.makeGroup(index++,this,label,oval);
		bounds = label.getBoundsInParent();
	}
	
	public String nickname()
	{
		return nickname;
	}

	
	@Override
	public void outline(KvOutline output)
	{
		
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
		oval.setFill(Color.TRANSPARENT);
		context.styles.get(StyleId.LineColor).apply(oval);
		context.styles.get(StyleId.PenWidth).apply(oval);
		context.styles.get(StyleId.Dash).apply(oval);
		context.styles.get(StyleId.Opacity).apply(oval);
		PointPair pair = new PointPair(bounds);
		oval.setCenterX(pair.centerX());
		oval.setCenterY(pair.centerY());
		oval.setRadiusX(pair.width());
		oval.setRadiusY(pair.height());
		oval.setVisible(false);
	}

	protected void animateDrawBox(double frac, Context context)
	{
		oval.setRadiusX((bounds.getWidth()/2d)+HMARGIN * frac);
		oval.setRadiusY((bounds.getHeight()/2d)+VMARGIN * frac);
		if (frac != 0d) oval.setVisible(true);
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

	@Override
	public Group group()
	{
		return group;
	}

}
