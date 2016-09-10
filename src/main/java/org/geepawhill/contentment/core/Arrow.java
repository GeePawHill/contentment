package org.geepawhill.contentment.core;

import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Arrow implements Actor
{
	private Group group;
	private double fromX;
	private double fromY;
	private double toX;
	private double toY;
	private Line line;

	public Arrow(double fromX,double fromY,double toX,double toY)
	{
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.group = new Group();
		this.line = new Line(fromX,fromY,fromX,fromY);
		group.getChildren().add(line);

	}
	
	public Step sketch(double ms)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(500d,this::animateDrawLine),
		};
		return new TimedSequence(ms, group, substeps);
	}
	
	protected void animateDrawLine(double frac,Context context)
	{
		context.styles.get(StyleId.LineColor).apply(line);
		context.styles.get(StyleId.PenWidth).apply(line);
		context.styles.get(StyleId.Opacity).apply(line);
		double newX = fromX + (toX - fromX) * frac;
		double newY = fromY + (toY - fromY) * frac;
		line.setEndX(newX);
		line.setEndY(newY);
		line.setVisible(true);
	}
}
