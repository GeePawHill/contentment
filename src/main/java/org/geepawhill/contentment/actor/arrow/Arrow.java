package org.geepawhill.contentment.actor.arrow;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.HideStep;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;
import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.TypedTree;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Arrow implements Actor
{
	private Group group;
	private Line main;
	private Line toTop;
	private Line toBottom;
	private Line fromTop;
	private Line fromBottom;
	private ArrowPoints points;
	private ArrowComputer computer;
	private boolean pointAtFrom;
	private boolean pointAtTo;
	
	public Arrow(Actor from,boolean pointAtFrom, Actor to, boolean pointAtTo)
	{
		this(pointAtFrom,pointAtTo,new NodeArrowComputer(from.group(), to.group()));
	}
	
	@Override
	public void dump(TypedTree<KeyValue> output)
	{
		
	}
	
	public Arrow(boolean pointAtFrom,boolean pointAtTo,ArrowComputer computer)
	{
		this.pointAtFrom = pointAtFrom;
		this.pointAtTo = pointAtTo;
		this.computer = computer;
		this.group = new Group();
		this.main = new Line();
		group.getChildren().add(main);
		if(pointAtFrom)
		{
			this.fromTop = new Line();
			this.fromBottom = new Line();
			group.getChildren().add(fromTop);
			group.getChildren().add(fromBottom);
		}
		if(pointAtTo)
		{
			this.toTop = new Line();
			this.toBottom = new Line();
			group.getChildren().add(toTop);
			group.getChildren().add(toBottom);
		}
	}
	
	public Step sketch(double ms)
	{
		ArrayList<SubStep> substeps = new ArrayList<>();
		substeps.add(new SubStep(1d,this::computeArrow));
		substeps.add(StrokeHelper.makeSubStep(main, 180d, this::mainPoints));
		if(pointAtTo)
		{
			substeps.add(StrokeHelper.makeSubStep(toTop, 10d,this::topPoints));
			substeps.add(StrokeHelper.makeSubStep(toBottom, 10d, this::bottomPoints));
		}
		if(pointAtFrom)
		{
			substeps.add(StrokeHelper.makeSubStep(fromTop, 10d, this::fromBottomPoints));
			substeps.add(StrokeHelper.makeSubStep(fromBottom, 10d, this::fromTopPoints));
		}
		return new TimedSequence(ms, group, substeps.toArray(new SubStep[0]));
	}
	
	private void computeArrow(double frac,Context context)
	{
		if(frac==0d) return;
		points = computer.compute();
	}

	public PointPair mainPoints()
	{
		return points.main;
	}
	
	public PointPair topPoints()
	{
		return points.toTop;
	}
	
	public PointPair bottomPoints()
	{
		return points.toBottom;
	}
	
	public PointPair fromBottomPoints()
	{
		return points.fromBottom;
	}
	public PointPair fromTopPoints()
	{
		return points.fromTop;
	}


	@Override
	public Group group()
	{
		return group;
	}
	
	public Step hide()
	{
		return new HideStep(group);
	}
}
