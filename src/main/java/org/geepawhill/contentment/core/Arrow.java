package org.geepawhill.contentment.core;

import org.geepawhill.contentment.geometry.ArrowPoints;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.StrokeHelper;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Arrow implements Actor
{
	private Group group;
	private Line main;
	private Line top;
	private Line bottom;
	private Point from;
	private Point to;
	private ArrowPoints points;

	public Arrow(double fromX,double fromY,double toX,double toY)
	{
		this.from = new Point(fromX,fromY);
		this.to = new Point(toX,toY);
		this.group = new Group();
		this.main = new Line();
		this.top = new Line();
		this.bottom = new Line();
		group.getChildren().addAll(main,top,bottom);
	}
	
	public Step sketch(double ms)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(1d,this::computeArrow),
				StrokeHelper.makeSubStep(main,80d,this::mainPoints),
				StrokeHelper.makeSubStep(top, 10d,this::topPoints),
				StrokeHelper.makeSubStep(bottom, 10d, this::bottomPoints)
		};
		return new TimedSequence(ms, group, substeps);
	}
	
	private void computeArrow(double frac,Context context)
	{
		if(frac==0d) return;
		points = new ArrowPoints(new PointPair(from,to));
	}

	public PointPair mainPoints()
	{
		return points.main;
	}
	
	public PointPair topPoints()
	{
		return points.top;
	}
	
	public PointPair bottomPoints()
	{
		return points.bottom;
	}

	@Override
	public Group group()
	{
		return group;
	}
}
